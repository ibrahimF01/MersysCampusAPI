package Campus;

import Campus.Model.ClassesClass;
import Campus.Model.FieldsClass;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Classes_Test extends Parent {

    String classesID;
    String classesName;
    String shortName;

    @Test
    public void createClasses() {
        classesName = getRandomName();
        shortName = getRandomShortName();

        ClassesClass cc = new ClassesClass("6390f3207a3bcb6a7ac977f9");
        cc.setName(classesName);
        cc.setShortName(shortName);
        classesID=
                given()

                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(cc)


                        .when()
                        .post("school-service/api/classes")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
        ;


    }

    @Test(dependsOnMethods ="createClasses" )
    public void createClassesNegative() {
        classesName = getRandomName();
        shortName = getRandomShortName();

        ClassesClass cc = new ClassesClass("6390f3207a3bcb6a7ac977f9");
        cc.setName(classesName);
        cc.setShortName(shortName);
        cc.setId(classesID);
                given()

                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(cc)


                        .when()
                        .post("school-service/api/classes")
                        .then()
                        .log().body()
                        .statusCode(400)

        ;


    }
    @Test(dependsOnMethods ="createClasses" )
    public void editClasses() {
        classesName = getRandomName();
        shortName = getRandomShortName();

        ClassesClass cc = new ClassesClass("6390f3207a3bcb6a7ac977f9");
        cc.setName(classesName);
        cc.setShortName(shortName);
        cc.setId(classesID);
        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(cc)


                .when()
                .put("school-service/api/classes")
                .then()
                .log().body()
                .statusCode(200)

        ;


    }
    @Test(dependsOnMethods ="editClasses" )
    public void deleteClasses() {

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("classesID",classesID)


                .when()
                .delete("school-service/api/classes/{classesID}")
                .then()
                .log().body()
                .statusCode(200)

        ;


    }
    @Test(dependsOnMethods ="deleteClasses" )
    public void deleteClassesNegative() {

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("classesID",classesID)


                .when()
                .delete("school-service/api/classes/{classesID}")
                .then()
                .log().body()
                .statusCode(400)

        ;


    }
    @Test(dependsOnMethods ="deleteClasses" )
    public void editClassesNegative() {
        classesName = getRandomName();
        shortName = getRandomShortName();

        ClassesClass cc = new ClassesClass("6390f3207a3bcb6a7ac977f9");
        cc.setName(classesName);
        cc.setShortName(shortName);
        cc.setId(classesID);
        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(cc)


                .when()
                .put("school-service/api/classes")
                .then()
                .log().body()
                .statusCode(400)

        ;


    }
    public String getRandomName() {
        return RandomStringUtils.randomAlphabetic(8).toLowerCase();
    }

    public String getRandomShortName() {
        return RandomStringUtils.randomAlphabetic(3).toLowerCase();
    }

}
