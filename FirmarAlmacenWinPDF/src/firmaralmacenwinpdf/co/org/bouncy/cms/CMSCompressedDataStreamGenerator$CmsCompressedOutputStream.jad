// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSCompressedDataStreamGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.BERSequenceGenerator;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.cms:
//            CMSCompressedDataStreamGenerator

private class CMSCompressedDataStreamGenerator$CmsCompressedOutputStream extends OutputStream
{

    public void write(int b)
        throws IOException
    {
        _out.write(b);
    }

    public void write(byte bytes[], int off, int len)
        throws IOException
    {
        _out.write(bytes, off, len);
    }

    public void write(byte bytes[])
        throws IOException
    {
        _out.write(bytes);
    }

    public void close()
        throws IOException
    {
        _out.close();
        _eiGen.close();
        _cGen.close();
        _sGen.close();
    }

    private OutputStream _out;
    private BERSequenceGenerator _sGen;
    private BERSequenceGenerator _cGen;
    private BERSequenceGenerator _eiGen;
    final CMSCompressedDataStreamGenerator this$0;

    CMSCompressedDataStreamGenerator$CmsCompressedOutputStream(OutputStream out, BERSequenceGenerator sGen, BERSequenceGenerator cGen, BERSequenceGenerator eiGen)
    {
        this$0 = CMSCompressedDataStreamGenerator.this;
        super();
        _out = out;
        _sGen = sGen;
        _cGen = cGen;
        _eiGen = eiGen;
    }
}
