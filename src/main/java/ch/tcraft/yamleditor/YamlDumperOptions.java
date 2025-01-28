package ch.tcraft.yamleditor;

import org.yaml.snakeyaml.DumperOptions;

/**
 * YamlDumperOptions.
 * <p>
 *     The YamlDumperOptions class provides options for the YAML dumper.
 * </p>
 *
 * @author Tim Dürr
 * @version 1.0
 */
public class YamlDumperOptions extends DumperOptions {

    // -----------------------------------------------------------------------------------------------------------------
    // Fields.

    private boolean removeEmptyLines;

    // -----------------------------------------------------------------------------------------------------------------
    // Constructors.

    /**
     * Constructor.
     * <p>
     *     The YamlDumperOptions constructor of the YamlDumperOptions class.
     * </p>
     */
    public YamlDumperOptions() {

        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Getters and setters.

    /**
     * Getter for the removeEmptyLines field.
     *
     * @return The removeEmptyLines field.
     */
    public boolean isRemoveEmptyLines() {

        return removeEmptyLines;
    }

    /**
     * Setter for the removeEmptyLines field.
     *
     * @param removeEmptyLines The removeEmptyLines field.
     */
    public void setRemoveEmptyLines(boolean removeEmptyLines) {

        this.removeEmptyLines = removeEmptyLines;
    }
}
