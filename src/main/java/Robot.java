import java.util.concurrent.ThreadLocalRandom;

public class Robot {
    private final static int LOWER_CHAR_INCLUSIVE = 65, UPPER_CHAR_EXCLUSIVE = 91, LOWER_NUMBER_INCLUSIVE = 1, UPPER_NUMBER_EXCLUSIVE = 10, AMOUNT_OF_LETTERS = 2, AMOUNT_OF_NUMBERS = 3;
    private String name;
    public Robot() {
        generateName();
    }


    public String getName() {
        return name;
    }

    public void reset() {
        generateName();
    }

    private void generateName() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < AMOUNT_OF_LETTERS; i++) {
          sb.append((char) ThreadLocalRandom.current().nextInt(LOWER_CHAR_INCLUSIVE, UPPER_CHAR_EXCLUSIVE));
        }
        for(int i = 0; i < AMOUNT_OF_NUMBERS; i++) {
            sb.append(ThreadLocalRandom.current().nextInt(LOWER_NUMBER_INCLUSIVE, UPPER_NUMBER_EXCLUSIVE));
        }
        name = sb.toString();
        }
        }