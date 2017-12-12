// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePKCS12MacCalculatorBuilder.java

package co.org.bouncy.pkcs.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.DERNull;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCS12PBEParams;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.ExtendedDigest;
import co.org.bouncy.crypto.generators.PKCS12ParametersGenerator;
import co.org.bouncy.jcajce.*;
import co.org.bouncy.jcajce.io.MacOutputStream;
import co.org.bouncy.operator.*;
import co.org.bouncy.pkcs.PKCS12MacCalculatorBuilder;
import java.io.OutputStream;
import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.Mac;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class JcePKCS12MacCalculatorBuilder
    implements PKCS12MacCalculatorBuilder
{

    public JcePKCS12MacCalculatorBuilder()
    {
        this(OIWObjectIdentifiers.idSHA1);
    }

    public JcePKCS12MacCalculatorBuilder(ASN1ObjectIdentifier hashAlgorithm)
    {
        helper = new DefaultJcaJceHelper();
        iterationCount = 1024;
        algorithm = hashAlgorithm;
    }

    public JcePKCS12MacCalculatorBuilder setProvider(Provider provider)
    {
        helper = new ProviderJcaJceHelper(provider);
        return this;
    }

    public JcePKCS12MacCalculatorBuilder setProvider(String providerName)
    {
        helper = new NamedJcaJceHelper(providerName);
        return this;
    }

    public AlgorithmIdentifier getDigestAlgorithmIdentifier()
    {
        return new AlgorithmIdentifier(algorithm, DERNull.INSTANCE);
    }

    public MacCalculator build(final char password[])
        throws OperatorCreationException
    {
        if(random == null)
            random = new SecureRandom();
        try
        {
            final Mac mac = helper.createMac(algorithm.getId());
            saltLength = mac.getMacLength();
            final byte salt[] = new byte[saltLength];
            random.nextBytes(salt);
            SecretKeyFactory keyFact = helper.createSecretKeyFactory(algorithm.getId());
            PBEParameterSpec defParams = new PBEParameterSpec(salt, iterationCount);
            PBEKeySpec pbeSpec = new PBEKeySpec(password);
            javax.crypto.SecretKey key = keyFact.generateSecret(pbeSpec);
            mac.init(key, defParams);
            return new MacCalculator() {

                public AlgorithmIdentifier getAlgorithmIdentifier()
                {
                    return new AlgorithmIdentifier(algorithm, new PKCS12PBEParams(salt, iterationCount));
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
                    return new GenericKey(getAlgorithmIdentifier(), PKCS12ParametersGenerator.PKCS12PasswordToBytes(password));
                }

                final byte val$salt[];
                final Mac val$mac;
                final char val$password[];
                final JcePKCS12MacCalculatorBuilder this$0;

            
            {
                this$0 = JcePKCS12MacCalculatorBuilder.this;
                salt = abyte0;
                mac = mac1;
                password = ac;
                super();
            }
            }
;
        }
        catch(Exception e)
        {
            throw new OperatorCreationException((new StringBuilder()).append("unable to create MAC calculator: ").append(e.getMessage()).toString(), e);
        }
    }

    private JcaJceHelper helper;
    private ExtendedDigest digest;
    private ASN1ObjectIdentifier algorithm;
    private SecureRandom random;
    private int saltLength;
    private int iterationCount;


}
