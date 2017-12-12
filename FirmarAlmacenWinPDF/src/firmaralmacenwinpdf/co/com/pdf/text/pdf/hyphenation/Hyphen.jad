// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Hyphen.java

package co.com.pdf.text.pdf.hyphenation;

import java.io.Serializable;

public class Hyphen
    implements Serializable
{

    Hyphen(String pre, String no, String post)
    {
        preBreak = pre;
        noBreak = no;
        postBreak = post;
    }

    Hyphen(String pre)
    {
        preBreak = pre;
        noBreak = null;
        postBreak = null;
    }

    public String toString()
    {
        if(noBreak == null && postBreak == null && preBreak != null && preBreak.equals("-"))
        {
            return "-";
        } else
        {
            StringBuffer res = new StringBuffer("{");
            res.append(preBreak);
            res.append("}{");
            res.append(postBreak);
            res.append("}{");
            res.append(noBreak);
            res.append('}');
            return res.toString();
        }
    }

    private static final long serialVersionUID = 0x959c67a2abeaf049L;
    public String preBreak;
    public String noBreak;
    public String postBreak;
}
