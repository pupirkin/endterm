package com.company;

import java.util.Scanner;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);

        Connection connection = null;
        Statement statement = null;
        boolean is_continue = true;

        while (is_continue) {
            System.out.println("Who are you? ");
            System.out.println("1.Employ");
            System.out.println("2.Customer");
            System.out.println("3.Provider");
            int choice = sc.nextInt();
            while (choice != 1 && choice != 2 && choice != 3) {
                System.out.println("Please choose only from possible options 1-3");
                choice = sc.nextInt();
            }
            switch (choice) {
                /*Employee*/
                case 1:
                    boolean is_id_right = false;

                    System.out.println("Your ID?");
                    int employ_id1 = sc.nextInt();

                    try {
                        Class.forName("org.postgresql.Driver");
                        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/endterm", "postgres", "Alalop12");
                        connection.setAutoCommit(false);
                        statement = connection.createStatement();


                        ResultSet resultSet = statement.executeQuery("SELECT employ_ID FROM Employ WHERE employ_id = " + employ_id1 + "");
                        if (resultSet.next()) {
                            is_id_right = true;
                        } else {
                            System.out.println("Your id is wrong");
                            is_id_right = false;
                        }
                        resultSet.close();
                        statement.close();
                        connection.commit();
                        connection.close();
                    } catch (Exception e) {
                        System.err.println(e.getClass().getName() + ": " + e.getMessage());
                        System.exit(0);
                    }
                    if (is_id_right) {

                        try {
                            Class.forName("org.postgresql.Driver");
                            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/endterm", "postgres", "Alalop12");
                            connection.setAutoCommit(false);
                            statement = connection.createStatement();
                            int type_of_employee = 0;
                            String name = null;
                            String surname = null;
                            String country = null;
                            int salary = 0;
                            int employ_id = 0;
                            int shop_id = 0;
                            int comp_id = 0;
                            employ_id = employ_id1;

                            ResultSet resultSet = statement.executeQuery("SELECT type_of_emp FROM Employ WHERE employ_id = " + employ_id1 + "");
                            while (resultSet.next()) {

                                type_of_employee = resultSet.getInt("type_of_emp");

                            }

                            ResultSet resultSet_name = statement.executeQuery("SELECT name_emp FROM employ WHERE employ_id = " + employ_id1 + "");
                            while (resultSet_name.next()) {
                                name = resultSet_name.getString("name_emp");
                            }

                            ResultSet resultSet_surname = statement.executeQuery("SELECT surname_emp FROM employ WHERE employ_id = " + employ_id1 + "");
                            while (resultSet_surname.next()) {
                                surname = resultSet_surname.getString("surname_emp");
                            }

                            ResultSet resultSet_shop_id = statement.executeQuery("SELECT comp_ID  FROM Employ WHERE employ_id = " + employ_id1 + "");
                            while (resultSet_shop_id.next()) {
                                shop_id = resultSet_shop_id.getInt("comp_ID");
                            }
                            ResultSet resultSet_salary = statement.executeQuery("SELECT emp_count FROM salary WHERE employ_id = " + employ_id1 + "");
                            while (resultSet_salary.next()) {
                                salary = resultSet_salary.getInt("emp_count");
                            }
                            ResultSet result_comp_id = statement.executeQuery("SELECT comp_id FROM employ WHERE employ_id = " + employ_id1 + "");

                            if (result_comp_id.next()) {
                                comp_id = result_comp_id.getInt("comp_id");

                                ResultSet result_country = statement.executeQuery("SELECT country FROM company WHERE comp_id = " + comp_id + "");
                                if (result_country.next()) {
                                    country = result_country.getString("country");
                                }
                                // 1-director 2-manager 3-marketolog 4-consultant

                                if (type_of_employee == 1) { //1-director
                                    Createworker createDirector = new CreateDirector();
                                    Worker direc = createDirector.createworker(name, surname, salary, employ_id, shop_id);

                                    System.out.println("Hello,  " + name + " " + surname + ", what you want to do?");
                                    System.out.println("1.Watch my salary" + "\n2.In which country my off-line workplace" + "\n3.Add a new employee" + "\n4.Fire an employee");

                                    int num = sc.nextInt();
                                    if (num == 1) {
                                        direc.getSalary();
                                    } else if (num == 2) {
                                        System.out.println("Your off-line country: " + country);
                                    } else if (num == 3) {
                                        Statement statements = connection.createStatement();
                                        Scanner in = new Scanner(System.in);
                                        String name_emps;
                                        String surname_emp;
                                        int type_of_emp;
                                        int employ_ID_emp;
                                        int comp_ID_emp;
                                        int salary_emp;
                                        System.out.print("Enter name: ");
                                        name_emps = in.nextLine();

                                        System.out.print("Enter surname: ");
                                        surname_emp = in.nextLine();

                                        System.out.print("Enter type: ");
                                        type_of_emp = in.nextInt();

                                        System.out.print("Enter employee's ID: ");
                                        employ_ID_emp = in.nextInt();

                                        System.out.print("Enter company's ID: ");
                                        comp_ID_emp = in.nextInt();

                                        System.out.print("Enter salary: ");
                                        salary_emp = in.nextInt();

                                        statements.executeUpdate("INSERT INTO Employ (name_emp,surname_emp,employ_id,comp_id,type_of_emp) VALUES ('" + name_emps + "','" + surname_emp + "','" + employ_ID_emp + "'," + comp_ID_emp + ",'" + type_of_emp + "');");
                                        statements.executeUpdate("INSERT INTO Salary (emp_count) VALUES ('" + salary_emp + "');");

                                    } else {
                                        System.out.print("You can fired an employee which you hired");
                                        System.out.print("Enter a id of employee which you want to fire: ");
                                        int employ_ID_emp = sc.nextInt();
                                        System.out.println("");
                                        PreparedStatement st = connection.prepareStatement("DELETE FROM employ WHERE employ_id = " + employ_ID_emp + ";");
                                        st.executeUpdate();

                                    }
                                }
                                if (type_of_employee == 2) { //2-manager
                                    Createworker createManager = new CreateManager();
                                    Worker man = createManager.createworker(name, surname, salary, employ_id, shop_id);

                                    System.out.println("Hello, " + name + " " + surname + ", what you want to do?");
                                    System.out.println("1.Watch my salary" + "\n2.In which country my off-line workplace" + "\n3.Change salary for employee");

                                    int num = sc.nextInt();
                                    if (num == 1) {
                                        man.getSalary();
                                    } else if (num == 2) {
                                        System.out.println("Your off-line country: " + country);
                                    } else {
                                        int emp_id;
                                        int new_salary;
                                        System.out.println("enter employee's id");
                                        emp_id = sc.nextInt();
                                        System.out.println("Enter employee's new salary");
                                        new_salary = sc.nextInt();
                                        PreparedStatement st = connection.prepareStatement("UPDATE Salary SET emp_count = " + new_salary + " WHERE employ_id = " + emp_id + ";");
                                        st.executeUpdate();
                                    }
                                }
                                if (type_of_employee == 3) { //3-marketolog

                                    Createworker createMark = new CreateMarketolog();
                                    Worker mark = createMark.createworker(name, surname, salary, employ_id, shop_id);

                                    System.out.println("Hello, " + name + " " + surname + ", what you want to do?");
                                    System.out.println("1.Watch my salary" + "\n2.In which country my off-line workplace" + "\n3.create a request to the director");

                                    int num2 = sc.nextInt();
                                    if (num2 == 1) {
                                        mark.getSalary();
                                    } else if (num2 == 2) {
                                        System.out.println("Your off-line country: " + country);
                                    } else {
                                        Scanner scanner=new Scanner(System.in);
                                        String req;
                                        System.out.print("enter request: ");
                                        req=scanner.nextLine();
                                        Statement statements = connection.createStatement();
                                        statements.executeUpdate("INSERT INTO requests (employ_id,requst) VALUES ('" + employ_id1 + "','" + req + "');");

                                    }
                                }
                                if (type_of_employee == 4) { //4-consultant
                                    Createworker createworkers = new CreateConsultant();
                                    Worker cons = createworkers.createworker(name, surname, salary, employ_id, shop_id);

                                    System.out.println("Hello, " + name + " " + surname + ", what you want to do?");
                                    System.out.println("1.Watch my salary" + "\n2.In which country my off-line workplace");

                                    int num1 = sc.nextInt();
                                    if (num1 == 1) {
                                        cons.getSalary();
                                    } else {
                                        System.out.println("Your off-line country: " + country);
                                    }
                                }
                                resultSet_name.close();
                                resultSet_surname.close();
                                resultSet_shop_id.close();
                                resultSet_salary.close();
                                result_country.close();
                                result_comp_id.close();
                                resultSet.close();
                                statement.close();
                                connection.commit();
                                connection.close();

                            }
                        }
                        catch (Exception e) {
                            System.err.println(e.getClass().getName() + ": " + e.getMessage());
                            System.exit(0);
                        }

                    }

                    is_continue = false;
                    break;

                /*Cutomer*/
                case 2:

                //provider
                case 3:



            }
        }
    }
}

