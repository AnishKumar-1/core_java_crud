package com.common.test.dbImplementation;

import com.common.test.connection.SqlConnection;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class SqlConnectionImp implements SqlConnection {

    Properties props = new Properties();

    //add to db jdbc implementation
    @Override
    public Connection createConnection() {
        try {
            FileInputStream fis = new FileInputStream("db.properties");
            props.load(fis);

            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            return DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            return null;  // required since method must return Connection
        }
    }

}
