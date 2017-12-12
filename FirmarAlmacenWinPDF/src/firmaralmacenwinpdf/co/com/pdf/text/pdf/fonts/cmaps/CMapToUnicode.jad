// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMapToUnicode.java

package co.com.pdf.text.pdf.fonts.cmaps;

import co.com.pdf.text.ExceptionConverter;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.PdfObject;
import co.com.pdf.text.pdf.PdfString;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf.fonts.cmaps:
//            AbstractCMap

public class CMapToUnicode extends AbstractCMap
{

    public CMapToUnicode()
    {
        singleByteMappings = new HashMap();
        doubleByteMappings = new HashMap();
    }

    public boolean hasOneByteMappings()
    {
        return !singleByteMappings.isEmpty();
    }

    public boolean hasTwoByteMappings()
    {
        return !doubleByteMappings.isEmpty();
    }

    public String lookup(byte code[], int offset, int length)
    {
        String result = null;
        Integer key = null;
        if(length == 1)
        {
            key = Integer.valueOf(code[offset] & 0xff);
            result = (String)singleByteMappings.get(key);
        } else
        if(length == 2)
        {
            int intKey = code[offset] & 0xff;
            intKey <<= 8;
            intKey += code[offset + 1] & 0xff;
            key = Integer.valueOf(intKey);
            result = (String)doubleByteMappings.get(key);
        }
        return result;
    }

    public Map createReverseMapping()
        throws IOException
    {
        Map result = new HashMap();
        java.util.Map.Entry entry;
        for(Iterator i$ = singleByteMappings.entrySet().iterator(); i$.hasNext(); result.put(Integer.valueOf(convertToInt((String)entry.getValue())), entry.getKey()))
            entry = (java.util.Map.Entry)i$.next();

        java.util.Map.Entry entry;
        for(Iterator i$ = doubleByteMappings.entrySet().iterator(); i$.hasNext(); result.put(Integer.valueOf(convertToInt((String)entry.getValue())), entry.getKey()))
            entry = (java.util.Map.Entry)i$.next();

        return result;
    }

    public Map createDirectMapping()
        throws IOException
    {
        Map result = new HashMap();
        java.util.Map.Entry entry;
        for(Iterator i$ = singleByteMappings.entrySet().iterator(); i$.hasNext(); result.put(entry.getKey(), Integer.valueOf(convertToInt((String)entry.getValue()))))
            entry = (java.util.Map.Entry)i$.next();

        java.util.Map.Entry entry;
        for(Iterator i$ = doubleByteMappings.entrySet().iterator(); i$.hasNext(); result.put(entry.getKey(), Integer.valueOf(convertToInt((String)entry.getValue()))))
            entry = (java.util.Map.Entry)i$.next();

        return result;
    }

    private int convertToInt(String s)
        throws IOException
    {
        byte b[] = s.getBytes("UTF-16BE");
        int value = 0;
        for(int i = 0; i < b.length - 1; i++)
        {
            value += b[i] & 0xff;
            value <<= 8;
        }

        value += b[b.length - 1] & 0xff;
        return value;
    }

    void addChar(int cid, String uni)
    {
        doubleByteMappings.put(Integer.valueOf(cid), uni);
    }

    void addChar(PdfString mark, PdfObject code)
    {
        try
        {
            byte src[] = mark.getBytes();
            String dest = createStringFromBytes(code.getBytes());
            if(src.length == 1)
                singleByteMappings.put(Integer.valueOf(src[0] & 0xff), dest);
            else
            if(src.length == 2)
            {
                int intSrc = src[0] & 0xff;
                intSrc <<= 8;
                intSrc |= src[1] & 0xff;
                doubleByteMappings.put(Integer.valueOf(intSrc), dest);
            } else
            {
                throw new IOException(MessageLocalization.getComposedMessage("mapping.code.should.be.1.or.two.bytes.and.not.1", src.length));
            }
        }
        catch(Exception ex)
        {
            throw new ExceptionConverter(ex);
        }
    }

    private String createStringFromBytes(byte bytes[])
        throws IOException
    {
        String retval = null;
        if(bytes.length == 1)
            retval = new String(bytes);
        else
            retval = new String(bytes, "UTF-16BE");
        return retval;
    }

    private Map singleByteMappings;
    private Map doubleByteMappings;
}
