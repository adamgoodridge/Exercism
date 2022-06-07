import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.StringUtils;


class IsbnVerifier {

    boolean isValid(String stringToVerify) {
        stringToVerify = stringToVerify.replaceAll("-","");
        if(stringToVerify.length() != 10)
            return false;
        int lastValue;
        if (!StringUtils.isNumeric(stringToVerify.subSequence(9,10))) {
            //the character is not a number
            if(stringToVerify.charAt(9) == 'X')
                lastValue = 10;
            else
                return false;
        } else {
            lastValue = Integer.parseInt(stringToVerify.substring(9,10));
        }
        // last digit X 1
        int sum = lastValue;
        String[] characters = stringToVerify.substring(0,9).split("");
        int multiplication = 10;
        for(int i = 0; i < characters.length; i++) {
            try {
                sum+= Integer.parseInt(characters[i]) * multiplication;
                multiplication--;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return sum % 11 == 0;
    }
    boolean isValid13(String stringToVerify) {
        stringToVerify = stringToVerify.replaceAll("-","");
        if(stringToVerify.length() != 13)
            return false;
        int sum = 0;
        String[] characters = stringToVerify.split("");
        int multiplication = 1;
        for(int i = 0; i < characters.length; i++) {
            if(!StringUtils.isNumeric(characters[i]))
                return false;
            sum+= Integer.parseInt(characters[i]) * multiplication;
            multiplication = multiplication == 1 ? 3 : 1;
        }
        return sum % 10 == 0;
    }

    String generate(){
        int[] numbers = new int[10];
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 9; i++) {
            numbers[i] = ThreadLocalRandom.current().nextInt(0,10);
            sum += numbers[i] * (10-i);
            sb.append(numbers[i]);
        }
        sb.insert(1,'-');
        sb.insert(5,'-');
        sb.append("-");
        // 24 % 11 = 2, so last digit have equal 11-2=9 so  sum+lastDigit%11=0
        sum = sum % 11;
        numbers[9] = (11 - sum) % 11;
        if(numbers[9] == 10)
            sb.append("X");
        else
            sb.append(numbers[9]);
        return sb.toString();
    }

    String generate13(String isbnTen) throws Exception {
        isbnTen = isbnTen.replaceAll("-", "");
        if(!isValid(isbnTen))
            throw new Exception("Invaild input of ISBN-10");
        //drop the last digit as it need to be re-generated
        String keepDigits = isbnTen.substring(0,9);
        String[] digits = keepDigits.split("");
        //9×1 + 7×3 + 8×1
        int sum = 38;
        int multiply = 3;
        for(int i = 0; i < digits.length; i++) {
            sum += Integer.parseInt(digits[i]) * multiply;
            multiply = multiply == 3 ? 1 : 3;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("978-");
        sb.append(keepDigits);
        //inserts after the 5th character
        sb.insert(5,"-");
        sb.insert(8,"-");
        sb.insert(15,"-");
        sum = sum % 10;
        int lastDigit = (10 - sum) % 10;
        sb.append(lastDigit);
        return sb.toString();
    }
}
