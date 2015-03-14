package com.example.bodik13.duplom15;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Bodik13 on 13.03.2015.
 */
public class ConnectBD {
        Connection conn = null;
        String url = "jdbc:mysql://185.28.20.227/";
        String dbName = "u575490322_data";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "u575490322_user";
        String password = "qwerty123";

    public void setConn() {

            try {
                Class.forName(driver).newInstance();
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            try {
                conn = DriverManager.getConnection(url + dbName, userName, password);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.out.println("Connected to the database");
            try {
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.out.println("Disconnected from database");

    }


}