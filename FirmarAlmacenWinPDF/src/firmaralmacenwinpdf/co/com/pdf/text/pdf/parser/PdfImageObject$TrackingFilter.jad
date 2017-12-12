// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfImageObject.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.pdf.*;
import java.io.IOException;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            PdfImageObject

private static class PdfImageObject$TrackingFilter
    implements co.com.pdf.text.pdf.FilterHandlers.FilterHandler
{

    public byte[] decode(byte b[], PdfName filterName, PdfObject decodeParams, PdfDictionary streamDictionary)
        throws IOException
    {
        lastFilterName = filterName;
        return b;
    }

    public PdfName lastFilterName;

    private PdfImageObject$TrackingFilter()
    {
        lastFilterName = null;
    }

    PdfImageObject$TrackingFilter(PdfImageObject._cls1 x0)
    {
        this();
    }
}
