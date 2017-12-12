// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JceCMSMacCalculatorBuilder.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.jcajce.io.MacOutputStream;
import co.org.bouncy.operator.GenericKey;
import co.org.bouncy.operator.MacCalculator;
import co.org.bouncy.operator.jcajce.JceGenericKey;
import java.io.OutputStream;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            EnvelopedDataHelper, DefaultJcaJceExtHelper, ProviderJcaJceExtHelper, NamedJcaJceExtHelper

public class JceCMSMacCalculatorBuilder
{
    private class CMSMacCalculator
        implements MacCalculator
    {

        public AlgorithmIdentifier getAlgorithmIdentifier()
        {
            return algorithmIdentifier;
        }

        public OutputStream getOutputStream()
        {
            return new MacOutputStream(mac);
        }

        public byte[] getMac()
        {
            return mac.doFinal();
        }

        public GenericKey getKey()
        {
            return new JceGenericKey(algorithmIdentifier, encKey);
        }

        protected AlgorithmParameterSpec generateParameterSpec(ASN1ObjectIdentifier macOID, SecretKey encKey)
            throws CMSException
        {
            AlgorithmParameterGenerator pGen;
            AlgorithmParameters p;
            try
            {
                if(macOID.equals(PKCSObjectIdentifiers.RC2_CBC))
                {
                    byte iv[] = new byte[8];
                    random.nextBytes(iv);
                    return new RC2ParameterSpec(encKey.getEncoded().length * 8, iv);
                }
            }
            catch(GeneralSecurityException e)
            {
                return null;
            }
            pGen = helper.createAlgorithmParameterGenerator(macOID);
            p = pGen.generateParameters();
            return p.getParameterSpec(javax/crypto/spec/IvParameterSpec);
        }

        private SecretKey encKey;
        private AlgorithmIdentifier algorithmIdentifier;
        private Mac mac;
        private SecureRandom random;
        final JceCMSMacCalculatorBuilder this$0;

        CMSMacCalculator(ASN1ObjectIdentifier macOID, int keySize, SecureRandom random)
            throws CMSException
        {
            this$0 = JceCMSMacCalculatorBuilder.this;
            super();
            KeyGenerator keyGen = helper.createKeyGenerator(macOID);
            if(random == null)
                random = new SecureRandom();
            this.random = random;
            if(keySize < 0)
                keyGen.init(random);
            else
                keyGen.init(keySize, random);
            encKey = keyGen.generateKey();
            AlgorithmParameterSpec paramSpec = generateParameterSpec(macOID, encKey);
            algorithmIdentifier = helper.getAlgorithmIdentifier(macOID, paramSpec);
            mac = helper.createContentMac(encKey, algorithmIdentifier);
        }
    }


    public JceCMSMacCalculatorBuilder(ASN1ObjectIdentifier macOID)
    {
        this(macOID, -1);
    }

    public JceCMSMacCalculatorBuilder(ASN1ObjectIdentifier macOID, int keySize)
    {
        helper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
        this.macOID = macOID;
        this.keySize = keySize;
    }

    public JceCMSMacCalculatorBuilder setProvider(Provider provider)
    {
        helper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        return this;
    }

    public JceCMSMacCalculatorBuilder setProvider(String providerName)
    {
        helper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(providerName));
        return this;
    }

    public JceCMSMacCalculatorBuilder setSecureRandom(SecureRandom random)
    {
        this.random = random;
        return this;
    }

    public MacCalculator build()
        throws CMSException
    {
        return new CMSMacCalculator(macOID, keySize, random);
    }

    private final ASN1ObjectIdentifier macOID;
    private final int keySize;
    private EnvelopedDataHelper helper;
    private SecureRandom random;

}
