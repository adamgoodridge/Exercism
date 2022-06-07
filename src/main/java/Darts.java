public class Darts {
    private double distance;
    Darts(double x, double y) {
       distance=Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }
    int score() {
        if(distance<=1)
            return 10;
        else if(distance<=5)
            return 5;
        else if(distance<=10)
            return 1;
        else
            return 0;
    }
}
