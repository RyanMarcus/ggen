package edu.brandeis.ggen;

public class GGen {
	
	private static RandomGraphGenerator rgg = new RandomGraphGenerator();
	private static StaticGraphGenerator sgg = new StaticGraphGenerator();
	
	public static RandomGraphGenerator generateGraph() {
		return rgg;
	}
	
	public static StaticGraphGenerator staticGraph() {
		return sgg;
	}
	
	
}
