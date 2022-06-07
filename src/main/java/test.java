
import com.sun.jdi.ArrayReference;
import org.junit.Test;

import java.util.*;

import static java.util.Arrays.stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class test {
    private static AffineCipher affineCipher = new AffineCipher();
    private static final int CHARACTER_LIMIT = 26;

    public static void main(String[] args) {
        int i;
        long time;
        BeerSong b = new BeerSong();
        BeerSongv2 bv = new BeerSongv2();
        BeerSongv3 bThree = new BeerSongv3();
        long prev_time = System.currentTimeMillis();
        for(i = 0; i< 100000; i++){
            String s = b.singSong();
        }
        time = System.currentTimeMillis() - prev_time;
        System.out.println("time took : "+ time);
        prev_time = System.currentTimeMillis();
        for(i = 0; i< 100000; i++){
            String s = bv.singSong();
        }
        time = System.currentTimeMillis() - prev_time;
        System.out.println(" time took : "+ time);
        prev_time = System.currentTimeMillis();
        for(i = 0; i< 100000; i++){
            String s = bThree.singSong();
        }
        time = System.currentTimeMillis() - prev_time;
        System.out.println("v3 time took : "+ time);
    }
}

