package com.youngmike.mycinemobile.entity;

public class UserMovieLink {

  private int linkid;

  private int userid;

  private int movieid;

  private int quantity;

  private int starrating;

  private String movieTitle;

  private String movieSynopsis;

  public int getLinkid() {
    return linkid;
  }

  public void setLinkid(int linkid){
    this.linkid = linkid;
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

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getStarrating() {
    return starrating;
  }

  public void setStarrating(int starrating) {
    this.starrating = starrating;
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
}
