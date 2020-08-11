package com.mioto.sandbox.java.service;

import javax.inject.Inject;

import org.slf4j.LoggerFactory;

public class ExampleService {

	private final org.slf4j.Logger LOG = LoggerFactory.getLogger(ExampleService.class);

	@Inject
	public void callMethod() {
		LOG.info("example calling");
	}
}
