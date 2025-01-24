package ch.bfh.ingigroup.dockercomposeymlparser;

import ch.bfh.ingigroup.dockercomposeyamlparser.YamlFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * YamlTests.
 *
 * @author Tim Dürr
 * @version 1.0
 */
public class YamlTests {

    private static final String SAMPLE_YAML = """
            services:
              app:
                image: original
                container_name: app-container
            """;

    private YamlFile yamlFile;

    @BeforeEach
    void setUp() {

        yamlFile = new YamlFile(SAMPLE_YAML);
    }

    @Test
    void testGetContent() {

        String content = yamlFile.getContent();
        assertNotNull(content);
        assertTrue(content.contains("services"));
        assertTrue(content.contains("app"));
    }

    @Test
    void testSetContent() {

        String newYaml = """
            services:
              app:
                image: updated
                container_name: updated-container
            """;

        yamlFile.setContent(newYaml);
        String content = yamlFile.getContent();

        assertTrue(content.contains("updated"));
        assertTrue(content.contains("updated-container"));
    }

    @Test
    void testHasPropertyArrayPathExists() {

        boolean exists = yamlFile.hasProperty(new String[]{"services", "app", "image"});
        assertTrue(exists);
    }

    @Test
    void testHasPropertyArrayPathNotExists() {

        boolean exists = yamlFile.hasProperty(new String[]{"services", "app", "non_existing"});
        assertFalse(exists);
    }

    @Test
    void testHasPropertyStringPathExists() {

        boolean exists = yamlFile.hasProperty("services.app.image");
        assertTrue(exists);
    }

    @Test
    void testHasPropertyStringPathNotExists() {

        boolean exists = yamlFile.hasProperty("services.app.non_existing");
        assertFalse(exists);
    }

    @Test
    void testGetPropertyArrayPathExists() {

        Optional<String> property = yamlFile.getProperty(new String[]{"services", "app", "image"});
        assertTrue(property.isPresent());
        assertEquals("original", property.get());
    }

    @Test
    void testGetPropertyArrayPathNotExists() {

        Optional<String> property = yamlFile.getProperty(new String[]{"services", "app", "non_existing"});
        assertFalse(property.isPresent());
    }

    @Test
    void testGetPropertyStringPathExists() {

        Optional<String> property = yamlFile.getProperty("services.app.image");
        assertTrue(property.isPresent());
        assertEquals("original", property.get());
    }

    @Test
    void testGetPropertyStringPathNotExists() {

        Optional<String> property = yamlFile.getProperty("services.app.non_existing");
        assertFalse(property.isPresent());
    }

    @Test
    void testSetPropertyArrayPath() {

        yamlFile.setProperty(new String[]{"services", "app", "image"}, "updated");

        Optional<String> updatedProperty = yamlFile.getProperty("services.app.image");
        assertTrue(updatedProperty.isPresent());
        assertEquals("updated", updatedProperty.get());
    }

    @Test
    void testSetPropertyStringPath() {

        yamlFile.setProperty("services.app.image", "updated");

        Optional<String> updatedProperty = yamlFile.getProperty("services.app.image");
        assertTrue(updatedProperty.isPresent());
        assertEquals("updated", updatedProperty.get());
    }

    @Test
    void testRemovePropertyArrayPath() {

        yamlFile.removeProperty(new String[]{"services", "app", "image"});

        Optional<String> removedProperty = yamlFile.getProperty("services.app.image");
        assertFalse(removedProperty.isPresent());
    }

    @Test
    void testRemovePropertyStringPath() {

        yamlFile.removeProperty("services.app.image");

        Optional<String> removedProperty = yamlFile.getProperty("services.app.image");
        assertFalse(removedProperty.isPresent());
    }
}