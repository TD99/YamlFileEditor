package ch.bfh.ingigroup.dockercomposeyamlparser;

import java.util.HashMap;

/**
 * YamlDataMap.
 * <p>
 *     The YamlDataMap class represents a YAML data map (YAML object).
 * </p>
 *
 * @author Tim Dürr
 * @version 1.0
 */
public final class YamlDataMap extends HashMap<String, Object>{

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
    public YamlDataMap(YamlDataMap yamlData) {

        super(yamlData);
    }

    /**
     * The constructor of the YamlDataMap class.
     */
    public YamlDataMap() {
    }

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
