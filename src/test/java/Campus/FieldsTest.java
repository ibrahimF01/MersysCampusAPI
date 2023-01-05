package Campus;

import Campus.Model.FieldsClass;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FieldsTest extends Parent {

    String fieldID;
    String fieldName;
    String fieldCode;

    @Test
    public void createFields() {
        fieldName = getRandomName();
        fieldCode = getRandomCode();

        FieldsClass fc = new FieldsClass();
        fc.setType("STRING");
        fc.setSchoolId("5fe07e4fb064ca29931236a5");
        fc.setName(fieldName);
        fc.setCode(fieldCode);
        fieldID=
                given()

                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(fc)


                        .when()
                        .post("school-service/api/entity-field")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
        ;


    }

    @Test(dependsOnMethods = "createFields")
    public void createFieldsNegative() {
        fieldName = getRandomName();
        fieldCode = getRandomCode();

        FieldsClass fc = new FieldsClass();
        fc.setType("STRING");
        fc.setSchoolId("5fe07e4fb064ca29931236a5");
        fc.setId(fieldID);
        fc.setName(fieldName);
        fc.setCode(fieldCode);

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(fc)


                .when()
                .post("school-service/api/entity-field")
                .then()
                 .log().body()
                .statusCode(400)
                .body("name",equalTo(null))
        ;


    }
    @Test(dependsOnMethods = "createFields")
    public void updateFields() {
        fieldName = getRandomName();
        fieldCode = getRandomCode();

        FieldsClass fc = new FieldsClass();
        fc.setType("STRING");
        fc.setSchoolId("5fe07e4fb064ca29931236a5");
        fc.setId(fieldID);
        fc.setName(fieldName);
        fc.setCode(fieldCode);

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(fc)


                .when()
                .put("school-service/api/entity-field")
                .then()
                .log().body()
                .statusCode(200)
                .body("name",equalTo(fieldName))
        ;


    }

    @Test(dependsOnMethods = "updateFields")
    public void deleteFields() {

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("fieldID",fieldID)
              //  .log().uri()
                .when()
                .delete("school-service/api/entity-field/{fieldID}")
                .then()
                .log().body()
                .statusCode(204)

        ;


    }
    @Test(dependsOnMethods = "deleteFields")
    public void deleteFieldsNegative() {

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("fieldID",fieldID)
                //  .log().uri()
                .when()
                .delete("school-service/api/entity-field/{fieldID}")
                .then()
                 .log().body()
                .statusCode(400)

        ;


    }
    @Test(dependsOnMethods = "deleteFields")
    public void updateFieldsNegative() {
        fieldName = getRandomName();
        fieldCode = getRandomCode();

        FieldsClass fc = new FieldsClass();
        fc.setType("STRING");
        fc.setSchoolId("5fe07e4fb064ca29931236a5");
        fc.setId(fieldID);
        fc.setName(fieldName);
        fc.setCode(fieldCode);

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(fc)


                .when()
                .put("school-service/api/entity-field")
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



