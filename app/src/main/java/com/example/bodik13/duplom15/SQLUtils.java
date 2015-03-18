package com.example.bodik13.duplom15;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Bodik13 on 16.03.2015.
 */
public class SQLUtils {





    public void connect() throws IllegalAccessException, InstantiationException,
            ClassNotFoundException, SQLException {

        Connection conn = null;
        String url = "jdbc:mysql://185.28.20.227/";
        String dbName = "u575490322_data";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "u575490322_user";
        String password = "qwerty123";
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName,userName,password);
            System.out.println("Connected to the database");
            conn.close();
            System.out.println("Disconnected from database");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
