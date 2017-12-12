// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaEncryptedValueBuilder.java

package co.org.bouncy.cert.crmf.jcajce;

import co.org.bouncy.asn1.crmf.EncryptedValue;
import co.org.bouncy.cert.crmf.CRMFException;
import co.org.bouncy.cert.crmf.EncryptedValueBuilder;
import co.org.bouncy.cert.jcajce.JcaX509CertificateHolder;
import co.org.bouncy.operator.KeyWrapper;
import co.org.bouncy.operator.OutputEncryptor;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

public class JcaEncryptedValueBuilder extends EncryptedValueBuilder
{

    public JcaEncryptedValueBuilder(KeyWrapper wrapper, OutputEncryptor encryptor)
    {
        super(wrapper, encryptor);
    }

    public EncryptedValue build(X509Certificate certificate)
        throws CertificateEncodingException, CRMFException
    {
        return build(((co.org.bouncy.cert.X509CertificateHolder) (new JcaX509CertificateHolder(certificate))));
    }
}
