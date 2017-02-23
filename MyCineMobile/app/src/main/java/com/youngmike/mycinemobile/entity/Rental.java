package com.youngmike.mycinemobile.entity;

import org.joda.time.DateTime;

public class Rental {

  private int idrentals;

  private int renterid;

  private int movieid;

  private DateTime duedate;

  public int getIdrentals() {
    return idrentals;
  }

  public int getRenterid() {
    return renterid;
  }

  public void setRenterid(int renterid) {
    this.renterid = renterid;
  }

  public int getMovieid() {
    return movieid;
  }

  public void setMovieid(int movieid) {
    this.movieid = movieid;
  }

  public DateTime getDuedate() {
    return duedate;
  }

  public void setDuedate(DateTime duedate) {
    this.duedate = duedate;
  }

}
