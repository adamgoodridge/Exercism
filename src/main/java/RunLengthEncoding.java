public class RunLengthEncoding {
    public RunLengthEncoding() {

    }
    public String encode(String decoded) {
        if(decoded.equals(""))
            return "";
        StringBuilder sb = new StringBuilder();
        char previous = decoded.charAt(0);
        int count = 1;
        for(int i = 1; i < decoded.length(); i++) {
            if(decoded.charAt(i) == previous) {
                count++;
            } else {
                if(count > 1)
                    sb.append(count);
                sb.append(previous);
                previous = decoded.charAt(i);
                count = 1;
            }
        }
        if(count > 1)
            sb.append(count);
        sb.append(previous);
        return sb.toString();
    }

    public String decode(String encoded) {
        if(encoded.equals(""))
            return "";
        StringBuilder sb = new StringBuilder();
        int i = 0;
        StringBuilder repeatAmount = new StringBuilder();
        for(char c : encoded.toCharArray())
            if(Character.isDigit(c)) {
                repeatAmount.append(c);
            } else {
                if(repeatAmount.length() > 0) {
                    int value = Integer.parseInt(repeatAmount.toString());
                    repeatAmount = new StringBuilder();
                    sb.append(String.valueOf(c).repeat(value));
                } else {
                    sb.append(c);
                }
        }
       return sb.toString();
    }
}
