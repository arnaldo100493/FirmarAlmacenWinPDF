// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UrlBase64.java

package co.org.bouncy.util.encoders;

import java.io.*;

// Referenced classes of package co.org.bouncy.util.encoders:
//            EncoderException, DecoderException, UrlBase64Encoder, Encoder

public class UrlBase64
{

    public UrlBase64()
    {
    }

    public static byte[] encode(byte data[])
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        try
        {
            encoder.encode(data, 0, data.length, bOut);
        }
        catch(Exception e)
        {
            throw new EncoderException((new StringBuilder()).append("exception encoding URL safe base64 data: ").append(e.getMessage()).toString(), e);
        }
        return bOut.toByteArray();
    }

    public static int encode(byte data[], OutputStream out)
        throws IOException
    {
        return encoder.encode(data, 0, data.length, out);
    }

    public static byte[] decode(byte data[])
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        try
        {
            encoder.decode(data, 0, data.length, bOut);
        }
        catch(Exception e)
        {
            throw new DecoderException((new StringBuilder()).append("exception decoding URL safe base64 string: ").append(e.getMessage()).toString(), e);
        }
        return bOut.toByteArray();
    }

    public static int decode(byte data[], OutputStream out)
        throws IOException
    {
        return encoder.decode(data, 0, data.length, out);
    }

    public static byte[] decode(String data)
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        try
        {
            encoder.decode(data, bOut);
        }
        catch(Exception e)
        {
            throw new DecoderException((new StringBuilder()).append("exception decoding URL safe base64 string: ").append(e.getMessage()).toString(), e);
        }
        return bOut.toByteArray();
    }

    public static int decode(String data, OutputStream out)
        throws IOException
    {
        return encoder.decode(data, out);
    }

    private static final Encoder encoder = new UrlBase64Encoder();

}
