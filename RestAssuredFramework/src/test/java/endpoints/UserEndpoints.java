package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.UserPayload ;
import static io.restassured.RestAssured.given;

public class UserEndpoints {

    public static Response createUser(UserPayload payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(URLs.post_url);
        return response;
    }


    public static Response readUser(String userName){
        Response response = given()
                .pathParam("username",userName)
                .when()
                .get(URLs.get_url);
        return response;
    }

    public static Response updateUser(String userName, UserPayload payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .pathParam("username",userName)
                .when()
                .put(URLs.update_url);
        return response;
    }


    public static Response deleteUser(String userName){
        Response response = given()
                .pathParam("username",userName)
                .when()
                .delete(URLs.delete_url);
        return response;
    }
}
