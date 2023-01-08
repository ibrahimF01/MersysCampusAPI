package Campus;

import Campus.Model.ClassesClass;
import Campus.Model.DocumentTypesClass;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DocumentType extends Parent{
    String documentsID;
    String DocumentName;
    String Description;
    String[] attachmentstates={"EXAMINATION"};

    @Test
    public void CreateDocumentType() {
        DocumentName = getRandomName();
        Description = getRandomDescription();

        DocumentTypesClass dc = new DocumentTypesClass(attachmentstates,"6390f3207a3bcb6a7ac977f9");
        dc.setName(DocumentName);
        dc.setDescription(Description);
        documentsID=
                given()

                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(dc)


                        .when()
                        .post("school-service/api/attachments/create")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
        ;


    }
    @Test(dependsOnMethods = "CreateDocumentType")
    public void CreateDocumentTypeNegative() {
        DocumentName = getRandomName();
        Description = getRandomDescription();

        DocumentTypesClass dc = new DocumentTypesClass(attachmentstates,"6390f3207a3bcb6a7ac977f9");
        dc.setName(DocumentName);
        dc.setDescription(Description);
        dc.setId(documentsID);
        given()
                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(dc)


                        .when()
                        .post("school-service/api/attachments/create")
                        .then()
                        .log().body()
                        .statusCode(400)

        ;


    }

    @Test(dependsOnMethods ="CreateDocumentType" )
    public void EditDocumentType() {
        DocumentName = getRandomName();
        Description = getRandomDescription();

        DocumentTypesClass dc = new DocumentTypesClass(attachmentstates,"6390f3207a3bcb6a7ac977f9");
        dc.setName(DocumentName);
        dc.setDescription(Description);
        dc.setId(documentsID);
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(dc)


                .when()
                .put("school-service/api/attachments")
                .then()
                .log().body()
                .statusCode(200)

        ;


    }
    @Test(dependsOnMethods ="EditDocumentType" )
    public void DeleteDocumentTypes() {



        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("documentsID",documentsID)


                .when()
                .delete("school-service/api/attachments/{documentsID}")
                .then()
                .log().body()
                .statusCode(200)

        ;


    }
    @Test(dependsOnMethods ="DeleteDocumentTypes" )
    public void DeleteDocumentTypesNegative() {


        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("documentsID",documentsID)


                .when()
                .delete("school-service/api/attachments/{documentsID}")
                .then()
                .log().body()
                .statusCode(400)

        ;

    }
    @Test(dependsOnMethods ="DeleteDocumentTypes" )
    public void EditDocumentTypeNegative() {
        DocumentName = getRandomName();
        Description = getRandomDescription();

        DocumentTypesClass dc = new DocumentTypesClass(attachmentstates,"6390f3207a3bcb6a7ac977f9");
        dc.setName(DocumentName);
        dc.setDescription(Description);
        dc.setId(documentsID);
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(dc)


                .when()
                .put("school-service/api/attachments")
                .then()
                .log().body()
                .statusCode(400)

        ;


    }
    public String getRandomName() {
        return RandomStringUtils.randomAlphabetic(8).toLowerCase();
    }

    public String getRandomDescription() {
        return RandomStringUtils.randomAlphabetic(15).toLowerCase();
    }

}
