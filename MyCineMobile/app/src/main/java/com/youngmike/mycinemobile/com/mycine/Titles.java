package com.youngmike.mycinemobile.com.mycine;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * Titles class for MyCineMobile, part of mycine api call
 * Author: Mike Young
 */

@Generated("com.robohorse.robopojogenerator")
public class Titles{

	@JsonProperty("Titles")
	private List<TitlesItem> titles;

	/**
	 * setTitles method, public setter for titles list
	 * @param titles
     */
	public void setTitles(List<TitlesItem> titles){
		this.titles = titles;
	}

	/**
	 * getTitles method, public getter for titles list
	 * @return
     */
	public List<TitlesItem> getTitles(){
		return titles;
	}

	@Override
 	public String toString(){
		return 
			"Titles{" + 
			"titles = '" + titles + '\'' + 
			"}";
		}
}