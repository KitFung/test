package helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringDecoder {
	public static double getNumberExceptBusket(String fullString) {
		String numString = fullString.replaceAll("\\((.*?)\\)", "").replaceAll("[^0-9.]", "");
		return Double.parseDouble(numString);
	}

	public static boolean isBusketExist(String fullString) {
		int frontIdx = fullString.indexOf('(');
		int backIdx = fullString.indexOf(')');
		return frontIdx != -1 && backIdx != -1 && backIdx > frontIdx;
	}
	
	public static double getNumberInBusket(String fullString) {
		Pattern pattern = Pattern.compile("\\((.*?)\\)");
		Matcher matcher = pattern.matcher(fullString);
		if(matcher.find())
			return Double.parseDouble(matcher.group(1).replaceAll("[^0-9.]", ""));
		else
			return 0;
	}
	
	public static int getNumberofFollowerFromFullDesc(String fullString) {
    	Pattern pattern = Pattern.compile("([0-9]+) followers");
		Matcher matcher = pattern.matcher(fullString);
		if(matcher.find()) { 
			return Integer.parseInt(matcher.group(1));
		} else {
			throw new IllegalArgumentException("Invalid description");
		}
	}
}
