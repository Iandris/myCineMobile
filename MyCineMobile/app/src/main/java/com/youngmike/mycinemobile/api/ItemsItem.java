package com.youngmike.mycinemobile.api;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ItemsItem{

	@JsonProperty("longDescription")
	private String longDescription;

	@JsonProperty("largeImage")
	private String largeImage;

	@JsonProperty("bestMarketplacePrice")
	private BestMarketplacePrice bestMarketplacePrice;

	@JsonProperty("color")
	private String color;

	@JsonProperty("categoryNode")
	private String categoryNode;

	@JsonProperty("mediumImage")
	private String mediumImage;

	@JsonProperty("numReviews")
	private int numReviews;

	@JsonProperty("offerType")
	private String offerType;

	@JsonProperty("productTrackingUrl")
	private String productTrackingUrl;

	@JsonProperty("parentItemId")
	private int parentItemId;

	@JsonProperty("standardShipRate")
	private double standardShipRate;

	@JsonProperty("msrp")
	private double msrp;

	@JsonProperty("clearance")
	private boolean clearance;

	@JsonProperty("preOrder")
	private boolean preOrder;

	@JsonProperty("thumbnailImage")
	private String thumbnailImage;

	@JsonProperty("stock")
	private String stock;

	@JsonProperty("bundle")
	private boolean bundle;

	@JsonProperty("imageEntities")
	private List<ImageEntitiesItem> imageEntities;

	@JsonProperty("giftOptions")
	private GiftOptions giftOptions;

	@JsonProperty("brandName")
	private String brandName;

	@JsonProperty("addToCartUrl")
	private String addToCartUrl;

	@JsonProperty("salePrice")
	private double salePrice;

	@JsonProperty("categoryPath")
	private String categoryPath;

	@JsonProperty("upc")
	private String upc;

	@JsonProperty("isTwoDayShippingEligible")
	private boolean isTwoDayShippingEligible;

	@JsonProperty("shipToStore")
	private boolean shipToStore;

	@JsonProperty("itemId")
	private int itemId;

	@JsonProperty("customerRatingImage")
	private String customerRatingImage;

	@JsonProperty("customerRating")
	private String customerRating;

	@JsonProperty("affiliateAddToCartUrl")
	private String affiliateAddToCartUrl;

	@JsonProperty("name")
	private String name;

	@JsonProperty("freeShipToStore")
	private boolean freeShipToStore;

	@JsonProperty("modelNumber")
	private String modelNumber;

	@JsonProperty("availableOnline")
	private boolean availableOnline;

	@JsonProperty("productUrl")
	private String productUrl;

	@JsonProperty("freeShippingOver50Dollars")
	private boolean freeShippingOver50Dollars;

	@JsonProperty("ninetySevenCentShipping")
	private boolean ninetySevenCentShipping;

	public void setLongDescription(String longDescription){
		this.longDescription = longDescription;
	}

	public String getLongDescription(){
		return longDescription;
	}

	public void setLargeImage(String largeImage){
		this.largeImage = largeImage;
	}

	public String getLargeImage(){
		return largeImage;
	}

	public void setBestMarketplacePrice(BestMarketplacePrice bestMarketplacePrice){
		this.bestMarketplacePrice = bestMarketplacePrice;
	}

	public BestMarketplacePrice getBestMarketplacePrice(){
		return bestMarketplacePrice;
	}

	public void setColor(String color){
		this.color = color;
	}

	public String getColor(){
		return color;
	}

	public void setCategoryNode(String categoryNode){
		this.categoryNode = categoryNode;
	}

	public String getCategoryNode(){
		return categoryNode;
	}

	public void setMediumImage(String mediumImage){
		this.mediumImage = mediumImage;
	}

	public String getMediumImage(){
		return mediumImage;
	}

	public void setNumReviews(int numReviews){
		this.numReviews = numReviews;
	}

	public int getNumReviews(){
		return numReviews;
	}

	public void setOfferType(String offerType){
		this.offerType = offerType;
	}

	public String getOfferType(){
		return offerType;
	}

	public void setProductTrackingUrl(String productTrackingUrl){
		this.productTrackingUrl = productTrackingUrl;
	}

	public String getProductTrackingUrl(){
		return productTrackingUrl;
	}

	public void setParentItemId(int parentItemId){
		this.parentItemId = parentItemId;
	}

	public int getParentItemId(){
		return parentItemId;
	}

	public void setStandardShipRate(double standardShipRate){
		this.standardShipRate = standardShipRate;
	}

	public double getStandardShipRate(){
		return standardShipRate;
	}

	public void setMsrp(double msrp){
		this.msrp = msrp;
	}

	public double getMsrp(){
		return msrp;
	}

	public void setClearance(boolean clearance){
		this.clearance = clearance;
	}

	public boolean isClearance(){
		return clearance;
	}

	public void setPreOrder(boolean preOrder){
		this.preOrder = preOrder;
	}

	public boolean isPreOrder(){
		return preOrder;
	}

	public void setThumbnailImage(String thumbnailImage){
		this.thumbnailImage = thumbnailImage;
	}

	public String getThumbnailImage(){
		return thumbnailImage;
	}

	public void setStock(String stock){
		this.stock = stock;
	}

	public String getStock(){
		return stock;
	}

	public void setBundle(boolean bundle){
		this.bundle = bundle;
	}

	public boolean isBundle(){
		return bundle;
	}

	public void setImageEntities(List<ImageEntitiesItem> imageEntities){
		this.imageEntities = imageEntities;
	}

	public List<ImageEntitiesItem> getImageEntities(){
		return imageEntities;
	}

	public void setGiftOptions(GiftOptions giftOptions){
		this.giftOptions = giftOptions;
	}

	public GiftOptions getGiftOptions(){
		return giftOptions;
	}

	public void setBrandName(String brandName){
		this.brandName = brandName;
	}

	public String getBrandName(){
		return brandName;
	}

	public void setAddToCartUrl(String addToCartUrl){
		this.addToCartUrl = addToCartUrl;
	}

	public String getAddToCartUrl(){
		return addToCartUrl;
	}

	public void setSalePrice(double salePrice){
		this.salePrice = salePrice;
	}

	public double getSalePrice(){
		return salePrice;
	}

	public void setCategoryPath(String categoryPath){
		this.categoryPath = categoryPath;
	}

	public String getCategoryPath(){
		return categoryPath;
	}

	public void setUpc(String upc){
		this.upc = upc;
	}

	public String getUpc(){
		return upc;
	}

	public void setIsTwoDayShippingEligible(boolean isTwoDayShippingEligible){
		this.isTwoDayShippingEligible = isTwoDayShippingEligible;
	}

	public boolean isIsTwoDayShippingEligible(){
		return isTwoDayShippingEligible;
	}

	public void setShipToStore(boolean shipToStore){
		this.shipToStore = shipToStore;
	}

	public boolean isShipToStore(){
		return shipToStore;
	}

	public void setItemId(int itemId){
		this.itemId = itemId;
	}

	public int getItemId(){
		return itemId;
	}

	public void setCustomerRatingImage(String customerRatingImage){
		this.customerRatingImage = customerRatingImage;
	}

	public String getCustomerRatingImage(){
		return customerRatingImage;
	}

	public void setCustomerRating(String customerRating){
		this.customerRating = customerRating;
	}

	public String getCustomerRating(){
		return customerRating;
	}

	public void setAffiliateAddToCartUrl(String affiliateAddToCartUrl){
		this.affiliateAddToCartUrl = affiliateAddToCartUrl;
	}

	public String getAffiliateAddToCartUrl(){
		return affiliateAddToCartUrl;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setFreeShipToStore(boolean freeShipToStore){
		this.freeShipToStore = freeShipToStore;
	}

	public boolean isFreeShipToStore(){
		return freeShipToStore;
	}

	public void setModelNumber(String modelNumber){
		this.modelNumber = modelNumber;
	}

	public String getModelNumber(){
		return modelNumber;
	}

	public void setAvailableOnline(boolean availableOnline){
		this.availableOnline = availableOnline;
	}

	public boolean isAvailableOnline(){
		return availableOnline;
	}

	public void setProductUrl(String productUrl){
		this.productUrl = productUrl;
	}

	public String getProductUrl(){
		return productUrl;
	}

	public void setFreeShippingOver50Dollars(boolean freeShippingOver50Dollars){
		this.freeShippingOver50Dollars = freeShippingOver50Dollars;
	}

	public boolean isFreeShippingOver50Dollars(){
		return freeShippingOver50Dollars;
	}

	public void setNinetySevenCentShipping(boolean ninetySevenCentShipping){
		this.ninetySevenCentShipping = ninetySevenCentShipping;
	}

	public boolean isNinetySevenCentShipping(){
		return ninetySevenCentShipping;
	}

	@Override
 	public String toString(){
		return 
			"ItemsItem{" + 
			"longDescription = '" + longDescription + '\'' + 
			",largeImage = '" + largeImage + '\'' + 
			",bestMarketplacePrice = '" + bestMarketplacePrice + '\'' + 
			",color = '" + color + '\'' + 
			",categoryNode = '" + categoryNode + '\'' + 
			",mediumImage = '" + mediumImage + '\'' + 
			",numReviews = '" + numReviews + '\'' + 
			",offerType = '" + offerType + '\'' + 
			",productTrackingUrl = '" + productTrackingUrl + '\'' + 
			",parentItemId = '" + parentItemId + '\'' + 
			",standardShipRate = '" + standardShipRate + '\'' + 
			",msrp = '" + msrp + '\'' + 
			",clearance = '" + clearance + '\'' + 
			",preOrder = '" + preOrder + '\'' + 
			",thumbnailImage = '" + thumbnailImage + '\'' + 
			",stock = '" + stock + '\'' + 
			",bundle = '" + bundle + '\'' + 
			",imageEntities = '" + imageEntities + '\'' + 
			",giftOptions = '" + giftOptions + '\'' + 
			",brandName = '" + brandName + '\'' + 
			",addToCartUrl = '" + addToCartUrl + '\'' + 
			",salePrice = '" + salePrice + '\'' + 
			",categoryPath = '" + categoryPath + '\'' + 
			",upc = '" + upc + '\'' + 
			",isTwoDayShippingEligible = '" + isTwoDayShippingEligible + '\'' + 
			",shipToStore = '" + shipToStore + '\'' + 
			",itemId = '" + itemId + '\'' + 
			",customerRatingImage = '" + customerRatingImage + '\'' + 
			",customerRating = '" + customerRating + '\'' + 
			",affiliateAddToCartUrl = '" + affiliateAddToCartUrl + '\'' + 
			",name = '" + name + '\'' + 
			",freeShipToStore = '" + freeShipToStore + '\'' + 
			",modelNumber = '" + modelNumber + '\'' + 
			",availableOnline = '" + availableOnline + '\'' + 
			",productUrl = '" + productUrl + '\'' + 
			",freeShippingOver50Dollars = '" + freeShippingOver50Dollars + '\'' + 
			",ninetySevenCentShipping = '" + ninetySevenCentShipping + '\'' + 
			"}";
		}
}