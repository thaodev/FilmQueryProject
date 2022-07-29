package com.skilldistillery.filmquery.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;

public class Film {
	private int filmId;
	private String title;
	private String description;
	private short releaseYear;
	private int languageId;
	private int rentDuration;
	private double rate;
	private int length;
	private double replacementCost;
	private String rating;
	private String features;
	
	private List<Actor> actorList;
	
	
	public Film() {
		
	}


	public Film(int filmId, String title, String description, short releaseYear, int languageId, int rentDuration,
			double rate, int length, double replacementCost, String rating, String features) {
		
		this.filmId = filmId;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.rentDuration = rentDuration;
		this.rate = rate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.features = features;
	}


	public int getFilmId() {
		return filmId;
	}


	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public short getReleaseYear() {
		return releaseYear;
	}


	public void setReleaseYear(short releaseYear) {
		this.releaseYear = releaseYear;
	}


	public int getLanguageId() {
		return languageId;
	}


	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}


	public int getRentDuration() {
		return rentDuration;
	}


	public void setRentDuration(int rentDuration) {
		this.rentDuration = rentDuration;
	}


	public double getRate() {
		return rate;
	}


	public void setRate(double rate) {
		this.rate = rate;
	}


	public int getLength() {
		return length;
	}


	public void setLength(int length) {
		this.length = length;
	}


	public double getReplacementCost() {
		return replacementCost;
	}


	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}


	public String getRating() {
		return rating;
	}


	public void setRating(String rating) {
		this.rating = rating;
	}


	public String getFeatures() {
		return features;
	}


	public void setFeatures(String features) {
		this.features = features;
	}


	@Override
	public String toString() {
		return "Film [filmId=" + filmId + ", title=" + title + ", description=" + description + ", releaseYear="
				+ releaseYear + ", languageId=" + languageId + ", rentDuration=" + rentDuration + ", rate=" + rate
				+ ", length=" + length + ", replacementCost=" + replacementCost + ", rating=" + rating + ", features="
				+ features + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(description, features, filmId, languageId, length, rate, rating, releaseYear, rentDuration,
				replacementCost, title);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(description, other.description) && Objects.equals(features, other.features)
				&& filmId == other.filmId && languageId == other.languageId && length == other.length
				&& Double.doubleToLongBits(rate) == Double.doubleToLongBits(other.rate)
				&& Objects.equals(rating, other.rating) && releaseYear == other.releaseYear
				&& rentDuration == other.rentDuration
				&& Double.doubleToLongBits(replacementCost) == Double.doubleToLongBits(other.replacementCost)
				&& Objects.equals(title, other.title);
	}


	public List<Actor> getActorList() {
		DatabaseAccessor db = new DatabaseAccessorObject();
		actorList = db.findActorsByFilmId(filmId);
		
		return actorList;
	}


	public void setActorList(List<Actor> actorList) {
		this.actorList = actorList;
	}
	
	
	
	
	
}
