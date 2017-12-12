// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseFont.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.DocumentException;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfStream, PdfNumber, PdfName, BaseFont

static class BaseFont$StreamFont extends PdfStream
{

    public BaseFont$StreamFont(byte contents[], int lengths[], int compressionLevel)
        throws DocumentException
    {
        try
        {
            bytes = contents;
            put(PdfName.LENGTH, new PdfNumber(bytes.length));
            for(int k = 0; k < lengths.length; k++)
                put(new PdfName((new StringBuilder()).append("Length").append(k + 1).toString()), new PdfNumber(lengths[k]));

            flateCompress(compressionLevel);
        }
        catch(Exception e)
        {
            throw new DocumentException(e);
        }
    }

    public BaseFont$StreamFont(byte contents[], String subType, int compressionLevel)
        throws DocumentException
    {
        try
        {
            bytes = contents;
            put(PdfName.LENGTH, new PdfNumber(bytes.length));
            if(subType != null)
                put(PdfName.SUBTYPE, new PdfName(subType));
            flateCompress(compressionLevel);
        }
        catch(Exception e)
        {
            throw new DocumentException(e);
        }
    }
}
