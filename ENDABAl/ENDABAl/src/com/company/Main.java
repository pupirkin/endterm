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
                    System.out.println("1.Order list");
                    System.out.println("2.Make a purchase");
                    System.out.println("3.View the shopping cart");

                    int choice2 = sc.nextInt();
                    while (choice2 != 1 && choice2 != 2 && choice2 != 3) {
                        System.out.println("Please choose only from possible options 1-3");
                        choice2 = sc.nextInt();
                    }

                    System.out.println("Your phone number?");
                    int cust_number1 = sc.nextInt();

                    switch (choice2) {
                        case 1:
                            try {
                                Class.forName("org.postgresql.Driver");
                                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/endterm", "postgres", "Alalop12");
                                connection.setAutoCommit(false);

                                statement = connection.createStatement();

                                ResultSet resultSet = statement.executeQuery("SELECT * FROM product;");
                                while (resultSet.next()) {
                                    int id = resultSet.getInt("product_id");
                                    String name = resultSet.getString("description");

                                    System.out.println("ID = " + id);
                                    System.out.println("NAME = " + name);
                                    System.out.println();
                                }


                                resultSet.close();
                                statement.close();
                                connection.commit();
                                connection.close();

                            } catch (Exception e) {
                                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                                System.exit(0);
                            }
                            break;

                        case 2:
                            try {
                                String url = "jdbc:postgresql://localhost:5432/endterm";
                                String username = "postgres";
                                String password = "Alalop12";
                                Class.forName("org.postgresql.Driver");


                                System.out.println("Your ID:");
                                sc.nextLine();
                                int customer_id1 = sc.nextInt();


                                int product_id1;
                                System.out.println("Enter dish ID:");
                                product_id1=sc.nextInt();

                                int cart_id1;
                                System.out.println("Enter your bank card:");
                                cart_id1=sc.nextInt();

                                connection = DriverManager.getConnection(url, username, password);
                                String sql = "INSERT INTO \"shopping_cart\" (cart_id,customer_id, product_id) VALUES (?,?,?)";
                                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                                preparedStatement.setInt(2, customer_id1);
                                preparedStatement.setInt(3, product_id1);
                                preparedStatement.setInt(1, cart_id1);

                                preparedStatement.execute();
                            }


                            catch (Exception ex) {
                                System.out.println("Connection failed...");
                                System.out.println(ex);
                            }
                            break;
                        case 3:
                            try {
                                Class.forName("org.postgresql.Driver");
                                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/endterm", "postgres", "Alalop12");
                                connection.setAutoCommit(false);
                                statement = connection.createStatement();

                                int customer_id1=0;
                                int product_id1=0;
                                String description1=null;
                                int total_sum1=0;
                                ResultSet resultSet = statement.executeQuery("SELECT customer_id FROM customer WHERE cust_phone = " + cust_number1 + "");

                                while (resultSet.next()) {
                                    customer_id1 = resultSet.getInt("customer_id");

                                }
                                resultSet.close();

                                    ResultSet resultSet1 = statement.executeQuery("SELECT product_id FROM shopping_cart WHERE customer_id = " + customer_id1 + "");

                                    while (resultSet1.next()) {

                                        product_id1 = resultSet1.getInt("product_id");
                                    }

                                        ResultSet resultSet2 = statement.executeQuery("SELECT description FROM product WHERE product_id = " + product_id1 + "");
                                        while (resultSet2.next()) {
                                            description1 = resultSet2.getString("description");}



                                                ResultSet resultSet5 = statement.executeQuery("SELECT cust_name FROM customer WHERE customer_id = " + customer_id1 + "");
                                                while (resultSet5.next()) {
                                                    String cust_name1 = resultSet5.getString("cust_name");
                                                    System.out.println(cust_name1 + " " + "you ordered" + " " + description1 + " " + "at ID:" + " " + product_id1);}



                                statement.close();
                                connection.commit();
                                connection.close();
                            } catch (Exception e) {
                                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                                System.exit(0);
                            }

                    }
                    is_continue = false;
                    break;
                //provider
                case 3:
                    System.out.println("1.Which country should I ship to?");
                    System.out.println("2.Find out the warehouse id where to deliver.");


                    int choice3 = sc.nextInt();
                    while (choice3 != 1 && choice3 != 2) {
                        int choice1;
                        System.out.println("Please choose only from possible options 1-2");
                        choice1 = sc.nextInt();
                    }

                    System.out.println("Your ID?");
                    int provider_id1 = sc.nextInt();


                    switch (choice3) {
                        case 1:
                            try {
                                Class.forName("org.postgresql.Driver");
                                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/endterm", "postgres", "Alalop12");
                                connection.setAutoCommit(false);

                                statement = connection.createStatement();
                                int comp_id1=0;
                                ResultSet resultSet = statement.executeQuery("SELECT comp_id FROM provider WHERE provider_id = " + provider_id1+";");
                                if (resultSet.next()) {
                                    comp_id1 = resultSet.getInt("comp_id");}

                                    ResultSet resultSet1 = statement.executeQuery("SELECT country FROM company WHERE comp_id = " + comp_id1);
                                    if (resultSet1.next()) {
                                        String country = resultSet1.getString("country");
                                        System.out.println("You must deliver the furniture to the country: " + country);
                                        System.out.println();


                                }


                                resultSet.close();
                                statement.close();
                                connection.commit();
                                connection.close();

                            } catch (Exception e) {
                                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                                System.exit(0);
                            }
                            break;
                        case 2:
                            try {
                                Class.forName("org.postgresql.Driver");
                                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/endterm", "postgres", "Alalop12");
                                connection.setAutoCommit(false);
                                statement = connection.createStatement();


                                ResultSet resultSet = statement.executeQuery("SELECT warehouse_id FROM product WHERE provider_id = " + provider_id1 + "");

                                if (resultSet.next()) {
                                    int warehouse_id1 = resultSet.getInt("warehouse_id");

                                    System.out.println("You must put in the warehouse ID: " + warehouse_id1);
                                    System.out.println();
                                }

                                resultSet.close();
                                statement.close();
                                connection.commit();
                                connection.close();
                            } catch (Exception e) {
                                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                                System.exit(0);
                            }



                    }
                    is_continue=false;
                    break;


            }
        }
    }
}


