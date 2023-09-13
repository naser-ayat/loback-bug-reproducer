package com.example.reproduce;

import com.twitter.inject.logging.MDCInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReproduceApplication {
	private static final Logger logger = LoggerFactory.getLogger(ReproduceApplication.class);

	public static void main(String[] args) {
		init();

		SpringApplication.run(ReproduceApplication.class, args);

		writeLogLine();
	}

	private static void init() {
		MDCInitializer.init();
	}

	private static void writeLogLine() {
		MDCInitializer.let(() -> {
			MDC.put("mdc.key1", "mdc-value-1");
			MDC.put("mdc.key2", "mdc-value-2");

			logger.info("The sample log line that should be enriched with MDC data.");

			return null;
		});
	}
}
