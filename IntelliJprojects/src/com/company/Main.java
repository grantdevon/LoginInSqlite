package com.company;

import java.util.Scanner;

public class Main {

    static DataSource dataSource = new DataSource();

    public static void main(String[] args) {
	// write your code here
        Scanner sc = new Scanner(System.in);

        if (!dataSource.open()){
            System.out.println("cant open datasource");
            return;
        }

        //log in page
        System.out.println("Please choose what you would like to do ");
        System.out.println("1: Login");
        System.out.println("2: Register");
        int choice = sc.nextInt();

        if (choice == 1) {
            DataSource.validate();
        } else if (choice == 2) {
            DataSource.createUser();
        } else {
            System.out.println("Please choose one of the values above");
        }



        dataSource.close();
    }


}
