// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ISO8601Converter.java

package co.com.pdf.xmp.impl;

import co.com.pdf.xmp.XMPException;

class ParseState
{

    public ParseState(String str)
    {
        pos = 0;
        this.str = str;
    }

    public int length()
    {
        return str.length();
    }

    public boolean hasNext()
    {
        return pos < str.length();
    }

    public char ch(int index)
    {
        return index >= str.length() ? '\0' : str.charAt(index);
    }

    public char ch()
    {
        return pos >= str.length() ? '\0' : str.charAt(pos);
    }

    public void skip()
    {
        pos++;
    }

    public int pos()
    {
        return pos;
    }

    public int gatherInt(String errorMsg, int maxValue)
        throws XMPException
    {
        int value = 0;
        boolean success = false;
        for(char ch = ch(pos); '0' <= ch && ch <= '9'; ch = ch(pos))
        {
            value = value * 10 + (ch - 48);
            success = true;
            pos++;
        }

        if(success)
        {
            if(value > maxValue)
                return maxValue;
            if(value < 0)
                return 0;
            else
                return value;
        } else
        {
            throw new XMPException(errorMsg, 5);
        }
    }

    private String str;
    private int pos;
}
