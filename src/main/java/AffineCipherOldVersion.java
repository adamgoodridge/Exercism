import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class AffineCipherOldVersion {
    private final Integer LOWER_CASE_BOUNDARY = 97, ALPHABET_LENGTH = 26;
    private final String FIND_REGEX = "[^a-z0-9]", SPACE_FIND_REGEX = "(.{4}.)", SPACE_REPLACEMENT_REGEX = "$1 ";

    public AffineCipherOldVersion() {

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
        int inverseOfA = findInversev2(a);
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

    Integer findInverse(int num) {
        if (num == 1)
            return 1;
        LinkedList<Integer> rList = new LinkedList(), timesList = new LinkedList();
        rList.add(ALPHABET_LENGTH);
        rList.add(num);
        do {
            Integer reminder = (rList.get(rList.size() - 2) % rList.getLast());
            Integer time = (rList.get(rList.size() - 2) - reminder) / rList.getLast();
            rList.add(reminder);
            timesList.add(time);
        } while (rList.getLast() != 0);
        LinkedList<ExtendedInfo> map = new LinkedList<>();
        Integer gcdA = rList.get(0), gcdB = rList.get(1);
        HashMap<Integer, Integer> formula = new HashMap<>();
        map.add(new ExtendedInfo(1, gcdA, 1, gcdB));
        System.out.println(rList.get(2) + " =" + new ExtendedInfo(1, gcdA, timesList.get(0), gcdB));
        ExtendedInfo info = map.peek();
        System.out.println(rList.get(3) + " =" + new ExtendedInfo( info.getSi(), gcdA, timesList.get(0) + timesList.get(1), gcdB));
        map.offerFirst(new ExtendedInfo(info.getSi(), gcdA, timesList.get(0) + timesList.get(1), gcdB));
        for (int i = 4; i < rList.size() - 1; i++) {

        }
        int tI = map.getFirst().getTi();
        System.out.println(tI);
        return map.pop().total() == -1 ? tI : 26 - tI;
    }

    public static Integer findInversev2(int num) {
        if (num == 1)
            return 1;
        LinkedList<Integer> rList = new LinkedList(), timesList = new LinkedList();
        rList.add(26);
        rList.add(num);
        do {
            Integer reminder = (rList.get(rList.size() - 2) % rList.getLast());
            Integer time = (rList.get(rList.size() - 2) - reminder) / rList.getLast();
            rList.add(reminder);
            timesList.add(time);
        } while (rList.getLast() != 0);
        HashMap<Integer, ExtendedInfo> map = new HashMap();
        Integer gcdA = rList.get(0), gcdB = rList.get(1);
        for (int i = 2; i < rList.size() - 1; i++) {
            int rI = rList.get(i);
            Map<Integer, Integer> row = Map.of(rList.get(i - 2), 1, rList.get(i - 1), timesList.get(i - 2));
            HashMap<Integer, Integer> formula = new HashMap<>();
            formula.put(gcdA, 0);
            formula.put(gcdB, 0);
            row.forEach((k, v) -> {
                if (!formula.containsKey(k)) {
                    ExtendedInfo info = map.get(k);
                    formula.put(info.getR0(), formula.get(info.getR0()) + (info.getSi() * v));
                    formula.put(info.getR1(), formula.get(info.getR1()) + (info.getTi() * v));
                } else {
                    formula.put(k, formula.get(k) + v);
                }
            });
            map.put(rI, new ExtendedInfo(formula.get(gcdA), gcdA, formula.get(gcdB), gcdB));
        }
        System.out.println(map.get(1).getTi()+" "+map.get(1).total()+" "+map.get(1).toString());
        return map.get(1).total() == -1 ? map.get(1).getTi() : 26 - map.get(1).getTi();
    }
}

class ExtendedInfo {
    private Integer sI, r0, tI, r1;

    public ExtendedInfo(Integer sI, Integer r0, Integer tI, Integer r1) {
        this.sI = sI;
        this.r0 = r0;
        this.tI = tI;
        this.r1 = r1;
    }

    public Integer getTi() {
        return tI;
    }

    public Integer getR0() {
        return r0;
    }

    public Integer getSi() {
        return sI;
    }

    public Integer getR1() {
        return r1;
    }

    public Integer total() {
        return (sI * r0 - tI * r1) % 26;
    }

    @Override
    public String toString() {
        return " = -" + sI + " * " + r0 + " + " + tI + " * " + r1;
    }
}