package Campus.Model;

import java.util.ArrayList;
import java.util.List;

public class DepartmentClass {
    private String id;
    private String name;
    private String code;
    private boolean active;
    private String school="6390f3207a3bcb6a7ac977f9";

    private List<DeptSection> sections=new ArrayList<>();
    private List<DeptConstant> constants=new ArrayList<>();



    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() { return code; }

    public boolean isActive() {
        return active;
    }

    public String getSchool() {
        return school;
    }

    public List<DeptConstant> getConstants() {return constants;}

    public List<DeptSection> getSections() {return sections;}


    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public void setSchool(String school) {
        this.school = school;
    }

    public void setSections(List<DeptSection> sections) {this.sections = sections;}

    public void setConstants(List<DeptConstant> constants) {this.constants = constants;}


    public DepartmentClass(String name, String code, List<DeptConstant>constants, List<DeptSection>sections, boolean active)
    {
        setName(name);
        setCode(code);
        setSchool(school);
        setActive(active);
        setConstants(constants);
        setSections(sections);
    }

}

