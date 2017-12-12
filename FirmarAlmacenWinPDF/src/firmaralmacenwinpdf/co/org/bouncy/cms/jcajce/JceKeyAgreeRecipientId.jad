// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceKeyAgreeRecipientId.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.cms.KeyAgreeRecipientId;
import java.math.BigInteger;
import java.security.cert.X509Certificate;
import javax.security.auth.x500.X500Principal;

public class JceKeyAgreeRecipientId extends KeyAgreeRecipientId
{

    public JceKeyAgreeRecipientId(X509Certificate certificate)
    {
        this(certificate.getIssuerX500Principal(), certificate.getSerialNumber());
    }

    public JceKeyAgreeRecipientId(X500Principal issuer, BigInteger serialNumber)
    {
        super(X500Name.getInstance(issuer.getEncoded()), serialNumber);
    }
}
