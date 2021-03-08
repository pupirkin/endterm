package com.company;

public class consultant extends workers implements Worker{
    public consultant(String name, String surname, int salary, int employ_id, int level){}
    public consultant(String name, String surname, int salary, int employ_id, int shop_id, int level) {
        super(name, surname, salary, employ_id, shop_id, level);
    }

    @Override
    public void works() {
        System.out.println("Hello, I am your consultant");
    }

    @Override
    public void setWoker() {
        set_worker();
    }
    @Override
    public void get(){
        get_info();
    }
}
