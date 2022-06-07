import java.util.LinkedList;

class SumOfMultiples {
    private int sum;
    SumOfMultiples(int number, int[] set) {
        LinkedList<Integer> naturalNumbers = new LinkedList<Integer>();
        for(int i = 1;i<number;i++) {
            for(int k = 0; k < set.length; k++) {
                // e.g number = 20, the set is [3] and i=18, 18%3
                if(set[k] != 0 && i % set[k] == 0) {
                    naturalNumbers.add(i);
                    break;
                }
            }
        }
        for(int naturalNumber: naturalNumbers) {
            sum+=naturalNumber;
        }
    }

    int getSum() {
        return this.sum;
    }

}
