package com.mtyoung.entity;

public class State {

  private int idstate;

  private String shortname;

  private String longname;

  public int getIdstate() {
    return idstate;
  }

  public String getShortname() {
    return shortname;
  }

  public void setShort(String short_name) {
    this.shortname = short_name;
  }

  public String getLongname() {
    return longname;
  }

  public void setLong(String long_name) {
    this.longname = long_name;
  }

}
