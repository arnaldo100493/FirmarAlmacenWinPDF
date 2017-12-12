// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ContentByteUtils.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.pdf.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ListIterator;

public class ContentByteUtils
{

    private ContentByteUtils()
    {
    }

    public static byte[] getContentBytesFromContentObject(PdfObject contentObject)
        throws IOException
    {
        byte result[];
        switch(contentObject.type())
        {
        case 10: // '\n'
            PRIndirectReference ref = (PRIndirectReference)contentObject;
            PdfObject directObject = PdfReader.getPdfObjectRelease(ref);
            result = getContentBytesFromContentObject(directObject);
            break;

        case 7: // '\007'
            PRStream stream = (PRStream)PdfReader.getPdfObjectRelease(contentObject);
            result = PdfReader.getStreamBytes(stream);
            break;

        case 5: // '\005'
            ByteArrayOutputStream allBytes = new ByteArrayOutputStream();
            PdfArray contentArray = (PdfArray)contentObject;
            for(ListIterator iter = contentArray.listIterator(); iter.hasNext(); allBytes.write(32))
            {
                PdfObject element = (PdfObject)iter.next();
                allBytes.write(getContentBytesFromContentObject(element));
            }

            result = allBytes.toByteArray();
            break;

        default:
            String msg = (new StringBuilder()).append("Unable to handle Content of type ").append(contentObject.getClass()).toString();
            throw new IllegalStateException(msg);
        }
        return result;
    }

    public static byte[] getContentBytesForPage(PdfReader reader, int pageNum)
        throws IOException
    {
        PdfDictionary pageDictionary = reader.getPageN(pageNum);
        PdfObject contentObject = pageDictionary.get(PdfName.CONTENTS);
        if(contentObject == null)
        {
            return new byte[0];
        } else
        {
            byte contentBytes[] = getContentBytesFromContentObject(contentObject);
            return contentBytes;
        }
    }
}
