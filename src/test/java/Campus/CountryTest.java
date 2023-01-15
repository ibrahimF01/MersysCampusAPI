package Campus;

import Campus.Model.CountryClass;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class CountryTest extends Parent {

    String countryID;
    String countryName;
    String countryCode;

    @Test
    public void CountryCreate() {
        countryName = getRandomName();
        countryCode = getRandomCode();

        CountryClass cc=new CountryClass();
        cc.setName(countryName);
        cc.setCode(countryCode);
        countryID=
                given()
                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(cc)

                        .when()
                        .post("school-service/api/countries")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
                ;

    }

    @Test(dependsOnMethods = "CountryCreate")
    public void CountryCreateNegative(){

        CountryClass cc=new CountryClass();
        cc.setId(countryID);
        cc.setName(countryName);
        cc.setCode(countryCode);

                given()
                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(cc)


                        .when()
                        .post("school-service/api/countries")

                        .then()
                        .log().body()
                        .statusCode(400)
        ;

    }
    @Test(dependsOnMethods = "CountryCreateNegative")
    public void CountryUpdate() {

        CountryClass cc = new CountryClass();
        cc.setId(countryID);
        cc.setName(countryName);
        cc.setCode(countryCode);

                  given()
                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(cc)
                        .when()
                        .put("school-service/api/countries")

                        .then()
                        .log().body()
                        .statusCode(200)
                        .body("name",equalTo(countryName))
        ;
    }

    @Test(dependsOnMethods = "CountryUpdate")
    public void CountryDelete() {

        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("countryID", countryID)
                .when()
                .delete("school-service/api/countries/{countryID}")

                .then()
                .log().body()
                .statusCode(200)
        ;
    }

    @Test(dependsOnMethods = "CountryDelete")
    public void CountryDeleteNegative() {

        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("countryID", countryID)
                .when()
                .delete("school-service/api/countries/{countryID}")

                .then()
                .log().body()
                .statusCode(400)
        ;
    }

    @Test(dependsOnMethods = "CountryDeleteNegative")
    public void CountryUpdateNegative() {

        CountryClass cc = new CountryClass();
        cc.setId(countryID);
        cc.setName(countryName);
        cc.setCode(countryCode);

        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(cc)
                .when()
                .put("school-service/api/countries")

                .then()
                .log().body()
                .statusCode(400)
                .body("name",equalTo(null))
        ;
    }

    public String getRandomName() {
        return RandomStringUtils.randomAlphabetic(8).toLowerCase();
    }

    public String getRandomCode() {
        return RandomStringUtils.randomAlphabetic(3).toLowerCase();
    }
}