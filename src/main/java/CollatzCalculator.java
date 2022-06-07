class CollatzCalculator {

    int computeStepCount(int start) throws IllegalArgumentException {
        if(start < 1)
            throw new IllegalArgumentException("Only natural numbers are allowed");
        int steps = 0, n = start;
        while (n != 1){
            steps++;
            n = (n % 2 == 0) ? n/2 : 3*n+1;
        }
        return steps;
    }

}
