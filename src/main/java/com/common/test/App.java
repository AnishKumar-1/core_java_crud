package com.common.test;

import com.common.test.connection.JdbcCustom;
import com.common.test.connection.SqlConnection;
import com.common.test.dbImplementation.Book;
import com.common.test.dbImplementation.JdbcCostomImp;
import com.common.test.dbImplementation.SqlConnectionImp;
import com.common.test.dbImplementation.Util;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        SqlConnection sqlConn = new SqlConnectionImp();
        JdbcCustom jdbcOps = new JdbcCostomImp(sqlConn);
        Scanner sc=new Scanner(System.in);

        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("1. See all books");
            System.out.println("2. Add a book");
            System.out.println("3. delete a book");
            System.out.println("4. find a book by its id");
            System.out.println("5. update a book by its id");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    Util.getAll(jdbcOps);
                    break;

                case 2:
                    Util.add(sc,jdbcOps);
                    break;

                case 3:
                    Util.deletebyId(sc,jdbcOps);
                    break;
                case 4:
                    Util.findOne(sc,jdbcOps);
                    break;
                case 5:
                    Util.updateRecord(sc,jdbcOps);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println(); // for clean spacing
        }

    }






}
