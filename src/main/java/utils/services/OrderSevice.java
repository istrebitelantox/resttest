package utils.services;

import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import pojos.UserPojoFull;

import java.util.List;

import static io.restassured.RestAssured.given;

public class OrderSevice extends RestService {

    @Override
    protected String getBasePath() {
        return "/orders";
    }

    public OrderSevice(Cookies cookies) {
        super(cookies);
    }

    public List<UserPojoFull> getOrders(){
        return RestAssured.given().spec(REQ_SPEC)
                .get()
                .jsonPath().getList("data", UserPojoFull.class);
    }
}
