package twitter4j.demo.util;

public class Util {
    public static String textToJson(String text) {
        return text.replaceAll("\"", "\\\\\"")
            //TODO: This needs to be fixed
            .replaceAll("\n", "\\\\n");
    }
}
