package Campus;

import Campus.Model.FeesClass;
import Campus.Model.FieldsClass;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FeesTest extends Parent{

    String FeesID;
    String FeesName;
    int code;
    int priority;
    int integrationCode;

    public String getRandomName() {
        return RandomStringUtils.randomAlphabetic(8).toLowerCase();
    }

    public String getRandomCode() {
        return RandomStringUtils.randomAlphabetic(8).toLowerCase();
    }

    public int getRandomInt(){ return (int) (Math.random() * 10)+1;}

    @Test
    public void createFees() {
        FeesName = getRandomName();
        code = getRandomInt();
        integrationCode = getRandomInt();
        priority = getRandomInt();

        FeesClass feesC = new FeesClass();
        feesC.setPriority(priority);
        feesC.setBudgetAccountIntegrationCode(integrationCode);
        feesC.setName(FeesName);
        feesC.setCode(code);
        FeesID=
                given()

                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(feesC)


                        .when()
                        .post("school-service/api/fee-types")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
        ;


    }
    @Test(dependsOnMethods = "createFees")
    public void createFeesNegative() {
        FeesName = getRandomName();
        code = getRandomInt();
        integrationCode = getRandomInt();
        priority = getRandomInt();

        FeesClass feesC = new FeesClass();
        feesC.setPriority(priority);
        feesC.setBudgetAccountIntegrationCode(integrationCode);
        feesC.setName(FeesName);
        feesC.setCode(code);
        feesC.setId(FeesID);
        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(feesC)


                .when()
                .post("school-service/api/fee-types")
                .then()
                .log().body()
                .statusCode(400)
                .body("name",equalTo(null))
        ;


    }

    @Test(dependsOnMethods = "createFees")
    public void updateFees() {
        FeesName = getRandomName();
        code = getRandomInt();
        integrationCode = getRandomInt();
        priority = getRandomInt();

        FeesClass feesC = new FeesClass();
        feesC.setPriority(priority);
        feesC.setBudgetAccountIntegrationCode(integrationCode);
        feesC.setName(FeesName);
        feesC.setCode(code);
        feesC.setId(FeesID);
        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(feesC)


                .when()
                .put("school-service/api/fee-types")
                .then()
                .log().body()
                .statusCode(200)
                .body("name",equalTo(FeesName))
        ;


    }
    @Test(dependsOnMethods = "updateFees")
    public void deleteFees() {

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("FeesID",FeesID)
                //  .log().uri()
                .when()
                .delete("school-service/api/fee-types/{FeesID}")
                .then()
                .log().body()
                .statusCode(200)

        ;


    }
    @Test(dependsOnMethods = "deleteFees")
    public void deleteFieldsNegative() {

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("FeesID",FeesID)
                //  .log().uri()
                .when()
                .delete("school-service/api/fee-types/{FeesID}")
                .then()
                .log().body()
                .statusCode(400)

        ;


    }
    @Test(dependsOnMethods = "deleteFees")
    public void updateFieldsNegative() {
        FeesName = getRandomName();
        code = getRandomInt();
        integrationCode = getRandomInt();
        priority = getRandomInt();

        FeesClass feesC = new FeesClass();
        feesC.setPriority(priority);
        feesC.setBudgetAccountIntegrationCode(integrationCode);
        feesC.setName(FeesName);
        feesC.setCode(code);

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(feesC)


                .when()
                .put("school-service/api/fee-types")
                .then()
                .log().body()
                .statusCode(400)
                .body("name",equalTo(null))
        ;


    }
}
