package Campus;

import Campus.Model.GradingSchemeClass;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GradingSchemeTest extends Parent{
    String gradingSchemeID;
    String gradingSchemeName;

    @Test
    public void createGradingScheme() {
        gradingSchemeName = getRandomName();

        GradingSchemeClass gc = new GradingSchemeClass();
        gc.setSchoolId("6390f3207a3bcb6a7ac977f9");
        gc.setName(gradingSchemeName);
        gc.setGradeRanges();

        gradingSchemeID=
                given()

                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(gc)
                        .when()
                        .post("school-service/api/grading-schemes")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
        ;

        System.out.println("gradingSchemeID = " + gradingSchemeID);
    }
    public static String getRandomName() {
        return RandomStringUtils.randomAlphabetic(8).toLowerCase();
    }
    public static String getRandomDiscription() {
        return RandomStringUtils.randomAlphabetic(10).toLowerCase();
    }
}
