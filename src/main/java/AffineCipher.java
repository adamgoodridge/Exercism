import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;

public class AffineCipher {
    private final Integer LOWER_CASE_BOUNDARY = 97, ALPHABET_LENGTH = 26;
    private final String FIND_REGEX = "[^a-z0-9]", SPACE_FIND_REGEX = "(.{4}.)", SPACE_REPLACEMENT_REGEX = "$1 ";

    public AffineCipher() {

    }

    public String encode(String plainText, int a, int b) {
        int value = BigInteger.valueOf(a).gcd(BigInteger.valueOf(ALPHABET_LENGTH)).intValue();
        if (value != 1)
            throw new IllegalArgumentException("Error: keyA and alphabet size must be coprime.");
        StringBuilder sb = new StringBuilder();
        char[] chars = plainText.toLowerCase().replaceAll(FIND_REGEX, "").toCharArray();
        for (char c : chars) {
            if (Character.isAlphabetic(c)) {
                int plainValue = c - LOWER_CASE_BOUNDARY;
                int cipher = LOWER_CASE_BOUNDARY + ((a * plainValue + b) % ALPHABET_LENGTH);
                sb.append((char) cipher);
            } else
                sb.append(c);
        }
        //separates strings into 4 blocks each
        return sb.toString().replaceAll(SPACE_FIND_REGEX, SPACE_REPLACEMENT_REGEX).trim();
    }

    public String decode(String cipherText, int a, int b) {
        b = b % ALPHABET_LENGTH;
        int inverseOfA = findInverse(a);
        System.out.println(inverseOfA);
        int value = BigInteger.valueOf(a).gcd(BigInteger.valueOf(ALPHABET_LENGTH)).intValue();
        if (value != 1)
            throw new IllegalArgumentException("Error: keyA and alphabet size must be coprime.");
        cipherText = cipherText.replaceAll(" ", "");
        char[] chars = cipherText.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (Character.isAlphabetic(c)) {
                int cipherValue = c - LOWER_CASE_BOUNDARY;
                int secondHalf = b - cipherValue;
                secondHalf = secondHalf < 0 ? Math.abs(secondHalf) : 26 - secondHalf;
                int plainLetter = LOWER_CASE_BOUNDARY + (inverseOfA * secondHalf % ALPHABET_LENGTH);
                sb.append((char) plainLetter);
            } else
                sb.append(c);
        }
        return sb.toString();
    }
    private Integer findInverse(int num) {
        //source algorithm https://www.csee.umbc.edu/~chang/cs203.s09/exteuclid.shtml
        int a = ALPHABET_LENGTH;
        LinkedList<Integer> aS = new LinkedList<>(), bS = new LinkedList<>(), divS = new LinkedList<>();
        aS.add(a);
        int b = num;
        bS.add(b);
        while (b != 0) {
            int div = a / b;
            divS.add(div);
            int mod = a % b;
            a = b;
            aS.add(a);
            b = mod;
            bS.add(mod);
        }
        int[]  sS = new int[aS.size()], tS = new int[aS.size()];
        sS[sS.length - 1] = 1;
        tS[tS.length - 1] = 0;
        for(int i = tS.length - 2; i > -1; i--) {
            sS[i] = tS[i + 1];
            tS[i] = sS[i + 1] - divS.get(i) * tS[i + 1];
        }
        return tS[0] > 0 ? tS[0] : ALPHABET_LENGTH - Math.abs(tS[0]);
    }
}