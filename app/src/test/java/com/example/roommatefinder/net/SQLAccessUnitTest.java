package com.example.roommatefinder.net;
import com.example.roommatefinder.model.AuthToken;
import com.example.roommatefinder.model.User;
import com.example.roommatefinder.model.service.request.DeleteUserRequest;
import com.example.roommatefinder.model.service.request.RegisterRequest;
import com.example.roommatefinder.model.service.response.DeleteUserResponse;

import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class SQLAccessUnitTest {
    @Test
    public void addUserTest() throws SQLException {
        User user = new User("Test", "User", 'm', 30, "testuser@fakeemail.com", "Password",
                "1234567890");
        User userTest = SQLAccess.addEntryToUserTable(new RegisterRequest(user.getFirstName(), user.getLastName(), user.getGender(), user.getAge(),
                user.getEmail(), user.getPassword(), user.getPhoneNumber()));
        assertEquals(user, userTest);
        //Tear Down
        DeleteUserResponse response =  SQLAccess.deleteUser(new DeleteUserRequest(user.getEmail(),"Token"));
        assertTrue(response.isSuccess());
    }
}


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */