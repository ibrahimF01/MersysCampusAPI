package Campus.Model;

public class BankAccountsClass {
    private String id = null;
    private String name;
    private String iban;
    private String integrationCode;
    private String currency;
    private Boolean active = true;
    private String schoolId;

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

    public BankAccountsClass(String aname, String aiban, String aintegrationCode, String acurrency, String aschoolId) {
        this.name = aname;
        this.iban = aiban;
        this.integrationCode = aintegrationCode;
        this.currency = acurrency;
        this.schoolId = aschoolId;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getIntegrationCode() {
        return integrationCode;
    }

    public void setIntegrationCode(String integrationCode) {
        this.integrationCode = integrationCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }
}
