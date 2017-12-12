// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaX509CertSelectorConverter.java

package co.org.bouncy.cert.selector.jcajce;

import co.org.bouncy.asn1.DEROctetString;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.cert.selector.X509CertificateHolderSelector;
import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.X509CertSelector;

public class JcaX509CertSelectorConverter
{

    public JcaX509CertSelectorConverter()
    {
    }

    protected X509CertSelector doConversion(X500Name issuer, BigInteger serialNumber, byte subjectKeyIdentifier[])
    {
        X509CertSelector selector = new X509CertSelector();
        if(issuer != null)
            try
            {
                selector.setIssuer(issuer.getEncoded());
            }
            catch(IOException e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("unable to convert issuer: ").append(e.getMessage()).toString());
            }
        if(serialNumber != null)
            selector.setSerialNumber(serialNumber);
        if(subjectKeyIdentifier != null)
            try
            {
                selector.setSubjectKeyIdentifier((new DEROctetString(subjectKeyIdentifier)).getEncoded());
            }
            catch(IOException e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("unable to convert issuer: ").append(e.getMessage()).toString());
            }
        return selector;
    }

    public X509CertSelector getCertSelector(X509CertificateHolderSelector holderSelector)
    {
        return doConversion(holderSelector.getIssuer(), holderSelector.getSerialNumber(), holderSelector.getSubjectKeyIdentifier());
    }
}
