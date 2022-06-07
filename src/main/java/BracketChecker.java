import java.util.*;

public class BracketChecker {
    private final boolean isMatched;
    public BracketChecker(String input) {
        final List<Character> OPENING_BRACKETS = Arrays.asList('[','{','('), CLOSING_BRACKETS = Arrays.asList(']','}',')');
        input = input.replaceAll("[^\\[\\]\\{}(\\)]", "");
        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray()) {
            if (OPENING_BRACKETS.contains(c)) {
                stack.add(c);
            } else {
                if(stack.isEmpty()) {
                    isMatched = false;
                    return;
                }
                int index = OPENING_BRACKETS.indexOf(stack.pop());
                if (CLOSING_BRACKETS.get(index) != c) {
                    isMatched = false;
                    return;
                }
            }
        }
        isMatched = stack.isEmpty();
    }
    public boolean areBracketsMatchedAndNestedCorrectly() {
        return isMatched;
    }
}