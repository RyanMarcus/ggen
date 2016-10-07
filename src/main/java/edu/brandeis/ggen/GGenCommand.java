package edu.brandeis.ggen;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.IOUtils;

class GGenCommand extends GraphGenerator {

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
