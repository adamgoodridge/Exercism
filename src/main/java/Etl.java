import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Etl {
    Map<String, Integer> transform(Map<Integer, List<String>> old) {
        Map<String, Integer> updatedFormat = new HashMap<>();
        old.forEach((points, letters) ->
            letters.forEach(letter -> updatedFormat.put(letter.toLowerCase(), points))
        );
        return updatedFormat;
    }
}
