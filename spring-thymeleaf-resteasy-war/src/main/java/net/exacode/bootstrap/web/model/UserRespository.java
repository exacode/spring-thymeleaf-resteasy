package net.exacode.bootstrap.web.model;

import java.util.ArrayList;
import java.util.List;

import net.exacode.bootstrap.rest.api.UserDto;

import org.springframework.stereotype.Repository;

@Repository
public class UserRespository {
	private final List<UserDto> users = new ArrayList<UserDto>();

	private int lastId = 0;

	public UserRespository() {
		String[] names = new String[] { "John", "Steve", "Bill" };
		for (int i = 0; i < 5; ++i) {
			UserDto user = new UserDto();
			user.setId(lastId++);
			user.setLogin(names[i % 3] + i);
			users.add(user);
		}
	}

	public List<UserDto> getUsers(int limit, int offset) {
		return users.subList(offset, Math.min(offset + limit, users.size()));
	}

	synchronized public void addUsers(List<UserDto> users) {
		for (UserDto user : users) {
			user.setId(lastId++);
			addUser(user);
		}
	}

	synchronized public void addUser(UserDto user) {
		user.setId(lastId++);
		this.users.add(user);
	}
}
