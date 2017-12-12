// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GreekAlphabetFactory.java

package co.com.pdf.text.factories;

import co.com.pdf.text.SpecialSymbol;

public class GreekAlphabetFactory
{

    public GreekAlphabetFactory()
    {
    }

    public static final String getString(int index)
    {
        return getString(index, true);
    }

    public static final String getLowerCaseString(int index)
    {
        return getString(index);
    }

    public static final String getUpperCaseString(int index)
    {
        return getString(index).toUpperCase();
    }

    public static final String getString(int index, boolean lowercase)
    {
        if(index < 1)
            return "";
        index--;
        int bytes = 1;
        int start = 0;
        for(int symbols = 24; index >= symbols + start; symbols *= 24)
        {
            bytes++;
            start += symbols;
        }

        int c = index - start;
        char value[] = new char[bytes];
        while(bytes > 0) 
        {
            bytes--;
            value[bytes] = (char)(c % 24);
            if(value[bytes] > '\020')
                value[bytes]++;
            value[bytes] += lowercase ? '\u03B1' : '\u0391';
            value[bytes] = SpecialSymbol.getCorrespondingSymbol(value[bytes]);
            c /= 24;
        }
        return String.valueOf(value);
    }
}
