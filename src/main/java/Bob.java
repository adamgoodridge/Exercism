public class Bob {
    public Bob(){

    }
    public String hey(String say){
        say = say.strip();
        if(say.equals(""))
            return "Fine. Be that way!";
        say = say.replaceAll("[^A-Za-z? ]","").strip();
        if(say.equals(""))
            return "Whatever.";
        if(say.charAt(say.length() - 1) == '?')
            return !say.strip().equals("?") && say.equals(say.toUpperCase()) ? "Calm down, I know what I'm doing!" : "Sure.";
        if(say.equals(say.toUpperCase()))
            return "Whoa, chill out!";
        return "Whatever.";
    }
}