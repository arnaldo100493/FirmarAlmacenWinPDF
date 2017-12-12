// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509v3CertificateBuilder.java

package co.org.bouncy.cert;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.operator.ContentSigner;
import java.math.BigInteger;
import java.util.Date;

// Referenced classes of package co.org.bouncy.cert:
//            CertIOException, CertUtils, X509CertificateHolder

public class X509v3CertificateBuilder
{

    public X509v3CertificateBuilder(X500Name issuer, BigInteger serial, Date notBefore, Date notAfter, X500Name subject, SubjectPublicKeyInfo publicKeyInfo)
    {
        tbsGen = new V3TBSCertificateGenerator();
        tbsGen.setSerialNumber(new ASN1Integer(serial));
        tbsGen.setIssuer(issuer);
        tbsGen.setStartDate(new Time(notBefore));
        tbsGen.setEndDate(new Time(notAfter));
        tbsGen.setSubject(subject);
        tbsGen.setSubjectPublicKeyInfo(publicKeyInfo);
        extGenerator = new ExtensionsGenerator();
    }

    public X509v3CertificateBuilder setSubjectUniqueID(boolean uniqueID[])
    {
        tbsGen.setSubjectUniqueID(CertUtils.booleanToBitString(uniqueID));
        return this;
    }

    public X509v3CertificateBuilder setIssuerUniqueID(boolean uniqueID[])
    {
        tbsGen.setIssuerUniqueID(CertUtils.booleanToBitString(uniqueID));
        return this;
    }

    public X509v3CertificateBuilder addExtension(ASN1ObjectIdentifier oid, boolean isCritical, ASN1Encodable value)
        throws CertIOException
    {
        CertUtils.addExtension(extGenerator, oid, isCritical, value);
        return this;
    }

    public X509v3CertificateBuilder copyAndAddExtension(ASN1ObjectIdentifier oid, boolean isCritical, X509CertificateHolder certHolder)
    {
        Certificate cert = certHolder.toASN1Structure();
        Extension extension = cert.getTBSCertificate().getExtensions().getExtension(oid);
        if(extension == null)
        {
            throw new NullPointerException((new StringBuilder()).append("extension ").append(oid).append(" not present").toString());
        } else
        {
            extGenerator.addExtension(oid, isCritical, extension.getExtnValue().getOctets());
            return this;
        }
    }

    public X509CertificateHolder build(ContentSigner signer)
    {
        tbsGen.setSignature(signer.getAlgorithmIdentifier());
        if(!extGenerator.isEmpty())
            tbsGen.setExtensions(extGenerator.generate());
        return CertUtils.generateFullCert(signer, tbsGen.generateTBSCertificate());
    }

    private V3TBSCertificateGenerator tbsGen;
    private ExtensionsGenerator extGenerator;
}
