package helpers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extractions {
    private static Pattern pattern;
    private static Matcher matcher;

    public static String extractCost(String input) {
        String costPattern = "USD (\\d{1,3}(?:,\\d{3})*(?:\\.\\d{2})?)";
        pattern = pattern.compile(costPattern);
        matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static String parseCounterNumber(String text) {
        pattern = pattern.compile("\\d+");
        matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group();
        } else {
            return "Brak liczby w tekście";
        }
    }

    public static String parseCurrency(String text) {
        pattern = pattern.compile("\\d{1,3}(,\\d{3})*(\\.\\d{2})?");
        matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group();
        } else {
            return "Brak kwoty w tekście";
        }
    }

    public static String getCurrentDateTime() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return dateFormat.format(currentDate);
    }
}