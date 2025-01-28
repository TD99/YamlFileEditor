package ch.tcraft.yamleditor;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * YamlFile.
 * <p>
 *     The YamlFile class represents a yaml file.
 * </p>
 *
 * @version 1.0
 * @author Tim Dürr
 */
public class YamlFile {

    // -----------------------------------------------------------------------------------------------------------------
    // Constants.

    /**
     * The delimiter regex.
     */
    private final static String DELIMITER_REGEX = "\\.";

    // -----------------------------------------------------------------------------------------------------------------
    // Fields.

    /**
     * The yaml data.
     */
    private YamlDataMap yamlData;

    /**
     * The content cache.
     */
    private String contentCache;

    /**
     * The yaml data cache.
     */
    private boolean isCacheDirty = true;

    // -----------------------------------------------------------------------------------------------------------------
    // Constructors.

    /**
     * The constructor of the YamlFile class.
     * @param content The content of the docker-compose file.
     */
    public YamlFile(String content) {

        this.yamlData = new YamlDataMap(content);
    }

    /**
     * The default constructor of the YamlFile class.
     */
    public YamlFile() {

        this.yamlData = new YamlDataMap();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Content Getter and Setter.

    /**
     * Gets the file's content as a string.
     *
     * @return The content of the file.
     */
    public String getContent() {

        if (isCacheDirty) {
            updateCache();
        }

        return contentCache;
    }

    /**
     * Sets the file's content.
     *
     * @param content The content to set.
     */
    public void setContent(String content) {

        this.yamlData = new YamlDataMap(content);

        setCacheDirty();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Properties.

    /**
     * Checks if the file has a property.
     *
     * @param propertyPath The property path.
     * @return True if the property exists, false otherwise.
     */
    public boolean hasProperty(String[] propertyPath) {

        try {
            YamlDataMap currentData = yamlData;

            for (int i = 0; i < propertyPath.length; i++) {
                String key = propertyPath[i];
                Object value = currentData.get(key);

                // Key not found
                if (value == null) {
                    return false;
                }

                // If this is the last key, we found the property
                if (i == propertyPath.length - 1) {
                    return true;
                }

                if (value instanceof YamlDataMap yamlDataMap) {
                    // Go deeper into the nested map
                    currentData = yamlDataMap;
                } else {
                    return false; // Not a nested map, can't go deeper
                }
            }

            return false;
        } catch (YamlPropertyException e) {
            return false;
        }
    }

    /**
     * Checks if the file has a property.
     *
     * @param propertyPathString The property path as a string.
     * @return True if the property exists, false otherwise.
     */
    public boolean hasProperty(String propertyPathString) {

        return hasProperty(getPropertyPath(propertyPathString));
    }

    /**
     * Gets a property.
     *
     * @param propertyPath The property path.
     * @return The property value.
     */
    public Optional<String> getProperty(String[] propertyPath) {

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

            return Optional.ofNullable(finalValue.toString());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * Gets a property.
     *
     * @param propertyPathString The property path as a string.
     * @return The property value.
     */
    public Optional<String> getProperty(String propertyPathString) {

        return getProperty(getPropertyPath(propertyPathString));
    }

    /**
     * Sets a property.
     *
     * @param propertyPath The property path.
     * @param value The value to set.
     * @throws YamlPropertyException If an error occurs while setting the property.
     */
    public void setProperty(String[] propertyPath, String value) throws YamlPropertyException {

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

            setCacheDirty();
        } catch (Exception e) {
            throw new YamlPropertyException("An error occurred while setting the property: " + String.join(".", propertyPath), e);
        }
    }

    /**
     * Sets a property.
     *
     * @param propertyPathString The property path as a string.
     * @param value The value to set.
     */
    public void setProperty(String propertyPathString, String value) throws YamlPropertyException {

        setProperty(getPropertyPath(propertyPathString), value);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // HashCode and Equals.

    /**
     * The equals method of the YamlFile class.
     *
     * @param o The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) return false;
        YamlFile that = (YamlFile) o;
        return Objects.equals(yamlData, that.yamlData);
    }

    /**
     * The hashCode method of the YamlFile class.
     *
     * @return The hash code of the object.
     */
    @Override
    public int hashCode() {

        return Objects.hashCode(yamlData);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Private Methods.

    /**
     * Gets the property path.
     *
     * @param propertyPathString The property path as a string.
     * @return The property path as an array of strings.
     */
    private String[] getPropertyPath(String propertyPathString) {

        return propertyPathString.split(DELIMITER_REGEX);
    }

    /**
     * Sets the cache to dirty.
     */
    private void setCacheDirty() {

        isCacheDirty = true;
    }

    /**
     * Updates the cache.
     */
    private void updateCache() {

        contentCache = yamlData.toString();
    }
}
