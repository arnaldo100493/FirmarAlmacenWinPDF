// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Charsets.java

package org.apache.commons.io;

import java.nio.charset.Charset;

public class Charsets
{

    public Charsets()
    {
    }

    public static Charset toCharset(Charset charset)
    {
        return charset != null ? charset : Charset.defaultCharset();
    }

    public static Charset toCharset(String charset)
    {
        return charset != null ? Charset.forName(charset) : Charset.defaultCharset();
    }

    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Charset US_ASCII = Charset.forName("US-ASCII");
    public static final Charset UTF_16 = Charset.forName("UTF-16");
    public static final Charset UTF_16BE = Charset.forName("UTF-16BE");
    public static final Charset UTF_16LE = Charset.forName("UTF-16LE");
    public static final Charset UTF_8 = Charset.forName("UTF-8");

}
