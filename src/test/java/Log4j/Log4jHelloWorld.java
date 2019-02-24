package Log4j;


import org.apache.log4j.*;

;import java.io.IOException;

public class Log4jHelloWorld {

    private static final Logger log = Logger.getLogger(Log4jHelloWorld.class);


    public static void main(String[] args) throws IOException {

        //Configure logger to print out on console:
        //BasicConfigurator.configure();

        //create a appender to configure logger as FILE output with TTCC pattern
        FileAppender appender = new FileAppender(
                new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN),
                "Log.txt");
        //Configure the logger (instead of console)
        BasicConfigurator.configure(appender);

        //Set the lowest level of output as WARN
        log.setLevel(Level.WARN);

        log.trace("Trace Message!");
        log.debug("Debug Message!");
        log.info("Info Message!");
        log.warn("Warn Message!");
        log.error("Error Message!");
        log.fatal("Fatal Message!");
    }
}

