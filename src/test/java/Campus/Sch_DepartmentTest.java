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
    String deptNameII = "Art_Dept";
    String deptCodeII = "AD_11";
    List<Constant> deptConstant=new ArrayList<>();
    List<Section> deptSection=new ArrayList<>();

    Constant cons1 = new Constant("Experiment","Ex_10");
    Constant cons2 = new Constant("Reading","Rd_11");
    Section sect1 = new Section("Chemistry","Chm_10",false);
    Section sect2 = new Section("Literature","Lit_11",true);


    @Test
    public void creatingSchoolDepartment(){

        Sch_DepartmentClass scDept = new Sch_DepartmentClass(deptName,deptCode,true,deptConstant,deptSection);

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

    @Test (priority=1)
    public void Recreating_Negative() {

        Sch_DepartmentClass scDept = new Sch_DepartmentClass(deptName, deptCode,true,deptConstant,deptSection);

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

    @Test(priority=2)
    public void fillingOutSubComponents(){

        Sch_DepartmentClass scDept = new Sch_DepartmentClass(deptName,deptCode,true,deptConstant,deptSection);
        scDept.setId(deptID);

        deptSection.add(sect1);
        deptConstant.add(cons1);


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
    @Test(priority=3)
    public void fillingOutSubComp_Negative(){

        Sch_DepartmentClass scDept = new Sch_DepartmentClass(deptName,deptCode,true,deptConstant,deptSection);
        scDept.setId(deptID);

        deptConstant.add(cons1);
        deptSection.add(sect1);

        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(scDept)

                .when()
                .put("school-service/api/department")
                .then()
                .log().body()
                .statusCode(400);
    }

    @Test(priority=4)
    public void editingSchoolDepartment(){

        Sch_DepartmentClass scDept = new Sch_DepartmentClass(deptNameII,deptCodeII,false,deptConstant,deptSection);
        scDept.setId(deptID);

        deptConstant.remove(cons1);
        deptSection.remove(sect1);

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
    @Test(priority=5)
    public void editingSubComponents(){

        Sch_DepartmentClass scDept = new Sch_DepartmentClass(deptNameII,deptCodeII,false,deptConstant,deptSection);
        scDept.setId(deptID);

        deptConstant.add(cons2);
        deptSection.add(sect2);


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
