// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPBESecretKeyEncryptorBuilder.java

package co.org.bouncy.openpgp.operator.bc;

import co.org.bouncy.crypto.*;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.operator.PBESecretKeyEncryptor;
import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.openpgp.operator.bc:
//            SHA1PGPDigestCalculator, BcImplProvider, BcUtil

public class BcPBESecretKeyEncryptorBuilder
{

    public BcPBESecretKeyEncryptorBuilder(int encAlgorithm)
    {
        this(encAlgorithm, ((PGPDigestCalculator) (new SHA1PGPDigestCalculator())));
    }

    public BcPBESecretKeyEncryptorBuilder(int encAlgorithm, int s2kCount)
    {
        this(encAlgorithm, ((PGPDigestCalculator) (new SHA1PGPDigestCalculator())), s2kCount);
    }

    public BcPBESecretKeyEncryptorBuilder(int encAlgorithm, PGPDigestCalculator s2kDigestCalculator)
    {
        this(encAlgorithm, s2kDigestCalculator, 96);
    }

    public BcPBESecretKeyEncryptorBuilder(int encAlgorithm, PGPDigestCalculator s2kDigestCalculator, int s2kCount)
    {
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

    public BcPBESecretKeyEncryptorBuilder setSecureRandom(SecureRandom random)
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
                return encryptKeyData(key, null, keyData, keyOff, keyLen);
            }

            public byte[] encryptKeyData(byte key[], byte iv[], byte keyData[], int keyOff, int keyLen)
                throws PGPException
            {
                try
                {
                    BlockCipher engine = BcImplProvider.createBlockCipher(encAlgorithm);
                    if(iv != null)
                    {
                        this.iv = iv;
                    } else
                    {
                        if(random == null)
                            random = new SecureRandom();
                        this.iv = iv = new byte[engine.getBlockSize()];
                        random.nextBytes(iv);
                    }
                    BufferedBlockCipher c = BcUtil.createSymmetricKeyWrapper(true, engine, key, iv);
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

            public byte[] getCipherIV()
            {
                return iv;
            }

            private byte iv[];
            final BcPBESecretKeyEncryptorBuilder this$0;

            
            {
                this$0 = BcPBESecretKeyEncryptorBuilder.this;
                super(x0, x1, x2, x3, x4);
            }
        }
;
    }

    private int encAlgorithm;
    private PGPDigestCalculator s2kDigestCalculator;
    private SecureRandom random;
    private int s2kCount;
}
