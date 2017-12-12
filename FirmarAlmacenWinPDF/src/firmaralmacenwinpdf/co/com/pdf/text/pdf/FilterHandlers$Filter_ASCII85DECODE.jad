// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FilterHandlers.java

package co.com.pdf.text.pdf;

import java.io.IOException;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfReader, FilterHandlers, PdfName, PdfObject, 
//            PdfDictionary

private static class FilterHandlers$Filter_ASCII85DECODE
    implements FilterHandlers.FilterHandler
{

    public byte[] decode(byte b[], PdfName filterName, PdfObject decodeParams, PdfDictionary streamDictionary)
        throws IOException
    {
        b = PdfReader.ASCII85Decode(b);
        return b;
    }

    private FilterHandlers$Filter_ASCII85DECODE()
    {
    }

    FilterHandlers$Filter_ASCII85DECODE(FilterHandlers._cls1 x0)
    {
        this();
    }
}
