// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MarkedContentInfo.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.pdf.*;

public class MarkedContentInfo
{

    public MarkedContentInfo(PdfName tag, PdfDictionary dictionary)
    {
        this.tag = tag;
        this.dictionary = dictionary == null ? new PdfDictionary() : dictionary;
    }

    public PdfName getTag()
    {
        return tag;
    }

    public boolean hasMcid()
    {
        return dictionary.contains(PdfName.MCID);
    }

    public int getMcid()
    {
        PdfNumber id = dictionary.getAsNumber(PdfName.MCID);
        if(id == null)
            throw new IllegalStateException("MarkedContentInfo does not contain MCID");
        else
            return id.intValue();
    }

    private final PdfName tag;
    private final PdfDictionary dictionary;
}
