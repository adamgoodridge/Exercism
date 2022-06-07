public class BeerSongv3 {
    public BeerSongv3() { }
    private static String PART_A = " bottles of beer on the wall, ", PART_B = " bottles of beer.\nTake one down and pass it around, ",
            PART_C =" bottles of beer on the wall.\n\n";
    public String sing(int start, int amount) {
        StringBuilder sb = new StringBuilder();
        int end = start - amount + 1;
        int endFor = end > 2 ? end : 3;
        for(int i = start; i >= endFor; i--) {
            sb.append(i + PART_A + i + PART_B + (i + 1) + PART_C);
        }
        if(end <= 2 && start >= 2)
                    sb.append(2 + PART_A + 2 + PART_B + 1 + PART_C);

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
