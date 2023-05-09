package com.zwash.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zwash.exceptions.PatternFormatException;

public class RegisterationPlateChecker
{
	public RegisterationPlateChecker()
	{

	}
	public boolean check(String registerationPlate) throws PatternFormatException
	{
		Pattern pattern = Pattern.compile("[a-zA-Z]{3}-[0-9]{3}|[a-zA-Z]{2}-[0-9]{2}|[a-zA-Z]{2}-[0-9]{2}");
		Matcher matcher = pattern.matcher(registerationPlate);
		if(matcher.matches())
		{
			return true;
		}else {
			throw new PatternFormatException("Wrong pattern");
		}


	}
}