package com.apruve;

/**
 * A simple value object that encapsulates the HTTP response code and response
 * object that result from invoking an Apruve API call.
 * 
 * @author Todd Cochran
 * 
 * @param <T> The type of the expected return object.
 */
public class ApruveResponse<T> {
	private int responseCode;
	private T responseObject;
	private String errorResponse;

	public ApruveResponse(int response, T responseObject) {
		this(response, responseObject, null);
	}

	public ApruveResponse(int response, T payload, String errorResponse) {
		this.responseCode = response;
		this.responseObject = payload;
		this.errorResponse = errorResponse;
	}
	
	public boolean success() {
		return errorResponse == null;
	}
	
	/**
	 * @return 
	 */
	public int getResponseCode() {
		return responseCode;
	}

	public T getResponseObject() {
		return responseObject;
	}

	public String getErrorResponse() {
		return errorResponse;
	}
}
