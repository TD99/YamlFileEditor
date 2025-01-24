package ch.bfh.ingigroup.dockercomposeyamlparser;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final String INPUT_FILE = "src/docker-compose/docker-compose.in.yaml";
    private static final String OUTPUT_FILE = "src/docker-compose/docker-compose.out.yaml";

    public static void main(String[] args) {

        try {
            initializeOutputFile();

            String content = readInput();

            DockerComposeFile dockerComposeFile = DockerComposeYamlConverter.parse(content);
            dockerComposeFile.setProperty(new String[]{"services", "app", "image"}, "changed:latest");

            String newContent = DockerComposeYamlConverter.dump(dockerComposeFile);
            writeOutput(newContent);

            System.out.println("Updated docker-compose.yaml!");

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new java.io.File(OUTPUT_FILE));
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
    }
}
