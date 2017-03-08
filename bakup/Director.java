package com.youngmike.mycinemobile.entity;

/**
 * Director Entity class for MyCineMobile
 */
public class Director {

  private int iddirector;
  private String fname;
  private String lname;

  /**
   * getIddirector method, returns the integer value for ID in Director Entity
   * @return
     */
  public int getIddirector() {
    return iddirector;
  }

  /**
   * getFname method, returns string value of first name for Director Entity
   * @return
     */
  public String getFname() {
    return fname;
  }

  /**
   * setFname method, sets the string value of first name for Director Entity
   * @param fname
     */
  public void setFname(String fname) {
    this.fname = fname;
  }

  /**
   * getLname method, returns the string value of last name from Director Entity
   * @return
     */
  public String getLname() {
    return lname;
  }

  /**
   * setLname method, sets the string value of Last name in Director Entity
   * @param lname
     */
  public void setLname(String lname) {
    this.lname = lname;
  }

}
