// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RomanAlphabetFactory.java

package co.com.pdf.text.factories;

import co.com.pdf.text.error_messages.MessageLocalization;

public class RomanAlphabetFactory
{

    public RomanAlphabetFactory()
    {
    }

    public static final String getString(int index)
    {
        if(index < 1)
            throw new NumberFormatException(MessageLocalization.getComposedMessage("you.can.t.translate.a.negative.number.into.an.alphabetical.value", new Object[0]));
        index--;
        int bytes = 1;
        int start = 0;
        for(int symbols = 26; index >= symbols + start; symbols *= 26)
        {
            bytes++;
            start += symbols;
        }

        int c = index - start;
        char value[] = new char[bytes];
        while(bytes > 0) 
        {
            value[--bytes] = (char)(97 + c % 26);
            c /= 26;
        }
        return new String(value);
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
        if(lowercase)
            return getLowerCaseString(index);
        else
            return getUpperCaseString(index);
    }
}
