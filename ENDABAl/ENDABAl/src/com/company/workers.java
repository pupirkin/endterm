package com.company;
import java.lang.Object;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class workers {
    public workers(){}
    public String name;
    public String surname;
    int salary;
    int employ_id;
    int shop_id;
    Scanner Sc = new Scanner(System.in);
    Calendar hire_date=new GregorianCalendar();
    public workers(String name,String surname,int salary, int employ_id, int shop_id){
        this.name=name;
        this.surname=surname;
        this.salary=salary;
        this.employ_id=employ_id;
        this.shop_id=shop_id;
    };
    public void getSalary(){
        System.out.println(salary);
    }
    void set_worker(){
        System.out.println("Enter employee's name: ");
        name=Sc.nextLine();
        System.out.println("Enter employee's surame: ");
        surname=Sc.nextLine();
        System.out.println("Enter employee's salary: ");
        salary=Sc.nextInt();
        System.out.println("Enter employee's id: ");
        employ_id=Sc.nextInt();
        System.out.println("Enter shop's id where employee is working: ");
        shop_id=Sc.nextInt();
    }
    public void get_info(){
        System.out.println(name+" "+surname+" "+salary+" "+employ_id+" "+shop_id);
    }
}
