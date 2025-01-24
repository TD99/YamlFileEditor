package ch.bfh.ingigroup.dockercomposeyamlparser;

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

        dirtyCache();
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

        return getProperty(propertyPath).isPresent();
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
    public Optional<String> getProperty(String[] propertyPath) throws YamlPropertyException {

        try {
            YamlDataMap currentData = yamlData;

            for (int i = 0; i < propertyPath.length - 1; i++) {
                Object value = currentData.get(propertyPath[i]);

                if (value instanceof YamlDataMap) {
                    currentData = (YamlDataMap) value;
                } else {
                    return Optional.empty();
                }
            }

            return Optional.ofNullable(currentData.get(propertyPath[propertyPath.length - 1]).toString());
        } catch (Exception e) {
            throw new YamlPropertyException(e);
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
     */
    public void setProperty(String[] propertyPath, String value) throws YamlPropertyException {

        try {
            YamlDataMap currentData = yamlData;

            for (int i = 0; i < propertyPath.length - 1; i++) {
                currentData = (YamlDataMap) currentData.computeIfAbsent(propertyPath[i], k -> new YamlDataMap());
            }

            currentData.put(propertyPath[propertyPath.length - 1], value);

            dirtyCache();
        } catch (Exception e) {
            throw new YamlPropertyException(e);
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

    /**
     * Removes a property.
     *
     * @param propertyPath The property path.
     */
    public void removeProperty(String[] propertyPath) {

        try {
            YamlDataMap currentData = yamlData;

            for (String s : propertyPath) {
                if (currentData.get(s) instanceof YamlDataMap yamlDataMap) {
                    currentData = yamlDataMap;
                } else {
                    return;
                }
            }

            currentData.remove(propertyPath[propertyPath.length - 1]);

            dirtyCache();
        } catch (Exception e) {
            throw new YamlPropertyException(e);
        }
    }

    /**
     * Removes a property.
     *
     * @param propertyPathString The property path as a string.
     */
    public void removeProperty(String propertyPathString) {

        removeProperty(getPropertyPath(propertyPathString));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // HashCode and Equals

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
    private String[] getPropertyPath(String propertyPathString) {

        return propertyPathString.split(DELIMITER_REGEX);
    }

    private void dirtyCache() {

        isCacheDirty = true;
    }

    private void updateCache() {

        contentCache = yamlData.toString();
    }
}
