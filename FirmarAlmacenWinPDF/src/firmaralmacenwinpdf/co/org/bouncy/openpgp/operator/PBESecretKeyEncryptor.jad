// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PBESecretKeyEncryptor.java

package co.org.bouncy.openpgp.operator;

import co.org.bouncy.bcpg.S2K;
import co.org.bouncy.openpgp.PGPException;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.openpgp.operator:
//            PGPDigestCalculator, PGPUtil

public abstract class PBESecretKeyEncryptor
{

    protected PBESecretKeyEncryptor(int encAlgorithm, PGPDigestCalculator s2kDigestCalculator, SecureRandom random, char passPhrase[])
    {
        this(encAlgorithm, s2kDigestCalculator, 96, random, passPhrase);
    }

    protected PBESecretKeyEncryptor(int encAlgorithm, PGPDigestCalculator s2kDigestCalculator, int s2kCount, SecureRandom random, char passPhrase[])
    {
        this.encAlgorithm = encAlgorithm;
        this.passPhrase = passPhrase;
        this.random = random;
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

    public int getAlgorithm()
    {
        return encAlgorithm;
    }

    public int getHashAlgorithm()
    {
        if(s2kDigestCalculator != null)
            return s2kDigestCalculator.getAlgorithm();
        else
            return -1;
    }

    public byte[] getKey()
        throws PGPException
    {
        return PGPUtil.makeKeyFromPassPhrase(s2kDigestCalculator, encAlgorithm, s2k, passPhrase);
    }

    public S2K getS2K()
    {
        return s2k;
    }

    public byte[] encryptKeyData(byte keyData[], int keyOff, int keyLen)
        throws PGPException
    {
        if(s2k == null)
        {
            byte iv[] = new byte[8];
            random.nextBytes(iv);
            s2k = new S2K(s2kDigestCalculator.getAlgorithm(), iv, s2kCount);
        }
        return encryptKeyData(getKey(), keyData, keyOff, keyLen);
    }

    public abstract byte[] encryptKeyData(byte abyte0[], byte abyte1[], int i, int j)
        throws PGPException;

    public byte[] encryptKeyData(byte key[], byte iv[], byte keyData[], int keyOff, int keyLen)
        throws PGPException
    {
        throw new PGPException("encryption of version 3 keys not supported.");
    }

    public abstract byte[] getCipherIV();

    protected int encAlgorithm;
    protected char passPhrase[];
    protected PGPDigestCalculator s2kDigestCalculator;
    protected int s2kCount;
    protected S2K s2k;
    protected SecureRandom random;
}
