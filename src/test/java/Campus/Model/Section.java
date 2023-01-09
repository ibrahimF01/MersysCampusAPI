package Campus.Model;

public class Section {
    String name;
    String shortName;
    boolean active;

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

    public Section(String name, String shortName,boolean active) {
        setName(name);
        setShortName(shortName);
        setActive(active);


    }
}
