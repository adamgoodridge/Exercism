class TwelveDays {
    private static String[] numbersWord ={"first","second","third","fourth","fifth","sixth","seventh","eighth","ninth","tenth","eleventh","twelfth"};
    private static String[] gifts = {" a Partridge in a Pear Tree.\n", " two Turtle Doves,",
            " three French Hens,"," four Calling Birds,"," five Gold Rings,"
            ," six Geese-a-Laying,"," seven Swans-a-Swimming,"," eight Maids-a-Milking,",
            " nine Ladies Dancing,"," ten Lords-a-Leaping,"," eleven Pipers Piping,"," twelve Drummers Drumming,"};
    String verse(int verseNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append("On the ");
        sb.append(numbersWord[verseNumber-1]);
        sb.append(" day of Christmas my true love gave to me:");
        System.out.println("="+gifts.length);
        for(int i = verseNumber-1; i > 0; i--) {
            sb.append(gifts[i]);
        }
        if(verseNumber != 1)
            sb.append(" and");
        sb.append(gifts[0]);
        return sb.toString();
    }

    String verses(int startVerse, int endVerse) {
        StringBuilder sb = new StringBuilder();
        while (startVerse < endVerse) {
            sb.append(this.verse(startVerse));
            startVerse++;
            sb.append("\n");
        }
        //last one is out of the loop pass, don't need another \n
        sb.append(this.verse(startVerse));
        return sb.toString();
    }

    String sing() {
        return this.verses(1,12);
    }
}