//                        String url = "jdbc:postgresql://localhost:5432/endterm";
//                        String username = "postgres";
//                        String password = "Z1z2z3123!";
//                        Class.forName("org.postgresql.Driver");
//
//
//                        System.out.println("Your Name:");
//                        sc.nextLine();
//                        String name = sc.nextLine();
//
//                        System.out.println("Your Phone number:");
//                        String phone_number = sc.nextLine();
//
//                        int dish_id;
//                        System.out.println("Enter dish ID:");
//                        dish_id=sc.nextInt();
//
//                        int count;
//                        System.out.println("How much do you want?");
//                        sc.nextLine();
//                        count=sc.nextInt();
//
//                        connection = DriverManager.getConnection(url, username, password);
//                        String sql = "INSERT INTO \"Orders\" (order_id, customer_name, customer_phone, count1) VALUES (?,?,?,?)";
//                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//                        preparedStatement.setString(2, name);
//                        preparedStatement.setString(3, phone_number);
//                        preparedStatement.setInt(1, dish_id);
//                        preparedStatement.setInt(4, count);
//                        preparedStatement.execute();
//                    }
//
//
//                    catch (Exception ex) {
//                        System.out.println("Connection failed...");
//                        System.out.println(ex);
//                case 3:
//                    System.out.println("Your Name:");
//                    sc.nextLine();
//                    String name = sc.nextLine();
//
//                    System.out.println("Your Phone number:");
//                    String phone_number = sc.nextLine();
//
//                    PreparedStatement ps = connection.prepareStatement("SELECT order_id, count1 FROM \"Orders\" WHERE customer_name = ? and customer_phone = ?");
//                    ps.setString(1, "'"+name+"'");
//                    ps.setString(2, "'"+phone_number+"'");
//                    int order_id = 0, count = 0;
//                    ResultSet rs = ps.executeQuery();
//
//                    while (rs.next()){
//                        order_id = rs.getInt(1);
//                        count = rs.getInt(2);
//                    }
//
//                    PreparedStatement ps2 = connection.prepareStatement("SELECT \"product_id\" FROM \"Order_details\" WHERE order_id = ?");
//                    ps2.setInt(1, order_id);
//                    int product_id = 0;
//                    ResultSet rs2 = ps2.executeQuery();
//
//                    while(rs2.next()) {
//                        product_id = rs2.getInt(1);
//                    }
//
//                    PreparedStatement ps3 = connection.prepareStatement("SELECT \"price\" FROM \"Product\" WHERE product_id = ?");
//                    ps3.setInt(1, product_id);
//                    int price = 0;
//                    ResultSet rs3 = ps3.executeQuery();
//
//                    if(rs3.next()) {
//                        price = rs3.getInt(1);
//                    }
//
//                    System.out.println(price+" "+count);
//                    System.out.println(name+" the cost of your order is:"+(price*count));

//                case 0:
//                    System.exit(0);
//                    break;





//                    try {
//                        Class.forName("org.postgresql.Driver");
//                        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/endterm", "postgres", "Z1z2z3123!");
//                        connection.setAutoCommit(false);
//
//                        statement = connection.createStatement();
//
//                        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"Product\";");
//                        while (resultSet.next()) {
//                            int id = resultSet.getInt("product_id");
//                            String name = resultSet.getString("product_name");
//                            int price = resultSet.getInt("price");
//
//                            System.out.println("ID = " + id);
//                            System.out.println("NAME = " + name);
//                            System.out.println("Price = " + price);
//                            System.out.println();
//                        }
//                        resultSet.close();
//                        statement.close();
//                        connection.commit();
//                        connection.close();
//                    } catch (Exception e) {
//                        System.err.println(e.getClass().getName() + ": " + e.getMessage());
//                        System.exit(0);
//                    }
// break;
