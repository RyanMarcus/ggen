package edu.brandeis.ggen;

public class RandomGraphGenerator {
	public GraphGenerator erdosGNP(int numVertices, double p) {
		return new GGenCommand("generate-graph gnp " + numVertices + " " + p);
	}
	
	public GraphGenerator erdosGNM(int numVertices, double p) {
		return new GGenCommand("generate-graph gnm " + numVertices + " " + p);
	}
	
	public GraphGenerator layerByLayer(int numVertices, int numLayers, double p) {
		return new GGenCommand("generate-graph lbl " + numVertices + " " + numLayers + " " + p);
	}
	
	public GraphGenerator randomOrders(int numVertices, int numTotalOrders) {
		return new GGenCommand("generate-graph ro " + numVertices + " " + numTotalOrders);
	}
	
	public GraphGenerator fanInFanOut(int numVertices, int maxOutDegree, int maxInDegree) {
		return new GGenCommand("generate-graph fifo " + numVertices + " " + maxOutDegree + " " + maxInDegree);
	}
}
