package com.company;

public class consultant extends workers implements Worker{
    //public consultant(String name, String surname, int salary, int employ_id, int shop_id){}
    public consultant(String name, String surname, int salary, int employ_id, int shop_id) {
        super(name, surname, salary, employ_id, shop_id);
    }
    @Override
    public void getSalary() {
        System.out.print("Your salary is: ");
        super.getSalary();
    }
    @Override
    public void works() {
        System.out.println("Hello, I am your consultant");
    }

    @Override
    public void setWoker() {
        set_worker();
    }

    private void set_worker() {
    }

    @Override
    public void get(){
        get_info();
    }

    private void get_info() {
    }
}
