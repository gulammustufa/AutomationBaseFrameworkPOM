package restApi;

import base.BaseTest;
import base.Constant;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utility.restApiUtility.RestApiUtility;

import static org.assertj.core.api.Assertions.assertThat;

public class IpInfoApiTests extends BaseTest{

    @Test(testName = "Ip info api test", groups = {"api", "restApi", "IpInfoApi"})
    public void ipInfoApiTest() {
        RestApiUtility restApiUtility = new RestApiUtility(extentTest);
        Response response = restApiUtility.callPostApiWithToken(Constant.IP_INFO_URL, Constant.IP_INFO_TOKEN);
        String userIp = response.jsonPath().getString("ip");
        assertThat(userIp).isNotEmpty();
    }
}
