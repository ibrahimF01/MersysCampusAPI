package Campus.Model;

public class DeptSection {
    String name;
    String shortName;
    boolean active;
    int index;

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

    public int getIndex() { return index; }

    public void setIndex(int index) { this.index = index; }

    public DeptSection(String name, String shortName, boolean active) {
        setName(name);
        setShortName(shortName);
        setActive(active);



    }
}
