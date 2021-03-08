package com.company;

public class Marketolog extends workers implements Worker{
    //public Marketolog(String name, String surname, int salary, int employ_id, int ship_id){}
    public Marketolog(String name, String surname, int salary, int employ_id, int shop_id) {
        super(name, surname, salary, employ_id, shop_id);
    }
    @Override
    public void getSalary() {
        System.out.print("Your salary is: ");
        super.getSalary();
    }
    @Override
    public void works() {
        System.out.println("Hello, I am best marketolog!");
    }

    @Override
    public void set_worker(){
        set_worker();
    }

    @Override
    public void get() {

    }
}
