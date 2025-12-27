package com.qacart.todo.apis;

import com.qacart.todo.models.User;
import com.qacart.todo.utils.ConfigUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserApi {
    private static UserApi userApi;

    private UserApi(){}
    public static UserApi getInstance(){
        if(userApi==null){
            userApi=new UserApi();
        }
        return userApi;
    }
   public Response register(User user){
       //Given When then
           return given()
                   .baseUri(ConfigUtils.getInstance().getBaseUrl())
                   .contentType(ContentType.JSON)
                   .body(user)
                   .when()
                   .post("/api/v1/users/register")
                   .then()
                   .extract().response();

       }
}
