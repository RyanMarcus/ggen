package edu.brandeis.ggen;

import edu.brandeis.ggen.graph.GGenGraph;

public abstract class GraphGenerator {
	public abstract String generateGraphviz() throws GGenException;
	
	public GGenGraph generateGraph() throws GGenException {
		return GGenGraph.fromGGen(this);
	}
	
	public PropertyGenerator edgeProperty(String name) {
		return new PropertyGenerator(this, name, true);
	}
	
	public PropertyGenerator vertexProperty(String name) {
		return new PropertyGenerator(this, name, false);
	}

}
