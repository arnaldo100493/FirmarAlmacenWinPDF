// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePBEDataDecryptorFactoryBuilder.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.operator.*;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

// Referenced classes of package co.org.bouncy.openpgp.operator.jcajce:
//            JcePBEDataDecryptorFactoryBuilder, PGPUtil, OperatorHelper

class JcePBEDataDecryptorFactoryBuilder$1 extends PBEDataDecryptorFactory
{

    public byte[] recoverSessionData(int keyAlgorithm, byte key[], byte secKeyData[])
        throws PGPException
    {
        byte keyBytes[];
        try
        {
            if(secKeyData != null && secKeyData.length > 0)
            {
                String cipherName = PGPUtil.getSymmetricCipherName(keyAlgorithm);
                Cipher keyCipher = JcePBEDataDecryptorFactoryBuilder.access$000(JcePBEDataDecryptorFactoryBuilder.this).createCipher((new StringBuilder()).append(cipherName).append("/CFB/NoPadding").toString());
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
        return JcePBEDataDecryptorFactoryBuilder.access$000(JcePBEDataDecryptorFactoryBuilder.this).createDataDecryptor(withIntegrityPacket, encAlgorithm, key);
    }

    final JcePBEDataDecryptorFactoryBuilder this$0;

    JcePBEDataDecryptorFactoryBuilder$1(char x0[], PGPDigestCalculatorProvider x1)
    {
        this$0 = JcePBEDataDecryptorFactoryBuilder.this;
        super(x0, x1);
    }
}
