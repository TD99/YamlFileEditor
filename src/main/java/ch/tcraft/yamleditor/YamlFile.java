package ch.tcraft.yamleditor;

import java.util.Objects;
import java.util.Optional;

import static ch.tcraft.yamleditor.YamlUtil.getPropertyPath;

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

        return YamlUtil.hasProperty(yamlData, propertyPath);
    }

    /**
     * Checks if the file has a property.
     *
     * @param propertyPathString The property path.
     * @return True if the property exists, false otherwise.
     */
    public boolean hasProperty(String propertyPathString) {

        return hasProperty(getPropertyPath(propertyPathString));
    }

    /**
     * Gets a property.
     *
     * @param propertyPath The property path.
     * @param type The type of the property.
     * @return The property value.
     */
    public <T> Optional<T> getProperty(String[] propertyPath, Class<T> type) {

        return YamlUtil.getProperty(yamlData, propertyPath, type);
    }

    /**
     * Gets a property.
     *
     * @param propertyPath The property path.
     * @return The property value.
     */
    public Optional<String> getProperty(String[] propertyPath) {

        return getProperty(propertyPath, String.class);
    }

    /**
     * Gets a property.
     *
     * @param propertyPathString The property path as a string.
     * @param type The type of the property.
     * @return The property value.
     */
    public <T> Optional<T> getProperty(String propertyPathString, Class<T> type) {

        return getProperty(getPropertyPath(propertyPathString), type);
    }

    /**
     * Gets a property.
     *
     * @param propertyPathString The property path as a string.
     * @return The property value.
     */
    public Optional<String> getProperty(String propertyPathString) {

        return getProperty(propertyPathString, String.class);
    }

    /**
     * Sets a property.
     *
     * @param propertyPath The property path.
     * @param value The value to set.
     * @throws YamlPropertyException If an error occurs while setting the property.
     */
    public <T> void setProperty(String[] propertyPath, T value) throws YamlPropertyException {

        YamlUtil.setProperty(yamlData, propertyPath, value);

        setCacheDirty();
    }

    /**
     * Sets a property.
     *
     * @param propertyPathString The property path as a string.
     * @param value The value to set.
     */
    public <T> void setProperty(String propertyPathString, T value) throws YamlPropertyException {

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
