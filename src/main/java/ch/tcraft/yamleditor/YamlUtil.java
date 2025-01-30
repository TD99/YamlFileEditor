package ch.tcraft.yamleditor;

import java.util.Map;
import java.util.Optional;

import static ch.tcraft.yamleditor.TypeConversionUtil.convertValue;

/**
 * YamlFileUtil.
 * <p>
 *     Utility class for handling operations in yaml.
 * </p>
 *
 * @version 1.0
 * @author Tim Dürr
 */
public class YamlUtil {

    // -----------------------------------------------------------------------------------------------------------------
    // Constants.

    /**
     * The delimiter regex.
     */
    private final static String DELIMITER_REGEX = "\\.";

    // -----------------------------------------------------------------------------------------------------------------
    // Property utility methods.

    /**
     * Checks if the given YamlDataMap has a property.
     *
     * @param yamlData The YAML data map.
     * @param propertyPath The property path.
     * @return True if the property exists, false otherwise.
     */
    public static boolean hasProperty(YamlDataMap yamlData, String[] propertyPath) {

        try {
            YamlDataMap currentData = yamlData;

            for (int i = 0; i < propertyPath.length; i++) {
                String key = propertyPath[i];
                Object value = currentData.get(key);

                if (value == null) return false;

                if (i == propertyPath.length - 1) return true;

                if (value instanceof YamlDataMap yamlDataMap) {
                    currentData = yamlDataMap;
                } else {
                    return false;
                }
            }

            return false;
        } catch (YamlPropertyException e) {
            return false;
        }
    }

    /**
     * Gets a property value from the YAML data.
     *
     * @param yamlData The YAML data map.
     * @param propertyPath The property path.
     * @return An Optional containing the property value if found.
     */
    public static <T> Optional<T> getProperty(YamlDataMap yamlData, String[] propertyPath, Class<T> type) {

        try {
            YamlDataMap currentData = yamlData;

            for (int i = 0; i < propertyPath.length - 1; i++) {
                Object value = currentData.get(propertyPath[i]);

                if (value instanceof YamlDataMap) {
                    currentData = (YamlDataMap) value;
                } else {
                    throw new YamlPropertyException("Property path not found: " + String.join(".", propertyPath));
                }
            }

            Object finalValue = currentData.get(propertyPath[propertyPath.length - 1]);

            if (finalValue == null) {
                throw new YamlPropertyException("Property not found: " + String.join(".", propertyPath));
            }

            return Optional.ofNullable(convertValue(finalValue, type));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * Sets a property value in the YAML data.
     *
     * @param yamlData The YAML data map.
     * @param propertyPath The property path.
     * @param value The value to set.
     * @throws YamlPropertyException If an error occurs while setting the property.
     */
    public static <T> void setProperty(YamlDataMap yamlData, String[] propertyPath, T value) throws YamlPropertyException {

        try {
            YamlDataMap currentData = yamlData;

            for (int i = 0; i < propertyPath.length - 1; i++) {
                String key = propertyPath[i];
                Object existingValue = currentData.get(key);

                if (existingValue == null) {
                    YamlDataMap newMap = new YamlDataMap();
                    currentData.put(key, newMap);
                    currentData = newMap;
                } else if (existingValue instanceof Map<?, ?> nestedMap) {
                    YamlDataMap wrappedMap = new YamlDataMap(nestedMap);
                    currentData.put(key, wrappedMap);
                    currentData = wrappedMap;
                } else {
                    throw new YamlPropertyException("Cannot set property at path: " + String.join(".", propertyPath) +
                            " because a non-map value exists at: " + key);
                }
            }

            currentData.put(propertyPath[propertyPath.length - 1], value);
        } catch (YamlPropertyException e) {
            throw e;
        } catch (Exception e) {
            throw new YamlPropertyException("An error occurred while setting the property: " + String.join(".", propertyPath), e);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Other utility methods.

    /**
     * Converts a property path string to a property path (array of strings).
     *
     * @param propertyPathString The property path as a string.
     * @return The property path as an array of strings.
     */
    public static String[] getPropertyPath(String propertyPathString) {

        return propertyPathString.split(DELIMITER_REGEX);
    }
}
