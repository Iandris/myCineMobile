package com.youngmike.mycinemobile.entity;

/**
 * Format Entity for MyCineMobile - this table provides FK look up for Movie Entity
 */
public class Format {

  private int idformat;
  private String formattitle;

  /**
   * getIdformat method, returns integer value for ID of Format Entity
   * @return
     */
  public int getIdformat() {
    return idformat;
  }

  /**
   * getFormattitle method, returns string value of Format Title from Format Entity
   * @return
     */
  public String getFormattitle() {
    return formattitle;
  }

  /**
   * setFormattitle method, sets the string value of Format Title on Format Entity
   * @param formattitle
     */
  public void setFormattitle(String formattitle) {
    this.formattitle = formattitle;
  }

}
