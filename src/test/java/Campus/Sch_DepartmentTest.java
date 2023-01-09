package Campus;

import Campus.Model.Constant;
import Campus.Model.Sch_DepartmentClass;
import Campus.Model.Section;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Sch_DepartmentTest extends Parent{

    String deptID;
    String deptName = "Science_Dept";
    String deptCode = "SD_10";
    List<Constant> deptConstant=new ArrayList<>();
    List<Section> deptSection=new ArrayList<>();

    Constant cons = new Constant("Experiment","Ex_10");
    Section sect = new Section("Chemistry","Chm_10",true);


    @Test
    public void creatingSchoolDepartment(){

        Sch_DepartmentClass scDept = new Sch_DepartmentClass(deptName,deptCode,true);

        deptID =
                given()

                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(scDept)

                        .when()
                        .post("school-service/api/department")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id");
    }

    @Test (dependsOnMethods = "creatingSchoolDepartment")
    public void Recreating_Negative() {

        Sch_DepartmentClass scDept = new Sch_DepartmentClass(deptName, deptCode,true);

        given()

                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(scDept)

                .when()
                .post("school-service/api/department")
                .then()
                .log().body()
                .statusCode(400);
    }

    @Test(dependsOnMethods = "creatingSchoolDepartment")
    public void editingSchoolDepartment(){

        String deptName = "Art_Dept";
        String deptCode = "AD_11";

        Sch_DepartmentClass scDept = new Sch_DepartmentClass(deptName,deptCode,false);

        scDept.setId(deptID);
        scDept.setSections(deptSection);
        scDept.setConstants(deptConstant);

        deptSection.add(sect);
        deptConstant.add(cons);

        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(scDept)

                .when()
                .put("school-service/api/department")
                .then()
                .log().body()
                .statusCode(200);
    }


}
