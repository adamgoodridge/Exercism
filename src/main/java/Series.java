import java.util.LinkedList;
import java.util.List;

public class Series {
    private String digits;

    public Series(String digits) {
        this.digits = digits;
    }

    public List<String> slices(int number) {
        if(number < 1)
            throw new IllegalArgumentException("Slice size is too small.");
        if(number > digits.length())
            throw new IllegalArgumentException("Slice size is too big.");
        List<String> series = new LinkedList<>();
        for(int i = 0; i <= digits.length() - number; i++) {
            series.add(digits.substring(i, i + number));
        }
        return series;
    }
}
