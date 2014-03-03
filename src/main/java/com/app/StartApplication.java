package com.app;


import static java.lang.System.out;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.app.common.MessageUtils;
import com.app.exception.InvalidStringFormatException;
import com.app.exception.PathNotFoundException;
import com.app.extractor.StringExtractor;


/**
 ****************************************************************************************
 *        Created By : Deepak Garg
 *        Created Date : Mar 3, 2014
 *        Last Modified By :
 *        Last Modified Date:
 ****************************************************************************************
 *
 */

public class StartApplication
{
	private static Logger log = Logger.getLogger(StartApplication.class);
	
	private StartApplication()
	{
		//do Nothing
	}
	
	public static void main(String[] args)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");
		final String strFileLocation;
		final String strOutputData;
		
		try
		{
//			StringExtractor obj = (StringExtractor)context.getBean("SimpleStringExtractor");
			StringExtractor objExtractor = (StringExtractor)context.getBean("AdvanceStringExtractor");
			if (!objExtractor.validateFilePath())
			{
				out.println(MessageUtils.getMessage("filePath.error1"));
				//"Path not exist.");
				strFileLocation = getFilePathFromStream();
				objExtractor.setFileLocation(strFileLocation);
			}
			objExtractor.setInputContent();
			if (objExtractor.validateInputContent())
			{
				strOutputData = objExtractor.getExtractedContent();
				objExtractor.writeOutputContent(strOutputData);
			}
		}
		catch (PathNotFoundException | InvalidStringFormatException e)
		{
			out.println(MessageUtils.getMessage("filePath.error1"));
			log.error(e.getMessage(), e);
			System.exit(1);
		}
		System.exit(0);
	}
	
	private static String getFilePathFromStream()
	{
		try (Scanner scan = new Scanner(System.in))
		{
			out.println(MessageUtils.getMessage("filePath.input"));
			return scan.nextLine();
		}
	}
}
