package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Constant {
    public static String testEnv;
    public static final String DEFAULT_TEST_ENV = "ppe";
    public static String API_BASE_URL;
    public static String WEB_BASE_URL;
    public static String IP_INFO_URL;
    public static String GRAPHQL_API_URL;
    public static String IP_INFO_TOKEN;
    public static Properties TestDataProperties;

    private static String getTestEnv() {
        String testPropertyValue = System.getProperty("testEnv");
        String testEnvValue = System.getenv("testEnv");

        // First priority will be from the command line then environment value
        if (testPropertyValue != null) {
            return testPropertyValue;
        } else if (testEnvValue != null) {
            return testEnvValue;
        } else {
            return DEFAULT_TEST_ENV;
        }
    }

    public static void setUpTestEnvData() throws IOException {
        testEnv = getTestEnv();
        String filePath = "src/test/resources/";
        TestDataProperties = new Properties();
        File envFile = new File(filePath + testEnv + ".properties");
        FileInputStream fileInputStream = new FileInputStream(envFile);
        TestDataProperties.load(fileInputStream);
        fileInputStream.close();

        WEB_BASE_URL = TestDataProperties.getProperty("web_base_url");
        IP_INFO_URL = TestDataProperties.getProperty("ip_info_api_url");
        IP_INFO_TOKEN = TestDataProperties.getProperty("ip_info_token");
        API_BASE_URL = TestDataProperties.getProperty("reqres_api_url");
        GRAPHQL_API_URL = TestDataProperties.getProperty("graphQL_api_url");
    }
}