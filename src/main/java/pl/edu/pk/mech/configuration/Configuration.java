package pl.edu.pk.mech.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static java.util.logging.Logger.GLOBAL_LOGGER_NAME;

public class Configuration {

    public static final Logger logger = LoggerFactory.getLogger(GLOBAL_LOGGER_NAME);
    public static final List<String> possibleParameters = Arrays.asList("VCB", "UBT", "TMR", "TMP"); //todo: add the rest of parameters
}
