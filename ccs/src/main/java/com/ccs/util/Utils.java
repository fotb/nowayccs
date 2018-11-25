package com.ccs.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


/*
Modification:End
*/
public class Utils {
	private static final Set<Character> ALLOWED_ILLEGAL_SYMBOLS_IN_EMAIL =
		StringUtil.chars2Set(new char[] { '@' });
	private static final Logger LOG = Logger.getLogger(Utils.class);

	/**
	 * Constructing this object.
	 */
	protected Utils() {
	}

	
	/**
	 * Compare length of input String and input criteria
	 * @param sEntry Input String to compare
	 * @param iLength Target length
	 * @return If sEntry is null or shorter than iLength, return false; otherwise, return true
	 */
	public static boolean checkLength(final String sEntry, final int iLength) {
        if (sEntry == null) {
            return false;
        }
        if (sEntry.length() < iLength) {
            return false;
        }

        return true;
    }

	/**
	 * Check String of input String and input criteria is correct
	 * @param sEntry Input String to Check
	 * @param iType criteria
	 * @return If the check is correct, return true; otherwise, return false
	 */
	public static boolean checkEntry(final String sEntry, final int iType) {
		int i, iTest;
		char ch;
		boolean b = true;

		// 0 = Only Alpha's
		// 1 = Only Numbers
		// 2 = Only AlphaNumerics
		// 3 = Alpha's and spaces
		// 4 = Numerics and spaces
		// 5 = Alphanumerics and spaces

		if (iType > 2){
			iTest = iType - 3;
		}else{
			iTest = iType;
		}
		if (sEntry == null)
			{ b =  false;}

		for (i = 0; i < sEntry.length(); i++) {
			ch = sEntry.charAt(i);
			if ((iType > 2) && Character.isSpaceChar(ch)) {
				// Space is valid
				b = true;
			} else {
				switch (iTest) {
					case 0 :
						b = Character.isLetter(ch);
						break;
					case 1 :
						b = Character.isDigit(ch);
						break;
					case 2 :
						b = Character.isLetterOrDigit(ch);
						break;
					default :
						b = false;
						break;
				}
				if (!b) {
					break;
				}	
			}
		}
		return b;
	}

	/**
	 * remove String spaces
	 * @param sString 
	 * @return remove spaces of String 
	 */
	public static String removeNull(final String sString) {
	    String s = "";
		if (sString != null){
			s =  sString;
		}
		return s;
	}
	
	/**
	 * remove String spaces
	 * @param sString 
	 * @return remove spaces of String 
	 */
	public static String removeNullTrim(final String sString) {
	    String s = "";
		if (sString != null){
			s =  sString.trim();
		}
		return s;
	}

	/**
	 * convert String to int. 
	 * @param sValue sValue
	 * @return int
	 */
	public static int parseInt(final String sValue) {
		int i;
		try {
			i = Integer.parseInt(sValue);
		} catch (NumberFormatException e) {
			i = 0;
		}
		return i;
	}

	/** Validation on general eMail address format aaaaaaaaa@bbbbbb.ccc
	* where aaaaaaaaa is the user, bbbbbb is the domain and ccc is the top level domain
	* 1. the '@' sign must be within the e-mail address and allow enough room for user name
	* 2. user name must be at least 1 character
	* 3. domain name must be at least 3 characters
	* 4. top level domain name must be at least 2 characters
	* 5. '@' sign should appear only once
	* 6. No embedded colons, forward slashes, [,],<, >, |, &, ;, $, %, "'", ", (, ), +, \n, \r, ',', \
	*    Some of the above sybmols are blocked for penetration tests 
	* Created by:     Elaine Pang
	* Updated by :    Horace To
	* Creation Date:  06Mar02
	**/

