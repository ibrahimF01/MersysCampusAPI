package Campus.Model;

public class DeptConstant {
    String key;
    String value;

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public DeptConstant(String key, String value) {
        setKey(key);
        setValue(value);
    }
}
