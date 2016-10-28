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
 
 
package info.rmarcus.ggen4j;

public class PropertyGenerator {
	private String mode;
	private GraphGenerator input;
	private String name;
	
	public PropertyGenerator(GraphGenerator input, String name, boolean edge) {
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
