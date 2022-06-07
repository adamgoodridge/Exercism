import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PigLatinTranslator {
    private static final List<String> VOWELS_SOUNDS = Arrays.asList("xr", "yt");
    private static final List<Character> VOWELS = Arrays.asList('a', 'e', 'i', 'o', 'u');

    public PigLatinTranslator() {

    }

    public String translate(String input) {
        String[] words = input.split(" ");
        StringBuilder sb = new StringBuilder();
        for(String word: words) {
            if(VOWELS.contains(word.charAt(0)) || VOWELS_SOUNDS.contains(input.substring(0,2))) {
                sb.append(word);
            }else if(!VOWELS.contains(word.charAt(0))) {
                if(word.startsWith("qu")) {;
                    sb.append(word.substring(2));
                    sb.append("qu");
                } else if(word.startsWith("qu",1)) {;
                    sb.append(word.substring(3));
                    sb.append(word.charAt(0));
                    sb.append("qu");
                } else if (word.startsWith("y")) {
                    sb.append(word.substring(1));
                    sb.append("y");
                } else {
                    Pattern pattern = Pattern.compile("[aeiuoy]");
                    Matcher matcher = pattern.matcher(word);
                    matcher.find();
                    int index = matcher.start();
                    sb.append(word.substring(index));
                    sb.append(word, 0, index);
                }
            }
            sb.append("ay ");
        }
        String output = sb.toString();
        return output.substring(0, output.length() - 1);
    }
}