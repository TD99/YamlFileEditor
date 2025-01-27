package ch.bfh.ingigroup.dockercomposeymlparser;

import ch.bfh.ingigroup.dockercomposeyamlparser.YamlFile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static ch.bfh.ingigroup.dockercomposeymlparser.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * DockerComposeFileTests.
 * <p>
 *     Provides tests for the DockerComposeFile class.
 *     Tests the content, properties, and manipulation of a docker-compose file.
 * </p>
 *
 * @author Tim Dürr
 * @version 1.0
 */
public final class DockerComposeIOTests {

    private static String CONTENT;

    @BeforeAll
    static void prepareInputFile() throws IOException {

        if (!Files.exists(INPUT_FILE_PATH)) {
            throw new IOException("The input file does not exist: " + INPUT_FILE_PATH);
        }

        CONTENT = Files.readString(INPUT_FILE_PATH);
    }

    @BeforeAll
    static void prepareOutputFile() throws IOException {

        if (!Files.exists(OUTPUT_FILE_PATH.getParent())) {
            Files.createDirectories(OUTPUT_FILE_PATH.getParent());
        }

        if (Files.exists(OUTPUT_FILE_PATH)) {
            Files.delete(OUTPUT_FILE_PATH);
        }

        Files.createFile(OUTPUT_FILE_PATH);

        assertTrue(Files.exists(OUTPUT_FILE_PATH));
    }

    @Test
    void testYamlFileImageManipulation() throws Exception {

        YamlFile yamlFile = new YamlFile(CONTENT);
        yamlFile.setProperty("services.app.image", "CHANGED:1");

        String newContent = yamlFile.getContent();
        Files.writeString(OUTPUT_FILE_PATH, newContent);

        String outputContent = Files.readString(OUTPUT_FILE_PATH);
        String expectedContent = Files.readString(EXPECTED_OUTPUT_FILE_PATH);

        assertEquals(expectedContent, outputContent);
    }
}
