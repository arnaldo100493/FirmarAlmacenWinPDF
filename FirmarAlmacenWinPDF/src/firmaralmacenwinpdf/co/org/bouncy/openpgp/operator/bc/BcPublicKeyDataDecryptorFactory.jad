// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPublicKeyDataDecryptorFactory.java

package co.org.bouncy.openpgp.operator.bc;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.PGPPrivateKey;
import co.org.bouncy.openpgp.operator.PGPDataDecryptor;
import co.org.bouncy.openpgp.operator.PublicKeyDataDecryptorFactory;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.openpgp.operator.bc:
//            BcPGPKeyConverter, BcImplProvider, BcUtil

public class BcPublicKeyDataDecryptorFactory
    implements PublicKeyDataDecryptorFactory
{

    public BcPublicKeyDataDecryptorFactory(PGPPrivateKey privKey)
    {
        keyConverter = new BcPGPKeyConverter();
        this.privKey = privKey;
    }

    public byte[] recoverSessionData(int keyAlgorithm, BigInteger secKeyData[])
        throws PGPException
    {
        try
        {
            AsymmetricBlockCipher c = BcImplProvider.createPublicKeyCipher(keyAlgorithm);
            AsymmetricKeyParameter key = keyConverter.getPrivateKey(privKey);
            BufferedAsymmetricBlockCipher c1 = new BufferedAsymmetricBlockCipher(c);
            c1.init(false, key);
            if(keyAlgorithm == 2 || keyAlgorithm == 1)
            {
                byte bi[] = secKeyData[0].toByteArray();
                if(bi[0] == 0)
                    c1.processBytes(bi, 1, bi.length - 1);
                else
                    c1.processBytes(bi, 0, bi.length);
            } else
            {
                BcPGPKeyConverter converter = new BcPGPKeyConverter();
                ElGamalPrivateKeyParameters parms = (ElGamalPrivateKeyParameters)converter.getPrivateKey(privKey);
                int size = (parms.getParameters().getP().bitLength() + 7) / 8;
                byte tmp[] = new byte[size];
                byte bi[] = secKeyData[0].toByteArray();
                if(bi.length > size)
                {
                    c1.processBytes(bi, 1, bi.length - 1);
                } else
                {
                    System.arraycopy(bi, 0, tmp, tmp.length - bi.length, bi.length);
                    c1.processBytes(tmp, 0, tmp.length);
                }
                bi = secKeyData[1].toByteArray();
                for(int i = 0; i != tmp.length; i++)
                    tmp[i] = 0;

                if(bi.length > size)
                {
                    c1.processBytes(bi, 1, bi.length - 1);
                } else
                {
                    System.arraycopy(bi, 0, tmp, tmp.length - bi.length, bi.length);
                    c1.processBytes(tmp, 0, tmp.length);
                }
            }
            return c1.doFinal();
        }
        catch(InvalidCipherTextException e)
        {
            throw new PGPException((new StringBuilder()).append("exception encrypting session info: ").append(e.getMessage()).toString(), e);
        }
    }

    public PGPDataDecryptor createDataDecryptor(boolean withIntegrityPacket, int encAlgorithm, byte key[])
        throws PGPException
    {
        co.org.bouncy.crypto.BlockCipher engine = BcImplProvider.createBlockCipher(encAlgorithm);
        return BcUtil.createDataDecryptor(withIntegrityPacket, engine, key);
    }

    private BcPGPKeyConverter keyConverter;
    private PGPPrivateKey privKey;
}
