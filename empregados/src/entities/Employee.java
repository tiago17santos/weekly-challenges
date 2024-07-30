package entities;

public class Employee {

    private String nome;
    private int id;
    private double salary;

    public Employee(){

    }

    public Employee(int id, String nome, double salary){
        this.id = id;
        this.nome = nome;
        this.salary = salary;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSalary( double salary) {
        this.salary =  salary;
    }

    public double increaseSalary(double percent) {
        return salary += salary * (percent/100);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString(){
        return id + ", " + nome + ", R$" + String.format("%.2f", salary);
    }
}
