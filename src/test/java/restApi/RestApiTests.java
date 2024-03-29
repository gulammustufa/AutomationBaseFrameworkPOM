package restApi;

import base.BaseTest;
import base.Constant;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utility.ApiEndPoints;
import utility.restApiUtility.RestApiBody;
import utility.restApiUtility.RestApiUtility;

import static org.assertj.core.api.Assertions.assertThat;

public class RestApiTests extends BaseTest implements ApiEndPoints {

    @Test(testName = "Get users api test", groups = {"api", "restApi"})
    public void testGetUsersRestApi() {
        RestApiUtility restApiUtility = new RestApiUtility(extentTest);
        Response response = restApiUtility.callGetApi(Constant.API_BASE_URL + ReqresApiEndPoint.GET_USERS_END_POINT);
        String perPageData = response.jsonPath().getString("per_page");
        int dataArraySize = JsonPath.<Integer>read(response.asString(), "$.data.length()");
        assertThat(dataArraySize).isEqualTo(Integer.parseInt(perPageData));
    }

    @Test(testName = "Get user api test", groups = {"api", "restApi"})
    public void testGetUserRestApi() {
        RestApiUtility restApiUtility = new RestApiUtility(extentTest);
        int userId = 1;
        Response response = restApiUtility.callGetApi(Constant.API_BASE_URL + ReqresApiEndPoint.GET_USER_END_POINT + userId);
        int actualUserId = JsonPath.<Integer>read(response.asString(), "$.data.id");
        assertThat(actualUserId).isEqualTo(userId);
    }

    @Test(testName = "Create user api", groups = {"api", "restApi"})
    public void testCreateUserRestApi() {
        RestApiUtility restApiUtility = new RestApiUtility(extentTest);
        String email = "eve.holt@reqres.in";
        String password = "pistol";
        String queryBody = RestApiBody.getCreateUserBody(email, password);
        Response response = restApiUtility.callPostApiWithoutToken(Constant.API_BASE_URL + ReqresApiEndPoint.CREATE_USER_END_POINT, queryBody);

        String createdUserEmail = response.jsonPath().getString("email");
        assertThat(createdUserEmail).isEqualTo(email);

        String createdUserId = response.jsonPath().getString("id");
        extentTest.info("Created User Id: " + createdUserId);
        assertThat(createdUserId).isNotEmpty();
    }
}
