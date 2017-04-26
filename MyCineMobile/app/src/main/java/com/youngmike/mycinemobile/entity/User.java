package com.youngmike.mycinemobile.entity;


/**
 * User Entity class for MyCineMobile
 */
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

  /**
   * getUuid public getter method
   * @return
     */
  public int getUuid() {
    return uuid;
  }

  /**
   * setUuid public setter method
   * @param uuid
   */
  public void setUuid(int uuid) {
    this.uuid = uuid;
  }

  /**
   * getRoleid public getter method
   * @return
   */
  public int getRoleid() {
    return roleid;
  }

  /**
   * setRoleid public setter method
   * @param roleid
     */
  public void setRoleid(int roleid) {
    this.roleid = roleid;
  }

  /**
   * getFname public getter method
   * @return
   */
  public String getFname() {
    return fname;
  }

  /**
   * setFname  public setter method
   * @param fname
     */
  public void setFname(String fname) {
    this.fname = fname;
  }

  /**
   * getLname public getter method
   * @return
   */
  public String getLname() {
    return lname;
  }

  /**
   * setLname public setter method
   * @param lname
     */
  public void setLname(String lname) {
    this.lname = lname;
  }

  /**
   * getAddressid public getter method
   * @return
   */
  public int getAddressid() {
    return id_address;
  }

  /**
   * setAddressid public setter method
   * @param addressid
     */
  public void setAddressid(int addressid) {
    this.id_address = addressid;
  }

  /**
   * getEmail public getter method
   * @return
   */
  public String getEmail() {
    return email;
  }

  /**
   * setEmail public setter method
   * @param email
     */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * getCellnumber public getter method
   * @return
   */
  public String getCellnumber() {
    return cell_number;
  }

  /**
   * setCellnumber public setter method
   * @param cellnumber
     */
  public void setCellnumber(String cellnumber) {
    this.cell_number = cellnumber;
  }

  /**
   * getReminderthreshold public getter method
   * @return
   */
  public int getReminderthreshold() {
    return reminderthreshold;
  }

  /**
   * setReminderthreshold public setter method
   * @param reminderthreshold
     */
  public void setReminderthreshold(int reminderthreshold) {
    this.reminderthreshold = reminderthreshold;
  }

  /**
   * getDefaultrentalperiod public getter method
   * @return
   */
  public int getDefaultrentalperiod() {
    return defaultrentalperiod;
  }

  /**
   * setDefaultrentalperiod public setter method
   * @param defaultrentalperiod
     */
  public void setDefaultrentalperiod(int defaultrentalperiod) {
    this.defaultrentalperiod = defaultrentalperiod;
  }

  /**
   * getfirebaseuid public getter method
   * @return
   */
  public String getFirebaseUID() {
    return firebaseUID;
  }

  /**
   * setFirebaseuid public setter method
   * @param firebaseUID
     */
  public void setFirebaseUID(String firebaseUID) {
    this.firebaseUID = firebaseUID;
  }
}
