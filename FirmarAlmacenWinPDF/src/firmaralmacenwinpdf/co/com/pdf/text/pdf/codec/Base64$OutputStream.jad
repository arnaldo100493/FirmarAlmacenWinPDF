// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Base64.java

package co.com.pdf.text.pdf.codec;

import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.*;

// Referenced classes of package co.com.pdf.text.pdf.codec:
//            Base64

public static class Base64$OutputStream extends FilterOutputStream
{

    public void write(int theByte)
        throws IOException
    {
        if(suspendEncoding)
        {
            super.out.write(theByte);
            return;
        }
        if(encode)
        {
            buffer[position++] = (byte)theByte;
            if(position >= bufferLength)
            {
                out.write(Base64.access$400(b4, buffer, bufferLength, options));
                lineLength += 4;
                if(breakLines && lineLength >= 76)
                {
                    out.write(10);
                    lineLength = 0;
                }
                position = 0;
            }
        } else
        if(decodabet[theByte & 0x7f] > -5)
        {
            buffer[position++] = (byte)theByte;
            if(position >= bufferLength)
            {
                int len = Base64.access$300(buffer, 0, b4, 0, options);
                out.write(b4, 0, len);
                position = 0;
            }
        } else
        if(decodabet[theByte & 0x7f] != -5)
            throw new IOException(MessageLocalization.getComposedMessage("invalid.character.in.base64.data", new Object[0]));
    }

    public void write(byte theBytes[], int off, int len)
        throws IOException
    {
        if(suspendEncoding)
        {
            super.out.write(theBytes, off, len);
            return;
        }
        for(int i = 0; i < len; i++)
            write(theBytes[off + i]);

    }

    public void flushBase64()
        throws IOException
    {
        if(position > 0)
            if(encode)
            {
                out.write(Base64.access$400(b4, buffer, position, options));
                position = 0;
            } else
            {
                throw new IOException(MessageLocalization.getComposedMessage("base64.input.not.properly.padded", new Object[0]));
            }
    }

    public void close()
        throws IOException
    {
        flushBase64();
        super.close();
        buffer = null;
        out = null;
    }

    public void suspendEncoding()
        throws IOException
    {
        flushBase64();
        suspendEncoding = true;
    }

    public void resumeEncoding()
    {
        suspendEncoding = false;
    }

    private boolean encode;
    private int position;
    private byte buffer[];
    private int bufferLength;
    private int lineLength;
    private boolean breakLines;
    private byte b4[];
    private boolean suspendEncoding;
    private int options;
    private byte alphabet[];
    private byte decodabet[];

    public Base64$OutputStream(OutputStream out)
    {
        this(out, 1);
    }

    public Base64$OutputStream(OutputStream out, int options)
    {
        super(out);
        breakLines = (options & 8) != 8;
        encode = (options & 1) == 1;
        bufferLength = encode ? 3 : 4;
        buffer = new byte[bufferLength];
        position = 0;
        lineLength = 0;
        suspendEncoding = false;
        b4 = new byte[4];
        this.options = options;
        alphabet = Base64.access$000(options);
        decodabet = Base64.access$100(options);
    }
}
