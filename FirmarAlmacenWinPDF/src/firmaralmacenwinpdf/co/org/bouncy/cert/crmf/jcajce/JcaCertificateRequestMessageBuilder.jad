// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaCertificateRequestMessageBuilder.java

package co.org.bouncy.cert.crmf.jcajce;

import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.GeneralName;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.cert.crmf.CertificateRequestMessageBuilder;
import java.math.BigInteger;
import java.security.PublicKey;
import javax.security.auth.x500.X500Principal;

public class JcaCertificateRequestMessageBuilder extends CertificateRequestMessageBuilder
{

    public JcaCertificateRequestMessageBuilder(BigInteger certReqId)
    {
        super(certReqId);
    }

    public JcaCertificateRequestMessageBuilder setIssuer(X500Principal issuer)
    {
        if(issuer != null)
            setIssuer(X500Name.getInstance(issuer.getEncoded()));
        return this;
    }

    public JcaCertificateRequestMessageBuilder setSubject(X500Principal subject)
    {
        if(subject != null)
            setSubject(X500Name.getInstance(subject.getEncoded()));
        return this;
    }

    public JcaCertificateRequestMessageBuilder setAuthInfoSender(X500Principal sender)
    {
        if(sender != null)
            setAuthInfoSender(new GeneralName(X500Name.getInstance(sender.getEncoded())));
        return this;
    }

    public JcaCertificateRequestMessageBuilder setPublicKey(PublicKey publicKey)
    {
        setPublicKey(SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()));
        return this;
    }
}
