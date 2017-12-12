// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPCompressedDataGenerator.java

package co.org.bouncy.openpgp;

import co.org.bouncy.apache.bzip2.CBZip2OutputStream;
import co.org.bouncy.bcpg.BCPGOutputStream;
import co.org.bouncy.bcpg.CompressionAlgorithmTags;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

// Referenced classes of package co.org.bouncy.openpgp:
//            WrappedGeneratorStream, StreamGenerator, PGPException

public class PGPCompressedDataGenerator
    implements CompressionAlgorithmTags, StreamGenerator
{
    private class SafeDeflaterOutputStream extends DeflaterOutputStream
    {

        public void close()
            throws IOException
        {
            finish();
            def.end();
        }

        final PGPCompressedDataGenerator this$0;

        public SafeDeflaterOutputStream(OutputStream output, int compression, boolean nowrap)
        {
            this$0 = PGPCompressedDataGenerator.this;
            super(output, new Deflater(compression, nowrap));
        }
    }

    private static class SafeCBZip2OutputStream extends CBZip2OutputStream
    {

        public void close()
            throws IOException
        {
            finish();
        }

        public SafeCBZip2OutputStream(OutputStream output)
            throws IOException
        {
            super(output);
        }
    }


    public PGPCompressedDataGenerator(int algorithm)
    {
        this(algorithm, -1);
    }

    public PGPCompressedDataGenerator(int algorithm, int compression)
    {
        switch(algorithm)
        {
        default:
            throw new IllegalArgumentException("unknown compression algorithm");

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            break;
        }
        if(compression != -1 && (compression < 0 || compression > 9))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("unknown compression level: ").append(compression).toString());
        } else
        {
            this.algorithm = algorithm;
            this.compression = compression;
            return;
        }
    }

    public OutputStream open(OutputStream out)
        throws IOException
    {
        if(dOut != null)
        {
            throw new IllegalStateException("generator already in open state");
        } else
        {
            pkOut = new BCPGOutputStream(out, 8);
            doOpen();
            return new WrappedGeneratorStream(dOut, this);
        }
    }

    public OutputStream open(OutputStream out, byte buffer[])
        throws IOException, PGPException
    {
        if(dOut != null)
        {
            throw new IllegalStateException("generator already in open state");
        } else
        {
            pkOut = new BCPGOutputStream(out, 8, buffer);
            doOpen();
            return new WrappedGeneratorStream(dOut, this);
        }
    }

    private void doOpen()
        throws IOException
    {
        pkOut.write(algorithm);
        switch(algorithm)
        {
        case 0: // '\0'
            dOut = pkOut;
            break;

        case 1: // '\001'
            dOut = new SafeDeflaterOutputStream(pkOut, compression, true);
            break;

        case 2: // '\002'
            dOut = new SafeDeflaterOutputStream(pkOut, compression, false);
            break;

        case 3: // '\003'
            dOut = new SafeCBZip2OutputStream(pkOut);
            break;

        default:
            throw new IllegalStateException();
        }
    }

    public void close()
        throws IOException
    {
        if(dOut != null)
        {
            if(dOut != pkOut)
            {
                dOut.close();
                dOut.flush();
            }
            dOut = null;
            pkOut.finish();
            pkOut.flush();
            pkOut = null;
        }
    }

    private int algorithm;
    private int compression;
    private OutputStream dOut;
    private BCPGOutputStream pkOut;
}
