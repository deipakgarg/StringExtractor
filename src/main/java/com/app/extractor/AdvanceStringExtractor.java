package com.app.extractor;


import java.util.Date;

import com.app.common.AppUtils;
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

public class AdvanceStringExtractor extends AlphaNumericExtractor implements StringExtractor
{
	private String[] regExpression = new String[] {"[a-zA-Z]", "[0-9]"};
	
	public void setFilterPattern(String[] strRegEx)
	{
		regExpression = strRegEx;
	}
	
	@Override
	public String getExtractedContent() throws PathNotFoundException, InvalidStringFormatException
	{
		StringBuilder strContent = new StringBuilder();
		long startTime = new Date().getTime();
		String strData;
		boolean blnFlag = false;
		for (String strRegEx : regExpression)
		{
			strData = AppUtils.extractInformationByRegEx(getInput(), strRegEx);
			if (AppUtils.isValidString(strData))
			{
				if (blnFlag)
				{
					strContent.append(",");
				}
				strContent.append(strData).append(",").append(strData.length());
				blnFlag = true;
			}
		}
		long endTime = new Date().getTime();
		
		System.out.println("Total Time for filteration (in miliseconds): " + (endTime - startTime));
		return strContent.toString();
	}
}
