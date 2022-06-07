import java.util.Arrays;
import java.util.stream.Collectors;

class LargestSeriesProductCalculator {
    private long[] inputNumbers;
    LargestSeriesProductCalculator(String inputNumber) {
        this.inputNumbers = new long[inputNumber.length()];
        for(int i = 0; i < inputNumbers.length; i++) {
            try {
                this.inputNumbers[i] = Long.parseLong(String.valueOf(inputNumber.charAt(i)));
            } catch(NumberFormatException e) {
                throw new IllegalArgumentException("String to search may only contain digits.");
            }
        }
    }

    long calculateLargestProductForSeriesLength(int numberOfDigits) throws IllegalArgumentException {
        if(numberOfDigits < 0)
            throw new IllegalArgumentException("Series length must be non-negative.");
        if(numberOfDigits > inputNumbers.length)
            throw new IllegalArgumentException("Series length must be less than or equal to the length of the string to search.");
        if(inputNumbers.length == 0 || numberOfDigits == 0)
            return 1;
        long highestProduct = 0;
        // +1 required as if numberOfD is 3 and 23521, inputNumbers.length-numberOfDigits=5-3=2 it would only cover i=0 2*3*5,i=1 3*5*2, AND NOT i=2 5*2*1
        for(int i = 0; i < inputNumbers.length-numberOfDigits+1; i++) {
            long product = inputNumbers[i];
            for(int k = i + 1; k < (i + numberOfDigits); k++) {
                product *= inputNumbers[k];
            }
            if(product > highestProduct)
                //true if we found a new highest Product
                highestProduct = product;
        }
        return highestProduct;
    }
}
