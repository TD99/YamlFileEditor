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

    // -----------------------------------------------------------------------------------------------------------------
    // YAML constants.

    /**
     * A valid YAML string.
     */
    public static final String VALID_YAML = """
            key1: value
            key2:
              a: 1
              b: two
            """;

    /**
     * A valid YAML map.
     */
    public static final YamlDataMap VALID_YAML_MAP = new YamlDataMap() {{
        put("key1", "value");
        put("key2", new YamlDataMap() {{
            put("a", 1);
            put("b", "two");
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

    // -----------------------------------------------------------------------------------------------------------------
    // Key constants.

    /**
     * The key of "key1".
     */
    public static final String KEY_KEY1 = "key1";

    /**
     * The key of "key2".
     */
    public static final String KEY_KEY2 = "key2";

    /**
     * The key of "key2.a".
     */
    public static final String KEY_KEY2_A = KEY_KEY2 + ".a";

    /**
     * The key of "key2.b".
     */
    public static final String KEY_KEY2_B = KEY_KEY2 + ".b";

    /**
     * An invalid key.
     */
    public static final String KEY_INVALID = "invalid";

    /**
     * An invalid nested key.
     */
    public static final String KEY_INVALID_NESTED = KEY_KEY2 + ".invalid";

    /**
     * A new key.
     */
    public static final String KEY_NEW = "newKey";

    /**
     * A new nested key.
     */
    public static final String KEY_NEW_NESTED = KEY_KEY2 + ".newSubKey";

    // -----------------------------------------------------------------------------------------------------------------
    // Value constants.

    /**
     * The value of "key1".
     */
    public static final String VALUE_KEY1 = "value";

    /**
     * The value of "key2.a".
     */
    public static final int VALUE_KEY2_A = 1;

    /**
     * The value of "key2.b".
     */
    public static final String VALUE_KEY2_B = "two";

    /**
     * A new value.
     */
    public static final String VALUE_NEW = "newValue";

    /**
     * A new nested value.
     */
    public static final String VALUE_NEW_NESTED = "newSubValue";

    // -----------------------------------------------------------------------------------------------------------------
    // File constants.

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

    // -----------------------------------------------------------------------------------------------------------------
    // Constructor.

    /**
     * The constructor of the TestConstants class.
     */
    private TestConstants() {

        // Prevent instantiation.
    }
}
