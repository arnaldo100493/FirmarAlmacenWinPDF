// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSTypedStream.java

package co.org.bouncy.cms;

import co.org.bouncy.util.io.Streams;
import java.io.*;

// Referenced classes of package co.org.bouncy.cms:
//            CMSTypedStream

private static class CMSTypedStream$FullReaderStream extends FilterInputStream
{

    public int read(byte buf[], int off, int len)
        throws IOException
    {
        int totalRead = Streams.readFully(super.in, buf, off, len);
        return totalRead <= 0 ? -1 : totalRead;
    }

    CMSTypedStream$FullReaderStream(InputStream in)
    {
        super(in);
    }
}
