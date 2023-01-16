package Campus.Model;
class country{
    private String id;

    public country(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
public class CitiesClass {
    country ct=new country("63bf9e590104b35331c62491");

    private String id=null;
    private String name;
    private country country= ct;
    private String state=null;
    private String[] translateName={};


    public CitiesClass() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Campus.Model.country getCt() {
        return ct;
    }

    public void setCt(Campus.Model.country ct) {
        this.ct = ct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public country getCountry() {
        return country;
    }

    public void setCountry(country country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String[] getTranslateName() {
        return translateName;
    }

    public void setTranslateName(String[] translateName) {
        this.translateName = translateName;
    }
}
