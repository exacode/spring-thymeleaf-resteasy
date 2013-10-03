package net.hexacode.bootstrap.rest.api;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExceptionMessage implements Serializable {
	/**
	 * The status property is merely the same HTTP status code (integer) in the
	 * response header. This is a convenience mechanism: by including the status
	 * code in the body, any REST client that processes the error has one and
	 * only one place to look to fully understand the error: the error
	 * representation itself. There is no need to check header values or other
	 * locations to understand the message.
	 */
	private Integer status;
	/**
	 * An error code specific that is specific for particular REST API. It is
	 * usually something that conveys information very specific to your problem
	 * domain.
	 */
	private Integer code;

	/**
	 * human readable error message that can potentially be shown directly to an
	 * application end user (not a developer). It should be friendly and easy to
	 * understand and convey a concise reason as to why the error occurred. It
	 * should probaby not contain technical information.
	 */
	private String message;

	/**
	 * conveys any and all technical information that a developer calling your
	 * REST API might find useful. This is where you might include exception
	 * messages, stack traces, or anything else that you think will help a
	 * developer.
	 */
	private String developerMessage;

	private String exceptionClass;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public String getExceptionClass() {
		return exceptionClass;
	}

	public void setExceptionClass(String exceptionClass) {
		this.exceptionClass = exceptionClass;
	}
}
