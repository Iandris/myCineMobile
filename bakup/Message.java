package com.youngmike.mycinemobile.entity;

import org.joda.time.DateTime;

/**
 * Message Entity for MyCineMobile - this table will track communication between users in a future release
 */
public class Message {

  private int idmessagelog;
  private int senderid;
  private int recipientid;
  private String messagebody;
  private DateTime datetime;

  /**
   * getIDMessageLog method, returns integer value of ID from Message Entity
   * @return
     */
  public int getIdmessagelog() {
    return idmessagelog;
  }

  /**
   * getSenderID method, returns ID value of FK ID on User, for Message Entity
   * @return
     */
  public int getSenderid() {
    return senderid;
  }

  /**
   * setSenderID method, sets the integer value of ID from FK of User, for Message Entity
   * @param senderid
     */
  public void setSenderid(int senderid) {
    this.senderid = senderid;
  }

  /**
   * getRecipientID method, returns integer value of FK ID on User, for Message Entity
   * @return
     */
  public int getRecipientid() {
    return recipientid;
  }

  /**
   * setRecipientID method, sets the integer value of ID from FK on User, for Message Entity
   * @param recipientid
     */
  public void setRecipientid(int recipientid) {
    this.recipientid = recipientid;
  }

  /**
   * getMessageBody method, return string value of message body from Message Entity
   * @return
     */
  public String getMessagebody() {
    return messagebody;
  }

  /**
   * setMessageBody method, sets the string value of Message body on Message Entity
   * @param messagebody
     */
  public void setMessagebody(String messagebody) {
    this.messagebody = messagebody;
  }

  /**
   * getDateTime mehod, returns datetime value of timestamp for Message entity
   * @return
     */
  public DateTime getDatetime() {
    return datetime;
  }

  /**
   * setDateTime method, sets the datetime value for timestamp on Message Entity
   * @param datetime
     */
  public void setDatetime(DateTime datetime) {
    this.datetime = datetime;
  }

}
