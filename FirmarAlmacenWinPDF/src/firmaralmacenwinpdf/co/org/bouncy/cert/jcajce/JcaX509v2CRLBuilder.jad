// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaX509v2CRLBuilder.java

package co.org.bouncy.cert.jcajce;

import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.cert.X509v2CRLBuilder;
import java.security.cert.X509Certificate;
import java.util.Date;
import javax.security.auth.x500.X500Principal;

public class JcaX509v2CRLBuilder extends X509v2CRLBuilder
{

    public JcaX509v2CRLBuilder(X500Principal issuer, Date now)
    {
        super(X500Name.getInstance(issuer.getEncoded()), now);
    }

    public JcaX509v2CRLBuilder(X509Certificate issuerCert, Date now)
    {
        this(issuerCert.getSubjectX500Principal(), now);
    }
}
