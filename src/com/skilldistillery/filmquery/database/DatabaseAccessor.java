package com.skilldistillery.filmquery.database;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Inventory;

public interface DatabaseAccessor {
  public Film findFilmById(int filmId);
  public List<Film> findFilmsBySearchKeyWord(String search);
  public Actor findActorById(int actorId);
  public List<Actor> findActorsByFilmId(int filmId);
  public String findCategoryByFilmId(int filmId);
  public List<Inventory> getFilmInventory(int filmId);
}
