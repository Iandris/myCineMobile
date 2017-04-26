package com.youngmike.mycinemobile.com.mycine;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * TitlesItem class for MyCineMobile, part of mycine api call
 * Author: Mike Young
 */

@Generated("com.robohorse.robopojogenerator")
public class TitlesItem{

	@JsonProperty("IMDB id:")
	private String iMDBId;

	@JsonProperty("Movie Title:")
	private String movieTitle;

	/**
	 * setIMDBid method, public setter for attribute value
	 * @param iMDBId
     */
	public void setIMDBId(String iMDBId){
		this.iMDBId = iMDBId;
	}

	/**
	 * getIMDBid method, public getter method for attribute
	 * @return
     */
	public String getIMDBId(){
		return iMDBId;
	}

	/**
	 * setMovieTitle method, public setter method
	 * @param movieTitle
     */
	public void setMovieTitle(String movieTitle){
		this.movieTitle = movieTitle;
	}

	/**
	 * getMovieTitle method, public getter method
	 * @return
     */
	public String getMovieTitle(){
		return movieTitle;
	}

	/**
	 * toString, returns string value of object
	 * @return
     */
	@Override
 	public String toString(){
		return 
			"TitlesItem{" + 
			"iMDB id: = '" + iMDBId + '\'' + 
			",movie Title: = '" + movieTitle + '\'' + 
			"}";
		}
}