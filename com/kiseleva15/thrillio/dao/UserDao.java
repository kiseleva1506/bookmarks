package com.kiseleva15.thrillio.dao;

import com.kiseleva15.thrillio.DataStore;
import com.kiseleva15.thrillio.entities.User;

public class UserDao {
	public User[] getUsers() {
		return DataStore.getUsers();
	}
}
