import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Hamming {
    private int hammingDistance;
    public Hamming(String leftStrand, String rightStrand) throws IllegalArgumentException {
        if(leftStrand.isEmpty() && !rightStrand.isEmpty())
            throw new IllegalArgumentException("left strand must not be empty.");
        //leftStrand.isEmpty() is still required as need to return 0 if they are empty
        if(rightStrand.isEmpty() && !leftStrand.isEmpty())
            throw new IllegalArgumentException("right strand must not be empty.");
        if(leftStrand.length() != rightStrand.length())
            throw new IllegalArgumentException("leftStrand and rightStrand must be of equal length.");
        hammingDistance = leftStrand.length();
        for(int i=0;i<leftStrand.length();i++) {
            if(leftStrand.charAt(i) == rightStrand.charAt(i)) {
                hammingDistance--;
            }
        }
    }

    public int getHammingDistance() {
        //this isn't required
        return this.hammingDistance;
    }
}
