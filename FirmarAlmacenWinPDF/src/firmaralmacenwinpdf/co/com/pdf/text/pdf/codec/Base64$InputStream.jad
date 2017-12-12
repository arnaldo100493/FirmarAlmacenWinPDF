// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Base64.java

package co.com.pdf.text.pdf.codec;

import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.*;

// Referenced classes of package co.com.pdf.text.pdf.codec:
//            Base64

public static class Base64$InputStream extends FilterInputStream
{

    public int read()
        throws IOException
    {
        if(position < 0)
            if(encode)
            {
                byte b3[] = new byte[3];
                int numBinaryBytes = 0;
                for(int i = 0; i < 3; i++)
                    try
                    {
                        int b = in.read();
                        if(b >= 0)
                        {
                            b3[i] = (byte)b;
                            numBinaryBytes++;
                        }
                        continue;
                    }
                    catch(IOException e)
                    {
                        if(i == 0)
                            throw e;
                    }

                if(numBinaryBytes > 0)
                {
                    Base64.access$200(b3, 0, numBinaryBytes, buffer, 0, options);
                    position = 0;
                    numSigBytes = 4;
                } else
                {
                    return -1;
                }
            } else
            {
                byte b4[] = new byte[4];
                int i = 0;
                i = 0;
                do
                {
                    if(i >= 4)
                        break;
                    int b = 0;
                    do
                        b = in.read();
                    while(b >= 0 && decodabet[b & 0x7f] <= -5);
                    if(b < 0)
                        break;
                    b4[i] = (byte)b;
                    i++;
                } while(true);
                if(i == 4)
                {
                    numSigBytes = Base64.access$300(b4, 0, buffer, 0, options);
                    position = 0;
                } else
                if(i == 0)
                    return -1;
                else
                    throw new IOException(MessageLocalization.getComposedMessage("improperly.padded.base64.input", new Object[0]));
            }
        if(position >= 0)
        {
            if(position >= numSigBytes)
                return -1;
            if(encode && breakLines && lineLength >= 76)
            {
                lineLength = 0;
                return 10;
            }
            lineLength++;
            int b = buffer[position++];
            if(position >= bufferLength)
                position = -1;
            return b & 0xff;
        } else
        {
            throw new IOException(MessageLocalization.getComposedMessage("error.in.base64.code.reading.stream", new Object[0]));
        }
    }

    public int read(byte dest[], int off, int len)
        throws IOException
    {
        int i = 0;
        do
        {
            if(i >= len)
                break;
            int b = read();
            if(b >= 0)
            {
                dest[off + i] = (byte)b;
            } else
            {
                if(i == 0)
                    return -1;
                break;
            }
            i++;
        } while(true);
        return i;
    }

    private boolean encode;
    private int position;
    private byte buffer[];
    private int bufferLength;
    private int numSigBytes;
    private int lineLength;
    private boolean breakLines;
    private int options;
    private byte alphabet[];
    private byte decodabet[];

    public Base64$InputStream(InputStream in)
    {
        this(in, 0);
    }

    public Base64$InputStream(InputStream in, int options)
    {
        super(in);
        breakLines = (options & 8) != 8;
        encode = (options & 1) == 1;
        bufferLength = encode ? 4 : 3;
        buffer = new byte[bufferLength];
        position = -1;
        lineLength = 0;
        this.options = options;
        alphabet = Base64.access$000(options);
        decodabet = Base64.access$100(options);
    }
}
