class PrimeCalculator {

    int nth(int nth) throws IllegalArgumentException {
        if(nth < 1)
            throw new IllegalArgumentException("Input number can't be less than one");
        int prime = 2, primeCount = 0, num = 2;
        while (primeCount != nth) {
            //check every number to the current number, if it is multiple of number, it go to see the next number is prime
            if (isPrime(num)) {
                //if it gets to this line, we know it is a prime
                prime = num;
                primeCount++;
            }
            num++;
        }
        return prime;
    }

    private Boolean isPrime(int num) {
        for (int i = 2; i < num; i++) {
            int temp = num;
            while (temp >= i) {
                temp -= i;
            }
            // if the num is not, the dividend is a divisor of orginal num.
            if (temp == 0)
                return false;
        }
        return true;
    }
}