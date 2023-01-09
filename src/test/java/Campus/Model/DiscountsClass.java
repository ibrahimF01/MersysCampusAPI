package Campus.Model;

public class DiscountsClass {
    private String id;
    private String description;
    private String code;
    private boolean active=false;
    private int priority;


    public void setId(String id) {
        this.id = id;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


            public String getId() {
                return id;
            }

            public String getDescription() {
                return description;
            }

            public String getCode() {
                return code;
            }

            public boolean isActive() {
                return active;
            }

            public int getPriority() {
                return priority;
            }

    public DiscountsClass(String description, String code, int priority){
        setDescription(description);
        setCode(code);
        setPriority(priority);
    }

}
