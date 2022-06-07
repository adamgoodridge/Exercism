import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Flattener {
    public Flattener() {

    }

    public List<Object> flatten(List<Object> list) {
        List<Object> output = new LinkedList<>();
        Stack<List> listsPath = new Stack<>();
        Stack<Integer> indexes = new Stack<>();
        int i = 0;
        while (true) {
            Object item = list.get(i);
            if(item instanceof List) {
                listsPath.add(list);
                indexes.add(i + 1);
                i = 0;
                list = (List) item;
            } else {
                if(item != null)
                    output.add(item);
                i++;
            }
            while (i == list.size()) {
                if(listsPath.isEmpty())
                    return output;
                list = listsPath.pop();
                i = indexes.pop();
            }
        }
    }
}