package com.youngmike.mycinemobile.com.mycine;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Titles{

	@JsonProperty("Titles")
	private List<TitlesItem> titles;

	public void setTitles(List<TitlesItem> titles){
		this.titles = titles;
	}

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