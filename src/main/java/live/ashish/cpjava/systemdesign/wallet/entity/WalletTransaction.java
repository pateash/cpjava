package live.ashish.cpjava.systemdesign.wallet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data @AllArgsConstructor
public class WalletTransaction {
    WalletAccount from;
    WalletAccount to;
    double amount;
    Date date;

    @Override
    public String toString() {
        return "WalletTransaction{" +
                "from=" + from.getUser().getName() +
                ", to=" + to.getUser().getName() +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
