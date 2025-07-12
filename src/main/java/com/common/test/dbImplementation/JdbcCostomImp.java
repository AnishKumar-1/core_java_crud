package com.common.test.dbImplementation;

import com.common.test.connection.JdbcCustom;
import com.common.test.connection.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcCostomImp implements JdbcCustom {

    private final Connection con;

    public JdbcCostomImp(SqlConnection sqlConnection) {
        this.con = sqlConnection.createConnection();
    }


    @Override
    public int saveOne(String book_name, double book_price) {
        String query = "INSERT INTO books(book_name, book_price) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, book_name);
            ps.setDouble(2, book_price);
            return ps.executeUpdate();
        } catch (Exception ex) {
            ex.getStackTrace();
            return 0;
        }
    }


    @Override
    public String updateOne(int book_id, String new_book_name, Double book_price) {
        String query;

        if (new_book_name != null && !new_book_name.isEmpty() && book_price != null) {
            query = "UPDATE books SET book_name=?, book_price=? WHERE id=?";
        } else if (new_book_name != null && !new_book_name.isEmpty()) {
            query = "UPDATE books SET book_name=? WHERE id=?";
        } else if (book_price != null) {
            query = "UPDATE books SET book_price=? WHERE id=?";
        } else {
            return "❌ Nothing to update";
        }

        try (PreparedStatement ps = con.prepareStatement(query)) {
            if (new_book_name != null && !new_book_name.isEmpty() && book_price != null) {
                ps.setString(1, new_book_name);
                ps.setDouble(2, book_price);
                ps.setInt(3, book_id);
            } else if (new_book_name != null && !new_book_name.isEmpty()) {
                ps.setString(1, new_book_name);
                ps.setInt(2, book_id);
            } else {
                ps.setDouble(1, book_price);
                ps.setInt(2, book_id);
            }
            ps.executeUpdate();
            return  "✅ Record updated successfully.";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "❌ Update failed due to an exception.";
        }
    }



    @Override
    public String deleteOne(int id) {
     String query="delete from books where id=?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                return "Record deleted successfully.";
            } else {
                return "No record found with the given ID.";
            }
        } catch (Exception ex) {
            return "something went wrong";
        }
    }

    @Override
    public Book findById(int id) {
        String query = "SELECT * FROM books WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                return new Book(
                        result.getInt("id"),
                        result.getString("book_name"),
                        result.getDouble("book_price")
                );
            } else {
                System.out.println("No book found with id " + id);
                return null;
            }

        } catch (Exception ex) {
            System.out.println("something went wrong..");
            ex.printStackTrace();
            return null;
        }
    }


    @Override
    public boolean existsByBookName(String book_name) {
        String query = "SELECT book_name FROM books WHERE book_name = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, book_name);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();  // if result exists
            }
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<Book> findAll() {
        List<Book> books =new ArrayList<>();
        String query="select *from books";
        try(PreparedStatement ps=con.prepareStatement(query)){
          ResultSet data=ps.executeQuery();
          while(data.next()){
              Book book=new Book(data.getInt("id"),data.getString("book_name")
              ,data.getDouble("book_price"));
              books.add(book);
          }
          return books;
        }catch (Exception ex){
            ex.getStackTrace();
            return null;
        }
    }


}
