package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	private DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.test();
//    app.launch();
	}

	private void test() {
		Film film = db.findFilmById(1);
		System.out.println(film);
		
		List<Actor> actorsbyfilm = db.findActorsByFilmId(4);
		actorsbyfilm.forEach((actor) -> System.out.println(actor));

		Actor actor = db.findActorById(99);
		if (actor != null) {
			System.out.println(actor);
		} else {
			System.out.println("No such actor found.");
		}
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {

	}

}
