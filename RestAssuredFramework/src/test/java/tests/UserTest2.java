package tests;

import com.github.javafaker.Faker;
import endpoints.UserEndpoints;
import endpoints.UserEndpointsURLFromFile;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import payloads.UserPayload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class UserTest2 {
    Faker faker;
    UserPayload userPayload;

    public Logger logger; //For Logs
    @BeforeClass
    public void setup(){
        faker = new Faker();
        userPayload = new  UserPayload();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //Logs
        logger = (Logger) LogManager.getLogger(this.getClass()); // As the Same class name will log the file
        logger.debug("Debugging..............."); // To know when rerun
    }

    @Test(priority = 1)
    public void testPostUser(){
        logger.info("************* Creating User *****************");

        Response response = UserEndpointsURLFromFile.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("************* User is Created *****************");
    }

    @Test(priority = 2)
    public void  testGetUserByName(){
        logger.info("************* Reading User Info *****************");

        Response response = UserEndpointsURLFromFile.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("************* User Info is Displayed *****************");
    }

    @Test(priority = 3)
    public void  testUpdateUserByName(){
        logger.info("************* Updating User *****************");

        //Update data using payload
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndpointsURLFromFile.updateUser(this.userPayload.getUsername(),userPayload);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);

        //Checking the data after update
        Response responseAfterUpdate = UserEndpointsURLFromFile.readUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("************* User is Updated *****************");
    }

    @Test(priority = 4)
    public void  testDeleteUserByName(){
        logger.info("************* Deleting User *****************");

        Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("************* User is Deleted *****************");
    }

}
