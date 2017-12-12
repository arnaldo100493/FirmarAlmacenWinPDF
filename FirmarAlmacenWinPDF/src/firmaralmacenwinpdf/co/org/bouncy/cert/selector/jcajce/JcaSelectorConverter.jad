// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaSelectorConverter.java

package co.org.bouncy.cert.selector.jcajce;

import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.cert.selector.X509CertificateHolderSelector;
import java.io.IOException;
import java.security.cert.X509CertSelector;

public class JcaSelectorConverter
{

    public JcaSelectorConverter()
    {
    }

    public X509CertificateHolderSelector getCertificateHolderSelector(X509CertSelector certSelector)
    {
        try
        {
            if(certSelector.getSubjectKeyIdentifier() != null)
                return new X509CertificateHolderSelector(X500Name.getInstance(certSelector.getIssuerAsBytes()), certSelector.getSerialNumber(), ASN1OctetString.getInstance(certSelector.getSubjectKeyIdentifier()).getOctets());
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("unable to convert issuer: ").append(e.getMessage()).toString());
        }
        return new X509CertificateHolderSelector(X500Name.getInstance(certSelector.getIssuerAsBytes()), certSelector.getSerialNumber());
    }
}
