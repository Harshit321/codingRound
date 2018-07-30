/**
 * 
 */
package com.codingground.exception;

/**
 * @author hkumar
 *
 */
public class DriverIntializationException extends Exception {

	private String message;

	public DriverIntializationException(String message) {
		super();
		this.message = message;
	}
	public DriverIntializationException() {
		super();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

   
	
}
