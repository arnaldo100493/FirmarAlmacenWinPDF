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
//            BcPBESecretKeyEncryptorBuilder, BcImplProvider, BcUtil

class BcPBESecretKeyEncryptorBuilder$1 extends PBESecretKeyEncryptor
{

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

    BcPBESecretKeyEncryptorBuilder$1(int x0, PGPDigestCalculator x1, int x2, SecureRandom x3, char x4[])
    {
        this$0 = BcPBESecretKeyEncryptorBuilder.this;
        super(x0, x1, x2, x3, x4);
    }
}
