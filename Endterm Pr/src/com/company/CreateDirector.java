package com.company;

public class CreateDirector implements Createworker{
    @Override
    public Worker createworker(String name,String surname,int salary, int employ_id,int level) {
        return new Director(name,surname,salary,employ_id,level);
    }
}
