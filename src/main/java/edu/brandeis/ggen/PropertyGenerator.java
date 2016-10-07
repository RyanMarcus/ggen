package edu.brandeis.ggen;

public class PropertyGenerator {
	private String mode;
	private GraphGenerator input;
	private String name;
	
	PropertyGenerator(GraphGenerator input, String name, boolean edge) {
		if (edge)
			this.mode = "edge";
		else
			this.mode = "vertex";
		
		this.input = input;
		this.name = name;
	}
	
	public GraphGenerator uniform(int n, int m) {
		return new GGenCommand("add-property uniformint " + n + " " + m + " --" + mode + " --name " + name, input);
	}
	
	public GraphGenerator exponential(double mu) {
		return new GGenCommand("add-property exponential " + mu + " --" + mode + " --name " + name, input);
	}
	
	public GraphGenerator gaussian(double sigma) {
		return new GGenCommand("add-property gaussian " + sigma + " --" + mode + " --name " + name, input);
	}
	
	public GraphGenerator flat(double min, double max) {
		return new GGenCommand("add-property flat " + min + " " + max + " --" + mode + " --name " + name, input);
	}
	
	public GraphGenerator pareto(double a, double b) {
		return new GGenCommand("add-property pareto " + a + " " + b + " --" + mode + " --name " + name, input);
	}
}
