package com.amirbahadoramiri.androidlearning.tools.rejex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    public static String IRAN_PHONE_NUMBER = "09(\\d{9})";

    public static String EMAIL_ADDRESS = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+";

    public static boolean isValid(String pattern, String text) {
        return isValid(compile(pattern), text);
    }

    public static boolean isValid(Pattern pattern, String text) {
        return getMatcher(pattern, text).matches();
    }

    public static Pattern compile(String pattern) {
        return Pattern.compile(pattern);
    }

    public static Matcher getMatcher(String pattern, String text) {
        return compile(pattern).matcher(text);
    }

    public static Matcher getMatcher(Pattern pattern, String text) {
        return pattern.matcher(text);
    }

    public static void iterator(String pattern, String text,RegexIterator regexIterator) {
        iterate(getMatcher(pattern,text),regexIterator);
    }

    public static void iterator(String pattern, String text,RegexIterator regexIterator,int group) {
        iterate(getMatcher(pattern,text),regexIterator,group);
    }

    public static void iterator(Pattern pattern, String text,RegexIterator regexIterator) {
        iterate(getMatcher(pattern,text),regexIterator);
    }

    public static void iterator(Pattern pattern, String text,RegexIterator regexIterator,int group) {
        iterate(getMatcher(pattern,text),regexIterator,group);
    }

    public static void iterate(Matcher matcher,RegexIterator regexIterator) {
        while ( matcher.find() ) {
            regexIterator.stringCallback(matcher.group());
        }
    }
    public static void iterate(Matcher matcher,RegexIterator regexIterator,int group) {
        if (group < 0) {
            throw new IllegalArgumentException("group must be >= 0");
        }
        while ( matcher.find() ) {
            regexIterator.stringCallback(matcher.group(group));
        }
    }

}