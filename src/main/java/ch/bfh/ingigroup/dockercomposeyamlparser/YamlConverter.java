package ch.bfh.ingigroup.dockercomposeyamlparser;

import org.yaml.snakeyaml.DumperOptions;
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

    /**
     * The DumperOptions of the YamlConverter class.
     */
    private static final DumperOptions dumperOptions;

    // Static initializer
    static {

        dumperOptions = new DumperOptions();
        dumperOptions.setPrettyFlow(true);
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
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
            return new Yaml().load(content);
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

        try {
            return new Yaml(dumperOptions).dump(yamlDataMap);
        } catch (YAMLException e) {
            throw new YamlDumpException(e);
        }
    }
}
