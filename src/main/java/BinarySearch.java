import java.util.List;

public class BinarySearch {
    private final List<Integer> list;

    public BinarySearch(List<Integer> list) {
        this.list = list;
    }

    public int indexOf(int value) throws ValueNotFoundException {
        if (list.isEmpty())
            throw new ValueNotFoundException("Value not in array");
        int high = list.size(), mid = high / 2, low = 0, oldMid = -1;
        while (mid != 0 && oldMid != mid) {
            if (list.get(mid) == value)
                return mid;
            if (list.get(mid) < value)
                low = mid;
            else
                high = mid;
            oldMid = mid;
            mid = low + ((high - low) / 2);
        }
        if (list.get(0) == value)
            return 0;
        else
            throw new ValueNotFoundException("Value not in array");
    }
}