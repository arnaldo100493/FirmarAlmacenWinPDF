// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ArrayBasedStringTokenizer.java

package co.com.pdf.text.pdf;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrayBasedStringTokenizer
{

    public ArrayBasedStringTokenizer(String tokens[])
    {
        regex = Pattern.compile(getRegexFromTokens(tokens));
    }

    public String[] tokenize(String text)
    {
        List tokens = new ArrayList();
        Matcher matcher = regex.matcher(text);
        int endIndexOfpreviousMatch;
        for(endIndexOfpreviousMatch = 0; matcher.find(); endIndexOfpreviousMatch = matcher.end())
        {
            int startIndexOfMatch = matcher.start();
            String previousToken = text.substring(endIndexOfpreviousMatch, startIndexOfMatch);
            if(previousToken.length() > 0)
                tokens.add(previousToken);
            String currentMatch = matcher.group();
            tokens.add(currentMatch);
        }

        String tail = text.substring(endIndexOfpreviousMatch, text.length());
        if(tail.length() > 0)
            tokens.add(tail);
        return (String[])tokens.toArray(new String[0]);
    }

    private String getRegexFromTokens(String tokens[])
    {
        StringBuilder regexBuilder = new StringBuilder(100);
        String arr$[] = tokens;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            String token = arr$[i$];
            regexBuilder.append("(").append(token).append(")|");
        }

        regexBuilder.setLength(regexBuilder.length() - 1);
        String regex = regexBuilder.toString();
        return regex;
    }

    private final Pattern regex;
}
