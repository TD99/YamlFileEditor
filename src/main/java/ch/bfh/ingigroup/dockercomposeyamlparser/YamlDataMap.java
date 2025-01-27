package ch.bfh.ingigroup.dockercomposeyamlparser;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * YamlDataMap.
 * <p>
 *     The YamlDataMap class represents a YAML data map (YAML object).
 * </p>
 *
 * @author Tim Dürr
 * @version 1.0
 */
public class YamlDataMap extends LinkedHashMap<String, Object> {

    // -----------------------------------------------------------------------------------------------------------------
    // Constructors.

    /**
     * The constructor of the YamlDataMap class.
     * <p>
     *     The provided content is parsed.
     * </p>
     *
     * @param content The content to parse.
     */
    public YamlDataMap(String content) {

        super(YamlConverter.parse(content));
    }

    /**
     * The constructor of the YamlDataMap class.
     * <p>
     *     The provided map is copied.
     * </p>
     *
     * @param yamlData The map to copy.
     */
    public YamlDataMap(LinkedHashMap<String, Object> yamlData) {

        super(yamlData);
    }

    /**
     * The constructor of the YamlDataMap class.
     * <p>
     *     The provided object is validated and cast to a map.
     * </p>
     *
     * @param object The object to cast.
     * @throws YamlParseException if the object is not a valid map.
     */
    @SuppressWarnings("unchecked")
    public YamlDataMap(Object object) throws YamlParseException {

        if (object instanceof LinkedHashMap<?, ?> map) {
            // Perform a safe cast with runtime type-checking for keys and values
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                if (!(entry.getKey() instanceof String)) {
                    throw new YamlParseException("All keys in the map must be of type String.");
                }
            }

            // Safe to cast now
            super.putAll((LinkedHashMap<String, Object>) map);
        } else {
            throw new YamlParseException("Object must be of type LinkedHashMap<String, Object>");
        }
    }

    /**
     * The constructor of the YamlDataMap class.
     */
    public YamlDataMap() {

        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // ToString.

    /**
     * The toString method of the YamlDataMap class.
     * <p>
     *     The toString method is used to dump the content of the YamlDataMap.
     * </p>
     *
     * @return The dumped content.
     */
    @Override
    public String toString() {

        return YamlConverter.dump(this);
    }
}
