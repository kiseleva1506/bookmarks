package com.kiseleva15.thrillio.controllers;

import com.kiseleva15.thrillio.entities.Bookmark;
import com.kiseleva15.thrillio.entities.User;
import com.kiseleva15.thrillio.managers.BookmarkManager;

public class BookmarkController {
	private static BookmarkController instance = new BookmarkController();

	private BookmarkController() {
	}

	public static BookmarkController getInstance() {
		return instance;
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().saveUserBookmark(user, bookmark);
	}
}
