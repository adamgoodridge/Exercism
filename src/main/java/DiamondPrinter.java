import java.util.LinkedList;
import java.util.List;

class DiamondPrinter {
    private final int UPPER_CASE_FIRST = 65;
    private final String SPACE = " ";
    List<String> printToList(char a) {
        List<String> output = new LinkedList<>();
        if(a == 'A') {
            output.add(String.valueOf((char) UPPER_CASE_FIRST));
        } else{
            int value = (int) a;
            int outerSpace = value - UPPER_CASE_FIRST;
            StringBuilder sb = new StringBuilder();
            sb.append(SPACE.repeat(outerSpace));
            sb.append('A');
            sb.append(SPACE.repeat(outerSpace));
            output.add(sb.toString());
            int innerSpace = 1;
            outerSpace--;
            for (int i = UPPER_CASE_FIRST + 1; i <= value; i++) {
                sb.setLength(0);
                sb.append(SPACE.repeat(outerSpace));
                sb.append((char) i);
                sb.append(SPACE.repeat(innerSpace));
                sb.append((char) i);
                sb.append(SPACE.repeat(outerSpace));
                output.add(sb.toString());
                outerSpace--;
                //as outerspace goes left & right
                innerSpace+=2;
            }
            //A will at index 0, -1 to not repeat last char like 'Z'
            int size = value - UPPER_CASE_FIRST - 1;
            while (size >= 0) {
                output.add(output.get(size));
                size--;
            }
        }
        return output;
    }

}
