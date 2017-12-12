// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaX509CertificateHolderSelector.java

package co.org.bouncy.cert.selector.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.Extension;
import co.org.bouncy.cert.selector.X509CertificateHolderSelector;
import java.math.BigInteger;
import java.security.cert.X509Certificate;
import javax.security.auth.x500.X500Principal;

public class JcaX509CertificateHolderSelector extends X509CertificateHolderSelector
{

    public JcaX509CertificateHolderSelector(X509Certificate certificate)
    {
        super(convertPrincipal(certificate.getIssuerX500Principal()), certificate.getSerialNumber(), getSubjectKeyId(certificate));
    }

    public JcaX509CertificateHolderSelector(X500Principal issuer, BigInteger serialNumber)
    {
        super(convertPrincipal(issuer), serialNumber);
    }

    public JcaX509CertificateHolderSelector(X500Principal issuer, BigInteger serialNumber, byte subjectKeyId[])
    {
        super(convertPrincipal(issuer), serialNumber, subjectKeyId);
    }

    private static X500Name convertPrincipal(X500Principal issuer)
    {
        if(issuer == null)
            return null;
        else
            return X500Name.getInstance(issuer.getEncoded());
    }

    private static byte[] getSubjectKeyId(X509Certificate cert)
    {
        byte ext[] = cert.getExtensionValue(Extension.subjectKeyIdentifier.getId());
        if(ext != null)
            return ASN1OctetString.getInstance(ASN1OctetString.getInstance(ext).getOctets()).getOctets();
        else
            return null;
    }
}
