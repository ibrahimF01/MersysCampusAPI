package Campus.Model;

public class FeesClass {


    private String id = null;
    private String name;
    private String[] translateName = {};
    private int code;
    private int priority;
    private boolean active = true;
    private int budgetAccountIntegrationCode;

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

    public String[] getTranslateName() {
        return translateName;
    }

    public void setTranslateName(String[] translateName) {
        this.translateName = translateName;
    }

    public int  getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getBudgetAccountIntegrationCode() {
        return budgetAccountIntegrationCode;
    }

    public void setBudgetAccountIntegrationCode(int budgetAccountIntegrationCode) {
        this.budgetAccountIntegrationCode = budgetAccountIntegrationCode;
    }
}
