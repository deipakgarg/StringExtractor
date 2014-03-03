package com.app.extractor;


import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.app.common.AppConstants;
import com.app.common.AppUtils;
import com.app.common.MessageUtils;
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

public class AlphaNumericExtractor implements StringExtractor
{
	private String fileLocation;
	private String input;
	
	@Override
	public final String getInput()
	{
		return input;
	}
	
	@Override
	public final void setFileLocation(String fileLocation)
	{
		this.fileLocation = fileLocation;
	}
	
	@Override
	public boolean validateFilePath() throws PathNotFoundException
	{
		return AppUtils.validateFilePath(fileLocation, AppConstants.INPUT_FILE_NAME);
	}
	
	@Override
	public void setInputContent() throws PathNotFoundException
	{
		input = AppUtils.readContentFromFile(fileLocation, AppConstants.INPUT_FILE_NAME);
	}
	
	@Override
	public boolean validateInputContent() throws InvalidStringFormatException
	{
		return AppUtils.validateString(input);
	}
	
	@Override
	public String getExtractedContent() throws PathNotFoundException, InvalidStringFormatException
	{
		StringBuilder strAlphabets = new StringBuilder();
		StringBuilder strDigits = new StringBuilder();
		int iAlphabetCount = 0, iDigitCount = 0;
		try
		{
			long startTime = new Date().getTime();
			for (char c : input.toCharArray())
			{
				if (Character.isDigit(c))
				{
					strDigits.append(c);
					iDigitCount++;
				}
				else if (Character.isAlphabetic(c))
				{
					strAlphabets.append(c);
					iAlphabetCount++;
				}
			}
			
			strAlphabets.append(",").append(iAlphabetCount).append(",");
			strAlphabets.append(strDigits.toString()).append(",").append(iDigitCount);
			
			long endTime = new Date().getTime();
			MessageUtils.getMessage("");
			out.println("Total Time for filteration (in miliseconds): " + (endTime - startTime));
		}
		catch (Exception e)
		{
			throw new InvalidStringFormatException(MessageUtils.getMessage("app.error1"), e);
		}
		return strAlphabets.toString();
	}
	
	@Override
	public void writeOutputContent(final String strOutputContent) throws PathNotFoundException
	{
		List<String> lines = new ArrayList<String>();
		String strFileName = AppConstants.OUTPUT_FILE_NAME;
//		lines.add("Output is:");
		lines.add(strOutputContent);
		AppUtils.writeIntoFile(fileLocation, strFileName, lines);
		out.println(MessageUtils.getMessage("app.sucess1", strFileName));
	}
}
