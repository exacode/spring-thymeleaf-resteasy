package net.hexacode.bootstrap.rest.api;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/users")
public interface UserService {

	public static final String DEFAULT_LIMIT = "10";

	public static final String DEFAULT_OFFSET = "0";

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	List<UserDto> getUsers(
			@QueryParam("limit") @DefaultValue(DEFAULT_LIMIT) int limit,
			@QueryParam("offset") @DefaultValue(DEFAULT_OFFSET) int offset);

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	void createUsers(@Valid UserDto user);

}
