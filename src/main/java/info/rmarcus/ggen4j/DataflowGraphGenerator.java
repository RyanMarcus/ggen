package info.rmarcus.ggen4j;

public class DataflowGraphGenerator {
	public GraphGenerator cholesky(int matrixBlocks) {
		return new GGenCommand("dataflow-graph cholesky " + matrixBlocks);
	}
	
	
	public GraphGenerator poisson2D(int n, int iter) {
		return new GGenCommand("dataflow-graph poisson2d " + n + " " + iter);
	}
	
	public GraphGenerator sparseLU(int size) {
		return new GGenCommand("dataflow-graph sparselu " + size);
	}
	
	public GraphGenerator denseLU(int size) {
		return new GGenCommand("dataflow-graph denselu " + size);
	}
}
