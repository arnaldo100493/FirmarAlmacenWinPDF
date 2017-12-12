// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ICC_Profile.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.ExceptionConverter;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.*;
import java.util.HashMap;

public class ICC_Profile
{

    protected ICC_Profile()
    {
    }

    public static ICC_Profile getInstance(byte data[], int numComponents)
    {
        if(data.length < 128 || data[36] != 97 || data[37] != 99 || data[38] != 115 || data[39] != 112)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("invalid.icc.profile", new Object[0]));
        try
        {
            ICC_Profile icc = new ICC_Profile();
            icc.data = data;
            Integer cs = (Integer)cstags.get(new String(data, 16, 4, "US-ASCII"));
            int nc = cs != null ? cs.intValue() : 0;
            icc.numComponents = nc;
            if(nc != numComponents)
                throw new IllegalArgumentException((new StringBuilder()).append("ICC profile contains ").append(nc).append(" component(s), the image data contains ").append(numComponents).append(" component(s)").toString());
            else
                return icc;
        }
        catch(UnsupportedEncodingException e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public static ICC_Profile getInstance(byte data[])
    {
        try
        {
            Integer cs = (Integer)cstags.get(new String(data, 16, 4, "US-ASCII"));
            int numComponents = cs != null ? cs.intValue() : 0;
            return getInstance(data, numComponents);
        }
        catch(UnsupportedEncodingException e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public static ICC_Profile getInstance(InputStream file)
    {
        try
        {
            byte head[] = new byte[128];
            int remain = head.length;
            int n;
            for(int ptr = 0; remain > 0; ptr += n)
            {
                n = file.read(head, ptr, remain);
                if(n < 0)
                    throw new IllegalArgumentException(MessageLocalization.getComposedMessage("invalid.icc.profile", new Object[0]));
                remain -= n;
            }

            if(head[36] != 97 || head[37] != 99 || head[38] != 115 || head[39] != 112)
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("invalid.icc.profile", new Object[0]));
            remain = (head[0] & 0xff) << 24 | (head[1] & 0xff) << 16 | (head[2] & 0xff) << 8 | head[3] & 0xff;
            byte icc[] = new byte[remain];
            System.arraycopy(head, 0, icc, 0, head.length);
            remain -= head.length;
            int n;
            for(int ptr = head.length; remain > 0; ptr += n)
            {
                n = file.read(icc, ptr, remain);
                if(n < 0)
                    throw new IllegalArgumentException(MessageLocalization.getComposedMessage("invalid.icc.profile", new Object[0]));
                remain -= n;
            }

            return getInstance(icc);
        }
        catch(Exception ex)
        {
            throw new ExceptionConverter(ex);
        }
    }

    public static ICC_Profile GetInstance(String fname)
    {
        FileInputStream fs = null;
        ICC_Profile icc_profile;
        try
        {
            fs = new FileInputStream(fname);
            ICC_Profile icc = getInstance(fs);
            icc_profile = icc;
        }
        catch(Exception ex)
        {
            throw new ExceptionConverter(ex);
        }
        try
        {
            fs.close();
        }
        catch(Exception x) { }
        return icc_profile;
        Exception exception;
        exception;
        try
        {
            fs.close();
        }
        catch(Exception x) { }
        throw exception;
    }

    public byte[] getData()
    {
        return data;
    }

    public int getNumComponents()
    {
        return numComponents;
    }

    protected byte data[];
    protected int numComponents;
    private static HashMap cstags;

    static 
    {
        cstags = new HashMap();
        cstags.put("XYZ ", Integer.valueOf(3));
        cstags.put("Lab ", Integer.valueOf(3));
        cstags.put("Luv ", Integer.valueOf(3));
        cstags.put("YCbr", Integer.valueOf(3));
        cstags.put("Yxy ", Integer.valueOf(3));
        cstags.put("RGB ", Integer.valueOf(3));
        cstags.put("GRAY", Integer.valueOf(1));
        cstags.put("HSV ", Integer.valueOf(3));
        cstags.put("HLS ", Integer.valueOf(3));
        cstags.put("CMYK", Integer.valueOf(4));
        cstags.put("CMY ", Integer.valueOf(3));
        cstags.put("2CLR", Integer.valueOf(2));
        cstags.put("3CLR", Integer.valueOf(3));
        cstags.put("4CLR", Integer.valueOf(4));
        cstags.put("5CLR", Integer.valueOf(5));
        cstags.put("6CLR", Integer.valueOf(6));
        cstags.put("7CLR", Integer.valueOf(7));
        cstags.put("8CLR", Integer.valueOf(8));
        cstags.put("9CLR", Integer.valueOf(9));
        cstags.put("ACLR", Integer.valueOf(10));
        cstags.put("BCLR", Integer.valueOf(11));
        cstags.put("CCLR", Integer.valueOf(12));
        cstags.put("DCLR", Integer.valueOf(13));
        cstags.put("ECLR", Integer.valueOf(14));
        cstags.put("FCLR", Integer.valueOf(15));
    }
}
