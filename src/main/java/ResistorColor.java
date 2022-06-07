import java.util.Arrays;
import java.util.List;

class ResistorColor {
    private static final String[] colors = {"black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"};
    private static final List<String> colorsList = Arrays.asList(colors);

    int colorCode(String color) {
        return colorsList.indexOf(color);
    }

    String[] colors() {
        return colors;
    }
}
