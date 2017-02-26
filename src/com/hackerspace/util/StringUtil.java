package com.hackerspace.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串验证工具类
 * @author tianx
 *
 */
public final class StringUtil {
	
    /**
     * Checks whether the specified string is numeric.
     * 
     * @param string the specified string
     * @return {@code true} if the specified string is numeric, returns 
     * returns {@code false} otherwise
     */
    public static boolean isNumeric(final String string) {
        if (isEmptyOrNull(string)) {
            return false;
        }

        final Pattern pattern = Pattern.compile("[0-9]*");
        final Matcher matcher = pattern.matcher(string);

        return matcher.matches();
    }
	
    
    public static boolean isEmptyOrNull(final String string) {
        return string == null || string.length() == 0;
    }
    
    
    
    public static boolean isEmptyOrNull(final String... string) {
    	for (int i = 0; i < string.length; i++) {
    		if( string[i] == null || string[i].length() == 0)
    			return true;
    	}
    	
    	return false;
    }
    public static boolean isURL(final String string) {
        try {
            new URL(string);

            return true;
        } catch (final MalformedURLException e) {
            return false;
        }
    }
    
    
    
    
    
}
