package com.youngmike.mycinemobile.entity;

public class Wishlist {

  private int idwishlistlink;
  private int userid;
  private int movieid;
  private String movieTitle;
  private String movieSynopsis;
  private String imagePath;

  /**
   * setIdWishlistlink  public setter method
   * @param idwishlistlink
     */
  public void setIdwishlistlink(int idwishlistlink) {
    this.idwishlistlink = idwishlistlink;
  }

  /**
   * getIdWishlistlink public getter method
   * @return
     */
  public int getIdwishlistlink() {
    return idwishlistlink;
  }

  /**
   * getUserid public getter method
   * @return
     */
  public int getUserid() {
    return userid;
  }

  /**
   * setUserId public setter method
   * @param userid
     */
  public void setUserid(int userid) {
    this.userid = userid;
  }

  /**
   * getMovieID  public getter method
   * @return
     */
  public int getMovieid() {
    return movieid;
  }

  /**
   * setMovieID  public setter method
   * @param movieid
     */
  public void setMovieid(int movieid) {
    this.movieid = movieid;
  }

  /**
   * getMovieSynopsis public getter method
   * @return
     */
  public String getMovieSynopsis() {
    return movieSynopsis;
  }

  /**
   * setMovieSynopsis public setter method
   * @param movieSynopsis
     */
  public void setMovieSynopsis(String movieSynopsis) {
    this.movieSynopsis = movieSynopsis;
  }

  /**
   * getMovieTitle public getter method
   * @return
     */
  public String getMovieTitle() {
    return movieTitle;
  }

  /**
   * setMovieTitle public setter method
   * @param movieTitle
     */
  public void setMovieTitle(String movieTitle) {
    this.movieTitle = movieTitle;
  }

  /**
   * getImagePath  public getter method
   * @return
     */
  public String getImagePath() {
    return imagePath;
  }

  /**
   * setImagepath public setter method
   * @param imagePath
     */
  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }
}
