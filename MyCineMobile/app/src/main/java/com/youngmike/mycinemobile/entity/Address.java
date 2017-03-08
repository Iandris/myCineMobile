package com.youngmike.mycinemobile.entity;

/**
 * Address Entity class for MyCineMobile - this table is a lookup, providing FK for Address in User Entity
 */
public class Address {

  private int idaddresses;
  private String streetaddress1;
  private String streetaddress2;
  private String city;
  private int state;
  private int zipcode;

  /**
   * getIDAddresses method, returns integer value of ID column from Address entity
   * @return
     */
  public int getIdaddresses() {
    return idaddresses;
  }

  /**
   * getStreetAddress1 method, returns string value of StreetAddress1 in Address Entity
   * @return
     */
  public String getStreetaddress1() {
    return streetaddress1;
  }

  /**
   * setStreetAddress1 method, sets the string value of StreetAddress1 in Address Entity
   * @param streetaddress1
     */
  public void setStreetaddress1(String streetaddress1) {
    this.streetaddress1 = streetaddress1;
  }

  /**
   * getStreetAddress2 method, returns the string value of StreetAddress2 in Address Entity
   * @return
     */
  public String getStreetaddress2() {
    return streetaddress2;
  }

  /**
   * setStreetAddress2 method, sets the string value of StreetAddress2 in Address Entity
   * @param streetaddress2
     */
  public void setStreetaddress2(String streetaddress2) {
    this.streetaddress2 = streetaddress2;
  }

  /**
   * getCity method, returns the string value of City in Address Entity
   * @return
     */
  public String getCity() {
    return city;
  }

  /**
   * setCity method, sets the string value of City in Address Entity
   * @param city
     */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * getState method, returns the integer value for FK of State, in Address Entity
   * @return
     */
  public int getState() {
    return state;
  }

  /**
   * setState method, sets the integer value for FK of State, in Address Entity
   * @param state
     */
  public void setState(int state) {
    this.state = state;
  }

  /**
   * getZipCode method, gets the 5 digit integer value of ZipCode in Address Entity
   * @return
     */
  public int getZipcode() {
    return zipcode;
  }

  /**
   * setZipCode method, sets the 5 digit integer value of ZipCode in Address Entity
   * @param zipcode
     */
  public void setZipcode(int zipcode) {
    this.zipcode = zipcode;
  }

}
