package com.app.extractor;


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

public interface StringExtractor
{
	public String getInput();
	
	public void setFileLocation(String fileLocation);
	
	public boolean validateFilePath() throws PathNotFoundException;
	
	public void setInputContent() throws PathNotFoundException;
	
	public boolean validateInputContent() throws InvalidStringFormatException;
	
	public String getExtractedContent() throws PathNotFoundException, InvalidStringFormatException;
	
	public void writeOutputContent(final String strOutputContent) throws PathNotFoundException;
}
