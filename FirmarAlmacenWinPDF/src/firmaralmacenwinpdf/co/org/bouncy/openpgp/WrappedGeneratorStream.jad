// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WrappedGeneratorStream.java

package co.org.bouncy.openpgp;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.openpgp:
//            StreamGenerator

class WrappedGeneratorStream extends OutputStream
{

    public WrappedGeneratorStream(OutputStream out, StreamGenerator sGen)
    {
        _out = out;
        _sGen = sGen;
    }

    public void write(byte bytes[])
        throws IOException
    {
        _out.write(bytes);
    }

    public void write(byte bytes[], int offset, int length)
        throws IOException
    {
        _out.write(bytes, offset, length);
    }

    public void write(int b)
        throws IOException
    {
        _out.write(b);
    }

    public void flush()
        throws IOException
    {
        _out.flush();
    }

    public void close()
        throws IOException
    {
        _sGen.close();
    }

    private final OutputStream _out;
    private final StreamGenerator _sGen;
}
