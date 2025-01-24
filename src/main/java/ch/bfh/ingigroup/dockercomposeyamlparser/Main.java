package ch.bfh.ingigroup.dockercomposeyamlparser;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

@Deprecated
public class Main {

    // DO NOT USE THIS CLASS!
    // This class is deprecated and will be removed in the next version.
    // Use the Unit tests instead.

    public static void main(String[] args) throws Exception {

        System.out.println("This class is deprecated and will be removed in the next version.");
        System.out.println("Use the Unit tests instead.");

        throw new Exception("This class is deprecated and will be removed in the next version.");
    }

    /*// Configuration
    private static final String INPUT_FILE = "src/example/docker-compose.in.yaml";
    private static final String OUTPUT_FILE = "src/example/docker-compose.out.yaml";
    private static final boolean OPEN_OUTPUT_FILE = true;

    // Logger
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        try {
            initializeOutputFile();

            String content = readInput();

            YamlFile yamlFile = YamlConverter.parse(content);

            // this works
            yamlFile.setProperty(new String[]{"services", "app", "image"}, "CHANGED");

            // this also works
            yamlFile.setProperty("services.app.container_name", "CHANGED");

            // this property does not exist
            yamlFile.setProperty("services.app.non_existing_property", "CHANGED");

            String newContent = YamlConverter.dump(yamlFile);
            writeOutput(newContent);

            LOGGER.info("Updated docker-compose.yaml!");

            if (Desktop.isDesktopSupported() && OPEN_OUTPUT_FILE) {
                Desktop.getDesktop().open(new File(OUTPUT_FILE));
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An unexpected error occurred", e);
        }
    }

    private static void initializeOutputFile() throws IOException {

        Path outputPath = Paths.get(OUTPUT_FILE);

        if (!Files.exists(outputPath)) {
            Files.createDirectories(outputPath.getParent());
            Files.createFile(outputPath);
            LOGGER.info("Output file initialized: " + outputPath.toAbsolutePath());
        } else {
            LOGGER.info("Output file already exists: " + outputPath.toAbsolutePath());
        }
    }

    private static String readInput() throws IOException {

        Path inputPath = Paths.get(INPUT_FILE);

        if (!Files.exists(inputPath)) {
            throw new IOException("Input file not found: " + inputPath.toAbsolutePath());
        }

        LOGGER.info("Reading input file: " + inputPath.toAbsolutePath());

        return Files.readString(inputPath);
    }

    private static void writeOutput(String content) throws IOException {

        Path outputPath = Paths.get(OUTPUT_FILE);
        Files.writeString(outputPath, content);
        LOGGER.info("Output file written: " + outputPath.toAbsolutePath());
    }*/
}
