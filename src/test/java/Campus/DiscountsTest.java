package Campus;

import Campus.Model.DiscountsClass;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class DiscountsTest extends Parent{

    String discountsId;
    String discountsDescription = "Desc_HsYz";
    String discountsCode = getRandomCode();
    int discountsPriority = getRandomInt();
    boolean discountsActive = true;

    @Test
    public void creatingDiscount (){

        DiscountsClass disClass = new DiscountsClass(discountsDescription,discountsCode,discountsPriority);

        discountsId =
                given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(disClass)

                .when()
                .post("school-service/api/discounts")
                .then()
                .log().body()
                .statusCode(201)
                .extract().jsonPath().getString("id");
    }


    @Test(dependsOnMethods = "creatingDiscount")
    public void creatingDiscount_Negative() {

        DiscountsClass disClass = new DiscountsClass(discountsDescription,discountsCode,discountsPriority);

        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(disClass)

                .when()
                .post("school-service/api/discounts")
                .then()
                .log().body()
                .statusCode(400)
                //.body("id", equalTo(null))
        ;
    }

    @Test(dependsOnMethods = "creatingDiscount")
    public void editingDiscount() {
        discountsDescription = "Desc_HsYzII";
        discountsCode = getRandomCode();
        discountsPriority = getRandomInt();

        DiscountsClass disClass = new DiscountsClass(discountsDescription,discountsCode,discountsPriority);
        disClass.setId(discountsId);
        disClass.setActive(discountsActive);

        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(disClass)

                .when()
                .put("school-service/api/discounts")
                .then()
                .log().body()
                .statusCode(200)
        ;
    }

    @Test(dependsOnMethods = "editingDiscount")
    public void deletingDiscount() {

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("discountsId", discountsId)
                .when()
                .delete("school-service/api/discounts/{discountsId}")
                .then()
                //.log().body()
                .statusCode(200)

        ;
    }

    @Test(dependsOnMethods = "deletingDiscount")
    public void editingTheDeleted_Negative() {
        discountsDescription = "Desc_HsYzIII";
        discountsCode = getRandomCode();
        discountsPriority = getRandomInt();

        DiscountsClass disClass = new DiscountsClass(discountsDescription, discountsCode, discountsPriority);
        disClass.setId(discountsId);
        disClass.setActive(discountsActive);

        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(disClass)

                .when()
                .put("school-service/api/discounts")
                .then()
                .log().body()
                .statusCode(400)
        ;
    }

    @Test(dependsOnMethods = "deletingDiscount")
    public void ReDeleting_Negative() {

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("discountsId", discountsId)
                .when()
                .delete("school-service/api/discounts/{discountsId}")
                .then()
                //.log().body()
                .statusCode(400)
        ;
    }

    public String getRandomCode() {return RandomStringUtils.randomAlphanumeric(3).toLowerCase();}
    public int getRandomInt(){ return (int) (Math.random() * 10)+1;}


}
