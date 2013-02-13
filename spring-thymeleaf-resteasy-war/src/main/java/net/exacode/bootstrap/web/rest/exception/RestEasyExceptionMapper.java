package net.exacode.bootstrap.web.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.spi.Failure;
import org.springframework.stereotype.Component;

@Provider
@Component
public class RestEasyExceptionMapper implements ExceptionMapper<Failure> {

	@Override
	public Response toResponse(Failure exception) {
		return new ExceptionResponseBuilder().status(exception.getErrorCode())
				.cause(exception.getCause()).buildExceptionResponse();
	}

}
