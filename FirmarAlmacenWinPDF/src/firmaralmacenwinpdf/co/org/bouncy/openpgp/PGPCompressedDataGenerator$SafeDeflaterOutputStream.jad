// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPCompressedDataGenerator.java

package co.org.bouncy.openpgp;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPCompressedDataGenerator

private class PGPCompressedDataGenerator$SafeDeflaterOutputStream extends DeflaterOutputStream
{

    public void close()
        throws IOException
    {
        finish();
        def.end();
    }

    final PGPCompressedDataGenerator this$0;

    public PGPCompressedDataGenerator$SafeDeflaterOutputStream(OutputStream output, int compression, boolean nowrap)
    {
        this$0 = PGPCompressedDataGenerator.this;
        super(output, new Deflater(compression, nowrap));
    }
}
