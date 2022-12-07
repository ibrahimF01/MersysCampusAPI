package Campus;

import Constants.CommonUtils;
import Constants.ConstantsAPI;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class LoginTest {
    Cookies cookies;

    @BeforeClass
    public void loginCampus() {
        CommonUtils.loadProperties();
        baseURI = ConstantsAPI.baseUrl;

        Map<String, String> credential = new HashMap<>();
        credential.put("username", ConstantsAPI.userName);
        credential.put("password", ConstantsAPI.password);
        credential.put("rememberMe", "true");

        cookies=
                given()
                        .contentType(ContentType.JSON)
                        .body(credential)

                        .when()
                        .post("auth/login")

                        .then()
                        //.log().cookies()
                        .statusCode(200)
                        .extract().response().getDetailedCookies()
        ;
    }




    @Test
    public void Test01() {
        System.out.println("Test passed");
    }
}
