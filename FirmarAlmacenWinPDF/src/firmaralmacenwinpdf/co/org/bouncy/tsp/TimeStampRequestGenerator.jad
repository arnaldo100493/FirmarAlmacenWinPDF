// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TimeStampRequestGenerator.java

package co.org.bouncy.tsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.tsp.MessageImprint;
import co.org.bouncy.asn1.tsp.TimeStampReq;
import co.org.bouncy.asn1.x509.*;
import java.io.IOException;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.tsp:
//            TimeStampRequest, TSPIOException, TSPUtil

public class TimeStampRequestGenerator
{

    public TimeStampRequestGenerator()
    {
        extGenerator = new ExtensionsGenerator();
    }

    /**
     * @deprecated Method setReqPolicy is deprecated
     */

    public void setReqPolicy(String reqPolicy)
    {
        this.reqPolicy = new ASN1ObjectIdentifier(reqPolicy);
    }

    public void setReqPolicy(ASN1ObjectIdentifier reqPolicy)
    {
        this.reqPolicy = reqPolicy;
    }

    public void setCertReq(boolean certReq)
    {
        this.certReq = ASN1Boolean.getInstance(certReq);
    }

    /**
     * @deprecated Method addExtension is deprecated
     */

    public void addExtension(String OID, boolean critical, ASN1Encodable value)
        throws IOException
    {
        addExtension(OID, critical, value.toASN1Primitive().getEncoded());
    }

    /**
     * @deprecated Method addExtension is deprecated
     */

    public void addExtension(String OID, boolean critical, byte value[])
    {
        extGenerator.addExtension(new ASN1ObjectIdentifier(OID), critical, value);
    }

    public void addExtension(ASN1ObjectIdentifier oid, boolean isCritical, ASN1Encodable value)
        throws TSPIOException
    {
        TSPUtil.addExtension(extGenerator, oid, isCritical, value);
    }

    public void addExtension(ASN1ObjectIdentifier oid, boolean isCritical, byte value[])
    {
        extGenerator.addExtension(oid, isCritical, value);
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public TimeStampRequest generate(String digestAlgorithm, byte digest[])
    {
        return generate(digestAlgorithm, digest, null);
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public TimeStampRequest generate(String digestAlgorithmOID, byte digest[], BigInteger nonce)
    {
        if(digestAlgorithmOID == null)
            throw new IllegalArgumentException("No digest algorithm specified");
        ASN1ObjectIdentifier digestAlgOID = new ASN1ObjectIdentifier(digestAlgorithmOID);
        AlgorithmIdentifier algID = new AlgorithmIdentifier(digestAlgOID, DERNull.INSTANCE);
        MessageImprint messageImprint = new MessageImprint(algID, digest);
        Extensions ext = null;
        if(!extGenerator.isEmpty())
            ext = extGenerator.generate();
        if(nonce != null)
            return new TimeStampRequest(new TimeStampReq(messageImprint, reqPolicy, new ASN1Integer(nonce), certReq, ext));
        else
            return new TimeStampRequest(new TimeStampReq(messageImprint, reqPolicy, null, certReq, ext));
    }

    public TimeStampRequest generate(ASN1ObjectIdentifier digestAlgorithm, byte digest[])
    {
        return generate(digestAlgorithm.getId(), digest);
    }

    public TimeStampRequest generate(ASN1ObjectIdentifier digestAlgorithm, byte digest[], BigInteger nonce)
    {
        return generate(digestAlgorithm.getId(), digest, nonce);
    }

    private ASN1ObjectIdentifier reqPolicy;
    private ASN1Boolean certReq;
    private ExtensionsGenerator extGenerator;
}
