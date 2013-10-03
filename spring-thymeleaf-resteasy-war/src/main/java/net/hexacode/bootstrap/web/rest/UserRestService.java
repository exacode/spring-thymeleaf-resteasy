package net.hexacode.bootstrap.web.rest;

import java.util.List;

import net.hexacode.bootstrap.rest.api.UserDto;
import net.hexacode.bootstrap.rest.api.UserService;
import net.hexacode.bootstrap.web.model.UserRespository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
public class UserRestService implements UserService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRespository userRespository;

	@Override
	public List<UserDto> getUsers(int limit, int offset) {
		logger.trace("Limit: {}, offset:{}", limit, offset);
		return userRespository.getUsers(limit, offset);
	}

	@Override
	public void createUsers(UserDto user) {
		userRespository.addUser(user);
	}

}
