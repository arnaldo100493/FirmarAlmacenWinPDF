// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPBESecretKeyDecryptorBuilder.java

package co.org.bouncy.openpgp.operator.bc;

import co.org.bouncy.crypto.BufferedBlockCipher;
import co.org.bouncy.crypto.InvalidCipherTextException;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.operator.PBESecretKeyDecryptor;
import co.org.bouncy.openpgp.operator.PGPDigestCalculatorProvider;

// Referenced classes of package co.org.bouncy.openpgp.operator.bc:
//            BcImplProvider, BcUtil

public class BcPBESecretKeyDecryptorBuilder
{

    public BcPBESecretKeyDecryptorBuilder(PGPDigestCalculatorProvider calculatorProvider)
    {
        this.calculatorProvider = calculatorProvider;
    }

    public PBESecretKeyDecryptor build(char passPhrase[])
    {
        return new PBESecretKeyDecryptor(passPhrase, calculatorProvider) {

            public byte[] recoverKeyData(int encAlgorithm, byte key[], byte iv[], byte keyData[], int keyOff, int keyLen)
                throws PGPException
            {
                try
                {
                    BufferedBlockCipher c = BcUtil.createSymmetricKeyWrapper(false, BcImplProvider.createBlockCipher(encAlgorithm), key, iv);
                    byte out[] = new byte[keyLen];
                    int outLen = c.processBytes(keyData, keyOff, keyLen, out, 0);
                    outLen += c.doFinal(out, outLen);
                    return out;
                }
                catch(InvalidCipherTextException e)
                {
                    throw new PGPException((new StringBuilder()).append("decryption failed: ").append(e.getMessage()).toString(), e);
                }
            }

            final BcPBESecretKeyDecryptorBuilder this$0;

            
            {
                this$0 = BcPBESecretKeyDecryptorBuilder.this;
                super(x0, x1);
            }
        }
;
    }

    private PGPDigestCalculatorProvider calculatorProvider;
}
