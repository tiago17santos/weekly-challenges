package entities;

public class BankAccount {

    private final double TAX = 5.00;

    private String nome;
    private int numConta;
    private double saldo;

    public BankAccount(){
    }

    public BankAccount(String nome, int numConta, double saldo){
        this.nome = nome;
        this.numConta = numConta;
        this.saldo = saldo;
    }

    public BankAccount(String nome, int numConta){
        this.nome = nome;
        this.numConta = numConta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumConta() {
        return numConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public double deposito(double deposito){
        this.saldo += deposito;
        return this.saldo;
    }

    public double saque(double saque){
        this.saldo -= (saque - TAX);
        return this.saldo;
    }

    @Override
    public String toString() {
        return "Account: " + this.numConta + ", Holder: " + this.nome + ", Balance: " + this.saldo;
    }
}
