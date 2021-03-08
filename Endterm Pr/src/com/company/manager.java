package com.company;

public class manager extends workers implements Worker{
    public manager(String name, String surname, int salary, int employ_id, int level){}
    public manager(String name, String surname, int salary, int employ_id, int shop_id, int level) {
        super(name, surname, salary, employ_id, shop_id, level);
    }
    @Override
    public void works() {
        System.out.println("Hello, I am manager, do you have a question?");
    }

    @Override
    public void setWoker() {
        set_worker();
    }

    @Override
    public void get() {
        System.out.println(name+" "+surname+" "+salary+" "+employ_id+" "+shop_id+" "+level);
    }

    void setManager(){
        System.out.println("Dear director");
    }
}
