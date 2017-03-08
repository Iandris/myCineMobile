package com.youngmike.mycinemobile.entity;

import org.joda.time.DateTime;

/**
 * Movie Entity for MyCineMobile
 */

public class Movie {

  private int idmovie;
  private String title;
  private int format;
  private int genre;
  private int director;
  private int studio;
  private String imdbid;
  private String upccode;
  private DateTime releaseDate;

  /**
   * getIDMovie method, returns integer value of ID for Movie Entity
   * @return
     */
  public int getIdmovie() {
    return idmovie;
  }

  /**
   * getTitle method, return string value of title from Movie Entity
   * @return
     */
  public String getTitle() {
    return title;
  }

  /**
   * setTitle method, sets the string value of title for Movie Entity
   * @param title
     */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * getFormat method, returns integer value of FK ID on Format, for Movie Entity
   * @return
     */
  public int getFormat() {
    return format;
  }

  /**
   * setFormat method, sets the integer value of FK ID on Format, for Movie Entity
   * @param format
     */
  public void setFormat(int format) {
    this.format = format;
  }

  /**
   * getGenre method, returns integer value of FK ID on Genre, for Movie Entity
   * @return
     */
  public int getGenre() {
    return genre;
  }

  /**
   * setGenre method, sets the integer value of FK ID on Genre, for Movie Entity
   * @param genre
     */
  public void setGenre(int genre) {
    this.genre = genre;
  }

  /**
   * getDirector method, returns integer value of FK ID on Director, for Movie Entity
   * @return
     */
  public int getDirector() {
    return director;
  }

  /**
   * setDirector method, sets the integer value of FK ID from Director, for Movie Entity
   * @param director
     */
  public void setDirector(int director) {
    this.director = director;
  }

  /**
   * getStudio method, returns integer value of FK ID on Studio, for Movie Entity
   * @return
     */
  public int getStudio() {
    return studio;
  }

  /**
   * setStudio method, sets the integer value of FK ID on Studio, for Movie Entity
   * @param studio
     */
  public void setStudio(int studio) {
    this.studio = studio;
  }

  /**
   * getImdbid method, returns the string value of IMDB id from Movie Entity
   * @return
     */
  public String getImdbid() {
    return imdbid;
  }

  /**
   * setImdbid method, sets the string value of IMDB id on Movie Entity
   * @param imdbid
     */
  public void setImdbid(String imdbid) {
    this.imdbid = imdbid;
  }

  /**
   * getUPCcode method, returns string value of UPCcode from Movie Entity
   * @return
     */
  public String getUpccode() {
    return upccode;
  }

  /**
   * setUpcCode method, sets the string value of UPCcode on Movie Entity
   * @param upccode
     */
  public void setUpccode(String upccode) {
    this.upccode = upccode;
  }

  /**
   * getReleaseDate method, returns DateTime value of release date for Movie Entity
   * @return
     */
  public DateTime getReleaseDate() {
    return releaseDate;
  }

  /**
   * setReleaseDate method, sets the DateTime value of release date on Movie Entity
   * @param releaseDate
     */
  public void setReleaseDate(DateTime releaseDate) {
    this.releaseDate = releaseDate;
  }
}
