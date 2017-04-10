package com.youngmike.mycinemobile.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ImageEntitiesItem{

	@JsonProperty("largeImage")
	private String largeImage;

	@JsonProperty("entityType")
	private String entityType;

	@JsonProperty("mediumImage")
	private String mediumImage;

	@JsonProperty("thumbnailImage")
	private String thumbnailImage;

	public void setLargeImage(String largeImage){
		this.largeImage = largeImage;
	}

	public String getLargeImage(){
		return largeImage;
	}

	public void setEntityType(String entityType){
		this.entityType = entityType;
	}

	public String getEntityType(){
		return entityType;
	}

	public void setMediumImage(String mediumImage){
		this.mediumImage = mediumImage;
	}

	public String getMediumImage(){
		return mediumImage;
	}

	public void setThumbnailImage(String thumbnailImage){
		this.thumbnailImage = thumbnailImage;
	}

	public String getThumbnailImage(){
		return thumbnailImage;
	}

	@Override
 	public String toString(){
		return 
			"ImageEntitiesItem{" + 
			"largeImage = '" + largeImage + '\'' + 
			",entityType = '" + entityType + '\'' + 
			",mediumImage = '" + mediumImage + '\'' + 
			",thumbnailImage = '" + thumbnailImage + '\'' + 
			"}";
		}
}