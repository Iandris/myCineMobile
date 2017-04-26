package com.youngmike.mycinemobile.entity;

/**
 * UserFriends entity class for MyCineMobile
 */
public class UserFriends {

  private int idConnector;
  private int friend_a;
  private int friend_b;

  /**
   * setidConnecto public setter method
   * @param idConnector
     */
  public void setIdConnector(int idConnector) {
    this.idConnector = idConnector;
  }

  /**
   * getIDConnector public getter method
   * @return
     */
  public int getIdConnector() {
    return idConnector;
  }

  /**
   * getFriendIdA public getter method
   * @return
     */
  public int getFriendidA() {
    return friend_a;
  }

  /**
   * setFriendidA public setter method
   * @param id_1
     */
  public void setFriendidA(int id_1) {
    this.friend_a = id_1;
  }

  /**
   * getFriendIdB public getter method
   * @return
     */
  public int getFriendidB() {
    return friend_b;
  }

  /**
   * setFriendIDb  public setter method
   * @param id_2
     */
  public void setFriendidB(int id_2) {
    this.friend_b = id_2;
  }

}
