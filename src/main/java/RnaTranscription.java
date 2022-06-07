import java.util.Arrays;
import java.util.Map;
import static java.util.Map.entry;
class RnaTranscription {
    private final static Map<Character,Character> map = Map.ofEntries(entry('G','C'),
            entry('C','G'),
            entry('T','A'),
            entry('A','U'));
    public RnaTranscription() {

    }

    String transcribe(String dnaStrand) {
        StringBuilder nucleotide = new StringBuilder();
        Character[] allLetters = dnaStrand.toUpperCase().chars().mapToObj(c -> (char)c).toArray(Character[]::new);
        Arrays.stream(allLetters).forEach(c -> nucleotide.append(map.get(c)));
        return nucleotide.toString();
    }
}
