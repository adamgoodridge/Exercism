import java.util.Set;

class IsogramChecker {
    boolean isIsogram(String phrase) {
        phrase = phrase.toUpperCase().replaceAll("[^A-Z]","");
        Character[] allLetters = phrase.chars().mapToObj(c -> (char)c).toArray(Character[]::new);
        try {
            Set<Character> set = Set.of(allLetters);
        } catch (IllegalArgumentException e) {return false; }
        return true;
    }

}
