package com.youngmike.mycinemobile.entity;

/**
 * Actor Entity class for MyCineMobile
 */
public class Actor {

  private int idactor;
  private String fname;
  private String lname;

  /**
   * getIDActor method, returns integer value of ID from Actor entity
   * @return
     */
  public int getIdactor() {
    return idactor;
  }

  /**
   * getFname method, returns string value of First Name in Actor Entity
   * @return
     */
  public String getFname() {
    return fname;
  }

  /**
   * setFname method, sets the string value for First Name in Actor Entity
   * @param fname
     */
  public void setFname(String fname) {
    this.fname = fname;
  }

  /**
   * getLname method, returns string value for Last Name in Actor Entity
   * @return
     */
  public String getLname() {
    return lname;
  }

  /**
   * setLname method, sets the string value for Last Name in Actor Entity
   * @param lname
     */
  public void setLname(String lname) {
    this.lname = lname;
  }

}
