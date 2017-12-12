// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPBEKeyEncryptionMethodGenerator.java

package co.org.bouncy.openpgp.operator.bc;

import co.org.bouncy.crypto.*;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.operator.PBEKeyEncryptionMethodGenerator;
import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.openpgp.operator.bc:
//            SHA1PGPDigestCalculator, BcImplProvider, BcUtil

public class BcPBEKeyEncryptionMethodGenerator extends PBEKeyEncryptionMethodGenerator
{

    public BcPBEKeyEncryptionMethodGenerator(char passPhrase[], PGPDigestCalculator s2kDigestCalculator)
    {
        super(passPhrase, s2kDigestCalculator);
    }

    public BcPBEKeyEncryptionMethodGenerator(char passPhrase[])
    {
        this(passPhrase, ((PGPDigestCalculator) (new SHA1PGPDigestCalculator())));
    }

    public BcPBEKeyEncryptionMethodGenerator(char passPhrase[], PGPDigestCalculator s2kDigestCalculator, int s2kCount)
    {
        super(passPhrase, s2kDigestCalculator, s2kCount);
    }

    public BcPBEKeyEncryptionMethodGenerator(char passPhrase[], int s2kCount)
    {
        super(passPhrase, new SHA1PGPDigestCalculator(), s2kCount);
    }

    public PBEKeyEncryptionMethodGenerator setSecureRandom(SecureRandom random)
    {
        super.setSecureRandom(random);
        return this;
    }

    protected byte[] encryptSessionInfo(int encAlgorithm, byte key[], byte sessionInfo[])
        throws PGPException
    {
        try
        {
            BlockCipher engine = BcImplProvider.createBlockCipher(encAlgorithm);
            BufferedBlockCipher cipher = BcUtil.createSymmetricKeyWrapper(true, engine, key, new byte[engine.getBlockSize()]);
            byte out[] = new byte[sessionInfo.length];
            int len = cipher.processBytes(sessionInfo, 0, sessionInfo.length, out, 0);
            len += cipher.doFinal(out, len);
            return out;
        }
        catch(InvalidCipherTextException e)
        {
            throw new PGPException((new StringBuilder()).append("encryption failed: ").append(e.getMessage()).toString(), e);
        }
    }
}
