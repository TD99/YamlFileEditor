package ch.bfh.ingigroup.dockercomposeyamlparser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class DockerComposeFile {
    /**
     * The content of the docker-compose file.
     */
    private String content;

    /**
     * The constructor of the DockerComposeFile class.
     * @param content The content of the docker-compose file.
     */
    public DockerComposeFile(String content) {

        this.content = content;
    }

    /**
     * The default constructor of the DockerComposeFile class.
     */
    public DockerComposeFile() {
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Getters and Setters

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Helper Methods
    public String setProperty(String[] propertyPath, String value) {

        try {
            // Parse YAML to Map
            Yaml yaml = new Yaml();
            Map<String, Object> yamlMap = yaml.load(content);

            // Convert YAML Map to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(yamlMap);
            JsonNode rootNode = objectMapper.readTree(json);

            // Traverse the JSON tree to find and set the property
            JsonNode currentNode = rootNode;
            for (int i = 0; i < propertyPath.length - 1; i++) {
                if (currentNode.has(propertyPath[i])) {
                    currentNode = currentNode.get(propertyPath[i]);
                } else {
                    throw new IllegalArgumentException("Invalid property path: " + String.join(".", propertyPath));
                }
            }

            // Update the property value
            if (currentNode instanceof ObjectNode) {
                ((ObjectNode) currentNode).put(propertyPath[propertyPath.length - 1], value);
            } else {
                throw new IllegalArgumentException("Invalid property path: " + String.join(".", propertyPath));
            }

            // Convert JSON back to YAML
            String updatedJson = objectMapper.writeValueAsString(rootNode);
            Map<String, Object> updatedYamlMap = objectMapper.readValue(updatedJson, new TypeReference<>() {});
            DumperOptions options = new DumperOptions();
            options.setPrettyFlow(true);
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            Yaml updatedYaml = new Yaml(options);

            content = updatedYaml.dump(updatedYamlMap);
            return content;

        } catch (IOException e) {
            throw new RuntimeException("Failed to set property: " + e.getMessage(), e);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // HashCode and Equals

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DockerComposeFile that = (DockerComposeFile) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(content);
    }
}
