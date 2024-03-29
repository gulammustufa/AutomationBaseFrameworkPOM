package graphQL;

import base.BaseTest;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utility.graphQlUtility.GraphQLCommonSteps;
import utility.graphQlUtility.GraphQLEndpoints;
import utility.graphQlUtility.QueryVariables;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GraphQLTests extends BaseTest {
    @Test(testName = "GraphQL: Search Api")
    public void testSearchGraphQlApi() {
        String countryCode = "IN";
        String currency = "INR";

        GraphQLCommonSteps graphQLCommonSteps = new GraphQLCommonSteps(extentTest);
        Response response = graphQLCommonSteps.sendGraphQLQuery(GraphQLEndpoints.COUNTRIES, QueryVariables.builder().countryCode(countryCode).currency((currency)).build());

        var actualCountry = JsonPath.<List<String>>read(response.asString(), "$.data.countries[*].code");
        assertThat(actualCountry).contains(countryCode);
    }

    @Test(testName = "GraphQL: Language Api")
    public void testLanguageGraphQlApi(){
        String languageCode = "hi";
        GraphQLCommonSteps graphQLCommonSteps = new GraphQLCommonSteps(extentTest);
        Response response = graphQLCommonSteps.sendGraphQLQuery(GraphQLEndpoints.LANGUAGES, QueryVariables.builder().languageCode(languageCode).build());
        var actualLanguageCode = response.jsonPath().getString("data.languages[0].code");
        assertThat(actualLanguageCode).isEqualTo(languageCode);
    }


}
