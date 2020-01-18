package com.elibrary.exceptions;

public class UserDataException extends Exception{
	private static final long serialVersionUID = 7032037278131910693L;

	public UserDataException() {
		super();
	}

	public UserDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserDataException(String message) {
		super(message);
	}

	public UserDataException(Throwable cause) {
		super(cause);
	}
}
