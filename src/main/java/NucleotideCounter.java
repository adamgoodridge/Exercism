import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NucleotideCounter {
    Map<Character,Integer> count;

    public NucleotideCounter(String nucleotides) {
        count = new HashMap<>(Map.of('A',0,'C',0,'G',0,'T',0));
        Character[] allLetters = nucleotides.chars().mapToObj(c -> (char)c).toArray(Character[]::new);

        Arrays.stream(allLetters).forEach(c -> {
            //1 = increase the count by one
            if(!count.containsKey(c))
                throw new IllegalArgumentException("Only 'A' & 'C' & 'G' & 'T' are valid nucleotides.");
            count.merge(c,1, Integer::sum);
        });
    }

    public Map nucleotideCounts() {
        return count;
    }
}