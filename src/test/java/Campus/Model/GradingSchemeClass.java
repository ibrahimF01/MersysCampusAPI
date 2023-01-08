package Campus.Model;

import Campus.GradingSchemeTest;
class css{
   private String value= "#00e5ff";
   private String foreground= "black";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getForeground() {
        return foreground;
    }

    public void setForeground(String foreground) {
        this.foreground = foreground;
    }
}

class gradeRanges{
    css cs=new css();
    private String id = null;
    private String grade= GradingSchemeTest.getRandomName();
    private String gradeDescription=GradingSchemeTest.getRandomDiscription();
    private String[] translateGradeDescription={};
    private int weightFrom= 30;
    private int weightTo= 100;
    private css css=cs;
    private String type= "PASS";
    private String point= null;
    private int coefficient= 10;
    private int index= 1;
    public gradeRanges(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGradeDescription() {
        return gradeDescription;
    }

    public void setGradeDescription(String gradeDescription) {
        this.gradeDescription = gradeDescription;
    }

    public String[] getTranslateGradeDescription() {
        return translateGradeDescription;
    }

    public void setTranslateGradeDescription(String[] translateGradeDescription) {
        this.translateGradeDescription = translateGradeDescription;
    }

    public int getWeightFrom() {
        return weightFrom;
    }

    public void setWeightFrom(int weightFrom) {
        this.weightFrom = weightFrom;
    }

    public int getWeightTo() {
        return weightTo;
    }

    public void setWeightTo(int weightTo) {
        this.weightTo = weightTo;
    }

    public Campus.Model.css getCss() {
        return css;
    }

    public void setCss(Campus.Model.css css) {
        this.css = css;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
public class GradingSchemeClass {
gradeRanges gr=new gradeRanges("072f-2c17");
    private String id = null;
    private String name;
    private boolean active = true;
    private String type = "RANGE";
    private boolean enablePoint = false;
    private gradeRanges[] gradeRanges = {gr};
    private String schoolId;

    public GradingSchemeClass() {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isEnablePoint() {
        return enablePoint;
    }

    public void setEnablePoint(boolean enablePoint) {
        this.enablePoint = enablePoint;
    }

    public Campus.Model.gradeRanges[] getGradeRanges() {
        return gradeRanges;
    }

    public void setGradeRanges() {
        this.gradeRanges = gradeRanges;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

}
