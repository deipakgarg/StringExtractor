package com.app.exception;


/**
 ****************************************************************************************
 *        Created By : Deepak Garg
 *        Created Date : Mar 3, 2014
 *        Last Modified By :
 *        Last Modified Date:
 ****************************************************************************************
 *
 */

public class InvalidStringFormatException extends Exception
{
	/**
	 *
	 */
	private static final long serialVersionUID = -6384071850282689294L;

	public InvalidStringFormatException()
	{
		super();
	}

	public InvalidStringFormatException(String message)
	{
		super(message);
	}

	public InvalidStringFormatException(Throwable cause)
	{
		super(cause);
	}

	public InvalidStringFormatException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
