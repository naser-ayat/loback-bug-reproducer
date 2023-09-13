package com.example.reproduce;

import com.twitter.inject.logging.MDCInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
class ReproduceApplicationIT {
	private ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(ReproduceApplicationIT.class);
	private static final String MDC_KEY = "mdcKey";
	private static final String MDC_VALUE = "mdcValue";

	@BeforeEach
	void init() {
		MDCInitializer.init();
	}

	@Test
	void testMDCLogbackIssue() {
		MDCInitializer.let(() -> {
			MDC.put(MDC_KEY, MDC_VALUE);

			assertEquals(MDC_VALUE, MDC.get(MDC_KEY));
			assertEquals(MDC_VALUE, logger.getLoggerContext().getMDCAdapter().get(MDC_KEY)); //fails

			return null;
		});
	}

	@Test
	void testMDCAdaptersAreTheSame() {
		assertSame(logger.getLoggerContext().getMDCAdapter(), MDC.getMDCAdapter()); //fails
	}
}
