import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RestApi {
    private final static String ADD_URL = "add", USERS_URL = "users", IOU_METHOD = "iou";
    private List<User> users;
    public RestApi() {
        this.users = new LinkedList<User>();
    }
    public RestApi(User... users) {
        this.users = new LinkedList<User>(Arrays.asList(users));
    }

    public String get(String url, JSONObject input){
        String method = url.replaceAll("/","");
        if (method.equals(USERS_URL)) {
                JSONArray names = input.getJSONArray("users");
                return getUsers(names);
        }
        return null;
    }

    public String get(String url) {
        url = url.replaceAll("/","").toLowerCase();
        if (url.equals(USERS_URL)) {
                JSONArray usersJSON = new JSONArray();
                users.forEach(user -> usersJSON.put(userJSON(user)));
                JSONObject output = new JSONObject();
                output.put("users", usersJSON);
                return output.toString();
        }
        return null;
    }
    private String getUsers(JSONArray names) {
        JSONArray usersFoundJSON = new JSONArray();
        for(int i = 0; i < names.length(); i++) {
            String name = names.get(i).toString();
            User u =  users.stream().filter(user -> name.equals(user.name())).findFirst().orElse(null);
            if (u == null)
                usersFoundJSON.put(new JSONObject().put(name,"User cannot be found").toString());
            else
                usersFoundJSON.put(userJSON(u));
        }
        JSONObject output = new JSONObject();
        output.put("users", usersFoundJSON);
        return output.toString();
    }
    public String post(String url, JSONObject payload) {
        String method = url.replaceAll("/","").toLowerCase();
        switch (method) {
            case IOU_METHOD:
                //get input
                String lenderName = payload.getString("lender");
                String borrowerName = payload.getString("borrower");
                int amount = payload.getInt("amount");
                //finds two users
                User lender = users.stream().filter(user -> lenderName.equals(user.name())).findFirst().orElse(null);
                User borrower = users.stream().filter(user -> borrowerName.equals(user.name())).findFirst().orElse(null);
                if (lender == null || borrower == null)
                    return new JSONObject().put("Exception","Users cannot be found").toString();
                int lenderIndex = users.indexOf(lender), borrowerIndex = users.indexOf(borrower);
                //balance to each together
                int borrowerBalance = - amount, lenderBalance = amount;
                //focusing on the borrower now
                borrower = rebuilt(borrowerBalance, borrower, lender);
                lender = rebuilt(lenderBalance, lender, borrower);
                users.add(lenderIndex, lender);
                users.add(borrowerIndex, lender);
                JSONArray usersJSON = new JSONArray();
                lenderBalance = lender.owedBy().stream().mapToInt(iou ->  (int) iou.amount).sum();
                lenderBalance -= lender.owes().stream().mapToInt(iou -> (int) iou.amount).sum();
                borrowerBalance = borrower.owedBy().stream().mapToInt(iou ->  (int) iou.amount).sum();
                borrowerBalance -= borrower.owes().stream().mapToInt(iou -> (int) iou.amount).sum();
                if(lenderBalance == 0 && borrowerBalance != 0) {
                    usersJSON.put(userJSON(borrower, borrowerBalance));
                    usersJSON.put(userJSON(lender, lenderBalance));

                } else {
                    usersJSON.put(userJSON(lender, lenderBalance));
                    usersJSON.put(userJSON(borrower, borrowerBalance));
                }
                return new JSONObject().put("users", usersJSON).toString();
            case ADD_URL:
                String name = payload.getString("user");
                User u = User.builder().setName(name).build();
                users.add(u);
                JSONObject userJSON = userJSON(u);
                return userJSON.toString();
        }
        return null;
    }
    private User rebuilt( double lenderBalance, User lender, User borrower) {
        User.Builder lenderBuilder = User.builder().setName(lender.name());
        boolean found = false;
        for (Iou l : lender.owes()) {
            if (l.name.equals(borrower.name())) {
                lenderBalance -= l.amount;
                found = true;
            } else
                lenderBuilder.owes(l.name, l.amount);
        }
        if (found)
            lender.owedBy().forEach(l -> lenderBuilder.owedBy(l.name, l.amount));
        else {
            for (Iou b : lender.owedBy()) {
                if (b.name.equals(borrower.name())) {
                    lenderBalance += b.amount;
                } else
                    lenderBuilder.owedBy(b.name, b.amount);
            }
        }
        if (lenderBalance > 0) {
            lenderBuilder.owedBy(borrower.name(), lenderBalance);
        } else if (lenderBalance < 0) {
            lenderBuilder.owes(borrower.name(), Math.abs(lenderBalance));
        }
        return lenderBuilder.build();
    }
    private JSONObject userJSON(User u) {
        int balance = u.owedBy().stream().mapToInt(iou ->  (int) iou.amount).sum();
        balance -= u.owes().stream().mapToInt(iou -> (int) iou.amount).sum();
        return userJSON(u,balance);
    }
    private JSONObject userJSON(User u, int balance) {
        JSONObject json = new JSONObject();
        JSONObject owesJSON = new JSONObject();
        u.owes().forEach(e -> owesJSON.put(e.name,e.amount) );
        json.put("owes", owesJSON);
        json.put("balance",balance);
        json.put("name",u.name());
        JSONObject owedByJSON = new JSONObject();
        u.owedBy().forEach(e -> owedByJSON.put(e.name,e.amount));
        json.put("owedBy",owedByJSON);
        return json;
    }
}