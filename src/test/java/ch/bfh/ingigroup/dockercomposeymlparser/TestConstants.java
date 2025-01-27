package ch.bfh.ingigroup.dockercomposeymlparser;

import ch.bfh.ingigroup.dockercomposeyamlparser.YamlDataMap;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * TestConstants.
 * <p>
 *     Provides constants for testing purposes.
 * </p>
 *
 * @author Tim Dürr
 * @version 1.0
 */
public final class TestConstants {

    /**
     * A valid YAML string.
     */
    public static final String VALID_YAML = """
            key: value
            anotherKey:
              value1: 1
              value2: two
            """;

    /**
     * A valid YAML map.
     */
    public static final YamlDataMap VALID_YAML_MAP = new YamlDataMap() {{
        put("key", "value");
        put("anotherKey", new YamlDataMap() {{
            put("value1", 1);
            put("value2", "two");
        }});
    }};

    /**
     * An empty YAML string.
     */
    public static final String EMPTY_YAML = "";

    /**
     * An invalid YAML string.
     */
    public static final String INVALID_YAML = ": invalid: yaml";

    /**
     * The base path.
     */
    public static final String BASE_PATH = "src/test/DockerComposeIOTests_assets/";

    /**
     * The input file.
     */
    public static final String INPUT_FILE = BASE_PATH + "docker-compose.in.yml";

    /**
     * The output file.
     */
    public static final String OUTPUT_FILE = BASE_PATH + "docker-compose.out.yml";

    /**
     * The expected output file.
     */
    public static final String EXPECTED_OUTPUT_FILE = BASE_PATH + "docker-compose.expected.yml";

    /**
     * The input file path.
     */
    public static final Path INPUT_FILE_PATH = Paths.get(INPUT_FILE).toAbsolutePath();

    /**
     * The output file path.
     */
    public static final Path OUTPUT_FILE_PATH = Paths.get(OUTPUT_FILE).toAbsolutePath();

    /**
     * The expected output file path.
     */
    public static final Path EXPECTED_OUTPUT_FILE_PATH = Paths.get(EXPECTED_OUTPUT_FILE).toAbsolutePath();

    /**
     * The constructor of the TestConstants class.
     */
    private TestConstants() {
        // Prevent instantiation.
    }
}
