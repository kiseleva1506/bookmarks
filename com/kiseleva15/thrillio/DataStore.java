package com.kiseleva15.thrillio;

import java.util.ArrayList;
import java.util.List;

import com.kiseleva15.thrillio.constants.Gender;
import com.kiseleva15.thrillio.entities.Bookmark;
import com.kiseleva15.thrillio.entities.User;
import com.kiseleva15.thrillio.entities.UserBookmark;
import com.kiseleva15.thrillio.managers.BookmarkManager;
import com.kiseleva15.thrillio.managers.UserManager;
import com.kiseleva15.thrillio.util.IOUtil;

public class DataStore {

	private static List<User> users = new ArrayList<>();
	private static List<List<Bookmark>> bookmarks = new ArrayList<>();
	private static List<UserBookmark> userBookmarks = new ArrayList<>();

	public static void loadData() {
		loadUsers();
		loadWebLinks();
		loadMovies();
		loadBooks();
	}

	private static void loadUsers() {
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "User");

		for (String row : data) {
			String[] values = row.split("\t");

			int gender = Gender.MALE;
			if (values[5].equals("f")) {
				gender = Gender.FEMALE;
			} else if (values[5].equals("t")) {
				gender = Gender.TRANSGENDER;
			}

			users.add(UserManager.getInstance().createUser(Long.parseLong(values[0]), values[1], values[2],
					values[3], values[4], gender, values[6]));
		}
	}

	private static void loadWebLinks() {
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "WebLink");

		List<Bookmark> bookmarkList = new ArrayList<>();
		for (String row : data) {
			String[] parts = row.split("\t");
			bookmarkList.add(BookmarkManager.getInstance().createWebLink(Long.parseLong(parts[0]), parts[1],
					parts[2], parts[3]));
		}
		bookmarks.add(bookmarkList);
	}

	private static void loadMovies() {
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "Movie");
		
		List<Bookmark> bookmarkList = new ArrayList<>();
		for (String row : data) {
			String[] parts = row.split("\t");

			String[] cast = parts[3].split(",");
			String[] directors = parts[4].split(",");

			bookmarkList.add(BookmarkManager.getInstance().createMovie(Long.parseLong(parts[0]), parts[1],
					Integer.parseInt(parts[2]), cast, directors, parts[5], Double.parseDouble(parts[6])));
		}
		bookmarks.add(bookmarkList);
	}

	private static void loadBooks() {
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "Book");
		
		List<Bookmark> bookmarkList = new ArrayList<>();
		for (String row : data) {
			String[] parts = row.split("\t");
			
			String[] authors = parts[4].split(",");
			
			bookmarkList.add(BookmarkManager.getInstance().createBook(Long.parseLong(parts[0]), parts[1], 
					Integer.parseInt(parts[2]), parts[3], authors, parts[5], Double.parseDouble(parts[6])));
		}
		bookmarks.add(bookmarkList);
	}

	public static List<List<Bookmark>> getBookmarks() {
		return bookmarks;
	}

	public static List<User> getUsers() {
		return users;
	}

	public static void add(UserBookmark userBookmark) {
		userBookmarks.add(userBookmark);
	}
}
