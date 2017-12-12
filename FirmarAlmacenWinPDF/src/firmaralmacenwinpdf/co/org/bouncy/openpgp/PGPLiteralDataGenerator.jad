// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPLiteralDataGenerator.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.BCPGOutputStream;
import co.org.bouncy.util.Strings;
import java.io.*;
import java.util.Date;

// Referenced classes of package co.org.bouncy.openpgp:
//            WrappedGeneratorStream, StreamGenerator, PGPLiteralData

public class PGPLiteralDataGenerator
    implements StreamGenerator
{

    public PGPLiteralDataGenerator()
    {
        oldFormat = false;
    }

    public PGPLiteralDataGenerator(boolean oldFormat)
    {
        this.oldFormat = false;
        this.oldFormat = oldFormat;
    }

    private void writeHeader(OutputStream out, char format, byte encName[], long modificationTime)
        throws IOException
    {
        out.write(format);
        out.write((byte)encName.length);
        for(int i = 0; i != encName.length; i++)
            out.write(encName[i]);

        long modDate = modificationTime / 1000L;
        out.write((byte)(int)(modDate >> 24));
        out.write((byte)(int)(modDate >> 16));
        out.write((byte)(int)(modDate >> 8));
        out.write((byte)(int)modDate);
    }

    public OutputStream open(OutputStream out, char format, String name, long length, Date modificationTime)
        throws IOException
    {
        if(pkOut != null)
        {
            throw new IllegalStateException("generator already in open state");
        } else
        {
            byte encName[] = Strings.toUTF8ByteArray(name);
            pkOut = new BCPGOutputStream(out, 11, length + 2L + (long)encName.length + 4L, oldFormat);
            writeHeader(pkOut, format, encName, modificationTime.getTime());
            return new WrappedGeneratorStream(pkOut, this);
        }
    }

    public OutputStream open(OutputStream out, char format, String name, Date modificationTime, byte buffer[])
        throws IOException
    {
        if(pkOut != null)
        {
            throw new IllegalStateException("generator already in open state");
        } else
        {
            pkOut = new BCPGOutputStream(out, 11, buffer);
            byte encName[] = Strings.toUTF8ByteArray(name);
            writeHeader(pkOut, format, encName, modificationTime.getTime());
            return new WrappedGeneratorStream(pkOut, this);
        }
    }

    public OutputStream open(OutputStream out, char format, File file)
        throws IOException
    {
        if(pkOut != null)
        {
            throw new IllegalStateException("generator already in open state");
        } else
        {
            byte encName[] = Strings.toUTF8ByteArray(file.getName());
            pkOut = new BCPGOutputStream(out, 11, file.length() + 2L + (long)encName.length + 4L, oldFormat);
            writeHeader(pkOut, format, encName, file.lastModified());
            return new WrappedGeneratorStream(pkOut, this);
        }
    }

    public void close()
        throws IOException
    {
        if(pkOut != null)
        {
            pkOut.finish();
            pkOut.flush();
            pkOut = null;
        }
    }

    public static final char BINARY = 98;
    public static final char TEXT = 116;
    public static final char UTF8 = 117;
    public static final String CONSOLE = "_CONSOLE";
    public static final Date NOW;
    private BCPGOutputStream pkOut;
    private boolean oldFormat;

    static 
    {
        NOW = PGPLiteralData.NOW;
    }
}
