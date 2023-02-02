package utils.services;

import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import pojos.CreateUserResponse;
import pojos.UserPojoFull;
import pojos.UserRequest;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserService extends RestService {

    @Override
    protected String getBasePath() {
        return "/users";
    }

    public UserService(Cookies cookies) {
        super(cookies);
    }

    public CreateUserResponse createUser(UserRequest rq)
    {
        return RestAssured.given().spec(REQ_SPEC).body(rq).post().as(CreateUserResponse.class);
    }
    public List<UserPojoFull> getUsers(){
        return RestAssured.given().spec(REQ_SPEC)
                .get()
                .jsonPath().getList("data", UserPojoFull.class);
    }
}
