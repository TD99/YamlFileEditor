package ch.bfh.ingigroup.dockercomposeyamlparser;

import org.yaml.snakeyaml.error.YAMLException;

/**
 * YamlDumpException.
 * The YamlDumpException class is used to indicate that an error occurred while dumping YAML content.
 *
 * @version 1.0
 * author Tim Dürr
 */
public class YamlDumpException extends YAMLException {

    /**
     * The default message of YamlDumpException.
     */
    public static final String DEFAULT_MESSAGE = "An error occurred while dumping YAML content";

    /**
     * The constructor of the YamlDumpException class.
     * <p>
     *     The default message is used.
     *     The cause of the exception is not specified.
     * </p>
     */
    public YamlDumpException() {

        super(DEFAULT_MESSAGE);
    }

    /**
     * The constructor of the YamlDumpException class.
     * <p>
     *     The specified message is used.
     *     The cause of the exception is not specified.
     * </p>
     * @param message The message of the exception.
     */
    public YamlDumpException(String message) {

        super(message);
    }

    /**
     * The constructor of the YamlDumpException class.
     * <p>
     *     The default message is used.
     *     The cause of the exception is specified.
     * </p>
     * @param cause The cause of the exception.
     */
    public YamlDumpException(Throwable cause) {

        super(DEFAULT_MESSAGE, cause);
    }

    /**
     * The constructor of the YamlDumpException class.
     * <p>
     *     The specified message is used.
     *     The cause of the exception is specified.
     * </p>
     * @param message The message of the exception.
     * @param cause The cause of the exception.
     */
    public YamlDumpException(String message, Throwable cause) {

        super(message, cause);
    }
}
