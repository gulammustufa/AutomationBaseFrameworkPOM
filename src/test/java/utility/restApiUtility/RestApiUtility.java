package utility.restApiUtility;

import base.Constant;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestApiUtility {
    ExtentTest extentTest;

    public RestApiUtility(ExtentTest extentTest) {
        this.extentTest = extentTest;
    }

    public Response callGetApi(String getApiUrl) {
        extentTest.info("Get Api Request: " + getApiUrl);
        Response response = given().get(getApiUrl);
        extentTest.info("Api Response:");
        extentTest.info(MarkupHelper.createCodeBlock(response.asString(), CodeLanguage.JSON));
        return response;
    }

    public Response callPostApiWithoutToken(String postApiUrl, String queryBody) {
        extentTest.info("Post Api Query Body: " + queryBody);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(queryBody)
                .post(postApiUrl);
        extentTest.info("Api Response:");
        extentTest.info(MarkupHelper.createCodeBlock(response.asString(), CodeLanguage.JSON));
        return response;
    }

    public Response callPostApiWithToken(String postApiUrl, String token, String queryBody) {
        extentTest.info("Post Api Query Body: " + queryBody);
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(queryBody)
                .post(postApiUrl);
        extentTest.info("Api Response:");
        extentTest.info(MarkupHelper.createCodeBlock(response.asString(), CodeLanguage.JSON));
        return response;
    }

    public Response callPostApiWithToken(String postApiUrl, String token) {
        extentTest.info("Post Api Url: " + Constant.IP_INFO_URL);
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .post(postApiUrl);
        extentTest.info("Api Response:");
        extentTest.info(MarkupHelper.createCodeBlock(response.asString(), CodeLanguage.JSON));
        return response;
    }
}
