import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumber {
    private String number;
    public PhoneNumber(String input) {
        Pattern pattern = Pattern.compile("[a-zA-B]");
        Matcher matcher = pattern.matcher(input);
        if(matcher.find())
            throw new IllegalArgumentException("letters not permitted");
        pattern= Pattern.compile("[:;',!@#$%^&*=/]");
        matcher = pattern.matcher(input);
        if(matcher.find())
            throw new IllegalArgumentException("punctuations not permitted");
        input = input.replaceAll("[^0-9]","");
        if(input.length() > 11)
            throw new IllegalArgumentException("more than 11 digits");
        if(input.length() < 10)
            throw new IllegalArgumentException("incorrect number of digits");
        if(input.length() == 11) {
            if(input.charAt(0) != '1')
                throw new IllegalArgumentException("11 digits must start with 1");
            input = input.substring(1);
        }
        if(input.charAt(0) == '0')
            throw new IllegalArgumentException("area code cannot start with zero");
        if(input.charAt(0) == '1')
            throw new IllegalArgumentException("area code cannot start with one");
        if(input.charAt(3) == '0')
            throw new IllegalArgumentException("exchange code cannot start with zero");
        if(input.charAt(3) == '1')
            throw new IllegalArgumentException("exchange code cannot start with one");
        number = input;
    }

    public String getNumber() {
        return number;
    }
}
