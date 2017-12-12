// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaCertificateRequestMessage.java

package co.org.bouncy.cert.crmf.jcajce;

import co.org.bouncy.asn1.crmf.CertReqMsg;
import co.org.bouncy.asn1.crmf.CertTemplate;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.cert.crmf.CRMFException;
import co.org.bouncy.cert.crmf.CertificateRequestMessage;
import co.org.bouncy.jcajce.*;
import java.io.IOException;
import java.security.Provider;
import java.security.PublicKey;
import javax.security.auth.x500.X500Principal;

// Referenced classes of package co.org.bouncy.cert.crmf.jcajce:
//            CRMFHelper

public class JcaCertificateRequestMessage extends CertificateRequestMessage
{

    public JcaCertificateRequestMessage(byte certReqMsg[])
    {
        this(CertReqMsg.getInstance(certReqMsg));
    }

    public JcaCertificateRequestMessage(CertificateRequestMessage certReqMsg)
    {
        this(certReqMsg.toASN1Structure());
    }

    public JcaCertificateRequestMessage(CertReqMsg certReqMsg)
    {
        super(certReqMsg);
        helper = new CRMFHelper(new DefaultJcaJceHelper());
    }

    public JcaCertificateRequestMessage setProvider(String providerName)
    {
        helper = new CRMFHelper(new NamedJcaJceHelper(providerName));
        return this;
    }

    public JcaCertificateRequestMessage setProvider(Provider provider)
    {
        helper = new CRMFHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public X500Principal getSubjectX500Principal()
    {
        X500Name subject = getCertTemplate().getSubject();
        if(subject != null)
            try
            {
                return new X500Principal(subject.getEncoded("DER"));
            }
            catch(IOException e)
            {
                throw new IllegalStateException((new StringBuilder()).append("unable to construct DER encoding of name: ").append(e.getMessage()).toString());
            }
        else
            return null;
    }

    public PublicKey getPublicKey()
        throws CRMFException
    {
        SubjectPublicKeyInfo subjectPublicKeyInfo = getCertTemplate().getPublicKey();
        if(subjectPublicKeyInfo != null)
            return helper.toPublicKey(subjectPublicKeyInfo);
        else
            return null;
    }

    private CRMFHelper helper;
}
