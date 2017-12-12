// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPEncryptedData.java

package co.org.bouncy.openpgp;

import java.io.*;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPEncryptedData

protected class PGPEncryptedData$TruncatedStream extends InputStream
{

    public int read()
        throws IOException
    {
        int ch = in.read();
        if(ch >= 0)
        {
            int c = lookAhead[bufPtr];
            lookAhead[bufPtr] = ch;
            bufPtr = (bufPtr + 1) % lookAhead.length;
            return c;
        } else
        {
            return -1;
        }
    }

    int[] getLookAhead()
    {
        int tmp[] = new int[lookAhead.length];
        int count = 0;
        for(int i = bufPtr; i != lookAhead.length; i++)
            tmp[count++] = lookAhead[i];

        for(int i = 0; i != bufPtr; i++)
            tmp[count++] = lookAhead[i];

        return tmp;
    }

    int lookAhead[];
    int bufPtr;
    InputStream in;
    final PGPEncryptedData this$0;

    PGPEncryptedData$TruncatedStream(InputStream in)
        throws IOException
    {
        this$0 = PGPEncryptedData.this;
        super();
        lookAhead = new int[22];
        for(int i = 0; i != lookAhead.length; i++)
            if((lookAhead[i] = in.read()) < 0)
                throw new EOFException();

        bufPtr = 0;
        this.in = in;
    }
}
