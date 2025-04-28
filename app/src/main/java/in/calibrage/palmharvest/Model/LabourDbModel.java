package in.calibrage.palmharvest.Model;

public class LabourDbModel {
    String firstname;
    String lastname;
    Integer userid;
    private Integer labourid;

    public LabourDbModel(String firstname, String lastname, Integer userid) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.userid = userid;
        this.labourid = labourid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getLabourid() {
        return labourid;
    }

    public void setLabourid(Integer labourid) {
        this.labourid = labourid;
    }
}
