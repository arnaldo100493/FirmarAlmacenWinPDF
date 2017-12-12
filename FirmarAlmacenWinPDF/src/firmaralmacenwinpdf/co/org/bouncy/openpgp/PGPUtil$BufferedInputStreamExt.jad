// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPUtil.java

package co.org.bouncy.openpgp;

import java.io.*;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPUtil

static class PGPUtil$BufferedInputStreamExt extends BufferedInputStream
{

    public synchronized int available()
        throws IOException
    {
        int result = super.available();
        if(result < 0)
            result = 0x7fffffff;
        return result;
    }

    PGPUtil$BufferedInputStreamExt(InputStream input)
    {
        super(input);
    }
}
