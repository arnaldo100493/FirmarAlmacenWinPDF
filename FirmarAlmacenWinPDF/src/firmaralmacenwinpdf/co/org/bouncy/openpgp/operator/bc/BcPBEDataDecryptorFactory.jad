// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPBEDataDecryptorFactory.java

package co.org.bouncy.openpgp.operator.bc;

import co.org.bouncy.crypto.BlockCipher;
import co.org.bouncy.crypto.BufferedBlockCipher;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.operator.PBEDataDecryptorFactory;
import co.org.bouncy.openpgp.operator.PGPDataDecryptor;

// Referenced classes of package co.org.bouncy.openpgp.operator.bc:
//            BcImplProvider, BcUtil, BcPGPDigestCalculatorProvider

public class BcPBEDataDecryptorFactory extends PBEDataDecryptorFactory
{

    public BcPBEDataDecryptorFactory(char pass[], BcPGPDigestCalculatorProvider calculatorProvider)
    {
        super(pass, calculatorProvider);
    }

    public byte[] recoverSessionData(int keyAlgorithm, byte key[], byte secKeyData[])
        throws PGPException
    {
        byte keyBytes[];
        try
        {
            if(secKeyData != null && secKeyData.length > 0)
            {
                BlockCipher engine = BcImplProvider.createBlockCipher(keyAlgorithm);
                BufferedBlockCipher cipher = BcUtil.createSymmetricKeyWrapper(false, engine, key, new byte[engine.getBlockSize()]);
                byte out[] = new byte[secKeyData.length];
                int len = cipher.processBytes(secKeyData, 0, secKeyData.length, out, 0);
                len += cipher.doFinal(out, len);
                return out;
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
        BlockCipher engine = BcImplProvider.createBlockCipher(encAlgorithm);
        return BcUtil.createDataDecryptor(withIntegrityPacket, engine, key);
    }
}
