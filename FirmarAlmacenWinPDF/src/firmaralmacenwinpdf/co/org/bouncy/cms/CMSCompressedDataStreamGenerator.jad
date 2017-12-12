// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSCompressedDataStreamGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.CMSObjectIdentifiers;
import co.org.bouncy.operator.OutputCompressor;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;

// Referenced classes of package co.org.bouncy.cms:
//            CMSUtils

public class CMSCompressedDataStreamGenerator
{
    private class CmsCompressedOutputStream extends OutputStream
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

        CmsCompressedOutputStream(OutputStream out, BERSequenceGenerator sGen, BERSequenceGenerator cGen, BERSequenceGenerator eiGen)
        {
            this$0 = CMSCompressedDataStreamGenerator.this;
            super();
            _out = out;
            _sGen = sGen;
            _cGen = cGen;
            _eiGen = eiGen;
        }
    }


    public CMSCompressedDataStreamGenerator()
    {
    }

    public void setBufferSize(int bufferSize)
    {
        _bufferSize = bufferSize;
    }

    /**
     * @deprecated Method open is deprecated
     */

    public OutputStream open(OutputStream out, String compressionOID)
        throws IOException
    {
        return open(out, CMSObjectIdentifiers.data.getId(), compressionOID);
    }

    /**
     * @deprecated Method open is deprecated
     */

    public OutputStream open(OutputStream out, String contentOID, String compressionOID)
        throws IOException
    {
        BERSequenceGenerator sGen = new BERSequenceGenerator(out);
        sGen.addObject(CMSObjectIdentifiers.compressedData);
        BERSequenceGenerator cGen = new BERSequenceGenerator(sGen.getRawOutputStream(), 0, true);
        cGen.addObject(new ASN1Integer(0L));
        DERSequenceGenerator algGen = new DERSequenceGenerator(cGen.getRawOutputStream());
        algGen.addObject(new ASN1ObjectIdentifier("1.2.840.113549.1.9.16.3.8"));
        algGen.close();
        BERSequenceGenerator eiGen = new BERSequenceGenerator(cGen.getRawOutputStream());
        eiGen.addObject(new ASN1ObjectIdentifier(contentOID));
        OutputStream octetStream = CMSUtils.createBEROctetOutputStream(eiGen.getRawOutputStream(), 0, true, _bufferSize);
        return new CmsCompressedOutputStream(new DeflaterOutputStream(octetStream), sGen, cGen, eiGen);
    }

    public OutputStream open(OutputStream out, OutputCompressor compressor)
        throws IOException
    {
        return open(CMSObjectIdentifiers.data, out, compressor);
    }

    public OutputStream open(ASN1ObjectIdentifier contentOID, OutputStream out, OutputCompressor compressor)
        throws IOException
    {
        BERSequenceGenerator sGen = new BERSequenceGenerator(out);
        sGen.addObject(CMSObjectIdentifiers.compressedData);
        BERSequenceGenerator cGen = new BERSequenceGenerator(sGen.getRawOutputStream(), 0, true);
        cGen.addObject(new ASN1Integer(0L));
        cGen.addObject(compressor.getAlgorithmIdentifier());
        BERSequenceGenerator eiGen = new BERSequenceGenerator(cGen.getRawOutputStream());
        eiGen.addObject(contentOID);
        OutputStream octetStream = CMSUtils.createBEROctetOutputStream(eiGen.getRawOutputStream(), 0, true, _bufferSize);
        return new CmsCompressedOutputStream(compressor.getOutputStream(octetStream), sGen, cGen, eiGen);
    }

    public static final String ZLIB = "1.2.840.113549.1.9.16.3.8";
    private int _bufferSize;
}
