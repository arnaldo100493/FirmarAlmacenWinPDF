// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSAuthenticatedDataStreamGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.AttributeTable;
import co.org.bouncy.operator.DigestCalculator;
import co.org.bouncy.operator.MacCalculator;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

// Referenced classes of package co.org.bouncy.cms:
//            DefaultAuthenticatedAttributeTableGenerator, CMSAuthenticatedDataStreamGenerator, CMSAttributeTableGenerator

private class CMSAuthenticatedDataStreamGenerator$CmsAuthenticatedDataOutputStream extends OutputStream
{

    public void write(int b)
        throws IOException
    {
        dataStream.write(b);
    }

    public void write(byte bytes[], int off, int len)
        throws IOException
    {
        dataStream.write(bytes, off, len);
    }

    public void write(byte bytes[])
        throws IOException
    {
        dataStream.write(bytes);
    }

    public void close()
        throws IOException
    {
        dataStream.close();
        eiGen.close();
        Map parameters;
        if(digestCalculator != null)
        {
            parameters = Collections.unmodifiableMap(getBaseParameters(contentType, digestCalculator.getAlgorithmIdentifier(), digestCalculator.getDigest()));
            if(authGen == null)
                authGen = new DefaultAuthenticatedAttributeTableGenerator();
            ASN1Set authed = new DERSet(authGen.getAttributes(parameters).toASN1EncodableVector());
            OutputStream mOut = macCalculator.getOutputStream();
            mOut.write(authed.getEncoded("DER"));
            mOut.close();
            envGen.addObject(new DERTaggedObject(false, 2, authed));
        } else
        {
            parameters = Collections.unmodifiableMap(new HashMap());
        }
        envGen.addObject(new DEROctetString(macCalculator.getMac()));
        if(unauthGen != null)
            envGen.addObject(new DERTaggedObject(false, 3, new BERSet(unauthGen.getAttributes(parameters).toASN1EncodableVector())));
        envGen.close();
        cGen.close();
    }

    private OutputStream dataStream;
    private BERSequenceGenerator cGen;
    private BERSequenceGenerator envGen;
    private BERSequenceGenerator eiGen;
    private MacCalculator macCalculator;
    private DigestCalculator digestCalculator;
    private ASN1ObjectIdentifier contentType;
    final CMSAuthenticatedDataStreamGenerator this$0;

    public CMSAuthenticatedDataStreamGenerator$CmsAuthenticatedDataOutputStream(MacCalculator macCalculator, DigestCalculator digestCalculator, ASN1ObjectIdentifier contentType, OutputStream dataStream, BERSequenceGenerator cGen, BERSequenceGenerator envGen, 
            BERSequenceGenerator eiGen)
    {
        this$0 = CMSAuthenticatedDataStreamGenerator.this;
        super();
        this.macCalculator = macCalculator;
        this.digestCalculator = digestCalculator;
        this.contentType = contentType;
        this.dataStream = dataStream;
        this.cGen = cGen;
        this.envGen = envGen;
        this.eiGen = eiGen;
    }
}
