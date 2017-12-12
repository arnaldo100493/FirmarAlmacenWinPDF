// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePBESecretKeyEncryptorBuilder.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.operator.PBESecretKeyEncryptor;
import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;

// Referenced classes of package co.org.bouncy.openpgp.operator.jcajce:
//            JcePBESecretKeyEncryptorBuilder, PGPUtil, OperatorHelper

class JcePBESecretKeyEncryptorBuilder$1 extends PBESecretKeyEncryptor
{

    public byte[] encryptKeyData(byte key[], byte keyData[], int keyOff, int keyLen)
        throws PGPException
    {
        try
        {
            c = JcePBESecretKeyEncryptorBuilder.access$000(JcePBESecretKeyEncryptorBuilder.this).createCipher((new StringBuilder()).append(PGPUtil.getSymmetricCipherName(encAlgorithm)).append("/CFB/NoPadding").toString());
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
            c = JcePBESecretKeyEncryptorBuilder.access$000(JcePBESecretKeyEncryptorBuilder.this).createCipher((new StringBuilder()).append(PGPUtil.getSymmetricCipherName(encAlgorithm)).append("/CFB/NoPadding").toString());
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

    JcePBESecretKeyEncryptorBuilder$1(int x0, PGPDigestCalculator x1, int x2, SecureRandom x3, char x4[])
    {
        this$0 = JcePBESecretKeyEncryptorBuilder.this;
        super(x0, x1, x2, x3, x4);
    }
}
