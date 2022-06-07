/* import java.util.stream.IntStream;

class NaturalNumber {
    private Classification classification;
    public NaturalNumber(int number) throws IllegalArgumentException {
        if(number<2)
            throw new IllegalArgumentException("You must supply a natural number (positive integer)");
        //1 is always a factor, fergot about filter until Intelij informed me when I did if-statement inside .forEach stream
        int sum = 1 + IntStream.range(2, number).filter(n -> n % number == 0).sum();
        classification = (sum < number || sum==1) ? Classification.DEFICIENT : ((sum == number ? Classification.PERFECT : Classification.ABUNDANT));
    }
    public Classification getClassification(){
        return this.classification;
    }
}
*/
enum Classification {

    DEFICIENT, PERFECT, ABUNDANT

}

class NaturalNumber {
    private Classification classification;
    public NaturalNumber(int number) throws IllegalArgumentException {
        if(number<1)
            throw new IllegalArgumentException("You must supply a natural number (positive integer)");
        //1 is always a factor
        int sum = 1;
        for(int i = 2;i < number;i++) {
            if(number % i == 0)
                sum += i;
        }
        if(sum < number || sum==1)
            classification = Classification.DEFICIENT;
        else if(sum == number)
            classification = Classification.PERFECT;
        else
            classification = Classification.ABUNDANT;
    }
    public Classification getClassification(){
        return this.classification;
    }
}