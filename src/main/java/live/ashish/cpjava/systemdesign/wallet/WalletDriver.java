package live.ashish.cpjava.systemdesign.wallet;

import live.ashish.cpjava.systemdesign.wallet.services.WalletService;

import java.util.Scanner;

/*
https://onedrive.live.com/view.aspx?resid=9B1BD26B02F2442D%21153&id=documents&wd=target%285.%20Other%20Interview%20Questions%2FMachine%20Coding.one%7CFB04D0B9-D9F3-C944-B13B-DADD6AFC1934%2FDigital%20wallet%7CAB689DAE-4BBB-DB43-99BC-D0206D9082F3%2F%29
onenote:https://d.docs.live.net/9b1bd26b02f2442d/Documents/notes/CP%20and%20Interview%20Notes/5.%20Other%20Interview%20Questions/Machine%20Coding.one#Digital%20wallet&section-id={FB04D0B9-D9F3-C944-B13B-DADD6AFC1934}&page-id={AB689DAE-4BBB-DB43-99BC-D0206D9082F3}&end
 */


// driver will only call service and service will call DAO, which only have access to any data

public class WalletDriver {
    public static void main(String[] args) {
        WalletService wallet = new WalletService();
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
                    wallet.createWalletAccount(userName, amount);
                    break;
                case 2:
                    System.out.println("You have chosen: Transfer Amount\nEnter accountNumber of payer");
                    int sender = input.nextInt();
                    System.out.println("Enter accountNumber of receiver");
                    int receiver = input.nextInt();
                    System.out.println("Enter Amount to be sent..");
                    double amountTransfer = input.nextDouble();
                    wallet.transferAmount(sender, receiver, amountTransfer);
                    break;
                case 3:
                    System.out.println("You have chosen: Account Statement\nEnter account Number");
                    Integer accountNumber = input.nextInt();
                    wallet.accountStatement(accountNumber);
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
}

