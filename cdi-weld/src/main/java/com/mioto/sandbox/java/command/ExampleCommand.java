package com.mioto.sandbox.java.command;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.slf4j.LoggerFactory;

import com.mioto.sandbox.java.service.ExampleService;

import picocli.CommandLine.Command;

@Command(
		description = "Starts the System",
		mixinStandardHelpOptions = true)
public class ExampleCommand implements Callable<Void> {

	private static final int ONE_MINUTE_IN_MILLISECONDS = 60000;

	private static final int ONE_SECOND_IN_MILLISECONDS = 1000;

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ExampleCommand.class);

	private static final String JOB_EXAMPLE_EXECUTION_ERRORS = "job.example.execution";

	@Inject
	private ExampleService exampleService;

	/**
	 * Call.
	 *
	 * @return the void
	 */
	@Override
	public Void call() {
		final Weld weld = new Weld();
		final WeldContainer container = weld.initialize();
		exampleService = container.select(ExampleService.class).get();

		LOG.info("Starting system");

		final HashMap<String, Date> executionController = new HashMap<String, Date>();

		boolean stopSignal = false;
		while (!stopSignal) {
			try {
				Thread.sleep(ONE_SECOND_IN_MILLISECONDS);
				final Date date = new Date();

				if (checkExecution(executionController, date, JOB_EXAMPLE_EXECUTION_ERRORS)) {
					LOG.info("Starting error checks");
					exampleService.callMethod();
					executionController.put(JOB_EXAMPLE_EXECUTION_ERRORS, new Date());
				}

			} catch (final Exception e) {
				LOG.error("Error: ", e);
				stopSignal = true;
			}
		}

		LOG.info("Done.");
		return null;
	}

	/**
	 * Check execution.
	 *
	 * @param executionController the execution controller
	 * @param date the date
	 * @param job the job
	 * @return true, if successful
	 */
	private boolean checkExecution(final HashMap<String, Date> executionController, final Date date, final String job) {
		return executionController.containsKey(job)
				&& date.getTime() - executionController.get(job).getTime() > ONE_MINUTE_IN_MILLISECONDS
				|| !executionController.containsKey(job);
	}

}
