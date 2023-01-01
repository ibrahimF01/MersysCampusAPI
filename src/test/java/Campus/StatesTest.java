package Campus;

import Campus.Model.StatesClass;
import Campus.Model.StatesCountryClass;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StatesTest extends Parent {
    String StatesID;
    String StatesName;
    String StatesShortName;

    @Test
    public void createStates() {
        StatesName = getRandomName();
        StatesShortName = getRandomShortName();

        StatesClass sc = new StatesClass();
        sc.setName(StatesName);
        sc.setShortName(StatesShortName);
        sc.setCountry(new StatesCountryClass("63a45bdbcb75ee5c2199a8cf"));
        StatesID =
                given()

                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(sc)


                        .when()
                        .post("school-service/api/states")
                        .then()
                        //.log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
        ;


    }

    @Test(dependsOnMethods = "createStates")
    public void createStatesNegative() {
        StatesName = getRandomName();
        StatesShortName = getRandomShortName();

        StatesClass sc = new StatesClass();
        sc.setId(StatesID);
        sc.setName(StatesName);
        sc.setShortName(StatesShortName);
        sc.setCountry(new StatesCountryClass("63a45bdbcb75ee5c2199a8cf"));
        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(sc)


                .when()
                .post("school-service/api/states")
                .then()
                //.log().body()
                .statusCode(400)
                .body("name", equalTo(null))
        ;


    }

    @Test(dependsOnMethods = "createStatesNegative")
    public void EditStates() {
        StatesName = getRandomName();
        StatesShortName = getRandomShortName();

        StatesClass sc = new StatesClass();
        sc.setId(StatesID);
        sc.setName(StatesName);
        sc.setShortName(StatesShortName);
        sc.setCountry(new StatesCountryClass("63a45bdbcb75ee5c2199a8cf"));
        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(sc)


                .when()
                .put("school-service/api/states")
                .then()
                //.log().body()
                .statusCode(200)
                .body("name", equalTo(StatesName))
        ;


    }

    @Test(dependsOnMethods = "EditStates")
    public void DeleteStates() {

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("StatesID", StatesID)
                .when()
                .delete("school-service/api/states/{StatesID}")
                .then()
                //.log().body()
                .statusCode(200)

        ;


    }

    @Test(dependsOnMethods = "DeleteStates")
    public void DeleteStatesNegative() {

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("StatesID", StatesID)
                .when()
                .delete("school-service/api/states/{StatesID}")
                .then()
                //.log().body()
                .statusCode(400)
        //There is a bug here. It deleting the deleted item again.
        //status code should be 400 but it is returning 200
        //Disclosure report sent to developer.

        ;


    }

    @Test(dependsOnMethods = "DeleteStates")
    public void EditStatesNegative() {
        StatesName = getRandomName();
        StatesShortName = getRandomShortName();

        StatesClass sc = new StatesClass();
        sc.setId(StatesID);
        sc.setName(StatesName);
        sc.setShortName(StatesShortName);
        sc.setCountry(new StatesCountryClass("63a45bdbcb75ee5c2199a8cf"));
        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(sc)


                .when()
                .put("school-service/api/states")
                .then()
                //.log().body()
                .statusCode(400)
                //There is a bug here. It editing the edited item again.
                //status code should be 400 but it is returning 200
                //Disclosure report sent to developer.
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
