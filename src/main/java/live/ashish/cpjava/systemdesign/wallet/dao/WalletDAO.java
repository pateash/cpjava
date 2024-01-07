package live.ashish.cpjava.systemdesign.wallet.dao;

import live.ashish.cpjava.systemdesign.wallet.entity.WalletAccount;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Data
public class WalletDAO {
    // this will store all accounts and related data
    Map<Integer, WalletAccount> walletAccountMap;

    public WalletDAO() {
        walletAccountMap=new HashMap<>();
    }
}
