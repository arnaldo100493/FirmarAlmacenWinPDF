// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FilterHandlers.java

package co.com.pdf.text.pdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

// Referenced classes of package co.com.pdf.text.pdf:
//            FilterHandlers, PdfName, PdfObject, PdfDictionary

private static class FilterHandlers$Filter_RUNLENGTHDECODE
    implements FilterHandlers.FilterHandler
{

    public byte[] decode(byte b[], PdfName filterName, PdfObject decodeParams, PdfDictionary streamDictionary)
        throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte dupCount = -1;
        for(int i = 0; i < b.length; i++)
        {
            dupCount = b[i];
            if(dupCount == -128)
                break;
            if(dupCount >= 0 && dupCount <= 127)
            {
                int bytesToCopy = dupCount + 1;
                baos.write(b, i, bytesToCopy);
                i += bytesToCopy;
                continue;
            }
            i++;
            for(int j = 0; j < 1 - dupCount; j++)
                baos.write(b[i]);

        }

        return baos.toByteArray();
    }

    private FilterHandlers$Filter_RUNLENGTHDECODE()
    {
    }

    FilterHandlers$Filter_RUNLENGTHDECODE(FilterHandlers._cls1 x0)
    {
        this();
    }
}
