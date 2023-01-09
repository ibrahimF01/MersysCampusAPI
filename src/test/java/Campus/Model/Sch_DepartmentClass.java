package Campus.Model;

import java.util.ArrayList;
import java.util.List;

public class Sch_DepartmentClass {
    private String id;
    private String name;
    private String code;
    private boolean active;
    private String school;

    private List<Section> sections=new ArrayList<>();
    private List<Constant> constants=new ArrayList<>();



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

    public List<Constant> getConstants() {return constants;}

    public List<Section> getSections() {return sections;}


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

    public void setSections(List<Section> sections) {this.sections = sections;}

    public void setConstants(List<Constant> constants) {this.constants = constants;}


    public Sch_DepartmentClass(String name, String code,boolean active)
    {
        setName(name);
        setCode(code);
        setSchool("6390f3207a3bcb6a7ac977f9");
        setActive(active);
    }

}

