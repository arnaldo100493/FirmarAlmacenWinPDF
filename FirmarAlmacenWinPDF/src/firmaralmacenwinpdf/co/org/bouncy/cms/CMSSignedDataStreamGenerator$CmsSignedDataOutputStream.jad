// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSSignedDataStreamGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

// Referenced classes of package co.org.bouncy.cms:
//            SignerInfoGenerator, CMSException, CMSStreamException, SignerInformation, 
//            CMSSignedDataStreamGenerator, CMSUtils

private class CMSSignedDataStreamGenerator$CmsSignedDataOutputStream extends OutputStream
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
        digests.clear();
        if(certs.size() != 0)
        {
            co.org.bouncy.asn1.ASN1Set certSet = CMSUtils.createBerSetFromList(certs);
            _sigGen.getRawOutputStream().write((new BERTaggedObject(false, 0, certSet)).getEncoded());
        }
        if(crls.size() != 0)
        {
            co.org.bouncy.asn1.ASN1Set crlSet = CMSUtils.createBerSetFromList(crls);
            _sigGen.getRawOutputStream().write((new BERTaggedObject(false, 1, crlSet)).getEncoded());
        }
        ASN1EncodableVector signerInfos = new ASN1EncodableVector();
        for(Iterator it = signerGens.iterator(); it.hasNext();)
        {
            SignerInfoGenerator sigGen = (SignerInfoGenerator)it.next();
            try
            {
                signerInfos.add(sigGen.generate(_contentOID));
                byte calculatedDigest[] = sigGen.getCalculatedDigest();
                digests.put(sigGen.getDigestAlgorithm().getAlgorithm().getId(), calculatedDigest);
            }
            catch(CMSException e)
            {
                throw new CMSStreamException((new StringBuilder()).append("exception generating signers: ").append(e.getMessage()).toString(), e);
            }
        }

        SignerInformation signer;
        for(Iterator it = _signers.iterator(); it.hasNext(); signerInfos.add(signer.toASN1Structure()))
            signer = (SignerInformation)it.next();

        _sigGen.getRawOutputStream().write((new DERSet(signerInfos)).getEncoded());
        _sigGen.close();
        _sGen.close();
    }

    private OutputStream _out;
    private ASN1ObjectIdentifier _contentOID;
    private BERSequenceGenerator _sGen;
    private BERSequenceGenerator _sigGen;
    private BERSequenceGenerator _eiGen;
    final CMSSignedDataStreamGenerator this$0;

    public CMSSignedDataStreamGenerator$CmsSignedDataOutputStream(OutputStream out, ASN1ObjectIdentifier contentOID, BERSequenceGenerator sGen, BERSequenceGenerator sigGen, BERSequenceGenerator eiGen)
    {
        this$0 = CMSSignedDataStreamGenerator.this;
        super();
        _out = out;
        _contentOID = contentOID;
        _sGen = sGen;
        _sigGen = sigGen;
        _eiGen = eiGen;
    }
}
