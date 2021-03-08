package com.company;

public class CreateMarketolog implements Createworker{
    @Override
    public Worker createworker(String name,String surname,int salary, int employ_id,int shop_id) {
        return new Marketolog(name,surname,salary,employ_id,shop_id);
    }
}
