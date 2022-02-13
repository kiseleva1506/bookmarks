package com.kiseleva15.thrillio.dao;

import java.util.List;

import com.kiseleva15.thrillio.DataStore;
import com.kiseleva15.thrillio.entities.User;

public class UserDao {
	public List<User> getUsers() {
		return DataStore.getUsers();
	}
}
