package com.youngmike.mycinemobile.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class BestMarketplacePrice{

	@JsonProperty("offerType")
	private String offerType;

	@JsonProperty("standardShipRate")
	private double standardShipRate;

	@JsonProperty("price")
	private double price;

	@JsonProperty("clearance")
	private boolean clearance;

	@JsonProperty("availableOnline")
	private boolean availableOnline;

	@JsonProperty("sellerInfo")
	private String sellerInfo;

	public void setOfferType(String offerType){
		this.offerType = offerType;
	}

	public String getOfferType(){
		return offerType;
	}

	public void setStandardShipRate(double standardShipRate){
		this.standardShipRate = standardShipRate;
	}

	public double getStandardShipRate(){
		return standardShipRate;
	}

	public void setPrice(double price){
		this.price = price;
	}

	public double getPrice(){
		return price;
	}

	public void setClearance(boolean clearance){
		this.clearance = clearance;
	}

	public boolean isClearance(){
		return clearance;
	}

	public void setAvailableOnline(boolean availableOnline){
		this.availableOnline = availableOnline;
	}

	public boolean isAvailableOnline(){
		return availableOnline;
	}

	public void setSellerInfo(String sellerInfo){
		this.sellerInfo = sellerInfo;
	}

	public String getSellerInfo(){
		return sellerInfo;
	}

	@Override
 	public String toString(){
		return 
			"BestMarketplacePrice{" + 
			"offerType = '" + offerType + '\'' + 
			",standardShipRate = '" + standardShipRate + '\'' + 
			",price = '" + price + '\'' + 
			",clearance = '" + clearance + '\'' + 
			",availableOnline = '" + availableOnline + '\'' + 
			",sellerInfo = '" + sellerInfo + '\'' + 
			"}";
		}
}