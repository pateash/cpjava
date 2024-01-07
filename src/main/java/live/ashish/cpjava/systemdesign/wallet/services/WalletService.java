package live.ashish.cpjava.systemdesign.wallet.services;

import live.ashish.cpjava.systemdesign.wallet.entity.WalletAccount;
import live.ashish.cpjava.systemdesign.wallet.dao.WalletDAO;
import live.ashish.cpjava.systemdesign.wallet.entity.WalletTransaction;

import java.util.Date;

public class WalletService {
    //    List<WalletAccount> walletAccountList = new ArrayList<>();
    WalletDAO dao;

    public WalletService() {
        dao = new WalletDAO();
    }

    public void overview() {
        // printing all
        dao.getWalletAccountMap().keySet().forEach(key -> System.out.println(dao.getWalletAccountMap().get(key)));
    }

    public synchronized void transferAmount(int sender, int receiver, double amountTransfer) {
        if (validateTransfer(sender, receiver, amountTransfer)) {
            WalletAccount senderAccount = dao.getWalletAccountMap().get(sender);
            WalletAccount receiverAccount = dao.getWalletAccountMap().get(receiver);
            senderAccount.setAmountBalance(senderAccount.getAmountBalance()-amountTransfer);
            receiverAccount.setAmountBalance(receiverAccount.getAmountBalance()+amountTransfer);
            senderAccount.getAccountStatement().add(new WalletTransaction(senderAccount,receiverAccount,amountTransfer, new Date()));
            receiverAccount.getAccountStatement().add(new WalletTransaction(senderAccount,receiverAccount,amountTransfer, new Date()));
            System.out.println("Amount transferred successfully!");
        }
    }

    public WalletAccount createWalletAccount(String userName, double amountBalance) {
        WalletAccount newAccount = new WalletAccount(userName, amountBalance);
        dao.getWalletAccountMap().put(newAccount.getAccountNumber(), newAccount);
        return newAccount;
    }

    public void accountStatement(int accountNumber) {
        WalletAccount account = null;
        if (checkAccountExists(accountNumber)) {
            account = dao.getWalletAccountMap().get(accountNumber);
            account.getAccountStatement().forEach(System.out::println);
        } else {
            System.out.println("Couldn't find user Account with accountNumber: " + accountNumber);
        }
    }

    private boolean checkAccountExists(int accountNumber) {
        return dao.getWalletAccountMap().containsKey(accountNumber);
    }

    private boolean validateTransfer(int sender, int receiver, double amountTransfer) {
        if (!checkAccountExists(sender)) {
            System.out.println("Invalid sender account");
            return false;
        }
        if (!checkAccountExists(receiver)) {
            System.out.println("Invalid receiver account");
            return false;
        }

        double senderAccountBalance = dao.getWalletAccountMap().get(sender).getAmountBalance();
        if(senderAccountBalance < amountTransfer){
            System.out.println("Balance not enough!");
            return false;
        }
        return true;
    }

}
