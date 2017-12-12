// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceKeyTransRecipientInfoGenerator.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.cms.IssuerAndSerialNumber;
import co.org.bouncy.cert.jcajce.JcaX509CertificateHolder;
import co.org.bouncy.cms.KeyTransRecipientInfoGenerator;
import co.org.bouncy.operator.jcajce.JceAsymmetricKeyWrapper;
import java.security.Provider;
import java.security.PublicKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

public class JceKeyTransRecipientInfoGenerator extends KeyTransRecipientInfoGenerator
{

    public JceKeyTransRecipientInfoGenerator(X509Certificate recipientCert)
        throws CertificateEncodingException
    {
        super(new IssuerAndSerialNumber((new JcaX509CertificateHolder(recipientCert)).toASN1Structure()), new JceAsymmetricKeyWrapper(recipientCert.getPublicKey()));
    }

    public JceKeyTransRecipientInfoGenerator(byte subjectKeyIdentifier[], PublicKey publicKey)
    {
        super(subjectKeyIdentifier, new JceAsymmetricKeyWrapper(publicKey));
    }

    public JceKeyTransRecipientInfoGenerator setProvider(String providerName)
    {
        ((JceAsymmetricKeyWrapper)wrapper).setProvider(providerName);
        return this;
    }

    public JceKeyTransRecipientInfoGenerator setProvider(Provider provider)
    {
        ((JceAsymmetricKeyWrapper)wrapper).setProvider(provider);
        return this;
    }

    public JceKeyTransRecipientInfoGenerator setAlgorithmMapping(ASN1ObjectIdentifier algorithm, String algorithmName)
    {
        ((JceAsymmetricKeyWrapper)wrapper).setAlgorithmMapping(algorithm, algorithmName);
        return this;
    }
}
