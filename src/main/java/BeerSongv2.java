import java.text.MessageFormat;

public class BeerSongv2 {
    public BeerSongv2() { }

    public String sing(int start, int amount) {
        StringBuilder sb = new StringBuilder();
        int end = start - amount + 1;
        int endFor = end > 2 ? end : 3;
        for(int i = start; i >= endFor; i--) {
            sb.append(i + " bottles of beer on the wall, " + i + " bottles of beer.\nTake one down and pass it around, " + (i - 1) + " bottles of beer on the wall.\n\n");
        }
        if(end <= 2 && start >= 2)
            sb.append("2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n\n");

        if(end <= 1 && start >= 1)
            sb.append("1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n\n");

        if(end <= 0 && start >= 0) {
            sb.append("No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n\n");
        }
        return sb.toString();
    }
    public String singSong() {
        return sing(99,100);
    }
}