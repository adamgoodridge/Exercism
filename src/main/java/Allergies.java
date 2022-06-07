
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Allergies {
    private static final int MAX_SCORE_EXCLUSIVE = 256;
    private List list;
    private int score;
    public Allergies(int score) {
        this.score = score % MAX_SCORE_EXCLUSIVE;
        list = new LinkedList<Allergen>();
        Stream.of(Allergen.values()).sorted(Collections.reverseOrder()).forEach( a -> {
            if (a.getScore() <= this.score) {
                list.add(a);
                this.score -= a.getScore();
            }
        });
        Collections.reverse(list);
    }

    public boolean isAllergicTo(Allergen allergen) {
        return (list.contains(allergen));
    }
    public List getList() {
        return list;
    }
}