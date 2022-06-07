import java.math.BigDecimal;
import java.util.LinkedList;

public class BaseConverter {
    //int is better than double when casting to string, leave double when it is for-sure required
    private int number;
    public BaseConverter(int base,int[] digits) throws IllegalArgumentException {
        if(base < 2)
            throw new IllegalArgumentException("Bases must be at least 2.");
        for(int digit: digits){
            if(digit >= base)
                throw new IllegalArgumentException("All digits must be strictly less than the base.");
            if(digit < 0)
                throw new IllegalArgumentException("Digits may not be negative.");
        }
        if(base == 10) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digits.length; i++) {
                sb.append(digits[i]);
            }
            number = Integer.parseInt(sb.toString());
        } else {
            int k = digits.length-1;
            for (int digit : digits) {
                number += digit * Math.pow(base, k);
                k--;
            }
        }
        System.out.println(number);
    }

    public int[] convertToBase(int baseTo) throws IllegalArgumentException {
        if (baseTo < 2)
            throw new IllegalArgumentException("Bases must be at least 2.");
        int[] output;
        String numberString = String.valueOf(number);
        if (number == 0) {
            //the if statement after baseTo == 10, on the basis there will be cases when baseTo 10 & number !=0, it would decrease code readability
            output = new int[]{0};
        } else if (baseTo == 10) {
            output = new int[numberString.length()];
            for (int i = 0; i < output.length; i++) {
                // - 0 makes return the actual value and not ASCII table value
                output[i] = numberString.charAt(i) - '0';
            }
        }else {
            LinkedList<Integer> list = new LinkedList<Integer>();
            int numberRound = number;
            while(numberRound != 0) {
                Double result = Double.valueOf(numberRound) / baseTo;
                numberRound = (int)Math.floor(result);
                //rounding as the reminder's calculation will not be perfect, e.g 2.0111
                int reminder = (int) Math.round((result-numberRound)*baseTo);
                list.add(reminder);
            }

            output = new int[list.size()];
            for(int i = 0; i < output.length; i++) {
                output[i] = list.removeLast();
            }
        }
        return output;
    }
}