package com.company;

public class Marketolog extends workers implements Worker{
    public Marketolog(String name, String surname, int salary, int employ_id, int level){}
    public Marketolog(String name, String surname, int salary, int employ_id, int shop_id, int level) {
        super(name, surname, salary, employ_id, shop_id, level);
    }



    @Override
    public void works() {
        System.out.println("Hello, I am best marketolog!");
    }

    @Override
    public void setWoker() {
        set_worker();
    }

    @Override
    public void get() {

    }
}
