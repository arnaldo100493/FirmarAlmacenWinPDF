// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Base64.java

package co.org.bouncy.util.encoders;

import co.org.bouncy.util.Strings;
import java.io.*;

// Referenced classes of package co.org.bouncy.util.encoders:
//            EncoderException, DecoderException, Base64Encoder, Encoder

public class Base64
{

    public Base64()
    {
    }

    public static String toBase64String(byte data[])
    {
        return toBase64String(data, 0, data.length);
    }

    public static String toBase64String(byte data[], int off, int length)
    {
        byte encoded[] = encode(data, off, length);
        return Strings.fromByteArray(encoded);
    }

    public static byte[] encode(byte data[])
    {
        return encode(data, 0, data.length);
    }

    public static byte[] encode(byte data[], int off, int length)
    {
        int len = ((length + 2) / 3) * 4;
        ByteArrayOutputStream bOut = new ByteArrayOutputStream(len);
        try
        {
            encoder.encode(data, off, length, bOut);
        }
        catch(Exception e)
        {
            throw new EncoderException((new StringBuilder()).append("exception encoding base64 string: ").append(e.getMessage()).toString(), e);
        }
        return bOut.toByteArray();
    }

    public static int encode(byte data[], OutputStream out)
        throws IOException
    {
        return encoder.encode(data, 0, data.length, out);
    }

    public static int encode(byte data[], int off, int length, OutputStream out)
        throws IOException
    {
        return encoder.encode(data, off, length, out);
    }

    public static byte[] decode(byte data[])
    {
        int len = (data.length / 4) * 3;
        ByteArrayOutputStream bOut = new ByteArrayOutputStream(len);
        try
        {
            encoder.decode(data, 0, data.length, bOut);
        }
        catch(Exception e)
        {
            throw new DecoderException((new StringBuilder()).append("unable to decode base64 data: ").append(e.getMessage()).toString(), e);
        }
        return bOut.toByteArray();
    }

    public static byte[] decode(String data)
    {
        int len = (data.length() / 4) * 3;
        ByteArrayOutputStream bOut = new ByteArrayOutputStream(len);
        try
        {
            encoder.decode(data, bOut);
        }
        catch(Exception e)
        {
            throw new DecoderException((new StringBuilder()).append("unable to decode base64 string: ").append(e.getMessage()).toString(), e);
        }
        return bOut.toByteArray();
    }

    public static int decode(String data, OutputStream out)
        throws IOException
    {
        return encoder.decode(data, out);
    }

    private static final Encoder encoder = new Base64Encoder();

}
