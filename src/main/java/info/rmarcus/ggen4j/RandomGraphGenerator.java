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

public class RandomGraphGenerator {
	
	RandomGraphGenerator() {
		
	}
	
	public GraphGenerator erdosGNP(int numVertices, double p) {
		return new GGenCommand("generate-graph gnp " + numVertices + " " + p);
	}
	
	public GraphGenerator erdosGNM(int numVertices, int numEdges) {
		return new GGenCommand("generate-graph gnm " + numVertices + " " + numEdges);
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
