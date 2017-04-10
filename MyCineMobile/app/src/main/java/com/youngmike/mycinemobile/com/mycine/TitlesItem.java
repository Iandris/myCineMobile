package com.youngmike.mycinemobile.com.mycine;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class TitlesItem{

	@JsonProperty("IMDB id:")
	private String iMDBId;

	@JsonProperty("Movie Title:")
	private String movieTitle;

	public void setIMDBId(String iMDBId){
		this.iMDBId = iMDBId;
	}

	public String getIMDBId(){
		return iMDBId;
	}

	public void setMovieTitle(String movieTitle){
		this.movieTitle = movieTitle;
	}

	public String getMovieTitle(){
		return movieTitle;
	}

	@Override
 	public String toString(){
		return 
			"TitlesItem{" + 
			"iMDB id: = '" + iMDBId + '\'' + 
			",movie Title: = '" + movieTitle + '\'' + 
			"}";
		}
}