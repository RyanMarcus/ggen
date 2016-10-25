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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class GGenCommand extends GraphGenerator {

	public static String GGEN_PATH = "ggen";

	private String args;
	private GraphGenerator input;

	public GGenCommand(String args) {
		this.args = args;
		this.input = null;
	}

	public GGenCommand(String args, GraphGenerator input) {
		this.args = args;

		// TODO in future versions, we could stream this with pipes
		this.input = input;
	}

	public String generateGraphviz() throws GGenException {
		ProcessBuilder pb = new ProcessBuilder();
		List<String> command = new LinkedList<>();
		command.add(GGEN_PATH);
		Arrays.stream(args.split(" ")).forEach(command::add);

		try {
			Process p = pb.command(command)
					.start();

			if (this.input != null)
				p.getOutputStream().write(this.input.generateGraphviz().getBytes());
			p.getOutputStream().close();

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			IOUtils.copy(p.getInputStream(), bos);
			return new String(bos.toByteArray());
		} catch (IOException e) {
			throw new GGenException("Could not launch the ggen command. Check that the GGenCommand.GGEN_PATH static variable is correctly set for your system. Message: " + e.getMessage());
		}
	}
}
