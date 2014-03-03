package com.app.common;


import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 ****************************************************************************************
 *        Created By : Deepak Garg
 *        Created Date : Mar 3, 2014
 *        Last Modified By :
 *        Last Modified Date:
 ****************************************************************************************
 *
 */

public class MessageUtils
{
	private static ApplicationContext context;
	
	static
	{
		context = new ClassPathXmlApplicationContext("SpringBeans.xml");
	}
	
	public static String getMessage(String strKey)
	{
		return context.getMessage(strKey, null, Locale.US);
	}
	
	public static String getMessage(String strKey, String... strParams)
	{
		return context.getMessage(strKey, strParams, Locale.US);
	}
	
}
