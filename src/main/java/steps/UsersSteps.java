package steps;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojos.CreateUserResponse;
import pojos.UserPojoFull;
import pojos.UserRequest;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UsersSteps {
    private static final RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri("https://reqres.in/api")
                    .setBasePath("/users")
                    .setContentType(ContentType.JSON)
                    .build();

    private CreateUserResponse user;
    public CreateUserResponse createUser(UserRequest rq){
        user = RestAssured.given().spec(REQ_SPEC).body(rq).post().as(CreateUserResponse.class);
        return user;
    }
    public static UserPojoFull getUser(int id){
        return  RestAssured.given().spec(REQ_SPEC).get("/" + id).as(UserPojoFull.class);

    }
    public static List<UserPojoFull> getUsers(){
        return RestAssured.given().spec(REQ_SPEC)
                .get()
                .jsonPath().getList("data", UserPojoFull.class);
    }



}
