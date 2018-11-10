import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;
import java.util.stream.StreamSupport;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mtumilowicz on 2018-11-10.
 */
public class FindMinimumTest {

    private static final int[] arr = new int[100_000];
    private static final int min = -1_000_005;

    @BeforeClass
    public static void before() {
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(2_000_000) - 1_000_000;
        }

        arr[50_000] = min;
    }

    @Test
    public void xxx() {
        int minimum = StreamSupport.intStream(new FindMinimumSpliterator(arr, 0, arr.length - 1), true)
                .min()
                .orElseThrow();
        
        assertThat(minimum, is(min));
    }
}
