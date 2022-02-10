package com.kiseleva15.thrillio;

import com.kiseleva15.thrillio.constants.Gender;
import com.kiseleva15.thrillio.entities.Bookmark;
import com.kiseleva15.thrillio.entities.User;
import com.kiseleva15.thrillio.entities.UserBookmark;
import com.kiseleva15.thrillio.managers.BookmarkManager;
import com.kiseleva15.thrillio.managers.UserManager;
import com.kiseleva15.thrillio.util.IOUtil;

public class DataStore {
	public static final int USER_BOOKMARK_LIMIT = 5;
	public static final int BOOKMARK_COUNT_PER_TYPE = 5;
	public static final int BOOKMARKS_TYPES_COUNT = 3;
	public static final int TOTAL_USER_COUNT = 5;

	private static User[] users = new User[TOTAL_USER_COUNT];
	private static Bookmark[][] bookmarks = new Bookmark[BOOKMARKS_TYPES_COUNT][BOOKMARK_COUNT_PER_TYPE];
	private static UserBookmark[] userBookmarks = new UserBookmark[TOTAL_USER_COUNT * USER_BOOKMARK_LIMIT];
	private static int bookmarkIndex = 0;

	public static void loadData() {
		loadUsers();
		loadWebLinks();
		loadMovies();
		loadBooks();
	}

	private static void loadUsers() {
		String[] data = new String[TOTAL_USER_COUNT];
		IOUtil.read(data, "User");
		int rowNum = 0;

		for (String row : data) {
			String[] values = row.split("\t");

			int gender = Gender.MALE;
			if (values[5].equals("f")) {
				gender = Gender.FEMALE;
			} else if (values[5].equals("t")) {
				gender = Gender.TRANSGENDER;
			}

			users[rowNum++] = UserManager.getInstance().createUser(Long.parseLong(values[0]), values[1], values[2],
					values[3], values[4], gender, values[6]);
		}
	}

	private static void loadWebLinks() {
		String[] data = new String[BOOKMARK_COUNT_PER_TYPE];
		IOUtil.read(data, "WebLink");
		int colNum = 0;

		for (String row : data) {
			String[] parts = row.split("\t");
			bookmarks[0][colNum++] = BookmarkManager.getInstance().createWebLink(Long.parseLong(parts[0]), parts[1],
					parts[2], parts[3]);
		}
	}

	private static void loadMovies() {
		String[] data = new String[BOOKMARK_COUNT_PER_TYPE];
		IOUtil.read(data, "Movie");
		int colNum = 0;

		for (String row : data) {
			String[] parts = row.split("\t");

			String[] cast = parts[3].split(",");
			String[] directors = parts[4].split(",");

			bookmarks[1][colNum++] = BookmarkManager.getInstance().createMovie(Long.parseLong(parts[0]), parts[1],
					Integer.parseInt(parts[2]), cast, directors, parts[5], Double.parseDouble(parts[6]));
		}
	}

	private static void loadBooks() {
		String[] data = new String[BOOKMARK_COUNT_PER_TYPE];
		IOUtil.read(data, "Book");
		int colNum = 0;
		
		for (String row : data) {
			String[] parts = row.split("\t");
			
			String[] authors = parts[4].split(",");
			
			bookmarks[2][colNum++] = BookmarkManager.getInstance().createBook(Long.parseLong(parts[0]), parts[1], 
					Integer.parseInt(parts[2]), parts[3], authors, parts[5], Double.parseDouble(parts[6]));
		}
	}

	public static Bookmark[][] getBookmarks() {
		return bookmarks;
	}

	public static User[] getUsers() {
		return users;
	}

	public static void add(UserBookmark userBookmark) {
		userBookmarks[bookmarkIndex] = userBookmark;
		bookmarkIndex++;
	}
}
