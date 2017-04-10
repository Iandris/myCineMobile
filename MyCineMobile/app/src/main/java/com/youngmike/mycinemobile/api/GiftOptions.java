package com.youngmike.mycinemobile.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class GiftOptions{

	@JsonProperty("allowGiftReceipt")
	private boolean allowGiftReceipt;

	@JsonProperty("allowGiftWrap")
	private boolean allowGiftWrap;

	@JsonProperty("allowGiftMessage")
	private boolean allowGiftMessage;

	public void setAllowGiftReceipt(boolean allowGiftReceipt){
		this.allowGiftReceipt = allowGiftReceipt;
	}

	public boolean isAllowGiftReceipt(){
		return allowGiftReceipt;
	}

	public void setAllowGiftWrap(boolean allowGiftWrap){
		this.allowGiftWrap = allowGiftWrap;
	}

	public boolean isAllowGiftWrap(){
		return allowGiftWrap;
	}

	public void setAllowGiftMessage(boolean allowGiftMessage){
		this.allowGiftMessage = allowGiftMessage;
	}

	public boolean isAllowGiftMessage(){
		return allowGiftMessage;
	}

	@Override
 	public String toString(){
		return 
			"GiftOptions{" + 
			"allowGiftReceipt = '" + allowGiftReceipt + '\'' + 
			",allowGiftWrap = '" + allowGiftWrap + '\'' + 
			",allowGiftMessage = '" + allowGiftMessage + '\'' + 
			"}";
		}
}