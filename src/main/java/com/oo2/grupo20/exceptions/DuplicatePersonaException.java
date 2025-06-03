package com.oo2.grupo20.exceptions;

@SuppressWarnings("serial")
public class DuplicatePersonaException extends RuntimeException {
	 public DuplicatePersonaException(String message) {
	     super(message);
	 }
}
