package live.ashish.cpjava.systemdesign.wallet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
https://onedrive.live.com/view.aspx?resid=9B1BD26B02F2442D%21153&id=documents&wd=target%285.%20Other%20Interview%20Questions%2FMachine%20Coding.one%7CFB04D0B9-D9F3-C944-B13B-DADD6AFC1934%2FDigital%20wallet%7CAB689DAE-4BBB-DB43-99BC-D0206D9082F3%2F%29
onenote:https://d.docs.live.net/9b1bd26b02f2442d/Documents/notes/CP%20and%20Interview%20Notes/5.%20Other%20Interview%20Questions/Machine%20Coding.one#Digital%20wallet&section-id={FB04D0B9-D9F3-C944-B13B-DADD6AFC1934}&page-id={AB689DAE-4BBB-DB43-99BC-D0206D9082F3}&end
 */

// this is suboptimal, see the better version in link in the docs above
public class DigitalWallet {
    List<WalletAccount> walletAccountList = new ArrayList<>();
    public static void main(String[] args) {
        DigitalWallet wallet = new DigitalWallet();
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Please choose an option: \n1 - Create wallet" +
                    "\n2 - Transfer Amount\n3 - Account Statement\n4 - Overview\n5 - Exit");
            int response = input.nextInt();
            input.nextLine();
            switch (response) {
                case 1:
                    System.out.println("You have chosen: Create Wallet\nEnter name of user");
                    String userName = input.nextLine();
                    System.out.println("Enter amount in the wallet");
                    double amount = input.nextDouble();
                    wallet.createWalletAccount(userName,amount);
                    break;
                case 2:
                    System.out.println("You have chosen: Transfer Amount\nEnter userId of payer");
                    String sender = input.next();
                    System.out.println("Enter userId of receiver");
                    String receiver = input.next();
                    System.out.println("Enter Amount to be sent..");
                    double amountTransfer = input.nextDouble();
                    wallet.transferAmount(sender,receiver,amountTransfer);
                    break;
                case 3:
                    System.out.println("You have chosen: Account Statement\nEnter userId");
                    String userId = input.next();
                    wallet.accountStatement(userId);
                    break;
                case 4:
                    System.out.println("You have chosen: Overview");
                    // show all balances
                    wallet.overview();
                    break;
                case 5:
                    System.out.println("You have chosen: Exit");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option, try again!!!");
                    break;
            }

        }
    }

    private void overview() {
        walletAccountList.forEach(System.out::println);
    }

    private void transferAmount(String sender, String receiver, double amountTransfer) {
        WalletAccount senderAccount = null;
        WalletAccount receiverAccount = null;
        for (WalletAccount account : walletAccountList) {
            if (account.getUserId().equals(sender)) {
                senderAccount = account;
            }
            if (account.getUserId().equals(receiver)) {
                receiverAccount = account;
            }
        }
        if (senderAccount != null && receiverAccount != null) {
            if (senderAccount.getAmountBalance() >= amountTransfer) {
                senderAccount.setAmountBalance(senderAccount.getAmountBalance() - amountTransfer);
                receiverAccount.setAmountBalance(receiverAccount.getAmountBalance() + amountTransfer);
                senderAccount.getAccountStatement().add("sending amount: "+ amountTransfer +" to "+receiverAccount.getUserId());
                receiverAccount.getAccountStatement().add("received amount: "+ amountTransfer +" from "+senderAccount.getUserId());
                System.out.println("Amount transferred successfully!");
                System.out.println("Balances, sender: "+ senderAccount.getAmountBalance()+ " receiver: "+receiverAccount.getAmountBalance());
            } else {
                System.out.println("Insufficient balance in sender's account!");
            }
        } else {
            System.out.println("Invalid sender or receiver userId!");
        }
    }

    private  WalletAccount createWalletAccount(String userName, double amountBalance) {
        WalletAccount newAccount =  new WalletAccount(userName,amountBalance);
        newAccount.getAccountStatement().add("created account with Balance: "+ amountBalance);
        walletAccountList.add(newAccount);
        return newAccount;
    }

    private void accountStatement(String userId){
        WalletAccount account = null;
        for (WalletAccount walletAccount : walletAccountList) {
            if (walletAccount.getUserId().equals(userId)) {
                account = walletAccount;
                break;
            }
        }
        if (account != null) {
            System.out.println(account.getAccountStatement());
        } else {
            System.out.println("Couldn't find user Account with ID: " + userId);
        }
    }
}

