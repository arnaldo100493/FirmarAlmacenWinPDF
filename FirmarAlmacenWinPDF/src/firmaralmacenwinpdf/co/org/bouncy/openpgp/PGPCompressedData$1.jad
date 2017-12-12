// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPCompressedData.java

package co.org.bouncy.openpgp;

import java.io.*;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPCompressedData

class PGPCompressedData$1 extends InflaterInputStream
{

    protected void fill()
        throws IOException
    {
        if(eof)
            throw new EOFException("Unexpected end of ZIP input stream");
        len = in.read(buf, 0, buf.length);
        if(len == -1)
        {
            buf[0] = 0;
            len = 1;
            eof = true;
        }
        inf.setInput(buf, 0, len);
    }

    private boolean eof;
    final PGPCompressedData this$0;

    PGPCompressedData$1(InputStream x0, Inflater x1)
    {
        this$0 = PGPCompressedData.this;
        super(x0, x1);
        eof = false;
    }
}
