package Campus.Model;

class gradeLevel {
    private String id;

    public gradeLevel(String id) {
        this.id = id;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

class academicPeriod {
    private String id;

    public academicPeriod(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
class teacher{
    private String id;

    public teacher(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
class location{
    private String id;

    public location(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
class department{

    private String id;

    public department(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


public class ClassesClass {

    gradeLevel gl=new gradeLevel("63b7dccf418dc97d82b4c962");
    academicPeriod ap=new academicPeriod("63918286c1e0ca225b833530");
    teacher teach=new teacher("63b412bdc1f14b24c71bbd81");
    location loc=new location("63b41344c1f14b24c71bbd84");
    department dep=new department("63b410a8c1f14b24c71bbd7e");







    private String id = null;
    private String name;
    private String shortName;
    private boolean active = true;
    private String section;
    private gradeLevel gradeLevel=gl;
    private academicPeriod academicPeriod=ap;
    private teacher teacher=teach;
    private location location=loc;
    private department department=dep;
    private String school;


    public ClassesClass(String school) {

        this.school = school;
    }


    public Campus.Model.gradeLevel getGl() {
        return gl;
    }

    public void setGl(Campus.Model.gradeLevel gl) {
        this.gl = gl;
    }

    public Campus.Model.academicPeriod getAp() {
        return ap;
    }

    public void setAp(Campus.Model.academicPeriod ap) {
        this.ap = ap;
    }

    public Campus.Model.teacher getTeach() {
        return teach;
    }

    public void setTeach(Campus.Model.teacher teach) {
        this.teach = teach;
    }

    public Campus.Model.location getLoc() {
        return loc;
    }

    public void setLoc(Campus.Model.location loc) {
        this.loc = loc;
    }

    public Campus.Model.department getDep() {
        return dep;
    }

    public void setDep(Campus.Model.department dep) {
        this.dep = dep;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Campus.Model.gradeLevel getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(Campus.Model.gradeLevel gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public Campus.Model.academicPeriod getAcademicPeriod() {
        return academicPeriod;
    }

    public void setAcademicPeriod(Campus.Model.academicPeriod academicPeriod) {
        this.academicPeriod = academicPeriod;
    }

    public Campus.Model.teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Campus.Model.teacher teacher) {
        this.teacher = teacher;
    }

    public Campus.Model.location getLocation() {
        return location;
    }

    public void setLocation(Campus.Model.location location) {
        this.location = location;
    }

    public Campus.Model.department getDepartment() {
        return department;
    }

    public void setDepartment(Campus.Model.department department) {
        this.department = department;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
