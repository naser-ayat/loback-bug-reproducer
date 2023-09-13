# logback-bug-reproducer
This application reproduces a bug related to `MDC` in the Logback library.

In order to view the bug:
1. run the application by running `mvn spring-boot:run` in project root folder or if you do not have maven installed, use the appropriate maven wrapper in the project root folder (`mvnw` for Linux and Mac, and `mvnw.cmd` for Windows)
2. view the log line to see that MDC values are missing: `The sample log line that should be enriched with MDC data.` 

Another option to view the bug is to run the failing tests in `ReproduceApplicationIT` by running `mvn test -Dtest="ReproduceApplicationIT"` in project root folder.
