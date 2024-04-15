package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import payloads.UserPayload;
import endpoints.UserEndpoints;
import utilities.DataProviders;

public class TestsUsingDataDriven {

    @Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProviders.class)
    public void testPostUser(String userID, String userName, String firstName, String lastName, String userEmail, String pwd, String ph){
        UserPayload userPayload = new UserPayload();

        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstname(firstName);
        userPayload.setLastname(lastName);
        userPayload.setEmail(userEmail);
        userPayload.setPassword(pwd);
        userPayload.setPhone(ph);

        Response response = UserEndpoints.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String userName){
        Response response = UserEndpoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
