// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TimeStampToken.java

package co.org.bouncy.tsp;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ess.ESSCertID;
import co.org.bouncy.asn1.ess.ESSCertIDv2;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.IssuerSerial;

// Referenced classes of package co.org.bouncy.tsp:
//            TimeStampToken

private class TimeStampToken$CertID
{

    public String getHashAlgorithmName()
    {
        if(certID != null)
            return "SHA-1";
        if(NISTObjectIdentifiers.id_sha256.equals(certIDv2.getHashAlgorithm().getAlgorithm()))
            return "SHA-256";
        else
            return certIDv2.getHashAlgorithm().getAlgorithm().getId();
    }

    public AlgorithmIdentifier getHashAlgorithm()
    {
        if(certID != null)
            return new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1);
        else
            return certIDv2.getHashAlgorithm();
    }

    public byte[] getCertHash()
    {
        if(certID != null)
            return certID.getCertHash();
        else
            return certIDv2.getCertHash();
    }

    public IssuerSerial getIssuerSerial()
    {
        if(certID != null)
            return certID.getIssuerSerial();
        else
            return certIDv2.getIssuerSerial();
    }

    private ESSCertID certID;
    private ESSCertIDv2 certIDv2;
    final TimeStampToken this$0;

    TimeStampToken$CertID(ESSCertID certID)
    {
        this$0 = TimeStampToken.this;
        super();
        this.certID = certID;
        certIDv2 = null;
    }

    TimeStampToken$CertID(ESSCertIDv2 certID)
    {
        this$0 = TimeStampToken.this;
        super();
        certIDv2 = certID;
        this.certID = null;
    }
}
