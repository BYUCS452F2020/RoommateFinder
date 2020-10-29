package com.example.roommatefinder.net;

import android.os.StrictMode;

import com.example.roommatefinder.model.AuthToken;
import com.example.roommatefinder.model.Location;
import com.example.roommatefinder.model.Posting;
import com.example.roommatefinder.model.Preference;
import com.example.roommatefinder.model.Rating;
import com.example.roommatefinder.model.User;
import com.example.roommatefinder.model.service.request.ChangeUserRequest;
import com.example.roommatefinder.model.service.request.CreateLocationRequest;
import com.example.roommatefinder.model.service.request.CreatePostingRequest;
import com.example.roommatefinder.model.service.request.CreatePreferenceRequest;
import com.example.roommatefinder.model.service.request.CreateRatingRequest;
import com.example.roommatefinder.model.service.request.DeleteUserRequest;
import com.example.roommatefinder.model.service.request.GetAuthTokenRequest;
import com.example.roommatefinder.model.service.request.LocationRequest;
import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.model.service.request.LogoutRequest;
import com.example.roommatefinder.model.service.request.PostingsRequest;
import com.example.roommatefinder.model.service.request.PreferenceRequest;
import com.example.roommatefinder.model.service.request.RatingsRequest;
import com.example.roommatefinder.model.service.request.RegisterRequest;
import com.example.roommatefinder.model.service.request.UpdateAuthTokenRequest;
import com.example.roommatefinder.model.service.response.ChangeUserResponse;
import com.example.roommatefinder.model.service.response.CreateLocationResponse;
import com.example.roommatefinder.model.service.response.CreatePostingResponse;
import com.example.roommatefinder.model.service.response.CreatePreferenceResponse;
import com.example.roommatefinder.model.service.response.CreateRatingResponse;
import com.example.roommatefinder.model.service.response.DeleteUserResponse;
import com.example.roommatefinder.model.service.response.GetAuthTokenResponse;
import com.example.roommatefinder.model.service.response.LocationResponse;
import com.example.roommatefinder.model.service.response.LogoutResponse;
import com.example.roommatefinder.model.service.response.PostingsResponse;
import com.example.roommatefinder.model.service.response.PreferenceResponse;
import com.example.roommatefinder.model.service.response.RatingsResponse;
import com.example.roommatefinder.model.service.response.RegisterResponse;
import com.example.roommatefinder.model.service.response.UpdateAuthTokenResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

        public static GetAuthTokenResponse queryAuthToken(GetAuthTokenRequest request) throws SQLException {
            establishConnection();
            if (conn != null) {
                //this doesn't work right now, syntax error
                if (request.getToken() != null) {
                    PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM [AuthToken] WHERE Token = ?");
                    preparedStatement.setString(1, request.getToken().getAuthToken());

                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        String token = resultSet.getString(1);
                        String email = resultSet.getString(2);
                        Time timeCreated = resultSet.getTime(3);
                        return new GetAuthTokenResponse(new AuthToken(token, email, timeCreated));
                    }
                } else if (request.getEmail() != null) {
                    PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM [AuthToken] WHERE Email = ?");
                    preparedStatement.setString(1, request.getEmail());

                    ResultSet resultSet = preparedStatement.executeQuery();
                    List<AuthToken> authTokens = new LinkedList<>();
                    while (resultSet.next()) {
                        String token = resultSet.getString(1);
                        String email = resultSet.getString(2);
                        Time timeCreated = resultSet.getTime(3);
                        authTokens.add(new AuthToken(token, email, timeCreated));
                    }
                    return new GetAuthTokenResponse(authTokens);
                }
            }
            return new GetAuthTokenResponse(false);
        }

        public static UpdateAuthTokenResponse updateAuthToken(UpdateAuthTokenRequest request) throws SQLException {
            establishConnection();
            if (conn != null) {
                PreparedStatement preparedStatement = conn.prepareStatement("UPDATE [AuthToken] SET TimeCreated = ?, Token = ? WHERE Token = ?");
                preparedStatement.setTime(1, new Time(System.currentTimeMillis()));
                preparedStatement.setString(2, request.getNewAuthToken());
                preparedStatement.setString(3, request.getOldAuthToken());
                int result = preparedStatement.executeUpdate();
                if (result != 0) {
                    return new UpdateAuthTokenResponse(new AuthToken(request.getNewAuthToken()));
                }
                else {
                    return new UpdateAuthTokenResponse(false);
                }
            }
            return new UpdateAuthTokenResponse(false);
        }

        public static LogoutResponse deleteAuthToken(LogoutRequest request) throws SQLException {
            establishConnection();
            if (conn != null) {
                PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM [AuthToken] WHERE Token = ?");
                preparedStatement.setString(1, request.getToken());
                int result = preparedStatement.executeUpdate();
                if (result != 0) {
                    return new LogoutResponse(true);
                }
                else {
                    return new LogoutResponse(false);
                }
            }
            return new LogoutResponse(false);
        }

        public static void deleteAllAuthToken() throws SQLException {
            establishConnection();
            if (conn != null) {
                PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM [AuthToken]");
                int result = preparedStatement.executeUpdate();

            }
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
                    String password = resultSet.getString(2);
                    String phoneNumber = resultSet.getString(3);
                    String gender = resultSet.getString(4);
                    String firstName = resultSet.getString(5);
                    String lastName = resultSet.getString(6);
                    Integer age = resultSet.getInt(7);
                    return new User(firstName, lastName, gender.charAt(0), age, email, password, phoneNumber);
                }
            }
            return null;
        }

        public static ChangeUserResponse updateUser(ChangeUserRequest request) throws SQLException {
            establishConnection();
            if (conn != null) {
                PreparedStatement preparedStatement = conn.prepareStatement("UPDATE [User] SET Password = ?, Phonenumber = ?, FirstName = ?, LastName = ?, Age = ? WHERE Email = ?");
                preparedStatement.setString(1, request.getPassword());
                preparedStatement.setString(2, request.getPhoneNumber());
                preparedStatement.setString(3, request.getFirstName());
                preparedStatement.setString(4, request.getLastName());
                preparedStatement.setInt(5, request.getAge());
                preparedStatement.setString(6, request.getEmail());
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

        public static DeleteUserResponse deleteAllUsers() throws SQLException {
            establishConnection();
            if (conn != null) {
                PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM [User]");
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

        public static CreatePostingResponse insertPostingIntoPostingTable(CreatePostingRequest request) throws SQLException {
            establishConnection();
            if (conn != null) {
                Posting posting = request.getPosting();

                //Delete any existing posing a user might have. Users can only have one postings at a time.
                PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM [Posting] WHERE Email = ?");
                preparedStatement.setString(1, posting.getUser().getEmail());
                preparedStatement.executeUpdate();

                //Insert the new posting into the Posting Table.
                preparedStatement = conn.prepareStatement("INSERT INTO [Posting] (Email, Country, State, City, StreetName, BuildingNumber, ApartmentNumber)" +
                        "VALUES(?,?,?,?,?,?,?)");

                preparedStatement.setString(1, posting.getUser().getEmail());
                preparedStatement.setString(2, posting.getCountry());
                preparedStatement.setString(3, posting.getState());
                preparedStatement.setString(4, posting.getCity());
                preparedStatement.setString(5, posting.getStreet());
                preparedStatement.setInt(6, posting.getBuildNum());
                preparedStatement.setInt(7, posting.getRoomNum());
                int result = preparedStatement.executeUpdate();
                if (result != 0) {
                    return new CreatePostingResponse(posting);
                }
                else {
                    return new CreatePostingResponse("Failed to insert posting into the database.");
                }
            }

            return new CreatePostingResponse("Failed to establish a connection for adding a posting.");
        }

        public static PostingsResponse queryAllPostings(PostingsRequest request) throws SQLException {
            establishConnection();
            if (conn != null) {
                User responseUser;
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM [Posting]");
                ResultSet resultSet = preparedStatement.executeQuery();
                ArrayList<Posting> postings = new ArrayList<>();
                while (resultSet.next()) {
                    String email = resultSet.getString(1);
                    String country = resultSet.getString(2);
                    String state = resultSet.getString(3);
                    String city = resultSet.getString(4);
                    String streetName = resultSet.getString(5);
                    Integer buildingNumber = resultSet.getInt(6);
                    Integer apartmentNumber = resultSet.getInt(7);

                    responseUser = new User(null,null, null, 0, email, null, null);

                    postings.add(new Posting(responseUser, country, state, city, streetName, buildingNumber, apartmentNumber));
                }

                return new PostingsResponse(postings);
            }

            return new PostingsResponse("Failed to establish a connection for querying postings.");
        }

        public static CreatePreferenceResponse createPreference(CreatePreferenceRequest request) throws SQLException {
            establishConnection();
            if (conn != null) {
                Preference preference = request.getPreference();

                //Delete any existing preference a user might have. Users can only have one preference at a time.
                //Note: a preference is an object that stores multiple preferences in real life.
                PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM [Preference] WHERE Email = ?");
                preparedStatement.setString(1, preference.getEmail());
                preparedStatement.executeUpdate();

                //Insert the new preference into the Preference Table.
                preparedStatement = conn.prepareStatement("INSERT INTO [Preference] (Email, TimeToSleep, SocialLevel, Cleanliness, Price, Type, Contract)" +
                        "VALUES(?,?,?,?,?,?,?)");

                preparedStatement.setString(1, preference.getEmail());
                preparedStatement.setInt(2, preference.getPreferredTimeToSleep());
                preparedStatement.setInt(3, preference.getSocialLevel());
                preparedStatement.setInt(4, preference.getCleanlinessLevel());
                preparedStatement.setDouble(5, preference.getPrice());
                preparedStatement.setString(6, preference.getType());
                preparedStatement.setString(7, preference.getLengthOfContract());
                int result = preparedStatement.executeUpdate();
                if (result != 0) {
                    return new CreatePreferenceResponse(true);
                }
                else {
                    return new CreatePreferenceResponse(false);
                }
            }

            return new CreatePreferenceResponse(false);
        }

        public static PreferenceResponse queryPreference(PreferenceRequest request) throws SQLException{
            establishConnection();
            if (conn != null) {
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM [Preference] WHERE Email = ?");
                preparedStatement.setString(1, request.getEmail());

                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()) {
                    String email = resultSet.getString(1);
                    Integer timeToSleep = resultSet.getInt(2);
                    Integer socialLevel = resultSet.getInt(3);
                    Integer cleanliness = resultSet.getInt(4);
                    Double price = resultSet.getDouble(5);
                    String type = resultSet.getString(6);
                    String contract = resultSet.getString(7);

                    return new PreferenceResponse(new Preference(email, timeToSleep, socialLevel, cleanliness, price, type, contract), true);
                }
            }

            return new PreferenceResponse(null, false);
        }

    public static CreateLocationResponse createLocation(CreateLocationRequest request) throws SQLException{
        establishConnection();
        if(conn != null){
            Location location = request.getLocation();

            //Delete any existing location a user might have. Users can only have one location at a time.
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM [Location] WHERE Email = ?");
            preparedStatement.setString(1,location.getUsername());
            preparedStatement.executeUpdate();

            preparedStatement = conn.prepareStatement("INSERT INTO [Location] (Email, Country, State, City, StreetName, BuildingNumber, ApartmentNumber)" +
                    "VALUES(?,?,?,?,?,?,?)");

            preparedStatement.setString(1, location.getUsername());
            preparedStatement.setString(2, location.getCountry());
            preparedStatement.setString(3, location.getState());
            preparedStatement.setString(4, location.getCity());
            preparedStatement.setString(5, location.getStreetName());
            preparedStatement.setInt(6, location.getBuildingNumber());
            preparedStatement.setInt(7, location.getRoomNumber());
            int result = preparedStatement.executeUpdate();

            if(result != 0){
                return new CreateLocationResponse(true);
            }
            else{
                return new CreateLocationResponse(false);
            }

        }

        return new CreateLocationResponse(false);
    }

    public static LocationResponse queryLocation(LocationRequest request) throws  SQLException{
        establishConnection();
        if(conn != null){
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM [Location] WHERE Email = ?");
            preparedStatement.setString(1, request.getUsername());

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                String email = resultSet.getString(1);
                String country = resultSet.getString(2);
                String state = resultSet.getString(3);
                String city = resultSet.getString(4);
                String streetName = resultSet.getString(5);
                Integer buildingNum = resultSet.getInt(6);
                Integer roomNum = resultSet.getInt(7);

                return new LocationResponse(new Location(email, country, state, city, streetName, buildingNum, roomNum));
            }
        }
        return new LocationResponse("Failed to establish a connection for querying locations.");
    }

    public static CreateRatingResponse createRating(CreateRatingRequest request) throws SQLException{
        establishConnection();
        if(conn != null){
            Rating rating = request.getRating();
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO [Rating] (RatingID, Email, RatingGiver, Score, Comment)" +
                    "VALUES (?,?,?,?,?);");

            preparedStatement.setString(1, rating.getRatingID());
            preparedStatement.setString(2, rating.getUsername());
            preparedStatement.setString(3, rating.getRatingGiver());
            preparedStatement.setInt(4, rating.getScore());
            preparedStatement.setString(5, rating.getComment());
            int result = preparedStatement.executeUpdate();

            if(result != 0){
                return new CreateRatingResponse(rating);
            }
            else{
                return new CreateRatingResponse("Failed to insert a rating");
            }
        }

        return new CreateRatingResponse("Failed to establish a connection for creating a rating");
    }

    public static RatingsResponse queryRatings(RatingsRequest request) throws SQLException{
        establishConnection();
        if(conn != null){
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM [Rating] WHERE Email = ?");
            preparedStatement.setString(1, request.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Rating> ratingList = new ArrayList<>();
            Rating tempRating;

            while(resultSet.next()){
                String ratingID = resultSet.getString(1);
                String email = resultSet.getString(2);
                String ratingGiver = resultSet.getString(3);
                Integer score = resultSet.getInt(4);
                String comment = resultSet.getString(5);

                tempRating = new Rating(ratingID, email, ratingGiver, score, comment);
                ratingList.add(tempRating);
            }

            return new RatingsResponse(ratingList);
        }
        return new RatingsResponse("Failed to establish connection for querying a rating");
    }
    
}
