package ch.bfh.ingigroup.dockercomposeymlparser;

import ch.bfh.ingigroup.dockercomposeyamlparser.YamlFile;
import ch.bfh.ingigroup.dockercomposeyamlparser.YamlPropertyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static ch.bfh.ingigroup.dockercomposeymlparser.TestConstants.VALID_YAML;
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

    private YamlFile yamlFile;

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

        assertTrue(yamlFile.hasProperty("key"));
        assertTrue(yamlFile.hasProperty("anotherKey.value1"));
    }

    @Test
    void testHasProperty_NonExistingProperty() {

        yamlFile.setContent(VALID_YAML);

        assertFalse(yamlFile.hasProperty("nonexistent"));
        assertFalse(yamlFile.hasProperty("anotherKey.nonexistent"));
    }

    @Test
    void testGetProperty_ExistingProperty() throws YamlPropertyException {

        yamlFile.setContent(VALID_YAML);

        assertEquals(Optional.of("value"), yamlFile.getProperty("key"));
        assertEquals(Optional.of("1"), yamlFile.getProperty("anotherKey.value1"));
    }

    @Test
    void testGetProperty_NonExistingProperty() throws YamlPropertyException {

        yamlFile.setContent(VALID_YAML);

        assertThrows(YamlPropertyException.class, () -> yamlFile.getProperty("nonexistent"));
        assertThrows(YamlPropertyException.class, () -> yamlFile.getProperty("non.existent"));
    }

    @Test
    void testSetProperty_NewProperty() throws YamlPropertyException {

        yamlFile.setProperty("newkey", "newvalue");
        yamlFile.setProperty("anotherKey.newSubKey", "newSubValue");

        assertEquals(Optional.of("newvalue"), yamlFile.getProperty("newkey"));
        assertEquals(Optional.of("newSubValue"), yamlFile.getProperty("anotherKey.newSubKey"));
    }

    @Test
    void testSetProperty_ExistingProperty() throws YamlPropertyException {

        yamlFile.setContent(VALID_YAML);

        yamlFile.setProperty("key", "newvalue");
        yamlFile.setProperty("anotherKey.value2", "three");

        assertEquals(Optional.of("newvalue"), yamlFile.getProperty("key"));
        assertEquals(Optional.of("three"), yamlFile.getProperty("anotherKey.value2"));
    }

    @Test
    void testRemoveProperty_ExistingProperty() {

        yamlFile.setContent(VALID_YAML);

        yamlFile.removeProperty("key");

        assertFalse(yamlFile.hasProperty("key"));
    }

    @Test
    void testRemoveProperty_NestedProperty() {

        yamlFile.setContent(VALID_YAML);

        assertTrue(yamlFile.hasProperty("anotherKey.value1"));
        yamlFile.removeProperty("anotherKey.value1");
        assertFalse(yamlFile.hasProperty("anotherKey.value1"));

        assertTrue(yamlFile.hasProperty("anotherKey"));
    }

    @Test
    void testRemoveProperty_NonExistingProperty() {

        yamlFile.setContent(VALID_YAML);

        assertDoesNotThrow(() -> yamlFile.removeProperty("nonexistent"));
    }
}