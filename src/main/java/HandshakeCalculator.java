
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class HandshakeCalculator {

    List<Signal> calculateHandshake(int number) {
        String binary = Integer.toBinaryString(number);
        //set length less than 4, if the string is less than 4
        int maxLength = binary.length() < 4 ? binary.length(): 4;
        Signal[] signalsOrder = {Signal.WINK,Signal.DOUBLE_BLINK,Signal.CLOSE_YOUR_EYES,Signal.JUMP};
        List<Signal> output = new LinkedList<>();
        for(int i = 0; i < maxLength; i++) {
            if (binary.charAt(binary.length()-i-1) == '1')
                output.add(signalsOrder[i]);
        }
        if (binary.length() == 5 && binary.charAt(4) == '1')
            Collections.reverse(output);
        return output;
    }
}
