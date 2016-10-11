// { begin copyright } 
// Copyright Ryan Marcus 2016
// 
// This file is part of poingnard.
// 
// poingnard is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
// 
// poingnard is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with poingnard.  If not, see <http://www.gnu.org/licenses/>.
// 
// { end copyright } 
 
 
package edu.brandeis.ggen.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import edu.brandeis.ggen.GGenException;
import edu.brandeis.ggen.GraphGenerator;

public class GGenGraph {
	
	private Map<Integer, Vertex> vertices;
	
	private GGenGraph() {
		vertices = new HashMap<>();
	}
	
	public static GGenGraph fromGGen(GraphGenerator gg) throws GGenException {
		String graphviz = gg.generateGraphviz();
		
		GGenGraph toR = new GGenGraph();
		toR.parseAllGV(graphviz);
		
		return toR;
	}
	
	public List<Vertex> getSources() {
		return vertices.values().stream()
				.filter(v -> v.getParents().size() == 0)
				.collect(Collectors.toList());
	}
	
	private void parseAllGV(String gv) {
		// remove all of the newlines
		gv = gv.replaceAll("\n", "");
		
		// find the statement list, in between { and }
		int startStmt = gv.indexOf('{') + 1;
		int endStmt = gv.indexOf('}');
		
		String statements = gv.substring(startStmt, endStmt);
		Arrays.stream(statements.split(";")).forEach(this::parseStatement);
	}
	
	private void parseStatement(String stmt) {
		// 0 -> 1    [test=9,test2=16];
		// 8 [prop=value];
		
		Map<String, String> props = new HashMap<>();
		if (stmt.contains("[")) {
			// we have properties!
			int propsStart = stmt.indexOf("[") + 1;
			int propsStop = stmt.indexOf("]");
			String propsString = stmt.substring(propsStart, propsStop);
			stmt = stmt.substring(0, propsStart - 1).trim();
			
			props = parseProperties(propsString);
		}
		
		if (stmt.contains("->")) {
			// it's an edge
			parseEdge(stmt, props);
		} else {
			// it isn't an edge
			parseVertex(stmt, props);
		}
	}
	
	private Map<String, String> parseProperties(String props) {
		Map<String, String> toR = new HashMap<>();
		// weights=1, weights=3
		String[] fields = props.split(",");
		for (String field : fields) {
			String label = field.split("=")[0].trim();	
			String value = field.split("=")[1].trim();
			toR.put(label, value);
		}
		
		return toR;
	}

	private void parseEdge(String stmt, Map<String,String> props) {
		String[] str = stmt.split("->");
		int v1 = Integer.valueOf(str[0].trim());
		int v2 = Integer.valueOf(str[1].trim());
		
		Vertex ver1 = getOrCreateVertex(v1);
		Vertex ver2 = getOrCreateVertex(v2);
		
		ver1.addChild(ver2, props);
		ver2.addParent(ver1, props);
	}
	
	private void parseVertex(String stmt, Map<String,String> props) {
		int v = Integer.valueOf(stmt);
		Vertex ver1 = getOrCreateVertex(v);
		ver1.noteProperties(props);
	}
	
	private Vertex getOrCreateVertex(int id) {
		if (vertices.containsKey(id))
			return vertices.get(id);
		
		Vertex v = new Vertex(id);
		vertices.put(id, v);
		return v;
	}
	
	
	private static String propsToGV(Map<String, String> props) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		sb.append(props.entrySet().stream()
		.map(e -> e.getKey() + "=" + e.getValue())
		.collect(Collectors.joining(",")));
		
		sb.append("]");
		return sb.toString();
	}
	
	public String toGraphviz() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("digraph dag {\n");
		
		for (Vertex v : vertices.values()) {
			if (v.getVertexProperties() != null) {
				sb.append(v.getID());
				sb.append(" ");
				sb.append(propsToGV(v.getVertexProperties()));
				sb.append(";\n");
			}
			
			for (Entry<Vertex, Map<String, String>> childEdge : v.getChildren().entrySet()) {
				sb.append(v.getID());
				sb.append(" -> ");
				sb.append(childEdge.getKey().getID());
				sb.append(" ");
				sb.append(propsToGV(childEdge.getValue()));
				sb.append(";\n");
			}
		}
		
		sb.append("}\n");
		return sb.toString();
	}
}
