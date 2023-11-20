package live.ashish.cpjava.systemdesign.splitwise;

import java.util.*;

// source - https://workat.tech/machine-coding/practice/splitwise-problem-0kp2yneec2q2
public class Splitwise {
    Map<User, Double> balances; // stores the balances for users
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<User> users = new ArrayList<>();
        List<String> userName = Arrays.asList("u1", "u2", "u3");
        for (String name : userName) {
            users.add(new User(name));
        }
        // now init all the balances
        for(User user: users){
            user.initBalances(users);
//            user.showBalances();
        }
        // now add bill with types
        User.addEqualBill("u1",300, Arrays.asList("u2","u3"));
        User.addEqualBill("u2",600, Arrays.asList("u1","u3"));
//        User.addUnEqualBill("u2",4, Arrays.asList("u1","u3"));

        //TODO: implement settle functionality
        // get the balance list from everyuser and then settle it.
    }


}
