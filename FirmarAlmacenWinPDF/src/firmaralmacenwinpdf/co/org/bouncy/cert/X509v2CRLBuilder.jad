// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509v2CRLBuilder.java

package co.org.bouncy.cert;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.operator.ContentSigner;
import java.math.BigInteger;
import java.util.Date;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.cert:
//            CertIOException, X509CRLHolder, CertUtils

public class X509v2CRLBuilder
{

    public X509v2CRLBuilder(X500Name issuer, Date thisUpdate)
    {
        tbsGen = new V2TBSCertListGenerator();
        extGenerator = new ExtensionsGenerator();
        tbsGen.setIssuer(issuer);
        tbsGen.setThisUpdate(new Time(thisUpdate));
    }

    public X509v2CRLBuilder setNextUpdate(Date date)
    {
        tbsGen.setNextUpdate(new Time(date));
        return this;
    }

    public X509v2CRLBuilder addCRLEntry(BigInteger userCertificateSerial, Date revocationDate, int reason)
    {
        tbsGen.addCRLEntry(new ASN1Integer(userCertificateSerial), new Time(revocationDate), reason);
        return this;
    }

    public X509v2CRLBuilder addCRLEntry(BigInteger userCertificateSerial, Date revocationDate, int reason, Date invalidityDate)
    {
        tbsGen.addCRLEntry(new ASN1Integer(userCertificateSerial), new Time(revocationDate), reason, new ASN1GeneralizedTime(invalidityDate));
        return this;
    }

    /**
     * @deprecated Method addCRLEntry is deprecated
     */

    public X509v2CRLBuilder addCRLEntry(BigInteger userCertificateSerial, Date revocationDate, X509Extensions extensions)
    {
        tbsGen.addCRLEntry(new ASN1Integer(userCertificateSerial), new Time(revocationDate), Extensions.getInstance(extensions));
        return this;
    }

    public X509v2CRLBuilder addCRLEntry(BigInteger userCertificateSerial, Date revocationDate, Extensions extensions)
    {
        tbsGen.addCRLEntry(new ASN1Integer(userCertificateSerial), new Time(revocationDate), extensions);
        return this;
    }

    public X509v2CRLBuilder addCRL(X509CRLHolder other)
    {
        TBSCertList revocations = other.toASN1Structure().getTBSCertList();
        if(revocations != null)
        {
            for(Enumeration en = revocations.getRevokedCertificateEnumeration(); en.hasMoreElements(); tbsGen.addCRLEntry(ASN1Sequence.getInstance(((ASN1Encodable)en.nextElement()).toASN1Primitive())));
        }
        return this;
    }

    public X509v2CRLBuilder addExtension(ASN1ObjectIdentifier oid, boolean isCritical, ASN1Encodable value)
        throws CertIOException
    {
        CertUtils.addExtension(extGenerator, oid, isCritical, value);
        return this;
    }

    public X509CRLHolder build(ContentSigner signer)
    {
        tbsGen.setSignature(signer.getAlgorithmIdentifier());
        if(!extGenerator.isEmpty())
            tbsGen.setExtensions(extGenerator.generate());
        return CertUtils.generateFullCRL(signer, tbsGen.generateTBSCertList());
    }

    private V2TBSCertListGenerator tbsGen;
    private ExtensionsGenerator extGenerator;
}
