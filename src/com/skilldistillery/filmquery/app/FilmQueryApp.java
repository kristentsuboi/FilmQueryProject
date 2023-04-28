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
				input.nextLine();
				if (result != null)
					System.out.println(result + "\n");
				else {
					System.out.println("No film found for the film id provided.\n");
				}
				break;
			case 2:
				System.out.println("Please enter the keyword you would like to search: ");
				String keyword = input.nextLine();
				List<Film> byKeyword = db.findFilmByKeyword(keyword);
				if (byKeyword.size() > 0) {
					byKeyword.forEach((film) -> System.out.println(film));
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


}
