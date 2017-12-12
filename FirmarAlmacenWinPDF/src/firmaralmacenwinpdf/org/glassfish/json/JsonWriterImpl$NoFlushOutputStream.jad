// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonWriterImpl.java

package org.glassfish.json;

import java.io.*;

// Referenced classes of package org.glassfish.json:
//            JsonWriterImpl

private static final class JsonWriterImpl$NoFlushOutputStream extends FilterOutputStream
{

    public void write(byte b[], int off, int len)
        throws IOException
    {
        out.write(b, off, len);
    }

    public void flush()
    {
    }

    public JsonWriterImpl$NoFlushOutputStream(OutputStream out)
    {
        super(out);
    }
}
