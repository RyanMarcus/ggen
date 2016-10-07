package edu.brandeis.ggen;

public class Driver {
	public static void main(String[] args) throws GGenException {
		GGenCommand.GGEN_PATH = "/Users/ryan/local/bin/ggen";
		
		System.out.println(GGen
				.staticGraph()
				.cholesky(5)
				.edgeProperty("weight")
					.flat(0.0, 100.5)
				.edgeProperty("weight2")
					.pareto(1.0, 5.0)
				.generateGraph()
				.toGraphviz());
	}
 }
