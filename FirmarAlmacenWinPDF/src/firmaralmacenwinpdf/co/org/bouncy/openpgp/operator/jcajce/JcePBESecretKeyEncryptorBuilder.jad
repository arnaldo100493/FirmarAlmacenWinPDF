// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePBESecretKeyEncryptorBuilder.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.jcajce.*;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.operator.PBESecretKeyEncryptor;
import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;

// Referenced classes of package co.org.bouncy.openpgp.operator.jcajce:
//            SHA1PGPDigestCalculator, OperatorHelper, PGPUtil

public class JcePBESecretKeyEncryptorBuilder
{

    public JcePBESecretKeyEncryptorBuilder(int encAlgorithm)
    {
        this(encAlgorithm, ((PGPDigestCalculator) (new SHA1PGPDigestCalculator())));
    }

    public JcePBESecretKeyEncryptorBuilder(int encAlgorithm, int s2kCount)
    {
        this(encAlgorithm, ((PGPDigestCalculator) (new SHA1PGPDigestCalculator())), s2kCount);
    }

    public JcePBESecretKeyEncryptorBuilder(int encAlgorithm, PGPDigestCalculator s2kDigestCalculator)
    {
        this(encAlgorithm, s2kDigestCalculator, 96);
    }

    public JcePBESecretKeyEncryptorBuilder(int encAlgorithm, PGPDigestCalculator s2kDigestCalculator, int s2kCount)
    {
        helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.s2kCount = 96;
        this.encAlgorithm = encAlgorithm;
        this.s2kDigestCalculator = s2kDigestCalculator;
        if(s2kCount < 0 || s2kCount > 255)
        {
            throw new IllegalArgumentException("s2KCount value outside of range 0 to 255.");
        } else
        {
            this.s2kCount = s2kCount;
            return;
        }
    }

    public JcePBESecretKeyEncryptorBuilder setProvider(Provider provider)
    {
        helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JcePBESecretKeyEncryptorBuilder setProvider(String providerName)
    {
        helper = new OperatorHelper(new NamedJcaJceHelper(providerName));
        return this;
    }

    public JcePBESecretKeyEncryptorBuilder setSecureRandom(SecureRandom random)
    {
        this.random = random;
        return this;
    }

    public PBESecretKeyEncryptor build(char passPhrase[])
    {
        if(random == null)
            random = new SecureRandom();
        return new PBESecretKeyEncryptor(encAlgorithm, s2kDigestCalculator, s2kCount, random, passPhrase) {

            public byte[] encryptKeyData(byte key[], byte keyData[], int keyOff, int keyLen)
                throws PGPException
            {
                try
                {
                    c = helper.createCipher((new StringBuilder()).append(PGPUtil.getSymmetricCipherName(encAlgorithm)).append("/CFB/NoPadding").toString());
                    c.init(1, PGPUtil.makeSymmetricKey(encAlgorithm, key), random);
                    iv = c.getIV();
                    return c.doFinal(keyData, keyOff, keyLen);
                }
                catch(IllegalBlockSizeException e)
                {
                    throw new PGPException((new StringBuilder()).append("illegal block size: ").append(e.getMessage()).toString(), e);
                }
                catch(BadPaddingException e)
                {
                    throw new PGPException((new StringBuilder()).append("bad padding: ").append(e.getMessage()).toString(), e);
                }
                catch(InvalidKeyException e)
                {
                    throw new PGPException((new StringBuilder()).append("invalid key: ").append(e.getMessage()).toString(), e);
                }
            }

            public byte[] encryptKeyData(byte key[], byte iv[], byte keyData[], int keyOff, int keyLen)
                throws PGPException
            {
                try
                {
                    c = helper.createCipher((new StringBuilder()).append(PGPUtil.getSymmetricCipherName(encAlgorithm)).append("/CFB/NoPadding").toString());
                    c.init(1, PGPUtil.makeSymmetricKey(encAlgorithm, key), new IvParameterSpec(iv));
                    this.iv = iv;
                    return c.doFinal(keyData, keyOff, keyLen);
                }
                catch(IllegalBlockSizeException e)
                {
                    throw new PGPException((new StringBuilder()).append("illegal block size: ").append(e.getMessage()).toString(), e);
                }
                catch(BadPaddingException e)
                {
                    throw new PGPException((new StringBuilder()).append("bad padding: ").append(e.getMessage()).toString(), e);
                }
                catch(InvalidKeyException e)
                {
                    throw new PGPException((new StringBuilder()).append("invalid key: ").append(e.getMessage()).toString(), e);
                }
                catch(InvalidAlgorithmParameterException e)
                {
                    throw new PGPException((new StringBuilder()).append("invalid iv: ").append(e.getMessage()).toString(), e);
                }
            }

            public byte[] getCipherIV()
            {
                return iv;
            }

            private Cipher c;
            private byte iv[];
            final JcePBESecretKeyEncryptorBuilder this$0;

            
            {
                this$0 = JcePBESecretKeyEncryptorBuilder.this;
                super(x0, x1, x2, x3, x4);
            }
        }
;
    }

    private OperatorHelper helper;
    private int encAlgorithm;
    private PGPDigestCalculator s2kDigestCalculator;
    private SecureRandom random;
    private int s2kCount;

}
