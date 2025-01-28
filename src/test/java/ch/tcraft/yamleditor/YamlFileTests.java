package ch.tcraft.yamleditor;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static ch.tcraft.yamleditor.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * YamlTests.
 * <p>
 *     Provides tests for the YamlFile class.
 *     Tests the content, properties, and manipulation of a YAML file.
 * </p>
 *
 * @author Tim Dürr
 * @version 1.0
 */
public class YamlFileTests {

    // -----------------------------------------------------------------------------------------------------------------
    // Tests.

    private YamlFile yamlFile;

    @BeforeAll
    static void prepare() {

        // The tests were only tested with the default options
        YamlConverter.setDumperOptions(DEFAULT_DUMPER_OPTIONS);
    }

    @BeforeEach
    void setUp() {

        yamlFile = new YamlFile();
    }

    @Test
    void testConstructorWithContent() {

        YamlFile yamlFileWithContent = new YamlFile(VALID_YAML);

        assertEquals(VALID_YAML, yamlFileWithContent.getContent());
    }

    @Test
    void testDefaultConstructor() {

        assertEquals("", yamlFile.getContent());
    }

    @Test
    void testSetAndGetContent() {

        yamlFile.setContent(VALID_YAML);

        assertEquals(VALID_YAML, yamlFile.getContent());
    }

    @Test
    void testHasProperty_ExistingProperty() {

        yamlFile.setContent(VALID_YAML);

        assertTrue(yamlFile.hasProperty(KEY_KEY1));
        assertTrue(yamlFile.hasProperty(KEY_KEY2_A));
    }

    @Test
    void testHasProperty_NonExistingProperty() {

        yamlFile.setContent(VALID_YAML);

        assertFalse(yamlFile.hasProperty(KEY_INVALID));
        assertFalse(yamlFile.hasProperty(KEY_INVALID_NESTED));
    }

    @Test
    void testGetProperty_ExistingProperty() throws YamlPropertyException {

        yamlFile.setContent(VALID_YAML);

        assertEquals(Optional.of(VALUE_KEY1), yamlFile.getProperty(KEY_KEY1));
        // TODO: Multiple data types
        assertEquals(Optional.of(String.valueOf(VALUE_KEY2_A)), yamlFile.getProperty(KEY_KEY2_A));
    }

    @Test
    void testGetProperty_NonExistingProperty() throws YamlPropertyException {

        yamlFile.setContent(VALID_YAML);

        assertEquals(Optional.empty(), yamlFile.getProperty(KEY_INVALID));
        assertEquals(Optional.empty(), yamlFile.getProperty(KEY_INVALID_NESTED));
    }

    @Test
    void testSetProperty_NewProperty() throws YamlPropertyException {

        yamlFile.setProperty(KEY_NEW, VALUE_NEW);
        yamlFile.setProperty(KEY_NEW_NESTED, VALUE_NEW_NESTED);

        assertEquals(Optional.of(VALUE_NEW), yamlFile.getProperty(KEY_NEW));
        assertEquals(Optional.of(VALUE_NEW_NESTED), yamlFile.getProperty(KEY_NEW_NESTED));
    }

    @Test
    void testSetProperty_ExistingProperty() throws YamlPropertyException {

        yamlFile.setContent(VALID_YAML);

        yamlFile.setProperty(KEY_KEY1, VALUE_NEW);
        yamlFile.setProperty(KEY_KEY2_A, VALUE_KEY2_B);

        assertEquals(Optional.of(VALUE_NEW), yamlFile.getProperty(KEY_KEY1));
        assertEquals(Optional.of(VALUE_KEY2_B), yamlFile.getProperty(KEY_KEY2_A));
    }
}