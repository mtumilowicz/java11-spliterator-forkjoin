import com.google.common.base.Preconditions;

import java.util.OptionalInt;
import java.util.stream.StreamSupport;

import static java.util.Objects.nonNull;

/**
 * Created by mtumilowicz on 2018-11-10.
 */
class FindMinimum {
    static OptionalInt find(int[] array) {
        Preconditions.checkArgument(nonNull(array));

        return StreamSupport.intStream(new FindMinimumSpliterator(array, 0, array.length - 1), true)
                .min();
    }
}
