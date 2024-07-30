package application;

import entities.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);

        List<Employee> list = new ArrayList<>();

        System.out.print("Quantos funcionarios há na empresa? ");
        int numEmployes = sc.nextInt();

//      Salvando os dados na lista
        for(int i=0; i< numEmployes; i++){
            System.out.println("Dados da " + (i+1) +"a pessoa");

            System.out.print("Id: ");
            int id = sc.nextInt();

            while(hasId(list, id)){
                System.out.print("Id already taken. Try again: ");
                id = sc.nextInt();
            }

            System.out.print("Name: ");
            String nome = sc.next();

            System.out.print("Salary: ");
            double salary = sc.nextDouble();

            list.add(new Employee(id,nome,salary));

        }

//      Operação para aumentar o salario
        System.out.print("Qual id terá o aumento de salario? ");
        int id = sc.nextInt();

        Employee emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);

        if (emp == null){
            System.out.println("Id incorreto");
        }else{
            System.out.print("Qual será a porcentagem de aumento? ");
            double percent = sc.nextDouble();
            double aumento = emp.increaseSalary(percent);
        }

        System.out.println("Lista de empregados: ");
        for(Employee obj: list){
            System.out.println(obj);
        }


        sc.close();
    }

//  Pesquisando se o id existe na lista
    public static boolean hasId(List<Employee> list, int id){
        Employee emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        return emp != null;
    }
}
