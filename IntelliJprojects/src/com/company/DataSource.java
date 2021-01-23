package com.company;

import com.sun.xml.internal.xsom.XSTerm;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class DataSource {
    public static final String DB_NAME = "users.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/grantsolomons/development/IntelliJprojects/"+ DB_NAME;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";


    private static Connection connection;
    static Scanner sc = new Scanner(System.in);

    public boolean open(){
        try{

           connection = DriverManager.getConnection(CONNECTION_STRING);
           return true;
        } catch (SQLException e){
            System.out.println("Could not open connection, " + e.getMessage());
            return false;
        }
    }

    public void close(){
        try{
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e){
            System.out.println("couldn't close connection " + e.getMessage());
        }
    }

    public static void createUser(){
        System.out.println("Register new user ");
        System.out.println("Please enter your name ");
        String name = sc.nextLine();
        System.out.println("Please enter your surname ");
        String surname = sc.nextLine();
        System.out.println("Please enter your username ");
        String username = sc.nextLine();
        System.out.println("Please enter your email ");
        String email = sc.nextLine();
        System.out.println("Please enter your password ");
        String password = sc.nextLine();

        try (Statement statement = connection.createStatement()) {
            statement.execute(new StringBuilder().append("CREATE TABLE IF NOT EXISTS ").append(TABLE_USERS).append(" (").append(COLUMN_NAME).append(" text,").append(COLUMN_SURNAME).append(" text,").append(COLUMN_USERNAME).append(" text,").append(COLUMN_EMAIL).append(" text,").append(COLUMN_PASSWORD).append(" text").append(" )").toString());
            statement.execute("INSERT INTO " + TABLE_USERS + " (" + COLUMN_NAME + ", " + COLUMN_SURNAME +
                    ", " + COLUMN_USERNAME + ", " + COLUMN_EMAIL  +", " + COLUMN_PASSWORD + " )" +
                    "VALUES('" + name  + " ', ' " + surname + "', '" + username + "', '" +email+"', '" +password+"')");

            System.out.println("You have been registered");

        } catch (SQLException e){
            System.out.println("Could not execute query, " + e.getMessage());
        }
    }

    public static void validate(){
        System.out.println("please enter your username\n");
        String checkUsername = sc.nextLine();
        System.out.println("please enter your password");
        String checkPassword = sc.nextLine();

        String query = "SELECT * FROM " + DataSource.TABLE_USERS + " WHERE username = "  +"'"+ checkUsername + "'" + " AND password = " +"'" +
                checkPassword + "'" + ";";

        try(Statement statement = connection.createStatement()) {
            statement.execute(new StringBuilder().append("CREATE TABLE IF NOT EXISTS ").append(TABLE_USERS).append(" (").append(COLUMN_NAME).append(" text,").append(COLUMN_SURNAME).append(" text,").append(COLUMN_USERNAME).append(" text,").append(COLUMN_EMAIL).append(" text,").append(COLUMN_PASSWORD).append(" text").append(" )").toString());
            ResultSet st = statement.executeQuery(query);
            if (!st.next()){
                    System.out.println("Login in failed");
                } else {
                System.out.println("Login success");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
