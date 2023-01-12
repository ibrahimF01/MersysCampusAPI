package Campus.Model;

import Campus.Parent;

public class GradeLevelClass{

//    {
//        "id": null,
//            "name": "Haydar",
//            "shortName": "Hay",
//            "nextGradeLevel": null,
//            "order": "1",
//            "translateName": [],
//        "translateShortName": [],
//        "active": true,
//            "schoolIds": [
//        "6390f3207a3bcb6a7ac977f9"
//  ]
//    }
    private String id = null;
    private String name;
    private String shortName;
    private String[] translateName = {};
    private String[] translateShortName = {};
    private int order;
    private String nextGradeLevel;
    private String schoolId;
    private boolean active = true;

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

    public String[] getTranslateName() {
        return translateName;
    }

    public void setTranslateName(String[] translateName) {
        this.translateName = translateName;
    }

    public String[] getTranslateShortName() {
        return translateShortName;
    }

    public void setTranslateShortName(String[] translateShortName) {
        this.translateShortName = translateShortName;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getNextGradeLevel() {
        return nextGradeLevel;
    }

    public void setNextGradeLevel(String nextGradeLevel) {
        this.nextGradeLevel = nextGradeLevel;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
