// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaSignerId.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.cms.SignerId;
import java.math.BigInteger;
import java.security.cert.X509Certificate;
import javax.security.auth.x500.X500Principal;

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            CMSUtils

public class JcaSignerId extends SignerId
{

    public JcaSignerId(X509Certificate certificate)
    {
        super(convertPrincipal(certificate.getIssuerX500Principal()), certificate.getSerialNumber(), CMSUtils.getSubjectKeyId(certificate));
    }

    public JcaSignerId(X500Principal issuer, BigInteger serialNumber)
    {
        super(convertPrincipal(issuer), serialNumber);
    }

    public JcaSignerId(X500Principal issuer, BigInteger serialNumber, byte subjectKeyId[])
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
}
