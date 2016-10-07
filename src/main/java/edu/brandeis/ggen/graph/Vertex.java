package edu.brandeis.ggen.graph;

import java.util.HashMap;
import java.util.Map;

public class Vertex {
	private int id;
	private Map<Vertex, Map<String, String>> children;
	private Map<Vertex, Map<String, String>> parents;
	private Map<String, String> props;
	
	Vertex(int num) {
		this.id = num;
		children = new HashMap<>();
		parents = new HashMap<>();
	}
	
	public int getID() {
		return id;
	}
	
	public Map<Vertex, Map<String, String>> getChildren() {
		return children;
	}
	
	public Map<Vertex, Map<String, String>> getParents() {
		return parents;
	}
	
	public Map<String, String> getVertexProperties() {
		return props;
	}
	
	void addChild(Vertex v, Map<String, String> edgeProps) {
		if (children.containsKey(v)) {
			children.get(v).putAll(edgeProps);
			return;
		}
		
		children.put(v, edgeProps);
	}
	
	void addParent(Vertex v, Map<String, String> edgeProps) {
		if (parents.containsKey(v)) {
			parents.get(v).putAll(edgeProps);
			return;
		}
		
		parents.put(v, edgeProps);
	}
	
	void noteProperties(Map<String, String> props) {
		if (this.props == null) {
			this.props = new HashMap<>(props);
			return;
		}
		
		this.props.putAll(props);
	}
	
	@Override
	public int hashCode() {
		return Integer.valueOf(id).hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Vertex)
			return ((Vertex) o).id == this.id;
		return false;
	}
}
