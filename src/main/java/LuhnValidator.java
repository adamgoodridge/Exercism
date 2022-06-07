import java.util.Arrays;

class LuhnValidator {

    boolean isValid(String candidate) {
        candidate = candidate.replaceAll(" ", "");
        String numberOnly = candidate.replaceAll("[^0-9]", "");
        if (numberOnly.length() == 1 || numberOnly.length() != candidate.length())
            return false;
        // replaceFirst("^0+(?!$)",""); gets rid of leading 0s after we check first check length == 0 as it will result in 0000 = 0 therefore = false which we don't want
        // source - https://stackoverflow.com/questions/2800739/how-to-remove-leading-zeros-from-alphanumeric-text
        char[] chars = candidate.replaceFirst("^0+(?!$)", "").toCharArray();
        int sum = 0;
        for (int i = chars.length - 2; i > -1; i -= 2) {
            int number = (int) chars[i] - '0';
            number = number * 2;
            sum += number > 9 ? number - 9 : number;
        }
        for (int i = chars.length - 1; i > -1; i -=2) {
            sum += (int) chars[i] - '0';
        }
        return  (sum % 10 == 0);
    }
}
