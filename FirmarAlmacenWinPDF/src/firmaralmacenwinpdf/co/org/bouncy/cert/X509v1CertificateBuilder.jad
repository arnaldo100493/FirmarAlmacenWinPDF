// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509v1CertificateBuilder.java

package co.org.bouncy.cert;

import co.org.bouncy.asn1.ASN1Integer;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.operator.ContentSigner;
import java.math.BigInteger;
import java.util.Date;

// Referenced classes of package co.org.bouncy.cert:
//            CertUtils, X509CertificateHolder

public class X509v1CertificateBuilder
{

    public X509v1CertificateBuilder(X500Name issuer, BigInteger serial, Date notBefore, Date notAfter, X500Name subject, SubjectPublicKeyInfo publicKeyInfo)
    {
        if(issuer == null)
            throw new IllegalArgumentException("issuer must not be null");
        if(publicKeyInfo == null)
        {
            throw new IllegalArgumentException("publicKeyInfo must not be null");
        } else
        {
            tbsGen = new V1TBSCertificateGenerator();
            tbsGen.setSerialNumber(new ASN1Integer(serial));
            tbsGen.setIssuer(issuer);
            tbsGen.setStartDate(new Time(notBefore));
            tbsGen.setEndDate(new Time(notAfter));
            tbsGen.setSubject(subject);
            tbsGen.setSubjectPublicKeyInfo(publicKeyInfo);
            return;
        }
    }

    public X509CertificateHolder build(ContentSigner signer)
    {
        tbsGen.setSignature(signer.getAlgorithmIdentifier());
        return CertUtils.generateFullCert(signer, tbsGen.generateTBSCertificate());
    }

    private V1TBSCertificateGenerator tbsGen;
}
