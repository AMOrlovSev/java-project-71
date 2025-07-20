//package hexlet.code.formatters;
//
//import java.util.List;
//import java.util.Map;
//
//public class JsonFormatter {
//    public static String format(List<Map<String, Object>>  keyValPrefix) {
//
//        StringBuilder result = new StringBuilder("{\n");
//
//        String keyCurrent = "";
//        String keyNext = "";
//        Object valCurrent;
//        Object valNext;
//        String prefixCurrent = "";
//
//        for (int i = 0; i < keyValPrefix.size() - 1; i++) {
//
//            keyCurrent = keyValPrefix.get(i).getFirst().toString();
//            keyNext = keyValPrefix.get(i + 1).getFirst().toString();
//            valCurrent = keyValPrefix.get(i).get(1);
//            valNext = keyValPrefix.get(i + 1).get(1);
//            prefixCurrent = keyValPrefix.get(i).getLast().toString();
//
//            if (prefixCurrent.equals(UNCHANGED_PREFIX)) {
//                result.append("\n  \"")
//                        .append(keyCurrent)
//                        .append("\": ")
//                        .append(objectValueToStringJson(valCurrent))
//                        .append(",");
//                continue;
//            } else if (keyCurrent.equals(keyNext)) {
//                result.append("\nProperty '")
//                        .append(keyCurrent)
//                        .append("' was updated. From ")
//                        .append(objectValueToStringJson(valCurrent))
//                        .append(" to ")
//                        .append(objectValueToStringJson(valNext));
//                i++;
//            } else if (prefixCurrent.equals(ADDED_PREFIX)) {
//                result.append("\nProperty '")
//                        .append(keyCurrent)
//                        .append("' was added with value ")
//                        .append(objectValueToStringJson(valCurrent));
//            } else if (prefixCurrent.equals(REMOVED_PREFIX)) {
//                result.append("\nProperty '")
//                        .append(keyCurrent)
//                        .append("' was removed");
//            }
//        }
//
//        return result.append("}").toString();
//    }
//
//    private static String objectValueToStringJson(Object obj) {
//        String result = "";
//        if (obj instanceof String) {
//            result = "\"" + obj.toString() + "\"";
//        } else if (obj instanceof Integer || obj instanceof Boolean) {
//            result = obj.toString();
//        } else if (obj == null) {
//            result = "null";
//        } else {
//            result = "[complex value]";
//        }
//        return result;
//    }
//}
