package com.youngmike.mycinemobile.entity;

public class Address {

  private int idaddresses;

  private String streetaddress1;

  private String streetaddress2;

  private String city;

  private int state;

  private int zipcode;


  public int getIdaddresses() {
    return idaddresses;
  }

  public String getStreetaddress1() {
    return streetaddress1;
  }

  public void setStreetaddress1(String streetaddress1) {
    this.streetaddress1 = streetaddress1;
  }


  public String getStreetaddress2() {
    return streetaddress2;
  }

  public void setStreetaddress2(String streetaddress2) {
    this.streetaddress2 = streetaddress2;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }


  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }


  public int getZipcode() {
    return zipcode;
  }

  public void setZipcode(int zipcode) {
    this.zipcode = zipcode;
  }

}
