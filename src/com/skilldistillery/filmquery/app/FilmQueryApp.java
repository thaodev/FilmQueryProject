package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.ArrayList;
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
		// app.test();
		app.launch();
	}

	private void test() throws SQLException {
		Film film = db.findFilmById(1);
		System.out.println(film);
		List<Actor> actorList = film.getActorList();
		System.out.println("Actor list of film: " + film.getTitle() + " is: ");
		for (Actor item : actorList) {
			System.out.println(item);
		}
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		boolean isContinued = true;

		while (isContinued) {

			menuDisplay();
			int choice = 0;

			while (true) {
				try {
					choice = Integer.parseInt(input.next());
					break;
				} catch (Exception e) {
					System.out.println("Invalid input. Try Again!");
				}
			}
			switch (choice) {
			case 1:
				System.out.println("Enter the id: ");
				int filmId = input.nextInt();
				Film film = lookUpFilmById(filmId);
				if (film == null) {
					System.out.println("Not found");
				} else {
					System.out.println(film);
					displaySubMenu(input, film);
				}
				break;

			case 2:
				System.out.println("Enter the search keyword: ");
				input.nextLine();
				String search = input.nextLine();
				List<Film> films = lookUpFilmBySearchKeyWord(search);
				if (films.size() == 0 || films == null) {
					System.out.println("Not found");
				} else {
					System.out.println("Total films found: " + films.size());
					for (Film item : films) {
						System.out.println(item);
						System.out.println("----");
					}
				}
				break;

			default:
				System.out.println("EXIT PROGRAM! GOOD BYE!!!");
				isContinued = false;
				break;
			}

		}

	}

	private void displaySubMenu(Scanner userInput, Film film) {
		subMenu();
		System.out.println("User enter: ");
		int userChoice = 0;
		while (true) {
			try {
				userChoice = Integer.parseInt(userInput.next());
				break;
			} catch (Exception e) {
				System.out.println("Invalid input. Try Again!");
			}
		}
		switch (userChoice) {
		case 1:
			startUserInterface(userInput);
			break;

		case 2:
			film.displayFilmDetails();
			break;
		}
	}

	// for LABS
//		System.out.println("Enter actor id: ");
//		int actorId = input.nextInt();
//		Actor actor = db.findActorById(actorId);
//		if (actor == null) {
//			System.out.println("There is no actor found with given id.");
//		} else {
//			System.out.println("The actor found is " + actor);
//		}
//
//		System.out.println("Enter the film id: ");
//		int fimlId = input.nextInt();
//		List<Actor> actorList = db.findActorsByFilmId(fimlId);
//		System.out.println("The actor list of given film id is: ");
//		for (Actor item : actorList) {
//			System.out.println(item);
//		}

	private void menuDisplay() {
		System.out.println();
		System.out.println("==============Film Query==================");
		System.out.println("1. Look up a film by its id.");
		System.out.println("2. Look up a film by a search keyword.");
		System.out.println("3. Exit the application.");
	}

	private void subMenu() {
		System.out.println();
		System.out.println("============Sub Menu============");
		System.out.println("1. Return to the main menu");
		System.out.println("2. View all film details");
	}

	private Film lookUpFilmById(int id) {
		Film film = db.findFilmById(id);
		return film;

	}

	private List<Film> lookUpFilmBySearchKeyWord(String search) {
		List<Film> films = db.findFilmsBySearchKeyWord(search);
		return films;
	}

}
