package ch.bfh.ingigroup.dockercomposeyamlparser;

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

    public boolean isRemoveEmptyLines() {

        return removeEmptyLines;
    }

    public void setRemoveEmptyLines(boolean removeEmptyLines) {

        this.removeEmptyLines = removeEmptyLines;
    }
}
