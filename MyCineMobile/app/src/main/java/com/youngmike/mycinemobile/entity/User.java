package com.youngmike.mycinemobile.entity;

public class User {

  private int uuid;
  private String email;
  private int roleid;
  private String fname;
  private String lname;
  private int id_address;
  private String firebaseUID;
  private String cell_number;
  private int reminderthreshold;
  private int defaultrentalperiod;

  public int getUuid() {
    return uuid;
  }

  public int getRoleid() {
    return roleid;
  }

  public void setRoleid(int roleid) {
    this.roleid = roleid;
  }

  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }

  public String getLname() {
    return lname;
  }

  public void setLname(String lname) {
    this.lname = lname;
  }

  public int getAddressid() {
    return id_address;
  }

  public void setAddressid(int addressid) {
    this.id_address = addressid;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCellnumber() {
    return cell_number;
  }

  public void setCellnumber(String cellnumber) {
    this.cell_number = cellnumber;
  }

  public int getReminderthreshold() {
    return reminderthreshold;
  }

  public void setReminderthreshold(int reminderthreshold) {
    this.reminderthreshold = reminderthreshold;
  }

  public int getDefaultrentalperiod() {
    return defaultrentalperiod;
  }

  public void setDefaultrentalperiod(int defaultrentalperiod) {
    this.defaultrentalperiod = defaultrentalperiod;
  }

  public String getFirebaseUID() {
    return firebaseUID;
  }

  public void setFirebaseUID(String firebaseUID) {
    this.firebaseUID = firebaseUID;
  }
}
