package com.youngmike.mycinemobile.entity;

import org.joda.time.DateTime;

public class Message {

  private int idmessagelog;

  private int senderid;

  private int recipientid;

  private String messagebody;

  private DateTime datetime;

  public int getIdmessagelog() {
    return idmessagelog;
  }

  public int getSenderid() {
    return senderid;
  }

  public void setSenderid(int senderid) {
    this.senderid = senderid;
  }

  public int getRecipientid() {
    return recipientid;
  }

  public void setRecipientid(int recipientid) {
    this.recipientid = recipientid;
  }

  public String getMessagebody() {
    return messagebody;
  }

  public void setMessagebody(String messagebody) {
    this.messagebody = messagebody;
  }

  public DateTime getDatetime() {
    return datetime;
  }

  public void setDatetime(DateTime datetime) {
    this.datetime = datetime;
  }

}
