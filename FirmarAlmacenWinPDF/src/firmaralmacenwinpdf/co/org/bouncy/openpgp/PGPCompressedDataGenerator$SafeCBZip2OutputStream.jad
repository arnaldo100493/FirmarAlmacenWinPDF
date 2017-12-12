// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPCompressedDataGenerator.java

package co.org.bouncy.openpgp;

import co.org.bouncy.apache.bzip2.CBZip2OutputStream;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPCompressedDataGenerator

private static class PGPCompressedDataGenerator$SafeCBZip2OutputStream extends CBZip2OutputStream
{

    public void close()
        throws IOException
    {
        finish();
    }

    public PGPCompressedDataGenerator$SafeCBZip2OutputStream(OutputStream output)
        throws IOException
    {
        super(output);
    }
}
