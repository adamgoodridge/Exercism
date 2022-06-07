
import java.util.Arrays;
import java.util.List;

class ResistorColorDuo {
	private static final List<String> lookup = Arrays.asList("black","brown","red","orange","yellow","green","blue","violet","grey","white");

    int value(String[] colors) {
		//improving my answer from last Resistor Color by looking community answers
		//for loop would over-complicated things as only it would only iterate twice
        String output = lookup.indexOf(colors[0].toLowerCase()) + "" + lookup.indexOf(colors[1].toLowerCase());
        return Integer.parseInt(output);
    }
}