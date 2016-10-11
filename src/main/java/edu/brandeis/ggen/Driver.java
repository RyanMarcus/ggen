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
