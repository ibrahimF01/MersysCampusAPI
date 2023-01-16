package Campus;

import Campus.Model.CountryClass;
import Campus.Model.NationalitiesClass;
import Campus.Parent;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class NationalitiesTest extends Parent {

    String nationalityID;
    String nationalityName;

    @Test
    public void nationalitiesCreate() {
        nationalityName = getRandomName();

        NationalitiesClass nc=new NationalitiesClass();
        nc.setName(nationalityName);

        nationalityID=
                given()
                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(nc)

                        .when()
                        .post("school-service/api/nationality")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
        ;

    }
    @Test(dependsOnMethods = "nationalitiesCreate")
    public void nationalitiesCreateNegative(){

        NationalitiesClass nc=new NationalitiesClass();
        nc.setId(nationalityID);
        nc.setName(nationalityName);


        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(nc)

                .when()
                .post("school-service/api/nationality")

                .then()
                .log().body()
                .statusCode(400)
        ;

    }
    @Test(dependsOnMethods = "nationalitiesCreateNegative")
    public void nationalitiesUpdate() {

        NationalitiesClass nc=new NationalitiesClass();
        nc.setId(nationalityID);
        nc.setName(nationalityName);

        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(nc)
                .when()
                .put("school-service/api/nationality")

                .then()
                .log().body()
                .statusCode(200)
                .body("name",equalTo(nationalityName))
        ;
    }

    @Test(dependsOnMethods = "nationalitiesUpdate")
    public void nationalitiesDelete() {

        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("nationalityID", nationalityID)
                .when()
                .delete("school-service/api/nationality/{nationalityID}")

                .then()
                .log().body()
                .statusCode(200)
        ;
    }

    @Test(dependsOnMethods = "nationalitiesDelete")
    public void nationalitiesDeleteNegative() {

        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("nationalityID", nationalityID)
                .when()
                .delete("school-service/api/nationality/{nationalityID}")

                .then()
                .log().body()
                .statusCode(400)
        ;
    }
    @Test(dependsOnMethods = "nationalitiesDeleteNegative")
    public void nationalitiesUpdateNegative() {

        NationalitiesClass nc=new NationalitiesClass();
        nc.setId(nationalityID);
        nc.setName(nationalityName);

        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(nc)
                .when()
                .put("school-service/api/nationality")

                .then()
                .log().body()
                .statusCode(400)
                .body("name",equalTo(null))
        ;
    }

    public String getRandomName() {
        return RandomStringUtils.randomAlphabetic(8).toLowerCase();
    }
}
