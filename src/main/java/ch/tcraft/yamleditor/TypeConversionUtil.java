package ch.tcraft.yamleditor;

import java.util.List;
import java.util.Map;

/**
 * TypeConversionUtil.
 * <p>
 *     Utility class for converting types.
 * </p>
 *
 * @version 1.0
 * @author Tim Dürr
 */
public class TypeConversionUtil {

    /**
     * Converts an object to the requested type while ensuring YAML-supported types.
     *
     * @param value The value to convert.
     * @param type The type to convert to.
     * @return The converted value.
     * @param <T> The type of the value.
     */
    public static <T> T convertValue(Object value, Class<T> type) {

        if (type.isInstance(value)) {
            return type.cast(value);
        }

        if (value instanceof Number) {
            return convertNumber((Number) value, type);
        }

        if (value instanceof String) {
            return convertString((String) value, type);
        }

        if (type == String.class) {
            return type.cast(value.toString());
        }

        if (type == Boolean.class) {
            return type.cast(convertBoolean(value));
        }

        if (type == List.class && value instanceof List) {
            return type.cast(value);
        }

        if (type == Map.class && value instanceof Map) {
            return type.cast(value);
        }

        throw new IllegalArgumentException("Unsupported type conversion: " + value.getClass().getSimpleName() + " -> " + type.getSimpleName());
    }

    /**
     * Converts a number to the requested type.
     *
     * @param value The number to convert.
     * @param type The type to convert to.
     * @return The converted value.
     * @param <T> The type of the value.
     */
    public static <T> T convertNumber(Number value, Class<T> type) {

        return switch (type.getSimpleName()) {
            case "Integer" -> type.cast(value.intValue());
            case "Double" -> type.cast(value.doubleValue());
            case "String" -> type.cast(value.toString());
            case "Character" -> type.cast((char) value.intValue());
            case "Long" -> type.cast(value.longValue());
            case "Float" -> type.cast(value.floatValue());
            case "Short" -> type.cast(value.shortValue());
            case "Byte" -> type.cast(value.byteValue());
            case "Boolean" -> type.cast(value.intValue() != 0);
            default -> throw new IllegalArgumentException("Unsupported number conversion: " + value.getClass().getSimpleName() + " -> " + type.getSimpleName());
        };
    }

    /**
     * Converts a string to the requested type.
     *
     * @param value The string to convert.
     * @param type The type to convert to.
     * @return The converted value.
     * @param <T> The type of the value.
     */
    public static <T> T convertString(String value, Class<T> type) {

        return switch (type.getSimpleName()) {
            case "Integer" -> type.cast(Integer.parseInt(value));
            case "Double" -> type.cast(Double.parseDouble(value));
            case "Long" -> type.cast(Long.parseLong(value));
            case "Float" -> type.cast(Float.parseFloat(value));
            case "Short" -> type.cast(Short.parseShort(value));
            case "Byte" -> type.cast(Byte.parseByte(value));
            case "Boolean" -> type.cast(Boolean.parseBoolean(value));
            default -> throw new IllegalArgumentException("Unsupported string conversion: " + value + " -> " + type.getSimpleName());
        };
    }

    /**
     * Converts a value to a Boolean.
     *
     * @param value The value to convert.
     */
    public static Boolean convertBoolean(Object value) {

        if (value instanceof Boolean) {
            return (Boolean) value;
        }

        if (value instanceof String) {
            return Boolean.valueOf((String) value);
        }

        if (value instanceof Number) {
            return ((Number) value).intValue() != 0;
        }

        throw new IllegalArgumentException("Cannot convert " + value.getClass().getSimpleName() + " to Boolean.");
    }
}
