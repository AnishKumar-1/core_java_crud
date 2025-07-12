package com.common.test.connection;

import com.common.test.dbImplementation.Book;

import java.util.List;

public interface JdbcCustom {
    int saveOne(String book_name, double book_price);
    String updateOne(int book_id,String new_book_name,Double book_price);
    String deleteOne(int id);
    Book findById(int id);
    boolean existsByBookName(String book_name);
    List<Book> findAll();
}
