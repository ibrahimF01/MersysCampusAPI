package Campus;

import Campus.Model.DeptConstant;
import Campus.Model.DepartmentClass;
import Campus.Model.DeptSection;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class DepartmentTest extends Parent{

String deptID;
String deptName = "Science_Dept";
String deptCode = "SD_10";
String deptNameII = "Art_Dept";
String deptCodeII = "AD_11";
List<DeptConstant> deptConstant=new ArrayList<>();
List<DeptSection> deptSection=new ArrayList<>();

DeptConstant cons1 = new DeptConstant("Experiment","Ex_10");
DeptConstant cons2 = new DeptConstant("Reading","Rd_11");
DeptSection sect1 = new DeptSection("Chemistry","Chm_10",false);
DeptSection sect2 = new DeptSection("Literature","Lit_11",true);


@Test(priority = 0)
public void creatingSchoolDepartment(){

DepartmentClass dept = new DepartmentClass(deptName,deptCode,deptConstant,deptSection,true);

    deptID =
            given()

                    .cookies(cookies)
                    .contentType(ContentType.JSON)
                    .body(dept)

                    .when()
                    .post("school-service/api/department")
                    .then()
                    .log().body()
                    .statusCode(201)
                    .extract().jsonPath().getString("id");
}

@Test (priority=1)
public void ReCreating_Negative() {

    DepartmentClass dept = new DepartmentClass(deptName, deptCode,deptConstant,deptSection,true);

    given()

            .cookies(cookies)
            .contentType(ContentType.JSON)
            .body(dept)

            .when()
            .post("school-service/api/department")
            .then()
            .log().body()
            .statusCode(400);
}

@Test(priority=2)
public void fillingOutSubComponents(){

    DepartmentClass dept = new DepartmentClass(deptName,deptCode,deptConstant,deptSection,true);
    dept.setId(deptID);

    deptSection.add(sect1);
    deptSection.add(sect2);
    deptConstant.add(cons1);
    deptConstant.add(cons2);


    given()
            .cookies(cookies)
            .contentType(ContentType.JSON)
            .body(dept)

            .when()
            .put("school-service/api/department")
            .then()
            .log().body()
            .statusCode(200);
}

@Test(priority=3)
public void editingSchoolDepartmentAndSubComponents(){

    DepartmentClass dept = new DepartmentClass(deptNameII,deptCodeII,deptConstant,deptSection,false);
    dept.setId(deptID);

    deptSection.remove(sect1);
    deptConstant.remove(cons1);

    given()
            .cookies(cookies)
            .contentType(ContentType.JSON)
            .body(dept)

            .when()
            .put("school-service/api/department")
            .then()
            .log().body()
            .statusCode(200);
}
@Test(priority=4)
public void editingSubComponentsOnly(){

    DepartmentClass dept = new DepartmentClass(deptNameII,deptCodeII,deptConstant,deptSection,false);
    dept.setId(deptID);

    deptConstant.add(cons1);
    deptConstant.remove(cons2);
    deptSection.add(sect1);
    deptSection.remove(sect2);


    given()
            .cookies(cookies)
            .contentType(ContentType.JSON)
            .body(dept)

            .when()
            .put("school-service/api/department")
            .then()
            .log().body()
            .statusCode(200);
}

    @Test(priority=5)
    public void listingSchoolDepartments(){


        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)

                .when()
                .get("school-service/api/school/6390f3207a3bcb6a7ac977f9/department")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test(priority = 6)
    public void deletingSchoolDepartment(){

        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("deptID",deptID)

                .when()
                .delete("school-service/api/department/{deptID}")
                .then()
                .log().body()
                .statusCode(204);
    }

    @Test(priority=7)
    public void editingDeletedSchoolDepartment(){

        DepartmentClass dept = new DepartmentClass(deptName,deptCode,deptConstant,deptSection,true);
        dept.setId(deptID);

        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(dept)

                .when()
                .put("school-service/api/department")
                .then()
                .log().body()
                .statusCode(400);
    }
    @Test(priority = 8)
    public void reDeletingSchoolDepartment_Negative(){

        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .pathParam("deptID",deptID)

                .when()
                .delete("school-service/api/department/{deptID}")
                .then()
                .log().body()
                .statusCode(400);
             // statusCode should have been 400! but it returns 204.
    }

}
