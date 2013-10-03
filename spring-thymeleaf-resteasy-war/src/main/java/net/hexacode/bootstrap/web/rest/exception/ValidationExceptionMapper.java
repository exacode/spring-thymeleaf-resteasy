package net.hexacode.bootstrap.web.rest.exception;

import java.util.Collection;

import javax.validation.ConstraintViolation;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.hibernate.validator.method.MethodConstraintViolation;
import org.hibernate.validator.method.MethodConstraintViolationException;
import org.springframework.stereotype.Component;

@Provider
@Component
@SuppressWarnings("deprecation")
public class ValidationExceptionMapper implements
		ExceptionMapper<MethodConstraintViolationException> {

	@Override
	public Response toResponse(MethodConstraintViolationException exception) {
		String msg = getConstaintViolationMessages(exception
				.getConstraintViolations());
		return new ExceptionResponseBuilder()
				.status(Status.BAD_REQUEST.getStatusCode())
				.cause(exception).message(msg).buildExceptionResponse();
	}

	private String getConstaintViolationMessages(
			Collection<MethodConstraintViolation<?>> constaintViolations) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (ConstraintViolation<?> constaintViolation : constaintViolations) {
			if (first) {
				first = false;
			} else {
				sb.append(", ");
			}
			sb.append(constaintViolation.getInvalidValue());
			sb.append(" - ");
			sb.append(constaintViolation.getMessage());
		}
		return sb.toString();
	}

}
