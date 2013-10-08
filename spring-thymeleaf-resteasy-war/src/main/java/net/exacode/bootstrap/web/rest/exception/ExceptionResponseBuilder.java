package net.exacode.bootstrap.web.rest.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.exacode.bootstrap.rest.api.ExceptionMessage;

public class ExceptionResponseBuilder {
	private Integer status = Status.INTERNAL_SERVER_ERROR.getStatusCode();
	private Integer code;
	private String message;
	private String developerMessage;
	private String exceptionClass;

	public ExceptionResponseBuilder status(int status) {
		this.status = status;
		return this;
	}

	public ExceptionResponseBuilder code(int code) {
		this.code = code;
		return this;
	}

	public ExceptionResponseBuilder message(String message) {
		this.message = message;
		return this;
	}

	public ExceptionResponseBuilder developerMessage(String developerMessage) {
		this.developerMessage = developerMessage;
		return this;
	}

	public ExceptionResponseBuilder exceptionClass(String exceptionClass) {
		this.exceptionClass = exceptionClass;
		return this;
	}

	public ExceptionResponseBuilder cause(Throwable throwable) {
		exceptionClass = throwable.getClass().getCanonicalName();
		message(throwable.getLocalizedMessage());
		developerMessage(getStackTrace(throwable));
		return this;
	}

	private String getStackTrace(Throwable exception) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		exception.printStackTrace(pw);
		return sw.getBuffer().toString();
	}

	public ExceptionMessage buildExceptionMessage() {
		ExceptionMessage exceptionMessage = new ExceptionMessage();
		exceptionMessage.setStatus(status);
		exceptionMessage.setCode(code);
		exceptionMessage.setMessage(message);
		exceptionMessage.setDeveloperMessage(developerMessage);
		exceptionMessage.setExceptionClass(exceptionClass);
		return exceptionMessage;
	}

	public Response buildExceptionResponse() {
		ExceptionMessage exceptionMessage = buildExceptionMessage();
		return Response.status(status).entity(exceptionMessage).build();
	}
}
