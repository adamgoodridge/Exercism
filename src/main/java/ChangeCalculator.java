import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import static java.util.Arrays.asList;

public class ChangeCalculator {
    private List<Integer> coins;

    public ChangeCalculator(List<Integer> coins) {
        this.coins = coins;
    }

    public List<Integer> computeMostEfficientChange(int total) {
        List<Integer> suitable = new LinkedList<>();
        for (Integer num : coins) {
            if (num <= total)
                suitable.add(num);
        }
        if (total == 0)
            return asList();
        if (total < 0)
            throw new IllegalArgumentException("Negative totals are not allowed.");
        if (suitable.size() == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("The total ");
            sb.append(total);
            sb.append(" cannot be represented in the given currency.");
            throw new IllegalArgumentException(sb.toString());
        }

        int sum = 0, sumOfSetCoins = suitable.stream().mapToInt(Integer::intValue).sum(), times = 0;

        while (total > sum) {
            sum += sumOfSetCoins;
            times++;
        }
        LinkedList<Integer> output;
        if (total == sum) {
            output = new LinkedList<>();
            for (int i = 0; i < suitable.size(); i++) {
                output.addAll(Collections.nCopies(times, suitable.get(i)));
            }
        } else {
            List<Integer> inRunning = new LinkedList<>();
            for (int i = suitable.size() - 1; i >= 0; i--) {
                List<Integer> tempList = new LinkedList<>();
                int k = i, tempTotal = total, reminder, amountOfCoins;
                do {
                    int coin = suitable.get(k);
                    if (tempTotal >= coin) {
                        reminder = tempTotal % coin;
                        amountOfCoins = (tempTotal - reminder) / coin;
                        //to determine we have right coins to cover the reminder if it uses this type of coin,
                        // in-order to meet this, the lowest coin must be lower than reminder
                        //e.g 21 ; coin 20, lowest coin is 2
                        //2 > 1
                        if (k != 0 && reminder != 0 && suitable.get(0) > reminder) {
                            if (amountOfCoins == 1) break;
                            else {
                                do {
                                    reminder += coin;
                                    amountOfCoins--;
                                } while (suitable.get(0) > reminder);
                            }
                        }
                        tempList.addAll(Collections.nCopies(amountOfCoins, coin));
                        tempTotal = reminder;
                    }
                    k--;
                } while (k >= 0 && tempTotal != 0 && (inRunning.isEmpty() || tempList.size() < inRunning.size()));
                if (tempTotal == 0 && (inRunning.isEmpty() || tempList.size() < inRunning.size())) {
                    inRunning = tempList;
                }
            }
            output = (LinkedList<Integer>) inRunning;
            if ((output == null || output.isEmpty())) {
                StringBuilder sb = new StringBuilder();
                sb.append("The total ");
                sb.append(total);
                sb.append(" cannot be represented in the given currency.");
                throw new IllegalArgumentException(sb.toString());
            }
            Collections.reverse(output);
        }

        return output;
    }
}
