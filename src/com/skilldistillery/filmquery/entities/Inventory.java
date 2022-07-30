package com.skilldistillery.filmquery.entities;

import java.util.Objects;

public class Inventory {
	
	private int id;
	private int film_id;
	private int store_id;
	private String mediaCondition;
	
	
	public Inventory(int store_id, String mediaCondition) {
		this.store_id = store_id;
		this.mediaCondition = mediaCondition;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getFilm_id() {
		return film_id;
	}


	public void setFilm_id(int film_id) {
		this.film_id = film_id;
	}


	public int getStore_id() {
		return store_id;
	}


	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}


	public String getMediaCondition() {
		return mediaCondition;
	}


	public void setMediaCondition(String mediaCondition) {
		this.mediaCondition = mediaCondition;
	}


	@Override
	public String toString() {
		return "    store_id = " + store_id + ", mediaCondition = " + mediaCondition;
	}


	@Override
	public int hashCode() {
		return Objects.hash(film_id, id, mediaCondition, store_id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		return film_id == other.film_id && id == other.id && Objects.equals(mediaCondition, other.mediaCondition)
				&& store_id == other.store_id;
	}
	
	
	
	

}
