package com.example.roommatefinder.net;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;

import com.example.roommatefinder.model.service.request.LoginRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

public class SQLAccess {
    private static String ip = "192.168.1.4"; //ip address of sql server
    private static String port = "1433";
    private static String ClassName = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "RoommateFinderDB";
    private static String username = "test";
    private static String password = "test";
    private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;
    private static Connection conn = null;

        private static void establishConnection() {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                Class.forName(ClassName);
                conn = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static boolean addEntryToAuthTokenTable(LoginRequest request, String generatedToken) throws SQLException {
            establishConnection();
            if (conn != null) {
                Statement statement = conn.createStatement();
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO AuthToken (Token, Email, TimeCreated)" +
                        "VALUES(?,?,?);");
                preparedStatement.setString(1,generatedToken);
                preparedStatement.setString(2, request.getEmail());
                preparedStatement.setTime(3, new Time(System.currentTimeMillis()));
                int result = preparedStatement.executeUpdate();
                if (result != 0) {
                    return true;
                }
                else {
                    return false;
                }
//                ResultSet resultSet = statement.executeQuery("Select * FROM AuthToken;");
//                while(resultSet.next()) {
//                    System.out.println(resultSet.getString(1));
//                }
            }
            return false;
        }

}
