// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePBESecretKeyDecryptorBuilder.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.jcajce.*;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.operator.PBESecretKeyDecryptor;
import co.org.bouncy.openpgp.operator.PGPDigestCalculatorProvider;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;

// Referenced classes of package co.org.bouncy.openpgp.operator.jcajce:
//            OperatorHelper, JcaPGPDigestCalculatorProviderBuilder, PGPUtil

public class JcePBESecretKeyDecryptorBuilder
{

    public JcePBESecretKeyDecryptorBuilder()
    {
        helper = new OperatorHelper(new DefaultJcaJceHelper());
        calculatorProviderBuilder = new JcaPGPDigestCalculatorProviderBuilder();
    }

    public JcePBESecretKeyDecryptorBuilder(PGPDigestCalculatorProvider calculatorProvider)
    {
        helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.calculatorProvider = calculatorProvider;
    }

    public JcePBESecretKeyDecryptorBuilder setProvider(Provider provider)
    {
        helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        if(calculatorProviderBuilder != null)
            calculatorProviderBuilder.setProvider(provider);
        return this;
    }

    public JcePBESecretKeyDecryptorBuilder setProvider(String providerName)
    {
        helper = new OperatorHelper(new NamedJcaJceHelper(providerName));
        if(calculatorProviderBuilder != null)
            calculatorProviderBuilder.setProvider(providerName);
        return this;
    }

    public PBESecretKeyDecryptor build(char passPhrase[])
        throws PGPException
    {
        if(calculatorProvider == null)
            calculatorProvider = calculatorProviderBuilder.build();
        return new PBESecretKeyDecryptor(passPhrase, calculatorProvider) {

            public byte[] recoverKeyData(int encAlgorithm, byte key[], byte iv[], byte keyData[], int keyOff, int keyLen)
                throws PGPException
            {
                try
                {
                    Cipher c = helper.createCipher((new StringBuilder()).append(PGPUtil.getSymmetricCipherName(encAlgorithm)).append("/CFB/NoPadding").toString());
                    c.init(2, PGPUtil.makeSymmetricKey(encAlgorithm, key), new IvParameterSpec(iv));
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
                catch(InvalidAlgorithmParameterException e)
                {
                    throw new PGPException((new StringBuilder()).append("invalid parameter: ").append(e.getMessage()).toString(), e);
                }
                catch(InvalidKeyException e)
                {
                    throw new PGPException((new StringBuilder()).append("invalid key: ").append(e.getMessage()).toString(), e);
                }
            }

            final JcePBESecretKeyDecryptorBuilder this$0;

            
            {
                this$0 = JcePBESecretKeyDecryptorBuilder.this;
                super(x0, x1);
            }
        }
;
    }

    private OperatorHelper helper;
    private PGPDigestCalculatorProvider calculatorProvider;
    private JcaPGPDigestCalculatorProviderBuilder calculatorProviderBuilder;

}
