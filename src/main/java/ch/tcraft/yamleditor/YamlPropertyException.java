package ch.tcraft.yamleditor;

import org.yaml.snakeyaml.error.YAMLException;

/**
 * YamlPropertyException.
 * The YamlPropertyException class is used to indicate that an error occurred while accessing a YAML property.
 *
 * @version 1.0
 * @author Tim Dürr
 */
public class YamlPropertyException extends YAMLException {

    // -----------------------------------------------------------------------------------------------------------------
    // Constants.

    /**
     * The default message of YamlParseException.
     */
    public static final String DEFAULT_MESSAGE = "An error occurred while accessing a YAML property";

    // -----------------------------------------------------------------------------------------------------------------
    // Constructors.

    /**
     * The constructor of the YamlParseException class.
     * <p>
     *     The default message is used.
     *     The cause of the exception is not specified.
     * </p>
     */
    public YamlPropertyException() {

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
    public YamlPropertyException(String message) {

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
    public YamlPropertyException(Throwable cause) {

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
    public YamlPropertyException(String message, Throwable cause) {

        super(message, cause);
    }
}