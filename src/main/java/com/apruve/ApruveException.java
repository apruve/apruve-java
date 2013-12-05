package com.apruve;

/**
 * @author Robert Nelson
 * @since 0.1
 */
public class ApruveException extends RuntimeException
{
	private static final long serialVersionUID = 4569854260718423835L;


	/**
	 * @param message
	 * @param cause
	 */
	public ApruveException(String message, Throwable cause)
	{
		super(message, cause);
	}


	/**
	 * @param message
	 */
	public ApruveException(String message)
	{
		super(message);
	}
}
