package com.company;

public class CreateConsultant implements Createworker{
    @Override
    public Worker createworker(String name,String surname,int salary, int employ_id, int level) {
        return new consultant(name,surname,salary,employ_id,level);
    }
}
