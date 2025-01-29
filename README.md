# YAML File Editor
**YAML File Editor** is a Java library designed to parse, edit, and dump YAML files.
This library provides a simple API for working with YAML data.
Even though the library was originally designed to work with Docker Compose files only, it can be used with any YAML file.

## Features
- **Parse**: Convert YAML content into structured Java objects for easy manipulation.
- **Edit**: Modify parsed YAML data and its properties effortlessly through type-safe methods.
- **Dump**: Serialize Java objects back into clean, well-formatted YAML strings.
- **Customizable Options**: Configure dumper options, including indentation, flow style, and empty line removal.

## Getting Started

### Prerequisites

- **Java**: Version 21 or higher.
- **Maven**: Dependency management tool.
- **SnakeYAML**: The library internally uses the SnakeYAML parser for YAML processing.

### Installation

#### Maven
Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>ch.tcraft</groupId>
    <artifactId>yamleditor</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### Gradle
Add the following dependency to your `build.gradle`:

```gradle
implementation group: 'ch.tcraft', name: 'yamleditor', version: '1.0.0'
```

### Usage
1. **Parsing YAML Content**:
    ```java
    public static void main(String[] args) {
   
        String yamlContent = "key: value\nnested:\n  key: nestedValue";

        try {
            YamlDataMap yamlData = YamlConverter.parse(yamlContent);
            // ... Work with the parsed data ...
        } catch (YamlParseException e) {
            e.printStackTrace();
        }
    }
    ```
2. **Getting/Setting Data**:
    ```java
    public static void main(String[] args) {
   
        // ... Parse YAML content ...
        YamlFile yamlFile = new YamlFile(yamlData);
        
        // Check if a property exists
        boolean exists = yamlFile.hasProperty("key");
   
        // Get a property
        String value = yamlFile.getProperty("key");
        String nestedValue = yamlFile.getProperty("nested.key");
        
        // Set a property
        yamlFile.setProperty("key", "newValue");
        yamlFile.setProperty("nested.key", "newNestedValue");
   
        // ... Work with the modified data ...
    }
    ```
3. **Dumping YAML Content**:
    ```java
    public static void main(String[] args) {
   
        // ... Create a YamlFile object ...
        String yamlOutput = YamlConverter.dump(yamlData);
        // ... Work with the dumped YAML content ...
    }
    ```
4. **Customizing Dumper Options**:
    ```java
    public static void main(String[] args) {
   
        YamlDumperOptions options = new YamlDumperOptions();
        options.setIndent(4);
        options.setPrettyFlow(true);
        options.setRemoveEmptyLines(true);
    
        YamlConverter.setDumperOptions(options);
    }
    ```
> **Note**: For more examples and detailed documentation, refer to the Unit Tests and Javadocs.

## Known Issues
- **Formatting**: The library may not preserve the original formatting of the YAML content.
  However, it is possible to modify the dumper options to achieve a specific output format.
- **Non-String Values**: The library treats all values as strings, which may cause issues with complex data structures. This will be addressed in future updates.
- **Missing remove feature**: It is currently not possible to remove properties. This will be addressed in future updates.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments
- **SnakeYAML**: The library uses the SnakeYAML parser for YAML processing.
- **JUnit**: The library uses JUnit for unit testing.
- **Maven**: The library uses Maven for dependency management.
- **Bern University of Applied Sciences**: The library was developed as part of, but not limited to, the internship at BFH.

## Authors
- **Tim Dürr** - [TD99](https://github.com/TD99)
