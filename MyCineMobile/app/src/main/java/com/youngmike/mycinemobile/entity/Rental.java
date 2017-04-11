package com.youngmike.mycinemobile.entity;

import org.joda.time.DateTime;

/**
 * Rental Entity class for MyCineMobile
 */
public class Rental {

  private int idrentals;
  private int renterid;
  private int movieid;
  private DateTime duedate;

  /**
   * getIdRentals method, returns integer value of ID from Rental Entity
   * @return
     */
  public int getIdrentals() {
    return idrentals;
  }

  /**
   * getIdRentals method, returns integer value of ID from Rental Entity
   * @return
   */
  public void setIdrentals(int idrentals) {
    this.idrentals = idrentals;
  }

  /**
   * getRenterID method, returns integer value of FK User from Rental Entity
   * @return
     */
  public int getRenterid() {
    return renterid;
  }

  /**
   * setRenterID method, sets integer value for FK User on Rental Entity
   * @param renterid
     */
  public void setRenterid(int renterid) {
    this.renterid = renterid;
  }

  /**
   * getMovieID method, returns integer value of FK Movie on Rental Entity
   * @return
     */
  public int getMovieid() {
    return movieid;
  }

  /**
   * setMovieId method, sets the integer value of FK Movie on Rental entity
   * @param movieid
     */
  public void setMovieid(int movieid) {
    this.movieid = movieid;
  }

  /**
   * getDueDate method, returns DateTime value of due date from Rental Entity
   * @return
     */
  public DateTime getDuedate() {
    return duedate;
  }

  /**
   * setDueDate method, sets DateTime value for due date on Rental Entity
   * @param duedate
     */
  public void setDuedate(DateTime duedate) {
    this.duedate = duedate;
  }

}
