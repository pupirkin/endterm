package com.company;

public class manager extends workers implements Worker{
    //public manager(String name, String surname, int salary, int employ_id, int shop_id){}
    public manager(String name, String surname, int salary, int employ_id, int shop_id) {
        super(name, surname, salary, employ_id, shop_id);
    }
    @Override
    public void getSalary() {
        System.out.print("Your salary is: ");
        super.getSalary();
    }
    @Override
    public void works() {
        System.out.println("Hello, I am manager, do you have a question?");
    }

    @Override
    public void set_worker() {
        set_worker();
    }

    @Override
    public void get_info() {
        get_info();
    }

    void setManager(){
        System.out.println("Dear director");
    }
}
