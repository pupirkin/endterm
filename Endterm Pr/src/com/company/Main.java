package com.company;
import java.util.Scanner;
import java.lang.Object;
import java.time.LocalDateTime;
public class Main {

    public static void main(String[] args) {

        System.out.println("Hello, are you employee or client");
        Scanner Sc = new Scanner(System.in);

        int how=Sc.nextInt();
        if (how==1){
            System.out.println("Please, enter your id");
            String login=Sc.nextLine();

        }
        else {
            //...
        }
        //taking info from database...
        String typeOfWorker = "director";
        if (typeOfWorker=="director"){
            String name=null;
            String surname=null;
            int salary=0;
            int employ_id=0;
            int level=0;
            Createworker createDirector=new CreateDirector();
            Worker direc=createDirector.createworker(name,surname,salary,employ_id,level);
            direc.setWoker();
            direc.get();
//            System.out.println("Hello, "+name+" "+surname+"what you want to do?");
//            System.out.println("1.\n2.\n3.\n4.");
//            int num=Sc.nextInt();
//            if (num==1){}
//            else if (num==2){}
//            else {}
        }
        else if (typeOfWorker=="manager"){
            String name=null;
            String surname=null;
            int salary=0;
            int employ_id=0;
            int level=0;
            Createworker createManager=new CreateManager();
            Worker man=createManager.createworker(name,surname,salary,employ_id,level);
            System.out.println("Hello, "+name+" "+surname+"what you want to do?");
            System.out.println("1.\n2.\n3.\n4.");
            int num=Sc.nextInt();
            if (num==1){}
            else if (num==2){}
            else if(num==3) {}
            else {}
        }
        else if (typeOfWorker=="Marketolog"){
            String name=null;
            String surname=null;
            int salary=0;
            int employ_id=0;
            int level=0;
            Createworker createMark=new CreateMarketolog();
            Worker mark=createMark.createworker(name,surname,salary,employ_id,level);

            System.out.println("Hello, "+name+" "+surname+"what you want to do?");
            System.out.println("1.\n2.\n3.\n4.");
            int num=Sc.nextInt();
            if (num==1){}
            else if (num==2){}
            else {}
        }
        else {
            String name=null;
            String surname=null;
            int salary=0;
            int employ_id=0;
            int level=0;
            Createworker createworkers=new CreateConsultant();
            Worker cons=createworkers.createworker(name,surname,salary,employ_id,level);
            System.out.println("Hello, "+name+" "+surname+"what you want to do?");
            System.out.println("1.\n2.\n3.\n4.");
            int num=Sc.nextInt();
            if (num==1){}
            else if (num==2){}
            else {}
        }

    }
}
