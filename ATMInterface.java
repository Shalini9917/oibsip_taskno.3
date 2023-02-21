import java.util.*;
class BankAccount{
    
    String name;
    String userName;
    String password;
    String accountNo ;
    float balance = 100000f;
    int transactions = 0;
    String transactionHistory = "";

    public void register(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name - ");
        this.name = sc.nextLine();
        System.out.println("Enter your Username - ");
        this.userName = sc.nextLine();
        System.out.println("Enter your password - ");
        this.password = sc.nextLine();
        System.out.println("Enter your Account No. - ");
        this.accountNo = sc.nextLine();
        System.out.println("Registration completed... Kindly login.");
    }

    public boolean login(){
        boolean isLogin = false;
        Scanner sc = new Scanner(System.in);
        while(!isLogin){
            System.out.println("Enter your Username - ");
            String Username = sc.nextLine();
            if(Username.equals(userName)){
                while(!isLogin){
                    System.out.println("Enter your password - ");
                    String Password = sc.nextLine();
                    if(Password.equals(password)){
                        System.out.println("Login successful !!");
                        isLogin =true;
                    }
                    else{
                        System.out.println("Incorrect password.");
                    }
                }
            }
            else{
                System.out.println("Username not found.");
            }
        }
        return isLogin;
    }

    public void withdraw(){
        System.out.println("Enter amount to withdraw - ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try{
            if(balance >= amount){
                transactions++;
                balance -= amount;
                System.out.println("Withdraw successful !!");
                String str = amount+" Rs Withdrawed";
                transactionHistory = transactionHistory.concat(str);
            }
            else{
                System.out.println("Insufficient balance");
            }
        }
        catch(Exception e){
        }
    }
    public void deposit(){
        System.out.println("Enter amount to deposit - ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();

        try{
            if(amount <= 100000f){
                transactions++;
                balance += amount;
                System.out.println("Successfully deposited.");
                String str = amount + " Rs deposited";
                transactionHistory = transactionHistory.concat(str);
            }
            else{
                System.out.println("Sorry limit is '100000.00'");
            }
        }
        catch(Exception e){
        }
    }

    public void transfer(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter receipent's name - ");
        String receipent = sc.nextLine();
        System.out.println("Enter amount to transfer - ");
        float amount = sc.nextFloat();

        try{
            if(balance >= amount){
                if(amount <= 50000f){
                    transactions++;
                    balance -= amount;
                    System.out.println("Successfully transfered to "+receipent);
                    String str = amount+" Rs transfered to "+receipent;
                    transactionHistory = transactionHistory.concat(str);
                }
                else{
                    System.out.println("Sorry limit is '50000.00'");
                }
            }
            else{
                System.out.println("Insufficient balance.");
            }
        }
        catch(Exception e){
        }
    }
    public void checkBalance(){
        System.out.println(balance+" Rs");
    }

    public void transHistory(){
        if(transactions == 0){
            System.out.println("The account is empty.");
        }
        else{
            System.out.println(transactionHistory);
        }
    }
}

public class ATMInterface{

    public static int takeIntegerInput(int limit){
        int input = 0;
        boolean flag = false;

        while(!flag){
            try{
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                flag = true;
                if(flag && input > limit || input < 1){
                    System.out.println("Choose the number between 1 to "+limit);
                    flag = false;
                }
            }
            catch(Exception e){
                System.out.println("Enter only integer value");
                flag = false;
            }
        }
        return input;
    }
    public static void main(String args[]){
        System.out.println("Welcome to ATM System.");
        System.out.println("1. Register \n2. Exit");
        System.out.println("Enter your choice - ");
        int choice = takeIntegerInput(2);

        if(choice == 1){
            BankAccount b = new BankAccount();
            b.register();
            while(true){
                System.out.println("\n1.Login \n2.Exit");
                System.out.println("Enter your choice - ");
                int ch = takeIntegerInput(2);
                if(ch ==1){
                    if(b.login()){
                        System.out.println("\n\n*****Welcome Back "+b.name+"*****\n");
                        boolean isFinished = false;
                        while(!isFinished){
                            System.out.println("\n1.Withdraw \n2.Deposit \n3.Transfer \n4.Check Balance \n5.Transaction History \n6.Exit");
                            System.out.println("Enter your choice - ");
                            int c = takeIntegerInput(6);
                            switch(c){
                                case 1: b.withdraw();
                                break;
                                case 2: b.deposit();
                                break;
                                case 3: b.transfer();
                                break;
                                case 4: b.checkBalance();
                                break;
                                case 5: b.transHistory();
                                break;
                                case 6: isFinished = true;
                                break;
                            }    
                        }
                    }
                }
                else{
                    System.exit(0);
                }
            }
        }
        else{
            System.exit(0);
        }
    }
}