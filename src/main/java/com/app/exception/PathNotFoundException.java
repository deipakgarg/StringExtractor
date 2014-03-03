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

public class PathNotFoundException extends Exception
{
	/**
	 *
	 */
	private static final long serialVersionUID = 2199350996210976169L;
	
	public PathNotFoundException()
	{
		super();
	}
	
	public PathNotFoundException(String message)
	{
		super(message);
	}
	
	public PathNotFoundException(Throwable cause)
	{
		super(cause);
	}
	
	public PathNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
