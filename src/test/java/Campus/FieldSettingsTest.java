package Campus;

import Campus.Model.FieldSettingsClass;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FieldSettingsTest extends Parent {

    @Test
    public void fieldsSettings(){
        FieldSettingsClass fs =new FieldSettingsClass();
        fs.setId("63b8690460998266b450a805");
        fs.setSchoolId("6390f3207a3bcb6a7ac977f9");
        fs.setFieldId("63b7256cebf97e7d4ea68589");
        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(fs)
                .when()
                .put("school-service/api/entity-field-setting")
                .then()
                .log().body()
                .statusCode(200)
                .body("id",equalTo("63b8690460998266b450a805"))
        ;

    }

}



