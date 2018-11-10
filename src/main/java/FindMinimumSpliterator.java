import com.google.common.base.Preconditions;

import java.util.Spliterator;
import java.util.function.IntConsumer;

import static java.util.Objects.nonNull;

/**
 * Created by mtumilowicz on 2018-11-10.
 */
class FindMinimumSpliterator implements Spliterator.OfInt {
    private int left;
    private int right;
    private final int[] arr;
    private static final int THRESHOLD = 50;

    FindMinimumSpliterator(int[] arr, int left, int right) {
        Preconditions.checkArgument(nonNull(arr));
        Preconditions.checkArgument(left >= 0);
        Preconditions.checkArgument(right <= arr.length);

        this.arr = arr;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean tryAdvance(IntConsumer action) {
        if (left <= right) {
            action.accept(arr[left]);
            left++;
            return true;
        }
        return false;
    }

    @Override
    public Spliterator.OfInt trySplit() {
        if (right - left < THRESHOLD) {
            return null;
        }

        int mid = (left + right) / 2;
        int splitRight = right;
        right = mid;
        return new FindMinimumSpliterator(arr, mid, splitRight);
    }

    @Override
    public long estimateSize() {
        return right - left;
    }

    @Override
    public int characteristics() {
        return IMMUTABLE | NONNULL | ORDERED | SIZED | SUBSIZED;
    }
}