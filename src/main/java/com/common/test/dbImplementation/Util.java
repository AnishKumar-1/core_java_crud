package com.common.test.dbImplementation;

import com.common.test.connection.JdbcCustom;

import java.util.List;
import java.util.Scanner;

public class Util {

    //delete by id method and get id from console too
    public static void findOne(Scanner sc, JdbcCustom jdbcOps){
        System.out.println("enter book id to get");
        int id=sc.nextInt();
        Book result=jdbcOps.findById(id);
        System.out.println("_________________________________________________");
        System.out.println("book_id "+" "+"book_name "+"book_price");
        System.out.println(result.getId()+" "+result.getName()+" "+result.getPrice());                 // ✅ print it
    }


    //delete by id method and get id from console too
    public static void deletebyId(Scanner sc,JdbcCustom jdbcOps){
        System.out.println("enter book id to delete");
        int book_id=sc.nextInt();
        String result=jdbcOps.deleteOne(book_id);
        System.out.println(result);                 // ✅ print it

    }

    public static void updateRecord(Scanner sc, JdbcCustom jdbcOps) {
        System.out.println("Enter book ID to update:");
        int book_id = sc.nextInt();
        sc.nextLine(); // consume leftover newline

        Book result = jdbcOps.findById(book_id);
        if (result != null) {
            System.out.println("Enter new book name (or leave blank to skip):");
            String new_book_name = sc.nextLine();

            System.out.println("Enter new book price (or 0 to skip):");
            double priceInput = sc.nextDouble();
            Double book_price = (priceInput > 0) ? priceInput : null;

            System.out.println(jdbcOps.updateOne(book_id, new_book_name, book_price));
        }
    }


    //add method
    public static void add(Scanner sc,JdbcCustom jdbcOps){
        String book_name;
        double book_price;



        System.out.println("___________________________________");

        while(true){
            System.out.println("enter book name to add in database");
            book_name =sc.nextLine();
            System.out.println("enter book price to add in database");
            book_price=sc.nextDouble();
            if(jdbcOps.existsByBookName(book_name)){
                System.out.println("book already exists");
                sc.nextLine();
            }else{
                break;
            }
            System.out.println("want to exist? press 0 or 1 for continue");
            int number=sc.nextInt();
            sc.nextLine(); // clear buffer after nextInt
            if (number == 0) break;
        }
        int row=jdbcOps.saveOne(book_name, book_price);
        if(row>0)System.out.println("data saved successfully");
    }


    //find all
    public static void getAll(JdbcCustom jdbcOps){
        List<Book> books = jdbcOps.findAll();
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book b : books) {
                System.out.println(b.getId()+" "+b.getName()+" "+b.getPrice());
            }
        }
    }


}
