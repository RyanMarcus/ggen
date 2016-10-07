package edu.brandeis.ggen;

import edu.brandeis.ggen.graph.GGenGraph;

public class Driver {
	public static void main(String[] args) throws GGenException {
		GGenCommand.GGEN_PATH = "/Users/ryan/local/bin/ggen";

		GGenGraph gg = GGen.staticGraph()
				.cholesky(5)
				.edgeProperty("weight")
				.flat(0.0, 100.5)
				.edgeProperty("weight2")
				.pareto(1.0, 5.0)
				.generateGraph();
		
		System.out.println("Sources: " + gg.getSources());
		System.out.println("As DOT: " + gg.toGraphviz());
	}
}
