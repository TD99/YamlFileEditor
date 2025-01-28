package ch.bfh.ingigroup.dockercomposeymlparser;

import ch.bfh.ingigroup.dockercomposeyamlparser.YamlConverter;
import ch.bfh.ingigroup.dockercomposeyamlparser.YamlDataMap;
import ch.bfh.ingigroup.dockercomposeyamlparser.YamlParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static ch.bfh.ingigroup.dockercomposeymlparser.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * YamlConverterTests.
 * <p>
 *     Provides tests for the YamlConverter class.
 *     Tests the parsing and dumping of YAML content.
 * </p>
 *
 * @author Tim Dürr
 * @version 1.0
 */
public final class YamlConverterTests {

    // -----------------------------------------------------------------------------------------------------------------
    // Tests.

    @BeforeAll
    static void prepare() {

        // The tests were only tested with the default options
        YamlConverter.setDumperOptions(DEFAULT_DUMPER_OPTIONS);
    }

    @Test
    void testParse_ValidYaml() {

        YamlDataMap result = YamlConverter.parse(VALID_YAML);

        assertNotNull(result, "The result should not be null");
        assertEquals(VALID_YAML_MAP, result, "The result should be equal to the expected map");
    }

    @Test
    void testParse_EmptyYaml() {

        YamlDataMap result = YamlConverter.parse(EMPTY_YAML);

        assertNotNull(result, "The result should not be null");
        assertTrue(result.isEmpty(), "The result should be an empty map");
    }

    @Test
    void testParse_InvalidYaml() {

        assertThrows(YamlParseException.class, () -> YamlConverter.parse(INVALID_YAML),
                "Parsing invalid YAML should throw a YamlParseException");
    }

    @Test
    void testParse_NullYaml() {

        YamlDataMap result = YamlConverter.parse(null);

        assertNotNull(result, "The result should not be null");
        assertTrue(result.isEmpty(), "The result should be an empty map");
    }

    @Test
    void testDump_ValidYamlMap() {

        String result = YamlConverter.dump(VALID_YAML_MAP);

        assertNotNull(result, "The result should not be null");
        assertEquals(VALID_YAML, result, "The dumped result should be equal to the expected YAML");
    }

    @Test
    void testDump_EmptyYamlMap() {

        String result = YamlConverter.dump(new YamlDataMap());

        assertNotNull(result, "The result should not be null");
        assertEquals("", result, "The dumped result of an empty map should be '{}\\n'");
    }

    @Test
    void testDump_NullYamlMap() {

        String result = YamlConverter.dump(null);

        assertNotNull(result, "The result should not be null");
        assertEquals("", result, "The dumped result of a null map should be '{}\\n'");
    }
}
