// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Hex.java

package co.org.bouncy.util.encoders;

import co.org.bouncy.util.Strings;
import java.io.*;

// Referenced classes of package co.org.bouncy.util.encoders:
//            EncoderException, DecoderException, HexEncoder, Encoder

public class Hex
{

    public Hex()
    {
    }

    public static String toHexString(byte data[])
    {
        return toHexString(data, 0, data.length);
    }

    public static String toHexString(byte data[], int off, int length)
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
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        try
        {
            encoder.encode(data, off, length, bOut);
        }
        catch(Exception e)
        {
            throw new EncoderException((new StringBuilder()).append("exception encoding Hex string: ").append(e.getMessage()).toString(), e);
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
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        try
        {
            encoder.decode(data, 0, data.length, bOut);
        }
        catch(Exception e)
        {
            throw new DecoderException((new StringBuilder()).append("exception decoding Hex data: ").append(e.getMessage()).toString(), e);
        }
        return bOut.toByteArray();
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
            throw new DecoderException((new StringBuilder()).append("exception decoding Hex string: ").append(e.getMessage()).toString(), e);
        }
        return bOut.toByteArray();
    }

    public static int decode(String data, OutputStream out)
        throws IOException
    {
        return encoder.decode(data, out);
    }

    private static final Encoder encoder = new HexEncoder();

}
