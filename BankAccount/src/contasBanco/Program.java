package contasBanco;

import entities.BankAccount;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Program {

    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        BankAccount bank = new BankAccount();
        double deposit;

        System.out.print("Enter account number: ");
        int account = sc.nextInt();

        System.out.print("Enter account holder: ");
        String name = sc.next();

        System.out.print("Is there initial deposit (y/n)? ");
        String validation = sc.next();

        if(Objects.equals(validation, "y")){
            System.out.print("Enter initial deposit value: ");
            deposit = sc.nextDouble();
            bank = new BankAccount(name,account,deposit);
        }else{
            bank = new BankAccount(name,account);
        }

        System.out.println("Account data: " + bank);

        System.out.print("Enter a deposit value: ");
        deposit = sc.nextDouble();
        bank.deposito(deposit);
        System.out.println("Updated account data: " + bank);

        System.out.print("Enter a withdraw value: ");
        double saque = sc.nextDouble();
        bank.saque(saque);
        System.out.println("Updated account data: " + bank);

    }
}
