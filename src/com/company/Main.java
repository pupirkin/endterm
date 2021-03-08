package com.company;
import java.util.Scanner;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        Connection connection = null;
        Statement statement = null;


        while (true) {
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
                    System.out.println("1.What is my salary?");
                    System.out.println("2.Where do I work?");


                    int choice1 = sc.nextInt();
                    while (choice1 != 1 && choice1 != 2) {
                        System.out.println("Please choose only from possible options 1-2");
                        choice1 = sc.nextInt();
                    }

                    System.out.println("Your ID?");
                    int employ_id1 = sc.nextInt();


                    switch (choice1){
                        case 1:
                            try {
                                Class.forName("org.postgresql.Driver");
                                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/endterm", "postgres", "Z1z2z3123!");
                                connection.setAutoCommit(false);

                                statement = connection.createStatement();

                                ResultSet resultSet = statement.executeQuery("SELECT emp_count FROM salary WHERE employ_id = " + employ_id1 + "");
                                while (resultSet.next()) {
                                    int emp_count = resultSet.getInt("emp_count");


                                    System.out.println("Your salary = " + emp_count);
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
                                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/endterm", "postgres", "Z1z2z3123!");
                                connection.setAutoCommit(false);
                                statement = connection.createStatement();


                                ResultSet resultSet = statement.executeQuery("SELECT comp_id FROM employ WHERE employ_id = " + employ_id1 + "");

                                if (resultSet.next()) {
                                    int comp_id = resultSet.getInt("comp_id");

                                    ResultSet resultSet1 = statement.executeQuery("SELECT country FROM company WHERE comp_id = " + comp_id + "");
                                    if (resultSet1.next()) {
                                        String country = resultSet1.getString("country");
                                        System.out.println("Your country = " + country);
                                        System.out.println();
                                    }
                                }
                                resultSet.close();
                                statement.close();
                                connection.commit();
                                connection.close();
                            }catch(Exception e) {
                                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                                System.exit(0);
                                }

                            break;

                    }

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
                                       connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/endterm", "postgres", "Z1z2z3123!");
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

                                }       catch (Exception e) {
                                        System.err.println(e.getClass().getName() + ": " + e.getMessage());
                                        System.exit(0);
                                      }
                                 break;

                             case 2:
                                 try {
                                     String url = "jdbc:postgresql://localhost:5432/endterm";
                                     String username = "postgres";
                                     String password = "Z1z2z3123!";
                                     Class.forName("org.postgresql.Driver");


                                     System.out.println("Your ID:");
                                     sc.nextLine();
                                     int customer_id1 = sc.nextInt();


                                     int product_id1;
                                     System.out.println("Enter dish ID:");
                                     product_id1=sc.nextInt();

                                     connection = DriverManager.getConnection(url, username, password);
                                     String sql = "INSERT INTO \"shopping_cart\" (customer_id, product_id) VALUES (?,?)";
                                     PreparedStatement preparedStatement = connection.prepareStatement(sql);
                                     preparedStatement.setInt(1, customer_id1);
                                     preparedStatement.setInt(2, product_id1);
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
                                    connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/endterm", "postgres", "Z1z2z3123!");
                                    connection.setAutoCommit(false);
                                    statement = connection.createStatement();


                                    ResultSet resultSet = statement.executeQuery("SELECT customer_id FROM customer WHERE cust_phone = " + cust_number1 + "");
                                    if (resultSet.next()) {
                                        int customer_id1 = resultSet.getInt("customer_id");

                                        ResultSet resultSet1 = statement.executeQuery("SELECT product_id FROM shopping_cart WHERE customer_id = " + customer_id1 + "");
                                        if (resultSet1.next()) {
                                            int product_id1 = resultSet1.getInt("product_id");


                                    ResultSet resultSet2 = statement.executeQuery("SELECT description FROM product WHERE product_id = " + product_id1+ "");
                                    if (resultSet2.next()) {
                                        String description1 = resultSet2.getString("description");


                                    ResultSet resultSet4 = statement.executeQuery("SELECT total_sum FROM shopping_cart WHERE customer_id = " + customer_id1 + "");
                                    if (resultSet4.next()) {
                                        int total_sum1 = resultSet4.getInt("total_sum");

                                        ResultSet resultSet5 = statement.executeQuery("SELECT cust_name FROM customer WHERE customer_id = " + customer_id1 + "");
                                        if (resultSet5.next()) {
                                            String cust_name1 = resultSet4.getString("cust_name");
                                        System.out.println(cust_name1 + " " + "you ordered" + " " + description1 + " " + "at ID:" + " " + product_id1 + " " + "for the sum:" + " " + total_sum1);
                                    }
                                }}}}
                                    resultSet.close();
                                    statement.close();
                                    connection.commit();
                                    connection.close();
                                 }catch (Exception e) {
                                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                                    System.exit(0);
                                }

                                break;

                        }
              /*Provider*/
                case 3:
                    System.out.println("1.Which country should I ship to?");
                    System.out.println("2.Find out the warehouse id where to deliver.");


                    int choice3 = sc.nextInt();
                    while (choice3 != 1 && choice3 != 2) {
                        System.out.println("Please choose only from possible options 1-2");
                        choice1 = sc.nextInt();
                    }

                    System.out.println("Your ID?");
                    int provider_id1 = sc.nextInt();


                    switch (choice3){
                        case 1:
                            try {
                                Class.forName("org.postgresql.Driver");
                                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/endterm", "postgres", "Z1z2z3123!");
                                connection.setAutoCommit(false);

                                statement = connection.createStatement();

                                ResultSet resultSet = statement.executeQuery("SELECT comp_id FROM provider     WHERE provider_id = " + provider_id1 + "");
                                if (resultSet.next()) {
                                    int comp_id1 = resultSet.getInt("comp_id");

                                    ResultSet resultSet1 = statement.executeQuery("SELECT country FROM company     WHERE comp_id = " + comp_id1 + "");
                                    if (resultSet1.next()) {
                                        String country = resultSet1.getString("comp_id");
                                        System.out.println("You must deliver the furniture to the country: " + country);
                                        System.out.println();
                                    }
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
                                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/endterm", "postgres", "Z1z2z3123!");
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
                            }catch(Exception e) {
                                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                                System.exit(0);
                            }

                            break;

                    }

            }


        }
}}



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
