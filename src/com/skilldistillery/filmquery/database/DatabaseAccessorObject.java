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
				film = new Film(filmResult.getInt("id"), filmResult.getString("title"),
						filmResult.getString("description"), filmResult.getShort("release_year"),
						filmResult.getInt("language_id"), filmResult.getInt("rental_duration"),
						filmResult.getDouble("rental_rate"), filmResult.getInt("length"),
						filmResult.getDouble("replacement_cost"), filmResult.getString("rating"),
						filmResult.getString("special_features"));

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
				actor.setFirstName(actorResult.getString("last_name"));
				actor.setLastName(actorResult.getString("first_name"));
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

}
