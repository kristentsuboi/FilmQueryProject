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
		app.launch();
	}

	private void launch() {
		Scanner input = new Scanner(System.in);
		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		boolean isTrue = true;
		do {
			System.out.println("Welcome to the Film Query Application");
			System.out.println("1. Look up a film by its id.");
			System.out.println("2. Look up a film by a keyword search.");
			System.out.println("3. Exit application");
			System.out.println("Please make a selection: ");
			int selection = input.nextInt();
			input.nextLine();
			switch (selection) {
			case 1:
				System.out.println("Please enter the film id you would like to display: ");
				int filmId = input.nextInt();
				input.nextLine();
				Film result = db.findFilmById(filmId);
				List<Actor> actorsById = db.findActorsByFilmId(filmId);
				input.nextLine();
				if (result != null) {
					System.out.println(result);
					languageChanger(result.getLanguageId());
					System.out.println("Actors: ");
					for (Actor actor : actorsById) {
						System.out.println(actor.getFirstName() + " " + actor.getLastName());
					}
					System.out.println();
				} else {
					System.out.println("No film found for the film id provided.\n");
				}

				break;
			case 2:
				System.out.println("Please enter the keyword you would like to search: ");
				String keyword = input.nextLine();
				List<Film> byKeyword = db.findFilmByKeyword(keyword);
				if (byKeyword != null) {
					for (Film film : byKeyword) {
						System.out.println(film);
						languageChanger(film.getLanguageId());
						System.out.println("Actors: ");
						List<Actor> actors = db.findActorsByFilmId(film.getId());
						for (Actor actor : actors) {
							System.out.println(actor.getFirstName() + " " + actor.getLastName());
						}
						System.out.println();
					}
				} else {
					System.out.println("There is no matches to your keyword");
				}
				break;
			case 3:
				System.out.println("You are exitting the program. Goodbye!");
				isTrue = false;
				break;
			default:
				System.out.println("Please enter a valid selection.");
			}
		} while (isTrue);
	}

	public void languageChanger(int langid) {
		switch (langid) {
		case 1:
			System.out.println("Film Language: English");
			break;
		case 2:
			System.out.println("Film Language: Italian");
			break;
		case 3:
			System.out.println("Film Language: Japanese");
			break;
		case 4:
			System.out.println("Film Language: Mandarin");
			break;
		case 5:
			System.out.println("Film Language: French");
			break;
		case 6:
			System.out.println("Film Language: German");
			break;
		default:
			break;
		}
	}

}
