package live.ashish.cpjava.systemdesign.splitwise;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter @Setter
public class User {
    UUID id;
    String name;
    static List<User> users = new ArrayList<>();

    Map<User, Float> balances = new HashMap<>(); // map storing balances for this user to other users

    public User(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        users.add(this);
    }

    public static User getUserByName(String name){
        for(User u: users){
            if(u.name.trim().equalsIgnoreCase(name))
                return u;
        }
        return null;
    }

    public void initBalances(List<User> users) {
        // setting initial balance as 0 for all
        for (User u : users) {
            balances.put(u, 0f);
        }
    }
    public void newUser(User user){
        balances.put(user,0f);
    }
    public void showBalances(){
        System.out.println("\nfor "+this);
        balances.keySet().forEach(user -> System.out.println(user.name+" -> "+ balances.get(user)));
    }

    private void addEqualBalanceForUser(List<User> paidForUsers, float amount){
        int totalUsers = paidForUsers.size()+1; // including the payer
        float userShare = amount/totalUsers;

        System.out.println("share of each: " + userShare);
        balances.keySet().forEach(user -> {
            if(user!=this){
                balances.put(user, userShare);
            }
        });
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static void addEqualBill(String paidBy, float amount, List<String> paidFors) {
        // find user
        // call respective method
        User paidByUser = getUserByName(paidBy);
        if(paidByUser!=null){ // we found the user for which we have to add balance
            List<User> paidForUsers = new ArrayList<>();
            for(String paidFor: paidFors){
                User tmp = getUserByName(paidFor);
                if(tmp!=null)
                    paidForUsers.add(tmp);
            }
            paidByUser.addEqualBalanceForUser(paidForUsers, amount);
            // now we have th user which paid, and for which they paid
            // lets add balance
            paidByUser.showBalances();
        }
        else
            System.out.println("User not found with name: "+paidBy);
    }

    public static void addUnEqualBill(String user, int amount, List<String> users, Map<User, Float> billSplit) {
    }
}