	/**
	 * Validate the EmailAddr is Valid
	 * @param sEmailAddr Input String to Validate
	 * @return If sEmailAddr is valid,return true; otherwise, return false
	 */
	public static boolean validEmailAddr(final String sEmailAddr) {
		/* added to check for double-byte characters */
		try{
			final byte[] ba = sEmailAddr.getBytes("UTF-8");
			if (sEmailAddr.length() != ba.length){
				return false;
			}
		}catch(UnsupportedEncodingException e){
			LOG.error(e.getMessage());
		}
		
		boolean bValid = true;
		if (!StringUtil.isNull(sEmailAddr)) {
			final int p1 = sEmailAddr.indexOf('@') + 1;
			final int p2 = sEmailAddr.indexOf('.', p1) + 1;
			final int l = sEmailAddr.trim().length();

			if (p1 == 0 || p2 == 0)
				{bValid = false;}
			if (p1 <= 1)
				{bValid = false;}
      /* 
      Modification: Merge from Enhancement Code
      Author: Excel-GITS(HZ) Date: Jan 24, 2007
      */
			if (p2 - p1 <= 1)
				{bValid = false;}
			/*if (p2 - p1 <= 3)
				bValid = false;*/
			/*
			Modification:End
			*/
			if (l - p2 < 2)
				{bValid = false;}

			if (sEmailAddr.indexOf('@', p1) >= 0)
				{bValid = false;}
			if (sEmailAddr.trim().indexOf(' ') >= 0
				|| sEmailAddr.indexOf('/') >= 0
				|| sEmailAddr.indexOf(':') >= 0
				|| sEmailAddr.indexOf('[') >= 0
				|| sEmailAddr.indexOf(']') >= 0
				|| StringUtil.containsIllegalSymbolInEmail(
					sEmailAddr,
					ALLOWED_ILLEGAL_SYMBOLS_IN_EMAIL))
				{bValid = false;}
		} else {
			bValid = false;
		}
		return bValid;
	}

	/**
	 * Validate the StdEmailAddr is Valid
	 * @param emailAddress Input String to Validate
	 * @return If StdEmailAddr is valid,return true; otherwise, return false
	 * @throws 	Exception The class Exception and its subclasses are a form of 
	 * 			Throwable that indicates conditions that a reasonable 
	 * 			application might want to catch.
	 */
	public static boolean validateStdEmailAddress(final String emailAddress){
	    //RFC 2822 token definitions for valid email - only used together to form a java Pattern object:
		final String sp = "!#$%&'*+-/=?^_`{|}~";
		final String atext = "[a-zA-Z0-9" + sp + "]";
		final String atom = atext + "+"; 						//one or more atext chars
		final String dotAtom = "\\." + atom;
		final String localPart = atom + "(" + dotAtom + ")*"; 	//one atom followed by 0 or more dotAtoms.
	    //RFC 1035 tokens for domain names:
		final String letter = "[a-zA-Z]";
		final String letDig = "[a-zA-Z0-9]";
		final String letDigHyp = "[a-zA-Z0-9-]";
		final String rfcLabel = letDig + letDigHyp + "{0,61}" + letDig;
		final String domain = rfcLabel + "(\\." + rfcLabel + ")*\\." + letter + "{2,6}";
	    //Combined together, these form the allowed email regexp allowed by RFC 2822:
		final String addrSpec = "^" + localPart + "@" + domain + "$";
	    //now compile it:
		final Pattern VALID_PATTERN = Pattern.compile( addrSpec );
	    return ( emailAddress != null ) && VALID_PATTERN.matcher( emailAddress ).matches();
	}

	/**
	 *  Convert amount/units/percentage fields to double
	 */
	public static double convertToDouble(final String sEntry) {
		int iSign, iLength;
		boolean bNegative = false;
		double dValue;
		String sNoSignNum = sEntry;

		iLength = sEntry.trim().length();
		// Quit if empty string is passed
		if (sEntry == null || iLength == 0) {
			return 0;
		}

		iSign = sEntry.indexOf('-') + 1;

		// Check for negative sign
		if (iSign > 0) {
			bNegative = true;
			sNoSignNum = sEntry.replace('-', ' ');
		}
		try {
			dValue = Double.parseDouble(sNoSignNum);
		} catch (NumberFormatException e) {
			return 0;
		}
		if (bNegative) {
			return (dValue * -1);
		} else {
			return dValue;
		}
	}

