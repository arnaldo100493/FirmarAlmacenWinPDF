// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PBESecretKeyDecryptor.java

package co.org.bouncy.openpgp.operator;

import co.org.bouncy.bcpg.S2K;
import co.org.bouncy.openpgp.PGPException;

// Referenced classes of package co.org.bouncy.openpgp.operator:
//            PGPDigestCalculatorProvider, PGPUtil, PGPDigestCalculator

public abstract class PBESecretKeyDecryptor
{

    protected PBESecretKeyDecryptor(char passPhrase[], PGPDigestCalculatorProvider calculatorProvider)
    {
        this.passPhrase = passPhrase;
        this.calculatorProvider = calculatorProvider;
    }

    public PGPDigestCalculator getChecksumCalculator(int hashAlgorithm)
        throws PGPException
    {
        return calculatorProvider.get(hashAlgorithm);
    }

    public byte[] makeKeyFromPassPhrase(int keyAlgorithm, S2K s2k)
        throws PGPException
    {
        return PGPUtil.makeKeyFromPassPhrase(calculatorProvider, keyAlgorithm, s2k, passPhrase);
    }

    public abstract byte[] recoverKeyData(int i, byte abyte0[], byte abyte1[], byte abyte2[], int j, int k)
        throws PGPException;

    private char passPhrase[];
    private PGPDigestCalculatorProvider calculatorProvider;
}
