package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Inventory;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		String user = "student";
		String pass = "student";
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);

			// ...
			String sql = "SELECT * FROM film WHERE id = ?";

			PreparedStatement stmt;

			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, filmId);

			ResultSet filmResult = stmt.executeQuery();
			if (filmResult.next()) {
				filmId = filmResult.getInt("id");
				String title = filmResult.getString("title");
				String desc = filmResult.getString("description");
				short releaseYear = filmResult.getShort("release_year");
				int langId = filmResult.getInt("language_id");
				String language = findLanguageById(langId);
				int rentDur = filmResult.getInt("rental_duration");
				double rate = filmResult.getDouble("rental_rate");
				int length = filmResult.getInt("length");
				double repCost = filmResult.getDouble("replacement_cost");
				String rating = filmResult.getString("rating");
				String features = filmResult.getString("special_features");
				film = new Film(filmId, title, desc, releaseYear, language, rentDur, rate, length, repCost, rating,
						features);
			}

			// ...
			filmResult.close();
			stmt.close();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		String user = "student";
		String pass = "student";
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);

			// ...
			String sql = "SELECT * FROM actor WHERE id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);

			ResultSet actorResult = stmt.executeQuery();

			if (actorResult.next()) {
				actor = new Actor(); // Create the object
				// Here is our mapping of query columns to our object fields:
				actor.setId(actorResult.getInt("id"));
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));
			}
			// ...
			actorResult.close();
			stmt.close();

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actor;
	}

//Implement findActorsByFilmId with an appropriate List implementation that will be populated using a ResultSet and returned.
	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		String user = "student";
		String pass = "student";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT * FROM actor JOIN film_actor ON actor.id = film_actor.actor_id "
					+ " WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int actorId = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");

				Actor actor = new Actor(actorId, firstName, lastName);
				actors.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actors;
	}

	public List<Film> findFilmsBySearchKeyWord(String searchWord) {
		List<Film> films = new ArrayList<>();
		String user = "student";
		String pass = "student";
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT * FROM film WHERE title LIKE ? OR description LIKE ?";

			PreparedStatement stmt;

			stmt = conn.prepareStatement(sql);
			searchWord = "%" + searchWord + "%";
			stmt.setString(1, searchWord);
			stmt.setString(2, searchWord);

			ResultSet filmResult = stmt.executeQuery();
			while (filmResult.next()) {
				int filmId = filmResult.getInt("id");
				String title = filmResult.getString("title");
				String desc = filmResult.getString("description");
				short releaseYear = filmResult.getShort("release_year");
				int langId = filmResult.getInt("language_id");
				String language = findLanguageById(langId);
				int rentDur = filmResult.getInt("rental_duration");
				double rate = filmResult.getDouble("rental_rate");
				int length = filmResult.getInt("length");
				double repCost = filmResult.getDouble("replacement_cost");
				String rating = filmResult.getString("rating");
				String features = filmResult.getString("special_features");
				films.add( new Film(filmId, title, desc, releaseYear, language, rentDur, rate, length, repCost, rating,
						features));

			}
			filmResult.close();
			stmt.close();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return films;

	}

	public String findCategoryByFilmId(int filmId) {
		String category = "";
		String user = "student";
		String pass = "student";
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);

			// ...
			String sql = "SELECT name FROM category " + "JOIN film_category ON category_id = film_category.category_id"
					+ " WHERE film_id = ?";

			PreparedStatement stmt;

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				category = rs.getString("name");
			}
			rs.close();
			stmt.close();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return category;
	}
	
	public String findLanguageById(int id) {
		String language = null;
		String user = "student";
		String pass = "student";
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);

			// ...
			String sql = "SELECT name FROM language WHERE id = ?";

			PreparedStatement stmt;

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				language = rs.getString("name");
			}
			rs.close();
			stmt.close();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return language;
	}
	
	public List<Inventory> getFilmInventory(int filmId) {
		List<Inventory> filmInventories = new ArrayList<>();
		String user = "student";
		String pass = "student";
		
		int store_id = 0;
		
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);

			// ...
			String sql = "SELECT store_id, media_condition FROM inventory_item WHERE film_id = ?";

			PreparedStatement stmt;

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int storeId = rs.getInt("store_id");
				String condition = rs.getString("media_condition");
				filmInventories.add(new Inventory(storeId, condition));
			}
			rs.close();
			stmt.close();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return filmInventories;
	}

}
