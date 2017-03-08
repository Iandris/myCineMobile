package com.youngmike.mycinemobile.entity;

/**
 * Genre Entity for MyCineMobile -  this table is a lookup providing FK for the Movie Entity
 */
public class Genre {

  private int idgenre;
  private String genretitle;

  /**
   * getIdgenre mehtod, returns integer value of ID on Genre Entity
   * @return
     */
  public int getIdgenre() {
    return idgenre;
  }

  /**
   * getGenreTitle method, returns string value of genre title from Genre Entity
   * @return
     */
  public String getGenretitle() {
    return genretitle;
  }

  /**
   * setGenreTitle method, sets the string value of genre title on Genre Entity
   * @param genretitle
     */
  public void setGenretitle(String genretitle) {
    this.genretitle = genretitle;
  }

}
