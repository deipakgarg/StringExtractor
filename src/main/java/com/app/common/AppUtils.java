package com.app.common;


import static java.lang.System.out;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.app.exception.InvalidStringFormatException;
import com.app.exception.PathNotFoundException;


/**
 ****************************************************************************************
 *        Created By : Deepak Garg
 *        Created Date : Mar 3, 2014
 *        Last Modified By :
 *        Last Modified Date:
 ****************************************************************************************
 *
 */

public class AppUtils
{
	/**
	 *
	 */
	private AppUtils()
	{
		// Do Nothing
	}
	
	public static boolean isValidString(String strParam)
	{
		if (null != strParam && !strParam.equals(""))
		{
			return true;
		}
		return false;
	}
	
	public static boolean validateString(final String strInput) throws InvalidStringFormatException
	{
		try
		{
			if (!isValidString(strInput))
			{
				out.println(MessageUtils.getMessage("stringformat.error1"));
				return false;
			}
			final int iLen = strInput.length();
			if (iLen < 2)
			{
				out.println(MessageUtils.getMessage("stringformat.error2"));
				return false;
			}
			if (!Character.isDigit(strInput.charAt(0)))
			{
				out.println(MessageUtils.getMessage("stringformat.error3"));
				return false;
			}
			if (!Character.isAlphabetic(strInput.charAt(iLen - 1)))
			{
				out.println(MessageUtils.getMessage("stringformat.error4", strInput.charAt(iLen - 1) + ""));
				return false;
			}
		}
		catch (Exception e)
		{
			throw new InvalidStringFormatException(MessageUtils.getMessage("app.error1"), e);
		}
		return true;
	}
	
	public static String extractInformationByRegEx(final String strInput, final String strRegEx) throws InvalidStringFormatException
	{
		StringBuilder strOutput = new StringBuilder();
		try
		{
			Pattern pat = Pattern.compile(strRegEx);
			Matcher m = pat.matcher(strInput);
			while (m.find())
			{
				strOutput.append(m.group());
			}
		}
		catch (Exception e)
		{
			throw new InvalidStringFormatException(MessageUtils.getMessage("app.error1"), e);
		}
		return strOutput.toString();
	}
	
	public static boolean validateFilePath(String strFileLocation, String strFileName) throws PathNotFoundException
	{
		try
		{
			Path path = Paths.get(strFileLocation);
			//Files.isWritable()
			return Files.isDirectory(path) && Files.exists(Paths.get(strFileLocation, strFileName));
		}
		catch (Exception e)
		{
			throw new PathNotFoundException(MessageUtils.getMessage("app.error2", strFileLocation), e);
		}
	}
	
	public static String readContentFromFile(String strFileLocation, String strFileName) throws PathNotFoundException
	{
		try
		{
			StringBuilder strFileContent = new StringBuilder();
			Path path = Paths.get(strFileLocation + File.separator + strFileName);
			List<String> strFiles = Files.readAllLines(path, Charset.defaultCharset());
			for (String strLine : strFiles)
			{
				strFileContent.append(strLine);
			}
			return strFileContent.toString();
		}
		catch (IOException ioe)
		{
			throw new PathNotFoundException(MessageUtils.getMessage("app.error3", strFileLocation, strFileName), ioe);
		}
	}
	
	public static void writeIntoFile(String strFileLocation, String strFileName, List<String> lines) throws PathNotFoundException
	{
		try
		{
			Path path = Paths.get(strFileLocation + File.separator + strFileName);
			Files.write(path, lines, Charset.defaultCharset());
		}
		catch (IOException ioe)
		{
			throw new PathNotFoundException(MessageUtils.getMessage("app.error4", strFileLocation), ioe);
		}
	}
}
