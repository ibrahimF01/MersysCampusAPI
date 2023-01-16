package Campus;

import Campus.Model.LocationClass;
import Campus.Model.StatesClass;
import Campus.Model.StatesCountryClass;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LocationTest extends Parent{
    String LocationID;
    String LocationName;
    String LocationShortName;




    @Test
    public void createLocation() {
        LocationName = getRandomName();
        LocationShortName = getRandomShortName();

        LocationClass lc = new LocationClass();
        lc.setName(LocationName);
        lc.setShortName(LocationShortName);
        LocationID =
                given()

                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(lc)


                        .when()
                        .post("school-service/api/location")
                        .then()
                        //.log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
        ;


    }

    @Test(dependsOnMethods = "createLocation")
    public void createLocationNegative() {
        LocationName = getRandomName();
        LocationShortName = getRandomShortName();

        LocationClass lc = new LocationClass();
        lc.setId(LocationID);
        lc.setName(LocationName);
        lc.setShortName(LocationShortName);

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(lc)


                .when()
                .post("school-service/api/location")
                .then()
                //.log().body()
                .statusCode(400)
                .body("name", equalTo(null))
        ;


    }

    @Test(dependsOnMethods = "createLocationNegative")
    public void EditLocation() {
        LocationName = getRandomName();
        LocationShortName = getRandomShortName();

        LocationClass lc = new LocationClass();
        lc.setId(LocationID);
        lc.setName(LocationName);
        lc.setShortName(LocationShortName);

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(lc)


                .when()
                .put("school-service/api/location")
                .then()
                //.log().body()
                .statusCode(200)
                .body("name", equalTo(LocationName))
        ;


    }

    @Test(dependsOnMethods = "EditLocation")
    public void Deletelocation() {

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("LocationID",LocationID)
                .when()
                .delete("school-service/api/location/{LocationID}")
                .then()
                .statusCode(200);
    }
    @Test(dependsOnMethods = "Deletelocation")
    public void DeleteLocationNegative() {

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("LocationID", LocationID)
                .when()
                .delete("school-service/api/location{LocationID}")
                .then()
                .statusCode(400);

    }

    @Test(dependsOnMethods = "DeleteLocationNegative")
    public void EditLocationNegative() {
        LocationName = getRandomName();
        LocationShortName = getRandomShortName();

        LocationClass lc = new LocationClass();
        lc.setId(LocationID);
        lc.setName(LocationName);
        lc.setShortName(LocationShortName);

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(lc)


                .when()
                .put("school-service/api/location")
                .then()
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




