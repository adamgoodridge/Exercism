import java.util.*;

public class Knapsack {
    public Knapsack(){

    }

    public int maximumValue(int limit, ArrayList<Item> items) {
        /*Collections.reverse(items);
        for(Item item: items){
            System.out.println(item);
        }

         */
        LinkedList<Item> bestItems;
        int bestWeight = 0, bestTotal = 0;
        Item item;
        for(int i = 0; i < items.size(); i++) {
            bestItems = new LinkedList<>();
            item = items.get(i);
            bestItems.add(item);

            int subTotal = items.get(i).getValue(), currentWeight = items.get(0).getWeight();
            for(int k = i + 1; k < items.size(); k++) {
                subTotal = items.get(i).getValue();
                currentWeight = items.get(0).getWeight();
                item = items.get(k);
                if(currentWeight + item.getWeight() < limit) {
                    subTotal += item.getValue();
                    currentWeight += item.getWeight();
                    int j = k + 1;
                    while (currentWeight < limit && j < items.size()) {
                        item = items.get(j);
                        if(currentWeight + item.getWeight() <= limit) {
                            subTotal += item.getValue();
                            currentWeight += item.getWeight();
                        }
                        j++;
                    }
                }
                System.out.println(items.get(i));
                if(bestTotal < subTotal)
                    bestTotal = subTotal;
            }
        }
        return bestTotal;
    }
}
class Item {
    private int weight, value;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Item{" +
                "weight=" + weight +
                ", value=" + value +
                '}';
    }

    public int compareTo(Item item) {
        if (weight == item.weight)
            return value < item.value ? 1 : (value > item.value ? -1 : 0);
        else
            return weight > item.weight? 1 : (weight < item.weight ? -1 : 0);

    }

}