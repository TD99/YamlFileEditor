package ch.tcraft.yamleditor;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * TypeConversionUtilTests.
 * <p>
 *     Provides tests for the TypeConversionUtil class.
 * </p>
 *
 * @author Tim Dürr
 * @version 1.0
 */
public class TypeConversionUtilTests {

    @Test
    void testConvertValue_PrimitiveTypes() {

        assertEquals("42", TypeConversionUtil.convertValue(42, String.class));
        assertEquals(42, TypeConversionUtil.convertValue("42", Integer.class));
        assertEquals(42.5, TypeConversionUtil.convertValue("42.5", Double.class));
        assertEquals(true, TypeConversionUtil.convertValue("true", Boolean.class));
        assertEquals(false, TypeConversionUtil.convertValue("false", Boolean.class));
        assertEquals(true, TypeConversionUtil.convertValue(1, Boolean.class));
        assertEquals(false, TypeConversionUtil.convertValue(0, Boolean.class));
    }

    @Test
    void testConvertValue_NumberConversions() {

        assertEquals(42, TypeConversionUtil.convertValue(42.5, Integer.class));
        assertEquals(42.5, TypeConversionUtil.convertValue(42.5, Double.class));
        assertEquals("42", TypeConversionUtil.convertValue(42, String.class));
        assertEquals('A', TypeConversionUtil.convertValue(65, Character.class));
        assertEquals(42L, TypeConversionUtil.convertValue(42, Long.class));
        assertEquals(42.5f, TypeConversionUtil.convertValue(42.5, Float.class));
        assertEquals((short) 42, TypeConversionUtil.convertValue(42, Short.class));
        assertEquals((byte) 42, TypeConversionUtil.convertValue(42, Byte.class));
        assertEquals(true, TypeConversionUtil.convertValue(1, Boolean.class));
    }

    @Test
    void testConvertValue_Collections() {

        List<Integer> intList = List.of(1, 2, 3);
        assertEquals(intList, TypeConversionUtil.convertValue(intList, List.class));

        Map<String, Object> map = Map.of("key", "value");
        assertEquals(map, TypeConversionUtil.convertValue(map, Map.class));
    }

    @Test
    void testConvertValue_InvalidConversions() {

        assertThrows(IllegalArgumentException.class, () -> TypeConversionUtil.convertValue("hello", Integer.class));
        assertThrows(IllegalArgumentException.class, () -> TypeConversionUtil.convertValue("not_a_number", Double.class));
        assertThrows(IllegalArgumentException.class, () -> TypeConversionUtil.convertValue(Map.of(), Integer.class));
        assertThrows(IllegalArgumentException.class, () -> TypeConversionUtil.convertValue(List.of(), Boolean.class));
    }

    @Test
    void testConvertNumber() {

        assertEquals(42, TypeConversionUtil.convertNumber(42.5, Integer.class));
        assertEquals(42.5, TypeConversionUtil.convertNumber(42.5, Double.class));
        assertEquals("42", TypeConversionUtil.convertNumber(42, String.class));
        assertEquals('A', TypeConversionUtil.convertNumber(65, Character.class));
        assertEquals(42L, TypeConversionUtil.convertNumber(42, Long.class));
        assertEquals(42.5f, TypeConversionUtil.convertNumber(42.5, Float.class));
        assertEquals((short) 42, TypeConversionUtil.convertNumber(42, Short.class));
        assertEquals((byte) 42, TypeConversionUtil.convertNumber(42, Byte.class));
        assertEquals(true, TypeConversionUtil.convertNumber(1, Boolean.class));
    }

    @Test
    void testConvertBoolean() {

        assertEquals(true, TypeConversionUtil.convertBoolean("true"));
        assertEquals(false, TypeConversionUtil.convertBoolean("false"));
        assertEquals(true, TypeConversionUtil.convertBoolean(1));
        assertEquals(false, TypeConversionUtil.convertBoolean(0));
        assertThrows(IllegalArgumentException.class, () -> TypeConversionUtil.convertBoolean(List.of()));
    }
}
