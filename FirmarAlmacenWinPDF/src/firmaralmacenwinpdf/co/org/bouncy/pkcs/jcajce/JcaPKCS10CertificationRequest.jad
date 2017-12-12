// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaPKCS10CertificationRequest.java

package co.org.bouncy.pkcs.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.pkcs.CertificationRequest;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.jcajce.*;
import co.org.bouncy.pkcs.PKCS10CertificationRequest;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Hashtable;

public class JcaPKCS10CertificationRequest extends PKCS10CertificationRequest
{

    public JcaPKCS10CertificationRequest(CertificationRequest certificationRequest)
    {
        super(certificationRequest);
        helper = new DefaultJcaJceHelper();
    }

    public JcaPKCS10CertificationRequest(byte encoding[])
        throws IOException
    {
        super(encoding);
        helper = new DefaultJcaJceHelper();
    }

    public JcaPKCS10CertificationRequest(PKCS10CertificationRequest requestHolder)
    {
        super(requestHolder.toASN1Structure());
        helper = new DefaultJcaJceHelper();
    }

    public JcaPKCS10CertificationRequest setProvider(String providerName)
    {
        helper = new NamedJcaJceHelper(providerName);
        return this;
    }

    public JcaPKCS10CertificationRequest setProvider(Provider provider)
    {
        helper = new ProviderJcaJceHelper(provider);
        return this;
    }

    public PublicKey getPublicKey()
        throws InvalidKeyException, NoSuchAlgorithmException
    {
        try
        {
            SubjectPublicKeyInfo keyInfo = getSubjectPublicKeyInfo();
            X509EncodedKeySpec xspec = new X509EncodedKeySpec(keyInfo.getEncoded());
            KeyFactory kFact;
            try
            {
                kFact = helper.createKeyFactory(keyInfo.getAlgorithm().getAlgorithm().getId());
            }
            catch(NoSuchAlgorithmException e)
            {
                if(keyAlgorithms.get(keyInfo.getAlgorithm().getAlgorithm()) != null)
                {
                    String keyAlgorithm = (String)keyAlgorithms.get(keyInfo.getAlgorithm().getAlgorithm());
                    kFact = helper.createKeyFactory(keyAlgorithm);
                } else
                {
                    throw e;
                }
            }
            return kFact.generatePublic(xspec);
        }
        catch(InvalidKeySpecException e)
        {
            throw new InvalidKeyException("error decoding public key");
        }
        catch(IOException e)
        {
            throw new InvalidKeyException("error extracting key encoding");
        }
        catch(NoSuchProviderException e)
        {
            throw new NoSuchAlgorithmException((new StringBuilder()).append("cannot find provider: ").append(e.getMessage()).toString());
        }
    }

    private static Hashtable keyAlgorithms;
    private JcaJceHelper helper;

    static 
    {
        keyAlgorithms = new Hashtable();
        keyAlgorithms.put(PKCSObjectIdentifiers.rsaEncryption, "RSA");
        keyAlgorithms.put(X9ObjectIdentifiers.id_dsa, "DSA");
    }
}
