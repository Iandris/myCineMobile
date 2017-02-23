package com.youngmike.mycinemobile.entity;

import org.joda.time.DateTime;

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

  public int getIdmovie() {
    return idmovie;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getFormat() {
    return format;
  }

  public void setFormat(int format) {
    this.format = format;
  }

  public int getGenre() {
    return genre;
  }

  public void setGenre(int genre) {
    this.genre = genre;
  }

  public int getDirector() {
    return director;
  }

  public void setDirector(int director) {
    this.director = director;
  }

  public int getStudio() {
    return studio;
  }

  public void setStudio(int studio) {
    this.studio = studio;
  }

  public String getImdbid() {
    return imdbid;
  }

  public void setImdbid(String imdbid) {
    this.imdbid = imdbid;
  }

  public String getUpccode() {
    return upccode;
  }

  public void setUpccode(String upccode) {
    this.upccode = upccode;
  }

  public DateTime getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(DateTime releaseDate) {
    this.releaseDate = releaseDate;
  }
}
