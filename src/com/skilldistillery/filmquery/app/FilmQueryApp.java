package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) throws SQLException {
    FilmQueryApp app = new FilmQueryApp();
    app.test();
    app.launch();
  }

  private void test() throws SQLException {
    Film film = db.findFilmById(1);
    System.out.println(film);
    List<Actor> actorList = film.getActorList();
    System.out.println("Actor list of film: "+ film.getTitle() + " is: ");
    for(Actor item : actorList) {
		  System.out.println(item);
	  }
  }

  private void launch() {
    Scanner input = new Scanner(System.in);
    
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) {
	  System.out.println("Enter actor id: ");
	  int actorId = input.nextInt();
	  Actor actor = db.findActorById(actorId);
	  if (actor == null) {
		  System.out.println("There is no actor found with given id.");
	  } else {
		  System.out.println("The actor found is " + actor);
	  }
	  
	  System.out.println("Enter the film id: ");
	  int fimlId = input.nextInt();
	  List<Actor> actorList = db.findActorsByFilmId(fimlId);
	  System.out.println("The actor list of given film id is: ");
	  for(Actor item : actorList) {
		  System.out.println(item);
	  }
  
    
  }

}
