class ArmstrongNumbers {

    boolean isArmstrongNumber(int input) {
        String[] digits = String.valueOf(input).split("");
        int sum=0;
        for(int i = 0;i< digits.length;i++) {
            sum += Math.pow(Integer.valueOf(digits[i]), digits.length);
        }
        return  input==sum;
    }

}

