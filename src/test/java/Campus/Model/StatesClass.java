package Campus.Model;

public class StatesClass {
 private String id=null;
 private String name;
 private String shortName;
 private StatesCountryClass country;
 private String[] translateName={};


 public StatesClass() {
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

 public String getShortName() {
  return shortName;
 }

 public void setShortName(String shortName) {
  this.shortName = shortName;
 }

 public StatesCountryClass getCountry() {
  return country;
 }

 public void setCountry(StatesCountryClass country) {
  this.country = country;
 }

 public String[] getTranslateName() {
  return translateName;
 }

 public void setTranslateName(String[] translateName) {
  this.translateName = translateName;
 }
}