	/**
	 * Convert to Date object / Date format checking
	 */
	public static Date convertToDate(final String sEntry, final String sDateFmt) {
		final SimpleDateFormat sdf = new SimpleDateFormat(sDateFmt, Locale.ENGLISH);
		sdf.setLenient(false);

		try {
			return sdf.parse(sEntry);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Format Date
	 */
	public static String formatDate(final Date dDate, final String sDateFmt) {
		final SimpleDateFormat sdf = new SimpleDateFormat(sDateFmt, Locale.ENGLISH);
		return sdf.format(dDate);
	}

	public static Date stringToDate(final String string, final String formatStyle) {
		Date result = null;
		try {
			final SimpleDateFormat format = new SimpleDateFormat(formatStyle,
					Locale.ENGLISH);
			result = format.parse(string);
		} catch (ParseException pe) {
			LOG.error(pe.getMessage(), pe);
		}
		return result;
	}

	/**
	 *  Check Minimum Amount
	 */
	public static boolean belowMinimum(
	                final double dAmount,
	                final double dLimit,
	                final double dTolerance) {
		return (dAmount < dLimit * (1 - dTolerance / 10000));
	}

	/**
	*   Check Maximum Amount
	*/
	public static boolean exceedMaximum(
	                final double dAmount,
	                final double dLimit,
	                final double dTolerance) {
		return (dAmount > dLimit * (1 + dTolerance / 10000));

	}

	/**
	 * According to input Object, Returns the Object class name
	 * @param O Object
	 * @return Class Name
	 */
	public static String getClassName(final Object o) {
		if (o == null) {
			return "";
		}

		final String asClass = o.getClass().getName();
		final StringTokenizer sToken = new StringTokenizer(asClass, ".");
		String asToken = asClass;
		while (sToken.hasMoreElements()) {
			asToken = sToken.nextToken();
		}
		return asToken;
	}

	/**
	 * get the collection of all objects name
	 * @param col collection
	 * @param colName String
	 * @return Returns the collection of all objects name
	 */
	public static String formSQLInList(final Collection<String> col, final String colName)
	{
		/* Oracle doesn't allow the 'in' list to have size > 1000, this routine should be
		 * changed to cater for this 
		 */
		final StringBuffer str = new StringBuffer(0);
		int index = 0;
		for (final Iterator<String> it = col.iterator(); it.hasNext();) {
			final String s = (String) it.next();
			if (index != 0) {
				str.append(String.valueOf(','));
			}
			str.append("'" + s + "'");
			index++;
		}
		return colName + " in (" + str.toString() + ")";
	}
	
	/**
	 * remove String CR("\r","\n")
	 * @param sString 
	 * @return remove CR of String 
	 */
	public static String removeCR(final String s) {
		if (s == null || "".equals(s)) {
			return "";
		}
		String s1 = s.replaceAll("\r","");
		s1 = s1.replaceAll("\n","");
		return s1;
	}
	
	/**
	 * remove String CR("\r","<p>","\n")
	 * @param sString 
	 * @return remove CR of String 
	 */
	public static String replaceCR(final String s) {
		if (s == null || "".equals(s)) {
			return "";
		}
		String s1 = s.replaceAll("\r","<p>");
		s1 = s1.replaceAll("\n","");
		return s1;
	}	
	
	//add by 020110
	/**
	 * According to input filePath and content of Create file
	 * @param filePath  file path 
	 * @param content  file content
	 * @throws IOException	Signals that an I/O exception of some sort has occurred. This
	 * 						class is the general class of exceptions produced by failed or
	 * 						interrupted I/O operations.
	 */
	public static void createFile(final String filePath, final String content) throws IOException {
	    FileWriter fw = null;
	    try{
	    	final File file = new File(filePath);
            if (file.exists()) {
                //if file exists, delete it and create new file
                if (file.delete()) {
                    file.createNewFile();
                } 
            } else {
                file.createNewFile();
            }
            fw = new FileWriter(file.getPath());
            fw.write(content);
	    } finally{
            if (fw != null) {
                fw.close();
            }
	    }   
	}
	//add end
	/**
	 * According to input filePath of delete file
	 * @param filePath  file path 
	 */
	public static void deleteFiles(final String filePath){
		final File f = new File(filePath);
		final File[] fs = f.listFiles();
		if(fs != null) {
			for (int i = 0; i < fs.length; i++){
				fs[i].delete();
			}
		}
	}
	
	/**
	 * According to input filePath of check the file exist
	 * @param filePath  file name 
	 * @return If check the file exist, return true; otherwise, return false
	 */
	public static boolean checkFileExist(final String filename){
		final File f = new File(filename);
		return (f == null ? false : f.exists());
	}
	
	/**
	 * copy list
	 * @param originalList
	 * @return a copy list of all originalList iterms
	 */
	@SuppressWarnings("unchecked")
    public static  List copyList(final List originalList){
        final List rtnList = new ArrayList();
        rtnList.addAll(originalList);
        return rtnList;
    }
}
