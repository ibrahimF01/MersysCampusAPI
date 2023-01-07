package Campus;

import Campus.Model.BankAccountsClass;
import Campus.Model.CurrencyType;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class BankAccountsTest extends Parent{
   String BankAccountId;
   String BankAccountName="";
   String BankAccountIban="";
   String BankAccountBic="";
   String BankAccountSchoolId = "6390f3207a3bcb6a7ac977f9";
   String BankAccountCurrency="";

   String methodAdress = "school-service/api/bank-accounts";

   private List<CurrencyType> currencyList;

   @Test
   public void BankAccountsAdding(){
      currencyList = getCurrencyTypeList();

      BankAccountName = getRandomName(BankAccountName);
      BankAccountIban = getRandomIban(BankAccountIban);
      BankAccountBic  = getRandomBic(BankAccountBic);
      BankAccountCurrency = getRandomCurrencyCode(BankAccountCurrency);

      BankAccountsClass bc = new BankAccountsClass(
              BankAccountName,
              BankAccountIban,
              BankAccountBic,
              BankAccountCurrency,
              BankAccountSchoolId );

      BankAccountId = given()
                     .cookies(cookies)
                     .contentType(ContentType.JSON)
                     .body(bc)
                     .when()
                     .post(methodAdress)
                     .then()
                     .log().body()
                     .statusCode(201)
                     .extract().jsonPath().getString("id");
   }

   @Test(dependsOnMethods = "BankAccountsAdding")
   public void BankAccountsAddingNegative(){
      BankAccountsClass bc = new BankAccountsClass(
              BankAccountName,
              BankAccountIban,
              BankAccountBic,
              BankAccountCurrency,
              BankAccountSchoolId );

      given()
      .cookies(cookies)
      .contentType(ContentType.JSON)
      .body(bc)
      .when()
      .post(methodAdress)
      .then()
      .log().body()
      .statusCode(400)
      .body("name",equalTo(null));
   }

   @Test(dependsOnMethods = "BankAccountsAdding")
   public void BankAccountsUpdating(){
      BankAccountName = getRandomName(BankAccountName);
      BankAccountIban = getRandomIban(BankAccountIban);
      BankAccountBic  = getRandomBic(BankAccountBic);
      BankAccountCurrency = getRandomCurrencyCode(BankAccountCurrency);

      BankAccountsClass bc = new BankAccountsClass(
              BankAccountName,
              BankAccountIban,
              BankAccountBic,
              BankAccountCurrency,
              BankAccountSchoolId );

      bc.setId(BankAccountId);

       given()
      .cookies(cookies)
      .contentType(ContentType.JSON)
      .body(bc)
      .when()
      .put(methodAdress)
      .then()
      .log().body()
      .statusCode(200)
      .body("name",equalTo(BankAccountName));
   }

   @Test(dependsOnMethods = "BankAccountsUpdating")
   public void BankAccountsDeleting(){
      String adres = methodAdress+"/"+BankAccountId.toString();

      given()
      .cookies(cookies)
      .contentType(ContentType.JSON)
      .when()
      .delete(adres)
      .then()
      .log().body()
      .statusCode(200);
   }

   @Test(dependsOnMethods = "BankAccountsDeleting")
   public void BankAccountsDeletingNegative(){
      String adres = methodAdress+"/"+BankAccountId.toString();

      given()
              .cookies(cookies)
              .contentType(ContentType.JSON)
              .when()
              .delete(adres)
              .then()
              .log().body()
              .statusCode(400);
   }

   @Test(dependsOnMethods = "BankAccountsDeleting")
   public void BankAccountsUpdatingNegative(){
      BankAccountsClass bc = new BankAccountsClass(
              BankAccountName,
              BankAccountIban,
              BankAccountBic,
              BankAccountCurrency,
              BankAccountSchoolId );

      bc.setId(BankAccountId);

      given()
      .cookies(cookies)
      .contentType(ContentType.JSON)
      .body(bc)
      .when()
      .put(methodAdress)
      .then()
      .log().body()
      .statusCode(400)
      .body("name",equalTo(null));
   }

   public String getRandomName(String lastName) {
      String name = "";

      do{
         name = RandomStringUtils.randomAlphabetic(17).toUpperCase();
      }while(name.equals(lastName));

      return name;
   }

   public String getRandomBic(String lastBic) {
      String bic="";

      do {
         bic = RandomStringUtils.randomAlphabetic(11).toUpperCase();
      }while(bic.equals(lastBic));

      return bic;
   }

   public String getRandomIban(String lastIban) {
      String iban = "";

      do{
         iban = "TR"+RandomStringUtils.randomAlphabetic(18).toUpperCase();
      }while(iban.equals(lastIban));

      return iban;
   }

   public List<CurrencyType> getCurrencyTypeList(){
      List<CurrencyType> list = new ArrayList<>();

      list.add(CurrencyType.EUR);
      list.add(CurrencyType.KZT);
      list.add(CurrencyType.USD);
      list.add(CurrencyType.PKR);
      list.add(CurrencyType.TRY);
      list.add(CurrencyType.TZS);
      list.add(CurrencyType.KGS);

      return list;
   }

   public String getRandomCurrencyCode(String lastCurrencyCode) {
      int size = currencyList.size();

      String currencyCode = "";

      do {
         int r = (int)(Math.random() * size);
         currencyCode = currencyList.get(r).toString();
      }while(currencyCode.equals(lastCurrencyCode));


      return currencyCode;
   }
}
