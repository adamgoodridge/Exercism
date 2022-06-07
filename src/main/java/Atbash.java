public class Atbash {
    private final int START_CHARACTER = 97, END_CHARACTER = 122;

    public Atbash(){


    }

    public String encode(String plainText) {
        StringBuilder sb = new StringBuilder();
        plainText = plainText.toLowerCase().replaceAll("[^a-z0-9]","");
        char[] chars = plainText.toCharArray();
        for(char c : chars) {
            int cInt = c;
            if(Character.isDigit(c)) {
                sb.append(c);
            } else {
                char cipherLetter = (char) (END_CHARACTER - (cInt - START_CHARACTER));
                sb.append(cipherLetter);
            }
        }
        return sb.toString().replaceAll("(.{4}.)","$1 ").trim();
    }

    public String decode(String cipherText) {
        StringBuilder sb = new StringBuilder();
        cipherText = cipherText.toLowerCase().replaceAll("[^a-z0-9]","");
        char[] chars = cipherText.toCharArray();
        for(char c : chars) {
            if(Character.isDigit(c)) {
                sb.append(c);
            } else {
                int cInt = c;
                char plainLetter = (char) (START_CHARACTER + (END_CHARACTER - cInt));
                sb.append(plainLetter);

            }
        }
        return sb.toString();
    }
}