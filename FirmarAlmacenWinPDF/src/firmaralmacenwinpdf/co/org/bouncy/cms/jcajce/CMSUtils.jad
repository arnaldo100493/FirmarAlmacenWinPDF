// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSUtils.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.cms.IssuerAndSerialNumber;
import co.org.bouncy.asn1.x509.*;
import java.security.Provider;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            EnvelopedDataHelper, ProviderJcaJceExtHelper, DefaultJcaJceExtHelper, NamedJcaJceExtHelper

class CMSUtils
{

    CMSUtils()
    {
    }

    static TBSCertificateStructure getTBSCertificateStructure(X509Certificate cert)
        throws CertificateEncodingException
    {
        return TBSCertificateStructure.getInstance(cert.getTBSCertificate());
    }

    static IssuerAndSerialNumber getIssuerAndSerialNumber(X509Certificate cert)
        throws CertificateEncodingException
    {
        Certificate certStruct = Certificate.getInstance(cert.getEncoded());
        return new IssuerAndSerialNumber(certStruct.getIssuer(), cert.getSerialNumber());
    }

    static byte[] getSubjectKeyId(X509Certificate cert)
    {
        byte ext[] = cert.getExtensionValue(X509Extension.subjectKeyIdentifier.getId());
        if(ext != null)
            return ASN1OctetString.getInstance(ASN1OctetString.getInstance(ext).getOctets()).getOctets();
        else
            return null;
    }

    static EnvelopedDataHelper createContentHelper(Provider provider)
    {
        if(provider != null)
            return new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        else
            return new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
    }

    static EnvelopedDataHelper createContentHelper(String providerName)
    {
        if(providerName != null)
            return new EnvelopedDataHelper(new NamedJcaJceExtHelper(providerName));
        else
            return new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
    }
}
