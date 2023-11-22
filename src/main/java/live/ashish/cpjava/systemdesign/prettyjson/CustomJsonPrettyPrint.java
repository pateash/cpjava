package live.ashish.cpjava.systemdesign.prettyjson;

import java.lang.reflect.Field;
import java.util.Map;

public class CustomJsonPrettyPrint {

    public static String customPrettyPrint(Object obj, int indent) {
        StringBuilder result = new StringBuilder();
        prettyPrintObject(obj, result, indent);
        return result.toString();
    }

    private static void prettyPrintObject(Object obj, StringBuilder result, int indent) {
        if (obj == null) {
            result.append("null");
        } else if (obj instanceof Map) {
            prettyPrintMap((Map<?, ?>) obj, result, indent);
        } else if (obj.getClass().isArray()) {
            prettyPrintArray(obj, result, indent);
        } else if (obj instanceof Iterable) {
            prettyPrintIterable((Iterable<?>) obj, result, indent);
        } else if (obj instanceof String || obj instanceof Number || obj instanceof Boolean) {
            result.append(obj);
        } else { // if not a simple type, we might have to recursively figure this object out
            prettyPrintFields(obj, result, indent);
        }
    }

    private static void prettyPrintMap(Map<?, ?> map, StringBuilder result, int indent) {
        result.append("{\n");
        map.forEach((key, value) -> {
            result.append(indentString(indent + 1))
                  .append("\"").append(key).append("\": ");
            prettyPrintObject(value, result, indent + 1);
            result.append(",\n");
        });
        result.append(indentString(indent)).append("}");
    }

    private static void prettyPrintArray(Object array, StringBuilder result, int indent) {
        result.append("[\n");
        int length = java.lang.reflect.Array.getLength(array);
        for (int i = 0; i < length; i++) {
            result.append(indentString(indent + 1));
            prettyPrintObject(java.lang.reflect.Array.get(array, i), result, indent + 1);
            result.append(i == length - 1 ? "\n" : ",\n");
        }
        result.append(indentString(indent)).append("]");
    }

    private static void prettyPrintIterable(Iterable<?> iterable, StringBuilder result, int indent) {
        result.append("[\n");
        iterable.forEach(item -> {
            result.append(indentString(indent + 1));
            prettyPrintObject(item, result, indent + 1);
            result.append(",\n");
        });
        result.append(indentString(indent)).append("]");
    }

    private static void prettyPrintFields(Object obj, StringBuilder result, int indent) {
        result.append("{\n");
        Class<?> clazz = obj.getClass();
        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();// all data members from a class
            for (Field field : fields) {
                field.setAccessible(true);
                result.append(indentString(indent + 1))
                      .append("\"").append(field.getName()).append("\": ");
                try {
                    prettyPrintObject(field.get(obj), result, indent + 1);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                result.append(",\n");
            }
            clazz = clazz.getSuperclass();
        }
        result.append(indentString(indent)).append("}");
    }

    private static String indentString(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sb.append("  "); // Two spaces for each level of indentation
        }
        return sb.toString();
    }
}
