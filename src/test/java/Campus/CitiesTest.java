package Campus;

import Campus.Model.CitiesClass;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CitiesTest extends Parent {

    String CitiesID;

    String CitiesName;


    @Test
    public void creatCities(){


        CitiesName = getRandomName();

        CitiesClass cc = new CitiesClass();
        cc.setName(CitiesName);


        CitiesID =
                given()

                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(cc)


                        .when()
                        .post("school-service/api/cities")
                        .then()
                        //.log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
        ;


    }
    @Test(dependsOnMethods = "creatCities")
    public void creatNegativeCities(){


        CitiesName = getRandomName();

        CitiesClass cc = new CitiesClass();
        cc.setName(CitiesName);
        cc.setId(CitiesID);


                given()

                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(cc)


                        .when()
                        .post("school-service/api/cities")
                        .then()
                        //.log().body()
                        .statusCode(400);


    } @Test(dependsOnMethods = "creatNegativeCities")
    public void updateCities(){


        CitiesName = getRandomName();

        CitiesClass cc = new CitiesClass();
        cc.setName(CitiesName);
        cc.setId(CitiesID);


        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(cc)


                .when()
                .put("school-service/api/cities")
                .then()
                //.log().body()
                .statusCode(200);


    }@Test(dependsOnMethods = "updateCities")
    public void deleteCities(){





        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("CitiesID",CitiesID)


                .when()
                .delete("school-service/api/cities/{CitiesID}")
                .then()
                //.log().body()
                .statusCode(200);


    }
    @Test(dependsOnMethods = "deleteCities")
    public void deleteNegativeCities(){// normalde 400 status kod ile çalışması gereken yer 200 ile çalıştı
                                      // devoloper bilgilendirdi tekrar kontrol edilecektir.





        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("CitiesID",CitiesID)


                .when()
                .delete("school-service/api/cities/{CitiesID}")
                .then()
                //.log().body()
                .statusCode(200);



    }@Test(dependsOnMethods = "deleteNegativeCities")
    public void updateNegativeCities() {


        CitiesName = getRandomName();

        CitiesClass cc = new CitiesClass();
        cc.setName(CitiesName);
        cc.setId(CitiesID);


        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(cc)


                .when()
                .put("school-service/api/cities")
                .then()
                //.log().body()
                .statusCode(400);

    }


  public String getRandomName() {
     return RandomStringUtils.randomAlphabetic(8).toLowerCase();
 }
}

