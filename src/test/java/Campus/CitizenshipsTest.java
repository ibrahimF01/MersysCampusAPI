package Campus;

import Campus.Model.CitizenshipsClass;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CitizenshipsTest extends Parent {
    String CitizenshipsID;
    String CitizenshipsName;
    String CitizenshipsShortName;

    @Test
    public void createCitizenships() {
        CitizenshipsName = getRandomName();
        CitizenshipsShortName = getRandomShortName();

        CitizenshipsClass cc = new CitizenshipsClass();
        cc.setName(CitizenshipsName);
        cc.setShortName(CitizenshipsShortName);
        CitizenshipsID =
                given()

                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(cc)


                        .when()
                        .post("school-service/api/citizenships")
                        .then()
                        //.log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
        ;


    }

    @Test(dependsOnMethods = "createCitizenships")
    public void createCitizenshipsNegative() {
        CitizenshipsName = getRandomName();
        CitizenshipsShortName = getRandomShortName();

        CitizenshipsClass cc = new CitizenshipsClass();
        cc.setId(CitizenshipsID);
        cc.setName(CitizenshipsName);
        cc.setShortName(CitizenshipsShortName);
        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(cc)


                .when()
                .post("school-service/api/citizenships")
                .then()
                //.log().body()
                .statusCode(400)
                .body("name", equalTo(null))
        ;


    }

    @Test(dependsOnMethods = "createCitizenshipsNegative")
    public void EditCitizenships() {
        CitizenshipsName = getRandomName();
        CitizenshipsShortName = getRandomShortName();

        CitizenshipsClass cc = new CitizenshipsClass();
        cc.setId(CitizenshipsID);
        cc.setName(CitizenshipsName);
        cc.setShortName(CitizenshipsShortName);
        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(cc)


                .when()
                .put("school-service/api/citizenships")
                .then()
                //.log().body()
                .statusCode(200)
                .body("name", equalTo(CitizenshipsName))
        ;


    }

    @Test(dependsOnMethods = "EditCitizenships")
    public void DeleteCitizenships() {

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("CitizenshipsID", CitizenshipsID)
                .when()
                .delete("school-service/api/citizenships/{CitizenshipsID}")
                .then()
                //.log().body()
                .statusCode(200)

        ;


    }

    @Test(dependsOnMethods = "DeleteCitizenships")
    public void DeleteCitizenshipsNegative() {

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("CitizenshipsID", CitizenshipsID)
                .when()
                .delete("school-service/api/citizenships/{CitizenshipsID}")
                .then()
                //.log().body()
                .statusCode(400)

        ;


    }

    @Test(dependsOnMethods = "DeleteCitizenships")
    public void EditCitizenshipsNegative() {
        CitizenshipsName = getRandomName();
        CitizenshipsShortName = getRandomShortName();

        CitizenshipsClass cc = new CitizenshipsClass();
        cc.setId(CitizenshipsID);
        cc.setName(CitizenshipsName);
        cc.setShortName(CitizenshipsShortName);
        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(cc)


                .when()
                .put("school-service/api/citizenships")
                .then()
                //.log().body()
                .statusCode(400)
                .body("name", equalTo(null))
        ;


    }

    public String getRandomName() {
        return RandomStringUtils.randomAlphabetic(8).toLowerCase();
    }

    public String getRandomShortName() {
        return RandomStringUtils.randomAlphabetic(3).toLowerCase();
    }
}
