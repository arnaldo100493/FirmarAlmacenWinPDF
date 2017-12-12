// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaPGPContentSignerBuilder.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.jcajce.*;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.PGPPrivateKey;
import co.org.bouncy.openpgp.operator.*;
import co.org.bouncy.util.io.TeeOutputStream;
import java.io.OutputStream;
import java.security.*;

// Referenced classes of package co.org.bouncy.openpgp.operator.jcajce:
//            OperatorHelper, JcaPGPDigestCalculatorProviderBuilder, JcaPGPKeyConverter, JcaPGPPrivateKey, 
//            SignatureOutputStream

public class JcaPGPContentSignerBuilder
    implements PGPContentSignerBuilder
{

    public JcaPGPContentSignerBuilder(int keyAlgorithm, int hashAlgorithm)
    {
        helper = new OperatorHelper(new DefaultJcaJceHelper());
        digestCalculatorProviderBuilder = new JcaPGPDigestCalculatorProviderBuilder();
        keyConverter = new JcaPGPKeyConverter();
        this.keyAlgorithm = keyAlgorithm;
        this.hashAlgorithm = hashAlgorithm;
    }

    public JcaPGPContentSignerBuilder setSecureRandom(SecureRandom random)
    {
        this.random = random;
        return this;
    }

    public JcaPGPContentSignerBuilder setProvider(Provider provider)
    {
        helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        keyConverter.setProvider(provider);
        digestCalculatorProviderBuilder.setProvider(provider);
        return this;
    }

    public JcaPGPContentSignerBuilder setProvider(String providerName)
    {
        helper = new OperatorHelper(new NamedJcaJceHelper(providerName));
        keyConverter.setProvider(providerName);
        digestCalculatorProviderBuilder.setProvider(providerName);
        return this;
    }

    public JcaPGPContentSignerBuilder setDigestProvider(Provider provider)
    {
        digestCalculatorProviderBuilder.setProvider(provider);
        return this;
    }

    public JcaPGPContentSignerBuilder setDigestProvider(String providerName)
    {
        digestCalculatorProviderBuilder.setProvider(providerName);
        return this;
    }

    public PGPContentSigner build(int signatureType, PGPPrivateKey privateKey)
        throws PGPException
    {
        if(privateKey instanceof JcaPGPPrivateKey)
            return build(signatureType, privateKey.getKeyID(), ((JcaPGPPrivateKey)privateKey).getPrivateKey());
        else
            return build(signatureType, privateKey.getKeyID(), keyConverter.getPrivateKey(privateKey));
    }

    public PGPContentSigner build(final int signatureType, final long keyID, PrivateKey privateKey)
        throws PGPException
    {
        final PGPDigestCalculator digestCalculator = digestCalculatorProviderBuilder.build().get(hashAlgorithm);
        final Signature signature = helper.createSignature(keyAlgorithm, hashAlgorithm);
        try
        {
            if(random != null)
                signature.initSign(privateKey, random);
            else
                signature.initSign(privateKey);
        }
        catch(InvalidKeyException e)
        {
            throw new PGPException("invalid key.", e);
        }
        return new PGPContentSigner() {

            public int getType()
            {
                return signatureType;
            }

            public int getHashAlgorithm()
            {
                return hashAlgorithm;
            }

            public int getKeyAlgorithm()
            {
                return keyAlgorithm;
            }

            public long getKeyID()
            {
                return keyID;
            }

            public OutputStream getOutputStream()
            {
                return new TeeOutputStream(new SignatureOutputStream(signature), digestCalculator.getOutputStream());
            }

            public byte[] getSignature()
            {
                try
                {
                    return signature.sign();
                }
                catch(SignatureException e)
                {
                    throw new IllegalStateException("unable to create signature");
                }
            }

            public byte[] getDigest()
            {
                return digestCalculator.getDigest();
            }

            final int val$signatureType;
            final long val$keyID;
            final Signature val$signature;
            final PGPDigestCalculator val$digestCalculator;
            final JcaPGPContentSignerBuilder this$0;

            
            {
                this$0 = JcaPGPContentSignerBuilder.this;
                signatureType = i;
                keyID = l;
                signature = signature1;
                digestCalculator = pgpdigestcalculator;
                super();
            }
        }
;
    }

    private OperatorHelper helper;
    private JcaPGPDigestCalculatorProviderBuilder digestCalculatorProviderBuilder;
    private JcaPGPKeyConverter keyConverter;
    private int hashAlgorithm;
    private SecureRandom random;
    private int keyAlgorithm;


}
