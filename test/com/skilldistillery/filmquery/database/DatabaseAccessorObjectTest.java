package com.skilldistillery.filmquery.database;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Inventory;

public class DatabaseAccessorObjectTest {
	
	private DatabaseAccessorObject dbo;

	  @BeforeEach
	  void setUp() throws Exception {
	    dbo = new DatabaseAccessorObject();
	  }

	  @AfterEach
	  void tearDown() throws Exception {
	    dbo = null;
	  }
	  
	  @Test
	  void test_getFilmById_returns_film_with_id() {
	    Film f = dbo.findFilmById(1);
	    assertNotNull(f);
	    assertEquals("ACADEMY DINOSAUR", f.getTitle());
	  }

	  @Test
	  void test_getFilmById_with_invalid_id_returns_null() {
	    Film f = dbo.findFilmById(-42);
	    assertNull(f);
	  }
	  
	  @Test
	  void test_findActorById_returns_Actor_firstname_with_actorID() {
		  Actor actor = dbo.findActorById(7);
		  assertNotNull(actor);
		  assertEquals("Grace", actor.getFirstName());
	  }
	  
	  @Test
	  void test_findActorById_with_invalid_actorID_returns_null() {
		  Actor actor = dbo.findActorById(-7);
		  assertNull(actor);
		  
	  }
	  
	  @Test
	  void test_findActorsByFilmId_returns_Actor_firstname_with_filmid() {
		  List<Actor> actorList = dbo.findActorsByFilmId(5);
		  String outcome_actor_firstName= actorList.get(0).getFirstName();
		  assertNotNull(outcome_actor_firstName);
		  assertEquals("Gary", outcome_actor_firstName);
	  }
	  
	  @Test
	  void test_findActorsByFilmId_with_invalid_filmid_returns_null() {
		  List<Actor> actorList = dbo.findActorsByFilmId(-5);
		  assertEquals(Collections.EMPTY_LIST, actorList);
	  }
	  
	  @Test
	  void test_findFilmsBySearchKeyWord_return_film_title() {
		  List<Film> filmList = dbo.findFilmsBySearchKeyWord("Boring drama");
		  int filmId = filmList.get(0).getFilmId();
		  assertNotNull(filmList);
		  assertEquals(20, filmId);
	  }
	  
	  
	  
	  @Test
	  void test_findCategoryByFilmId_returns_category_with_filmId() {
		  String category = dbo.findCategoryByFilmId(19);
		  assertNotNull(category);
		  assertEquals("Action", category);
	  }
	  
	  @Test
	  void test_findCategoryByFilmId_with_invalid_id_returns_empty_string() {
		  String category = dbo.findCategoryByFilmId(-19);
		  assertEquals(0,category.length());
	  }
	  
	  @Test
	  void test_findLanguageById_returns_language_with_id() {
		  String lang = dbo.findLanguageById(2);
		  assertNotNull(lang);
		  assertEquals("Italian", lang);
		  
	  }
	  @Test
	  void test_findLanguageById_with_invalid_id_returns_null() {
		  String lang = dbo.findLanguageById(-2);
		  assertNull(lang);		  
	  }
	  
	  @Test
	  void test_getFilmInventory_with_filmId_returns_film() {
	    List<Inventory> inventory = dbo.getFilmInventory(23);
	    assertNotNull(inventory);
	 //Ask question on return multiple values;
	  }
	  
	  @Test
	  void test_getFilmInventory_with_invalid_id_returns_empty() {
		List<Inventory> inventory = dbo.getFilmInventory(-23);
		assertEquals(Collections.emptyList(), inventory);
	  }
	  
	  
	  
}
