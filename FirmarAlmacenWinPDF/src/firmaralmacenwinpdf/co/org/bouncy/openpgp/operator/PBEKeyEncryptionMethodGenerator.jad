// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PBEKeyEncryptionMethodGenerator.java

package co.org.bouncy.openpgp.operator;

import co.org.bouncy.bcpg.*;
import co.org.bouncy.openpgp.PGPException;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.openpgp.operator:
//            PGPKeyEncryptionMethodGenerator, PGPDigestCalculator, PGPUtil

public abstract class PBEKeyEncryptionMethodGenerator extends PGPKeyEncryptionMethodGenerator
{

    protected PBEKeyEncryptionMethodGenerator(char passPhrase[], PGPDigestCalculator s2kDigestCalculator)
    {
        this(passPhrase, s2kDigestCalculator, 96);
    }

    protected PBEKeyEncryptionMethodGenerator(char passPhrase[], PGPDigestCalculator s2kDigestCalculator, int s2kCount)
    {
        this.passPhrase = passPhrase;
        this.s2kDigestCalculator = s2kDigestCalculator;
        if(s2kCount < 0 || s2kCount > 255)
        {
            throw new IllegalArgumentException("s2kCount value outside of range 0 to 255.");
        } else
        {
            this.s2kCount = s2kCount;
            return;
        }
    }

    public PBEKeyEncryptionMethodGenerator setSecureRandom(SecureRandom random)
    {
        this.random = random;
        return this;
    }

    public byte[] getKey(int encAlgorithm)
        throws PGPException
    {
        if(s2k == null)
        {
            byte iv[] = new byte[8];
            if(random == null)
                random = new SecureRandom();
            random.nextBytes(iv);
            s2k = new S2K(s2kDigestCalculator.getAlgorithm(), iv, s2kCount);
        }
        return PGPUtil.makeKeyFromPassPhrase(s2kDigestCalculator, encAlgorithm, s2k, passPhrase);
    }

    public ContainedPacket generate(int encAlgorithm, byte sessionInfo[])
        throws PGPException
    {
        byte key[] = getKey(encAlgorithm);
        if(sessionInfo == null)
        {
            return new SymmetricKeyEncSessionPacket(encAlgorithm, s2k, null);
        } else
        {
            byte nSessionInfo[] = new byte[sessionInfo.length - 2];
            System.arraycopy(sessionInfo, 0, nSessionInfo, 0, nSessionInfo.length);
            return new SymmetricKeyEncSessionPacket(encAlgorithm, s2k, encryptSessionInfo(encAlgorithm, key, nSessionInfo));
        }
    }

    protected abstract byte[] encryptSessionInfo(int i, byte abyte0[], byte abyte1[])
        throws PGPException;

    private char passPhrase[];
    private PGPDigestCalculator s2kDigestCalculator;
    private S2K s2k;
    private SecureRandom random;
    private int s2kCount;
}
