package utility.graphQlUtility;

import base.Constant;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GraphQLCommonSteps {
    ExtentTest extentTest;

    public GraphQLCommonSteps(ExtentTest extentTest) {
        this.extentTest = extentTest;
    }

    public Response sendGraphQLQuery(GraphQLEndpoints graphQLEndpoints, QueryVariables variables) {
        GraphQLQueryBuilder graphQLQueryBuilder = new GraphQLQueryBuilder();
        graphQLQueryBuilder.setQuery(setQueryTemplate(graphQLEndpoints));
        graphQLQueryBuilder.setVariables(variables);
        extentTest.info("GraphQL request: <br>" + graphQLQueryBuilder);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(graphQLQueryBuilder).post(Constant.GRAPHQL_API_URL);
        extentTest.info("GraphQL response:");
        extentTest.info(MarkupHelper.createCodeBlock(response.asString(), CodeLanguage.JSON));
        return response;
    }

    private String setQueryTemplate(GraphQLEndpoints endpoint) {
        return switch (endpoint) {
            case COUNTRIES -> GraphQLQueryBodyTemplates.countriesQueryBody();
            case LANGUAGES -> GraphQLQueryBodyTemplates.languagesQueryBody();
        };
    }
}
