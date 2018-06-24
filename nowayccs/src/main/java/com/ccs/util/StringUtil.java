package com.ccs.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class StringUtil {
	private static final Set<Character> ILLEGAL_SYMBOL_SET = getInvalidSymbolSet();
	private static final Set<Character> ILLEGAL_SYMBOL_SET_EMAIL = getInvalidSymbolSetEmail();
	private static final Set<Character> ILLEGAL_SYMBOL_SET_WEB = getInvalidSymbolSetWeb();
	private static final String SPLIT_STR="@";

	private static final Logger LOG = Logger.getLogger(StringUtil.class);

	
    public static boolean checkNumber(final String sEntry, final int intLength,
                    final int floatLength) throws AppException {
        boolean flag = false;
        try {
            if (sEntry.contains(".")) {
                final String intStr = sEntry.substring(0, sEntry.indexOf('.'));
                final String floatStr = sEntry.substring(sEntry.indexOf('.') + 1);
                if ((null != intStr) && (!"".equals(intStr)) && (intStr.length() <= intLength)
                                && (null != floatStr) && (!"".equals(floatStr))
                                && (floatStr.length() <= floatLength)) {
                    flag = true;
                } else {
                    throw new AppException("The value is too large!");
                }
            } else {
                if (sEntry.length() <= intLength) {
                    flag = true;
                } else {
                    throw new AppException("The value is too large!");
                }
            }

        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new AppException(e.getMessage(), e);
        }
        return flag;
    }


    public static String textToHTML(final String text) {
        if (text == null) {
            return null;
        }
        final int length = text.length();
        boolean prevSlashR = false;
        final StringBuffer out = new StringBuffer();
        for (int i = 0; i < length; i++) {
        	final char ch = text.charAt(i);
            switch (ch) {
                case '\r':
                    if (prevSlashR) {
                        out.append("<br>");
                    }
                    prevSlashR = true;
                    break;
                case '\n':
                    prevSlashR = false;
                    out.append("<br>");
                    break;
                case '"':
                    if (prevSlashR) {
                        out.append("<br>");
                        prevSlashR = false;
                    }
                    out.append("&quot;");
                    break;
                case '<':
                    if (prevSlashR) {
                        out.append("<br>");
                        prevSlashR = false;
                    }
                    out.append("&lt;");
                    break;
                case '>':
                    if (prevSlashR) {
                        out.append("<br>");
                        prevSlashR = false;
                    }
                    out.append("&gt;");
                    break;
                case '&':
                    if (prevSlashR) {
                        out.append("<br>");
                        prevSlashR = false;
                    }
                    out.append("&amp;");
                    break;
                default:
                    if (prevSlashR) {
                        out.append("<br>");
                        prevSlashR = false;
                    }
                    out.append(ch);
                    break;
            }
        }
        return out.toString();
    }










	
	/**
	 * Transfer html tag meaning.
	 * @param value Input String to Transfer
	 * @return Complete Transfer of String
	 */
	public static String htmlEncode(final String value) {	
		final int length = value.length();
		final StringBuffer encodeValue = new StringBuffer(length + (length/10));
		
		for (int i = 0; i < length; i++) {
			String nextCharacter = String.valueOf(value.charAt(i));
			
			if ("<".equals(nextCharacter)){
				nextCharacter = "&lt;";
			}				
			if (">".equals(nextCharacter)){
				nextCharacter = "&gt;";
			}				
			if ("\"".equals(nextCharacter)){
				nextCharacter = "&quot;";
			}				
			if ("&".equals(nextCharacter)){
				nextCharacter = "&amp;";
			}				
			if ("'".equals(nextCharacter)){
				nextCharacter = "&#39;";
			}				

			encodeValue.append(nextCharacter);
		}
		return encodeValue.toString();
	}

	/**
	 * Converter input Chinese charactor.
	 * @param value Input String to Converter
	 * @return Complete Converter of charactor
	 */
	public static String convertToUTF(final String value) {
		byte [] byte_value = null;
		String newValue = "";
		try {
		     byte_value = value.getBytes("ISO-8859-1");
		     newValue = new String(byte_value, "UTF-8");
		     
		} catch (UnsupportedEncodingException e) {
			LOG.error(e.getMessage());
		}
		return newValue;
	}
	
	/**
	 * Converter output Chinese charactor.
	 * @param value Input String to Converter
	 * @return Complete Converter of charactor
	 */
	public static String convertToISO(final String value) {
		byte [] byte_value = null;
		String newValue = "";
		try {
		     byte_value = value.getBytes("UTF-8");
		     newValue = new String(byte_value, "ISO-8859-1");
		     
		} catch (UnsupportedEncodingException e) {
			LOG.error(e.getMessage());
		}
		return newValue;
	}

	/**
	 * Handle newline on the textarea box.
	 * @param value Input String to Converter
	 * @return Complete Converter of charactor
	 */
	public static String formatOut(final String value) {
		final StringBuffer outValue = new StringBuffer();
		int Position = 0;
		while (true) {
			final int index = value.indexOf(0x0D, Position);
		    if (index == -1) {
		        break;
		    }
		    if (index > Position) {
		    	outValue.append(value.substring(Position, index));
		        }
		    outValue.append("<br>");
		    Position = index + 1;
		}
		if (Position >= 0) {
			outValue.append( value.substring(Position));
		}
		return outValue.toString();

	}
	//Modification End Here.
	
	/**
	 * @return Returns Invalid Symbol
	 */
	private static Set<Character> getInvalidSymbolSet()
	{
	    /*Start PEN test bug Fix Jun 21, 2007*/
		/*char[] invalidSymbols = new char[] 
			{'|', '&', ';', '$', '%', '@', '\'', '"', '<', '>', '(', ')', '+', '\n', '\r', ',', '\\'};*/
		final char[] invalidSymbols = new char[] {'!', '^', '|', '[', ']'};
	    /*Start PEN test bug Fix Jun 21, 2007*/
		return chars2Set(invalidSymbols);
	}

	/**
	 * @return Returns Invalid Symbol for Email
	 */
	private static Set<Character> getInvalidSymbolSetEmail()
	{
		final char[] invalidSymbols = new char[] 
			{'|', '&', ';', '$', '%', '@', '\'', '"', '<', '>', '(', ')', '+', '\n', '\r', ',', '\\'};
		return chars2Set(invalidSymbols);
	}

	/**
	 * @return Returns Invalid Symbol for Web
	 */
	private static Set<Character> getInvalidSymbolSetWeb()
	{
		final char[] invalidSymbols = new char[] 
			{'|', '&', ';', '$', '%', '@', '\'', '"', '<', '>', '(', ')', '+', '\n', '\r', ',', '\\'};
		return chars2Set(invalidSymbols);
	}

	/**
	 * Convert chars to set.
	 * @param chars char[]
	 * @return set
	 */
	public static Set<Character> chars2Set(final char[] chars)
	{
		final Set<Character> set = new HashSet<Character>();
		for (int i = 0; i < chars.length; i++) {
			set.add(new Character(chars[i]));
		}
		return set;
		
	}

	
	/**
	 * Validate input value is empty
	 * @param value input String to Validate
	 * @return If value is null, return true; otherwise, return false
	 */
	public static boolean isNull(final String value) {
		return (value == null || value.trim().equals(""));
	}
	
	public static boolean isNotBlank(final String value) {
		return (value != null && value.length() > 0 && value.trim().length() > 0);
	}
	
	public static boolean isNotEmpty(final String value) {
		return (value != null && value.length() > 0 && value.trim().length() > 0);
	}

	public static String emptyToNull(final String value) {
		return isNull(value) ? null : value.trim();
	}
	
	/**
	 * Validate input value is contains Non Digit Char
	 * @param value input String to Validate
	 * @return If value is contains Non Digit Char, return true; otherwise, return false
	 */
	public static boolean containsNonDigitChar(final String value) {
		if (!isNull(value))
		{
			final int len = value.length();
			for (int i = 0; i < len; i++)
			{
				// We don't use Character.isDigit since it covers characters other than '0'..'9'
				if (!isDigit(value.charAt(i)))
				{
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Validate input value is contains Non Ascii Char
	 * @param value input String to Validate
	 * @return If value is contains Non Digit Ascii Char, return true; otherwise, return false
	 */
	public static boolean containsNonAsciiChar(final String value) {

		if (!isNull(value))
		{
			final int len = value.length();
			for (int i = 0; i < len; i++)
			{
				if (value.charAt(i) > 127)
				{
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Validate input value is contains Control Char
	 * @param value input String to Validate
	 * @return If value is contains Control Char, return true; otherwise, return false
	 */
	public static boolean containsControlChar(final String value) {

		if (!isNull(value))
		{
			final int len = value.length();
			for (int i = 0; i < len; i++)
			{
				if (Character.isISOControl(value.charAt(i)))
				{
					return true;
				}
			}
		}

		return false;
	}	

	/**
	 * Validate input value is contains Illegal Symbol
	 * @param value input String to Validate
	 * @return If value is contains Illegal Symbol, return true; otherwise, return false
	 */
	public static boolean containsIllegalSymbol(final String value)
	{
		return containsIllegalSymbol(value, null);
	}

	/**
	 * Validate input value is contains Illegal Symbol
	 * @param value input String to Validate
	 * @param allowedIllegalSymbols allowedIllegalSymbols
	 * @return If value is contains Illegal Symbol, return true; otherwise, return false
	 */
	public static boolean containsIllegalSymbol(final String value, final Set<Character> allowedIllegalSymbols)
	{
		for (int i = 0; i < value.length(); i++)
		{
			final Character c = new Character(value.charAt(i));
			if (ILLEGAL_SYMBOL_SET.contains(c) &&
				(allowedIllegalSymbols == null || !allowedIllegalSymbols.contains(c))
			) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Validate input value is contains Illegal Symbol In Email
	 * @param value input String to Validate
	 * @param allowedIllegalSymbols allowedIllegalSymbols
	 * @return If value is contains Illegal Symbol In Email, return true; otherwise, return false
	 */
	public static boolean containsIllegalSymbolInEmail(final String value, final Set<Character> allowedIllegalSymbols)
	{
		for (int i = 0; i < value.length(); i++)
		{
			final Character c = new Character(value.charAt(i));
			if (ILLEGAL_SYMBOL_SET_EMAIL.contains(c) &&
				(allowedIllegalSymbols == null || !allowedIllegalSymbols.contains(c))
			) {
				return true;
			}
		}
		return false;
	}
	
	/* added for penetration test issue - to check invalid character and then forward to error page */
	/**
	 * Validate input value is contains Illegal Symbol In Web
	 * @param value input String to Validate
	 * @param allowedIllegalSymbols allowedIllegalSymbols
	 * @return If value is contains Illegal Symbol In Web, return true; otherwise, return false
	 */
	public static boolean containsIllegalSymbolInWeb(final String value, final Set<Character> allowedIllegalSymbols)
	{
		if (value == null){
			return false;
		}
		for (int i = 0; i < value.length(); i++)
		{
			final Character c = new Character(value.charAt(i));
			if (ILLEGAL_SYMBOL_SET_WEB.contains(c) &&
				(allowedIllegalSymbols == null || !allowedIllegalSymbols.contains(c))
			) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Validate input value is Digit
	 * @param value input char to Validate
	 * @return If value is Digit, return true; otherwise, return false
	 */
	public static boolean isDigit(final char c)
	{
		return c >= '0' && c <= '9';
	}

	/**
	 * Validate input value is Date
	 * @param date	Input String to Validate
	 * @param pattern pattern 
	 * @return If value is Date, return true; otherwise, return false
	 */
	public static boolean isDate(final String date, final String pattern)
	{
		try {
			final SimpleDateFormat sdf = new SimpleDateFormat(pattern,Locale.ENGLISH);
			sdf.parse(date);	
		} catch(Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Replace the string content
	 * @param paramStrSource String Source
	 * @param strFrom	the characters from replace
	 * @param strTo   the characters to replace
	 * @return Replaced string
	 */
	public static String replace(final String paramStrSource, final String strFrom, final String strTo){
		String strSource = paramStrSource;
		final StringBuffer  strDest  =  new StringBuffer("");
		final int  intFromLen  =  strFrom.length();
		int  intPos;

		while((intPos=strSource.indexOf(strFrom))!=-1){
			strDest.append(strSource.substring(0,intPos));
			strDest.append(strTo);
			strSource = strSource.substring(intPos+intFromLen);
		}
		strDest.append(strSource);

		return strDest.toString();
   }

	
	/**
	 * Replace the string content
	 * @param value String Source
	 * @param From	the characters from replace
	 * @param to   the characters to replace
	 * @param ignoreCase ignore Case(Lower Case)
	 * @return Replaced string
	 */
	public static String replaceAll(final String value, final String from, final String to, final boolean ignoreCase)
	{
		if (value == null) {
			return null;
		}

		final String activeValue = ignoreCase ? value.toLowerCase(Locale.ENGLISH) : value;
		final String fromValue = ignoreCase ? from.toLowerCase(Locale.ENGLISH) : from;

		final List<Integer> occurences = findOccurences(activeValue, fromValue);

		final StringBuffer buf = new StringBuffer();
		buf.append(value);
		int offset = 0;
		for (final Iterator<Integer> it = occurences.iterator(); it.hasNext(); )
		{
			final Integer i = (Integer) it.next();
			final int index = i.intValue() + offset;
			buf.replace(index, index + from.length(), to);
			offset += to.length() - from.length();
		}

		return buf.toString();
	}
	
	/**
	 * find an string in the string of position 
	 * @param content String Source
	 * @param matchValue the characters from find
	 * @return find results
	 */
	private static List<Integer> findOccurences(final String content, final String matchValue)
	{
		final List<Integer> occurences = new LinkedList<Integer>();

		int pos = 0;
		while (pos < content.length())
		{
			final int index = content.indexOf(matchValue, pos);
			if (index == -1)
			{
				break;
			}
			occurences.add(Integer.valueOf(String.valueOf(index)));
			pos = index + matchValue.length();
		}

		return occurences;
	}

	/**
	 * If the line for more than maxLength,According to the maxLength Completed line is split 
	 * @param line line
	 * @param maxLength maxLength
	 * @param splitters splitters
	 * @return split  of list
	 */
	public static List<String> wrap(final String line, final int maxLength, final char[] splitters)
	{
		final List<String> resultLines = new LinkedList<String>();
		String lineValue = line;
		while (true)
		{
			final String[] lines = wrapTo2Lines(lineValue, maxLength, splitters);
			resultLines.add(lines[0]);
			if (lines[1].length() == 0)
			{
				break;
			}
			lineValue = lines[1];
		}
		return resultLines;
	}

	/**
	 * If the line for more than maxLength,According to the maxLength Completed line is split  into two String
	 * @param line line
	 * @param maxLength maxLength
	 * @param splitters splitters
	 * @return split of String
	 */
	private static String[] wrapTo2Lines(final String line, final int maxLength, final char[] splitters)
	{
		if (line.length() <= maxLength) {
			return new String[] {line, ""};
		}

		final StringBuffer line1 = new StringBuffer();
		line1.append(line.substring(0, maxLength));
		String line2 = line.substring(maxLength);

		//Convert to Character Array
		final List<Character> splitterLst = new ArrayList<Character>();
		for (int i=0;i<splitters.length;i++){
			splitterLst.add(new Character(splitters[i]));
		}
		
		
		for (int k=0; (k < line2.length() && splitterLst.contains(new Character(line2.charAt(0)) ));k++) {
			line1.append(line2.charAt(0));
			line2=line2.substring(1);
		}

		// Calculate the cut-off character
		int maxIndex = -1;
		for (int i = 0; i < splitterLst.size(); i++)
		{
			final int index = line1.toString().substring(0,maxLength).lastIndexOf( ((Character)splitterLst.get(i)).charValue() );
			if (index > maxIndex) {
				maxIndex = index;
			}
		}

		if (maxIndex != -1)
		{
			line1.setLength(0);
			line1.append(line.substring(0, maxIndex+1));
			line2 = line.substring(maxIndex+1).trim();
		}

		return new String[] {line1.toString(), line2};
	}

	/**
	 * If the line for more than maxLength,According to the maxLength Completed line is split 
	 * @param line line
	 * @param maxLength maxLength
	 * @return split of list
	 */
	public static List<String> split(final String line, final int maxLength)
	{
		final List<String> resultLines = new LinkedList<String>();
		String lineValue = line;
		while (true)
		{
			final String[] lines = splitTo2Lines(lineValue, maxLength);
			resultLines.add(lines[0]);
			if (lines[1].length() == 0)
			{
				break;
			}
			lineValue = lines[1];
		}
		return resultLines;
	}

	/**
	 * If the line for more than maxLength,According to the maxLength Completed line is split into two String
	 * @param line line
	 * @param maxLength maxLength
	 * @return split of String
	 */
	private static String[] splitTo2Lines(final String line, final int maxLength)
	{
		if (line.length() <= maxLength) {
			return new String[] {line, ""};
		}

		final String line1 = line.substring(0, maxLength);
		final String line2 = line.substring(maxLength);

		return new String[] {line1, line2};
	}

	/**
	 * According to the patterns Completed s is split
	 * @param s line
	 * @param patterns patterns
	 * @return split of list
	 */
	public static List<String> split(final String s, final String[] patterns)
	{
		final Set<String> checkedPatterns = new HashSet<String>();
		Arrays.sort(patterns);
		List<String> result = null;
		for (int i = patterns.length - 1; i >= 0; i--)
		{
			if (i == patterns.length - 1)
			{
				result = split(s, patterns[i], true);
			}
			else
			{
				int count = 0;
				while (true)
				{
					if (count == result.size())
					{
						break;
					}
					final String temp = (String) result.get(count);
					if (checkedPatterns.contains(temp))
					{
						count++;
					}
					else
					{
						final List<String> newResult = split(temp, patterns[i], true);
						if (newResult.size() == 1)
						{
							count++;
						}
						else
						{
							result.remove(count);
							result.addAll(count, newResult);
							count += newResult.size();
						}
					}
				}
			}
			checkedPatterns.add(patterns[i]);
		}
		return result;
	}

	/**
	 * According to the regex position in str,Completed str is split 
	 * @param str str
	 * @param regex regex
	 * @return split of list
	 */
	public static List<String> splitByTrim(final String target, final String regex) {
		final List<String> list = new ArrayList<String>();
		boolean start = true;

		String s = "";
		if (target != null) {
		    s = target;
		}

		while (start) {
		    s = s.trim();
			if (s.indexOf(regex) != -1) {
			    final int beginIndex = s.indexOf(regex);
				list.add(s.substring(0, beginIndex));

				s = s.substring(beginIndex + 1).trim();
			} else {
				start = false;
				list.add(s);
			}
		}

		return list;
	}


	public static List<String> split(final String s, final String pattern) {
		return split(s, pattern, false);
	}

	/**
	 * According to the patterns Completed s is split
	 * @param s line
	 * @param patterns patterns
	 * @param includePattern includePattern
	 * @return split of list
	 */
	public static List<String> split(final String s, final String pattern, final boolean includePattern) {
		final List<String> result = new LinkedList<String>();
		int pos = 0;
		while (true)
		{
			if (pos == s.length())
			{
				break;
			}

			final int index = s.indexOf(pattern, pos);
			if (index == -1)
			{
				result.add(s.substring(pos, s.length()));
				break;
			}
			else
			{
				result.add(s.substring(pos, index));
				if (includePattern)
				{
					result.add(s.substring(index, index + pattern.length()));
				}
				pos = index + pattern.length();
			}
		}
		return result;
	}

	/**
	 * If the line for more than Length,According to the Length Completed line is split
	 * @param line line
	 * @param length length
	 * @param checkEmpty checkEmpty
	 * @return split of list
	 */
	public static List<String> splitWithFixedLength(final String line, final int length, final boolean checkEmpty) {
		final List<String> result = new ArrayList<String>();
		if (line != null)
		{
		   int iStart=0, iEnd=0;
		   final int iLength=line.length();
		   String sCode = "";
		   while (iStart < iLength) 
		   {
			   iEnd = (iStart+length < iLength) ? iStart+length: iLength;
			   sCode = line.substring(iStart, iEnd);
			   if (!checkEmpty || !isNull(sCode)) {
				result.add(sCode);
			   }
			   iStart += length;
		   }
		}
		return result;
	}
	
	/**
	 * If the target length for less than maxLength length,On the right complement pad
	 * @param target target
	 * @param pad pad
	 * @param maxLength maxLength
	 * @return To meet the requirements of a string
	 */
	public static String rightPad(final String target, final String pad, final int maxLength) {
	    final StringBuffer targetValue = new StringBuffer();
		targetValue.append(target == null ? "" : target);

		while (true)
		{
			if (targetValue.length() < maxLength) {
				targetValue.append(pad);
			} else {
				break;
			}
		}
		return targetValue.toString();
	}
	
	/**
	 * If the target length for less than maxLength length,On the left complement pad
	 * @param target target
	 * @param pad pad
	 * @param maxLength maxLength
	 * @return To meet the requirements of a string
	 */
	public static String leftPad(final String target, final String pad, final int maxLength) {
	    final StringBuffer targetValue = new StringBuffer();
		targetValue.append(target == null ? "" : target);

		while (true)
		{
			if (targetValue.length() < maxLength) {
				final String s = pad + targetValue.toString();
				targetValue.setLength(0);
				targetValue.append(s);
			} else {
				break;
			}
		}
		return targetValue.toString();
	}
	
	/**
	 * Convert String[] to string.
	 * @param string target
	 * @param pattern pad
	 * @return String
	 */
	public static String getListString(final String[] string, final String pattern){
		final StringBuffer sListString = new StringBuffer();
		final int length = string.length;
		for (int i=0;i<length;i++){
			if (!"".equals(sListString.toString())) {
				sListString.append(',');
			}
			sListString.append(pattern);
			sListString.append(string[i]);
			sListString.append(pattern);
		}
		return sListString.toString();
	}

	/**
	 * Convert byte[] to Hex String. 
	 * @param ba byte[]
	 * @return Hex String
	 */
	public static String toHex(final byte[] ba){
		final StringBuffer sb = new StringBuffer();
		for (int i=0; i<ba.length; i++){
			final int b = (int) ba[i];
			sb.append(Integer.toHexString ((b & 0xFF) | 0x100).substring(1,3)+ " ");
		}
		return sb.toString();
	}
	
	/**
	 * Convert String to "UTF-8" type.then Compare length of input String and input criteria
	 * @param sEntry sEntry
	 * @param iLength Target length
	 * @return If sEntry length than iLength, return true; otherwise, return false
	 */
	public static boolean checkLength(final String sEntry, final int iLength) {
		byte[] byte1 = null;
		try {
			byte1 = sEntry.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOG.error(e.getMessage());
		}
        //		if (sEntry.length() <= iLength)
		if (byte1.length > iLength) {
			return true;
		}

		return false;
	}
	
	/**
	 * find an string in the string of Frequencies 
	 * @param inputString String Source
	 * @param findString the characters from find
	 * @return Frequencies
	 */
	public static int findNumberOfOccurrence(final String inputString, final String findString){
		int count = 0;
		int pos = 0;
		int prevpos = 0;
		while (pos != -1){
			pos = inputString.indexOf(findString,prevpos);
			if (pos != -1){
				count++;
				prevpos = pos + findString.length();
			}
		}
		return count;
	}

	/**
	 * Convert String to "UTF-8" type.then Compare length of input String and Convert String is consistent
	 * @param inputString String Source
	 * @return If Compare length of input String and Convert String is consistent, return true; otherwise, return false
	 */
	public static boolean checkForDoubleByteChar(final String inputString){
		if (inputString == null || inputString.equals("")){
			return false;
		}
		
		try{
			return (inputString.getBytes("utf-8").length != inputString.length());
		}catch(UnsupportedEncodingException e){
			LOG.error(e.getMessage());
		}

		return false;
	}
	
	/**
	 * Compiles the given regular expression and attempts to match the given input against it.
	 * @param inputString character sequence to be matched
	 * @param pattern The The expression to be compiled
	 * @return If Compare length of input String and Convert String is consistent, return true; otherwise, return false
	 */
	public static boolean checkStringPattern(final String inputString, final String pattern){
	    //String regex = "[A-Z ]{2}[0-9]{6}[0-9A]{1}";
		if (inputString == null || pattern == null){
			return false;
		}
	    return Pattern.matches(pattern,inputString);
	}
	
	
	public static String[] getStringArrayByStr(final String parmStr,final String parmSplitStr){ 
        String str=parmStr.replaceAll(parmSplitStr, SPLIT_STR);
        while(true){
            final int beginLength=str.length();
            str=str.replaceAll(SPLIT_STR+SPLIT_STR, SPLIT_STR+" "+SPLIT_STR);
            final int endLength=str.length();
            if(beginLength==endLength){
                break;
            }
        }
        final String lastStr=str.substring(str.length()-1);
        if(SPLIT_STR.equals(lastStr)){
            str += " ";
        }

        return str.split(SPLIT_STR);
	}
	
	/**
	 * substring string as length
	 * @param inputString
	 * @param len
	 * @return
	 */
	public static String subStr(final String inputString, final int len){
	    String s=inputString;
	    if(s.length()>len){
	        s = s.substring(0,len);
	    }
	    return s;
	}

	/**
     * strip html 
     * @param content
     * @return
     */
	public static String stripHtml(final String content) {
	    String s = content;
        // <p>
        s = s.replaceAll("<p.*?>", "\r\n");
        // <br>
        s = s.replaceAll("<br\\s*/?>", "\r\n");
        // other html sign
        s = s.replaceAll("<.*?>", "");
        
        return s;
    }
	
	public static boolean checkIsDecimal(final String number){
		String number1 = "";
	    if((null==number) || ("".equals(number.trim()))){
	        return false;
	    }
	    final int indexFu=number.indexOf('.');
	    if(indexFu>0){
	        return false;
	    }else if(indexFu==0){
	        number1=number.substring(1);
	    }
	    final int index = number1.indexOf('.');  
	    if(index<0){  
	        return isNumeric(number1);  
	    }else{  
	    	final String num1 = number1.substring(0,index);  
	        final String num2 = number1.substring(index+1);
	        return isNumeric(num1) && isNumeric(num2);
	    } 
	   
	}
	
	public static boolean isNumeric(final String str){  
	    if(str == null){
	        return false;
	    }	             
	    final int sz = str.length();  
	    for(int i = 0; i < sz; i++){
	        if(!Character.isDigit(str.charAt(i))){  
	            return false;
	         }
	    }
	  
	    return true;  
	} 
	

	


}