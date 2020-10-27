package com.example.roommatefinder.net;

import android.os.StrictMode;

import com.example.roommatefinder.model.User;
import com.example.roommatefinder.model.service.request.ChangeUserRequest;
import com.example.roommatefinder.model.service.request.DeleteUserRequest;
import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.model.service.request.RegisterRequest;
import com.example.roommatefinder.model.service.response.ChangeUserResponse;
import com.example.roommatefinder.model.service.response.DeleteUserResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        public static User addEntryToUserTable(RegisterRequest request) throws SQLException {
            establishConnection();
            if (conn != null) {
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO [User] (Email, Password, Phonenumber, Gender, FirstName, LastName, Age)" +
                        "VALUES(?,?,?,?,?,?,?);");
                preparedStatement.setString(1, request.getEmail());
                preparedStatement.setString(2, request.getPassword());
                preparedStatement.setString(3, request.getPhoneNumber());
                preparedStatement.setString(4, request.getGender().toString());
                preparedStatement.setString(5, request.getFirstName());
                preparedStatement.setString(6, request.getLastName());
                preparedStatement.setInt(7, request.getAge());
                int result = preparedStatement.executeUpdate();
                if (result != 0) {
                    return new User(request.getFirstName(), request.getLastName(), request.getGender(), request.getAge(), request.getEmail(), request.getPassword(), request.getPhoneNumber());
                }
                else {
                    return null;
                }
            }
            return null;
        }

        public static User queryUser(LoginRequest request) throws SQLException {
            establishConnection();
            if (conn != null) {
                //this doesn't work right now, syntax error
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM [User] WHERE Email = ?");
                preparedStatement.setString(1, request.getEmail());

                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()) {
                    String email = resultSet.getString(1);
                    String phoneNumber = resultSet.getString(3);
                    String gender = resultSet.getString(4);
                    String firstName = resultSet.getString(5);
                    String lastName = resultSet.getString(6);
                    Integer age = resultSet.getInt(7);
                    return new User(firstName, lastName, gender.charAt(0), age, email, null, phoneNumber);
                }
            }
            return null;
        }

        public static ChangeUserResponse updateUser(ChangeUserRequest request) throws SQLException {
            establishConnection();
            if (conn != null) {
                PreparedStatement preparedStatement = conn.prepareStatement("UPDATE [User] SET Password = ?, Phonenumber = ?, FirstName = ?, LastName = ?, Age = ?");
                preparedStatement.setString(1, request.getPassword());
                preparedStatement.setString(2, request.getPhoneNumber());
                preparedStatement.setString(3, request.getFirstName());
                preparedStatement.setString(4, request.getLastName());
                preparedStatement.setInt(5, request.getAge());
                int result = preparedStatement.executeUpdate();
                if (result != 0) {
                    return new ChangeUserResponse(true);
                }
                else {
                    return new ChangeUserResponse(false);
                }
            }
            return new ChangeUserResponse(false);
        }

        public static DeleteUserResponse deleteUser(DeleteUserRequest request) throws SQLException {
            establishConnection();
            if (conn != null) {
                PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM [User] WHERE Email = ?");
                preparedStatement.setString(1, request.getEmail());
                int result = preparedStatement.executeUpdate();
                if (result != 0) {
                    return new DeleteUserResponse(true);
                }
                else {
                    return new DeleteUserResponse(false);
                }
            }
            return new DeleteUserResponse(false);
        }


}
