package com.mioto.sandbox.java;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import com.mioto.sandbox.java.command.ExampleCommand;

import picocli.CommandLine;

/**
 * @author eduardo.mioto
 */
public final class ExampleMain {

	/**
	 * Instantiates a new  main.
	 */
	private ExampleMain() {
		// Dummy
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(final String... args) throws Exception {

		final Weld weld = new Weld();
		final WeldContainer container = weld.initialize();
		final ExampleCommand exampleCommand = container.select(ExampleCommand.class).get();
		CommandLine.call(exampleCommand, args);
		container.shutdown();
	}

}
