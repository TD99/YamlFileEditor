package ch.bfh.ingigroup.dockercomposeyamlparser;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

/**
 * YamlConverter.
 * <p>
 *     The YamlConverter class provides methods to parse and dump YAML content.
 * </p>
 *
 * @version 1.0
 * @author Tim Dürr
 */
public class YamlConverter {

    // -----------------------------------------------------------------------------------------------------------------
    // Constants.

    /**
     * The DEFAULT_DUMPER_OPTIONS of the YamlConverter class.
     */
    private final static YamlDumperOptions DEFAULT_DUMPER_OPTIONS = new YamlDumperOptions() {{

        setDefaultFlowStyle(FlowStyle.AUTO);
        setPrettyFlow(true);
        setDefaultScalarStyle(ScalarStyle.PLAIN);
        setIndent(2);
        setProcessComments(true);
        setRemoveEmptyLines(true);
    }};

    // -----------------------------------------------------------------------------------------------------------------
    // Fields.

    /**
     * The DumperOptions of the YamlConverter class.
     */
    private static YamlDumperOptions DUMPER_OPTIONS = DEFAULT_DUMPER_OPTIONS;

    // -----------------------------------------------------------------------------------------------------------------
    // Utility methods.

    /**
     * The setDumperOptions method of the YamlConverter class.
     * <p>
     *     The setDumperOptions method is used to set the DumperOptions of the YamlConverter class.
     * </p>
     * @param dumperOptions The DumperOptions to set.
     */
    public static void setDumperOptions(YamlDumperOptions dumperOptions) {

        DUMPER_OPTIONS = dumperOptions;
    }

    /**
     * The parse method of the YamlConverter class.
     * <p>
     *     The parse method is used to convert a YAML string content to a Map.
     * </p>
     * @param content The content to parse.
     * @return The parsed content.
     * @throws IllegalArgumentException If an error occurred while parsing the content.
     */
    public static YamlDataMap parse(String content) throws YamlParseException {

        if (content == null || content.isEmpty()) {
            return new YamlDataMap();
        }

        try {
            Object object = new Yaml().load(content);

            return new YamlDataMap(object);
        } catch (YAMLException e) {
            throw new YamlParseException(e);
        }
    }

    /**
     * The dump method of the YamlConverter class.
     * <p>
     *     The dump method is used to convert a Map to a YAML string content.
     * </p>
     * @param yamlMap The map to dump.
     * @return The dumped content.
     * @throws YamlDumpException If an error occurred while dumping the content.
     */
    public static String dump(YamlDataMap yamlDataMap) throws YamlDumpException {

        if (yamlDataMap == null || yamlDataMap.isEmpty()) {
            return "";
        }

        try {
            String dumpedYaml = new Yaml(DUMPER_OPTIONS).dump(yamlDataMap);

            if (DUMPER_OPTIONS.isRemoveEmptyLines()) {
                dumpedYaml = removeEmptyLines(dumpedYaml);
            }

            return dumpedYaml;
        } catch (YAMLException e) {
            throw new YamlDumpException(e);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Private helper methods.

    /**
     * The removeEmptyLines method of the YamlConverter class.
     *
     * @param content The content to remove empty lines from.
     * @return The content without empty lines.
     */
    private static String removeEmptyLines(String content) {

        StringBuilder stringBuilder = new StringBuilder();

        for (String line : content.split("\n")) {
            if (!line.trim().isEmpty()) {
                stringBuilder.append(line).append("\n");
            }
        }

        return stringBuilder.toString();
    }
}
