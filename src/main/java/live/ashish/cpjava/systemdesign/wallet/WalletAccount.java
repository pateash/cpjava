package live.ashish.cpjava.systemdesign.wallet;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class WalletAccount {
    String userId;
    String userName;
    double amountBalance;
    List<String> accountStatement = new ArrayList<>();

    public WalletAccount(String userName, double amountBalance) {
        this.userId = UUID.randomUUID().toString();
        this.userName = userName;
        this.amountBalance = amountBalance;
    }
}
