public class Util {
    public static boolean isValidCardNumberFormat(String cardNumber) {
        return cardNumber.matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}");
    }

    public static String formatCardNumber(String rawCardNumber) {
        return rawCardNumber.replaceAll("(\\d{4})(?=\\d)", "$1-");
    }
}