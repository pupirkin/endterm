package com.company;

public class Director extends workers implements Worker{
    //public Director(String name, String surname, int salary, int employ_id, int shop_id){}
    public Director(String name, String surname, int salary, int employ_id, int shop_id) {
        super(name, surname, salary, employ_id, shop_id);
    }


    @Override
    public void getSalary() {
        System.out.print("Your salary is: ");
        super.getSalary();
    }

    @Override
    public void works() {
        System.out.println("Hello, I am Director of the company");
    }

    @Override
    public void set_worker() {
        set_worker();
    }

    @Override
    public void get() {

    }
}
