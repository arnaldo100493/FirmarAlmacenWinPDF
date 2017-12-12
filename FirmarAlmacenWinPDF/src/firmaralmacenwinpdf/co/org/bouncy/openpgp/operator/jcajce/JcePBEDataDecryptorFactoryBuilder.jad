// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePBEDataDecryptorFactoryBuilder.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.jcajce.*;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.operator.*;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

// Referenced classes of package co.org.bouncy.openpgp.operator.jcajce:
//            OperatorHelper, PGPUtil

public class JcePBEDataDecryptorFactoryBuilder
{

    public JcePBEDataDecryptorFactoryBuilder(PGPDigestCalculatorProvider calculatorProvider)
    {
        helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.calculatorProvider = calculatorProvider;
    }

    public JcePBEDataDecryptorFactoryBuilder setProvider(Provider provider)
    {
        helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JcePBEDataDecryptorFactoryBuilder setProvider(String providerName)
    {
        helper = new OperatorHelper(new NamedJcaJceHelper(providerName));
        return this;
    }

    public PBEDataDecryptorFactory build(char passPhrase[])
    {
        return new PBEDataDecryptorFactory(passPhrase, calculatorProvider) {

            public byte[] recoverSessionData(int keyAlgorithm, byte key[], byte secKeyData[])
                throws PGPException
            {
                byte keyBytes[];
                try
                {
                    if(secKeyData != null && secKeyData.length > 0)
                    {
                        String cipherName = PGPUtil.getSymmetricCipherName(keyAlgorithm);
                        Cipher keyCipher = helper.createCipher((new StringBuilder()).append(cipherName).append("/CFB/NoPadding").toString());
                        keyCipher.init(2, new SecretKeySpec(key, cipherName), new IvParameterSpec(new byte[keyCipher.getBlockSize()]));
                        return keyCipher.doFinal(secKeyData);
                    }
                }
                catch(Exception e)
                {
                    throw new PGPException("Exception recovering session info", e);
                }
                keyBytes = new byte[key.length + 1];
                keyBytes[0] = (byte)keyAlgorithm;
                System.arraycopy(key, 0, keyBytes, 1, key.length);
                return keyBytes;
            }

            public PGPDataDecryptor createDataDecryptor(boolean withIntegrityPacket, int encAlgorithm, byte key[])
                throws PGPException
            {
                return helper.createDataDecryptor(withIntegrityPacket, encAlgorithm, key);
            }

            final JcePBEDataDecryptorFactoryBuilder this$0;

            
            {
                this$0 = JcePBEDataDecryptorFactoryBuilder.this;
                super(x0, x1);
            }
        }
;
    }

    private OperatorHelper helper;
    private PGPDigestCalculatorProvider calculatorProvider;

}
