import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojos.CreateUserResponse;
import pojos.UserPojoFull;
import pojos.UserRequest;
import utils.RestWrapper;
import utils.UserGenerator;

import static org.assertj.core.api.Assertions.assertThat;

public class RestTest {
    private static RestWrapper api;

    @BeforeClass
    public static void prepareClient()
    {
        api=RestWrapper.loginAs("eve.holt@reqres.in","cityslicka");
    }

    @Test
    public void getUser()
    {
       assertThat(api.user.getUsers()).extracting(UserPojoFull::getEmail).contains("george.bluth@reqres.in");
    }
    @Test
    public void createUser()
    {
        UserRequest rq= UserGenerator.getSimpleUser();
        CreateUserResponse rs=api.user.createUser(rq);

        assertThat(rs)
                .isNotNull()
                .extracting(CreateUserResponse::getName)
                .isEqualTo(rq.getName());
    }
}
