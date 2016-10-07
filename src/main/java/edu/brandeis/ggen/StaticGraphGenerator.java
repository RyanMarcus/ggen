package edu.brandeis.ggen;

public class StaticGraphGenerator {
	public GraphGenerator cholesky(int matrixBlocks) {
		return new GGenCommand("static-graph cholesky " + matrixBlocks);
	}
	
	public GraphGenerator fibonacci(int toCompute, int cutOff) {
		return new GGenCommand("static-graph fibonacci " + toCompute + " " + cutOff);
	}
	
	public GraphGenerator forkJoin(int phases, int diameter) {
		return new GGenCommand("static-graph forkjoin " + phases + " " + diameter);
	}
	
	public GraphGenerator poisson2D(int n, int iter) {
		return new GGenCommand("static-graph poisson2d " + n + " " + iter);
	}
	
	public GraphGenerator sparseLU(int size) {
		return new GGenCommand("static-graph sparselu " + size);
	}
	
	public GraphGenerator strassen(int size, int depth, int cutoff) {
		return new GGenCommand("static-graph strassen " + size + " " + depth + " " + cutoff);
	}
}
