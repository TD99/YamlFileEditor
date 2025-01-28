package ch.tcraft.yamleditor;

import org.yaml.snakeyaml.error.YAMLException;

/**
 * YamlParseException.
 * The YamlParseException class is used to indicate that an error occurred while parsing YAML content.
 *
 * @version 1.0
 * @author Tim Dürr
 */
public class YamlParseException extends YAMLException {

    // -----------------------------------------------------------------------------------------------------------------
    // Constants.

    /**
     * The default message of YamlParseException.
     */
    public static final String DEFAULT_MESSAGE = "Invalid YAML content";

    // -----------------------------------------------------------------------------------------------------------------
    // Constructors.

    /**
     * The constructor of the YamlParseException class.
     * <p>
     *     The default message is used.
     *     The cause of the exception is not specified.
     * </p>
     */
    public YamlParseException() {

        super(DEFAULT_MESSAGE);
    }

    /**
     * The constructor of the YamlParseException class.
     * <p>
     *     The specified message is used.
     *     The cause of the exception is not specified.
     * </p>
     * @param message The message of the exception.
     */
    public YamlParseException(String message) {

        super(message);
    }

    /**
     * The constructor of the YamlParseException class.
     * <p>
     *     The default message is used.
     *     The cause of the exception is specified.
     * </p>
     * @param cause The cause of the exception.
     */
    public YamlParseException(Throwable cause) {

        super(DEFAULT_MESSAGE, cause);
    }

    /**
     * The constructor of the YamlParseException class.
     * <p>
     *     The specified message is used.
     *     The cause of the exception is specified.
     * </p>
     * @param message The message of the exception.
     * @param cause The cause of the exception.
     */
    public YamlParseException(String message, Throwable cause) {

        super(message, cause);
    }
}