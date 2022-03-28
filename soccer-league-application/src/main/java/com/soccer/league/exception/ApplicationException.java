package com.soccer.league.exception;

public class ApplicationException extends RuntimeException{

	
	private static final long serialVersionUID = -6469005961240655034L;
	
	public ApplicationException() {
		super();
	}
	
	public ApplicationException(String message) {
		super(message);
	}
	
	public ApplicationException(String message,Throwable e) {
		super(message,e);
	}

}
