package Campus;

import Campus.Model.FeesClass;
import Campus.Model.FieldsClass;
import Campus.Model.GradeLevelClass;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GradeLevelTest extends Parent{

    String glID;
    String glName;
    String glShortName;
    int glOrder;

    public String getRandomName() {
        return RandomStringUtils.randomAlphabetic(8).toLowerCase();
    }
    public int getRandomInt(){ return (int) (Math.random() * 10)+1;}

    @Test
    public void createGL() {
        glName = getRandomName();
        glShortName = getRandomName();
        glOrder = getRandomInt();

        GradeLevelClass glC = new GradeLevelClass();
        glC.setSchoolId("6390f3207a3bcb6a7ac977f9");
        glC.setName(glName);
        glC.setShortName(glShortName);
        glC.setOrder(glOrder);
        glID=
                given()

                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(glC)


                        .when()
                        .post("school-service/api/grade-levels")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
        ;

    }

    @Test(dependsOnMethods = "createGL")
    public void createGLNegative() {
        glName = getRandomName();
        glShortName = getRandomName();
        glOrder = getRandomInt();

        GradeLevelClass glC = new GradeLevelClass();
        glC.setSchoolId("6390f3207a3bcb6a7ac977f9");
        glC.setName(glName);
        glC.setShortName(glShortName);
        glC.setOrder(glOrder);
        glC.setId(glID);
        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(glC)


                .when()
                .post("school-service/api/grade-levels")
                .then()
                .log().body()
                .statusCode(400)
                .body("name",equalTo(null))
        ;

    }
    @Test(dependsOnMethods = "createGLNegative")
    public void updateGL() {
        glName = getRandomName();
        glShortName = getRandomName();
        glOrder = getRandomInt();

        GradeLevelClass glC = new GradeLevelClass();
        glC.setSchoolId("6390f3207a3bcb6a7ac977f9");
        glC.setName(glName);
        glC.setShortName(glShortName);
        glC.setOrder(glOrder);
        glC.setId(glID);
        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(glC)


                .when()
                .put("school-service/api/grade-levels")
                .then()
                .log().body()
                .statusCode(200)
                .body("name",equalTo(glName))
        ;


    }

    @Test(dependsOnMethods = "updateGL")
    public void deleteGL() {

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("glID",glID)
                //  .log().uri()
                .when()
                .delete("school-service/api/grade-levels/{glID}")
                .then()
                .log().body()
                .statusCode(200)

        ;


    }

    @Test(dependsOnMethods = "deleteGL")
    public void deleteGLNegative() {

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("glID",glID)
                //  .log().uri()
                .when()
                .delete("school-service/api/grade-levels/{glID}")
                .then()
                .log().body()
                .statusCode(400)

        ;


    }
    @Test(dependsOnMethods = "deleteGL")
    public void updateGLNegative() {
        glName = getRandomName();
        glShortName = getRandomName();
        glOrder = getRandomInt();

        GradeLevelClass glC = new GradeLevelClass();
        glC.setSchoolId("6390f3207a3bcb6a7ac977f9");
        glC.setName(glName);
        glC.setShortName(glShortName);
        glC.setOrder(glOrder);

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(glC)


                .when()
                .put("school-service/api/grade-levels")
                .then()
                .log().body()
                .statusCode(400)
                .body("name",equalTo(null))
        ;


    }
}
