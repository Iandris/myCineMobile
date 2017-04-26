package com.youngmike.mycinemobile.entity;

public class Wishlist {

  private int idwishlistlink;

  private int userid;

  private int movieid;

  private String movieTitle;

  private String movieSynopsis;

  private String imagePath;

  public int getIdwishlistlink() {
    return idwishlistlink;
  }

  public int getUserid() {
    return userid;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }

  public int getMovieid() {
    return movieid;
  }

  public void setMovieid(int movieid) {
    this.movieid = movieid;
  }

  public String getMovieSynopsis() {
    return movieSynopsis;
  }

  public void setMovieSynopsis(String movieSynopsis) {
    this.movieSynopsis = movieSynopsis;
  }

  public String getMovieTitle() {
    return movieTitle;
  }

  public void setMovieTitle(String movieTitle) {
    this.movieTitle = movieTitle;
  }


  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }
}
