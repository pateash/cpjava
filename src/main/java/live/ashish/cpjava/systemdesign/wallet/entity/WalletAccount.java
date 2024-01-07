package live.ashish.cpjava.systemdesign.wallet.entity;

import live.ashish.cpjava.systemdesign.splitwise.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class WalletAccount {
    int accountNumber;
    WalletUser user;
    double amountBalance;
    List<WalletTransaction> accountStatement = new ArrayList<>();
    private static int accountNumberGenerator=0;

    public WalletAccount(String userName, double amountBalance) {
        this.accountNumber = ++accountNumberGenerator;
        this.user = new WalletUser(userName);
        this.amountBalance = amountBalance;
    }

    @Override
    public String toString() {
        return "WalletAccount{" +
                "accountNumber=" + accountNumber +
                ", user=" + user +
                ", amountBalance=" + amountBalance +
                '}';
    }
}
