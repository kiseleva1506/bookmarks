package com.kiseleva15.thrillio.dao;

import java.util.List;

import com.kiseleva15.thrillio.DataStore;
import com.kiseleva15.thrillio.entities.Bookmark;
import com.kiseleva15.thrillio.entities.UserBookmark;

public class BookmarkDao {
	public List<List<Bookmark>> getBookmarks() {
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		DataStore.add(userBookmark);
	}

}
