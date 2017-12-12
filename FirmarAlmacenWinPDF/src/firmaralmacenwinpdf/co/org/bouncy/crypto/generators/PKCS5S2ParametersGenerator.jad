// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKCS5S2ParametersGenerator.java

package co.org.bouncy.crypto.generators;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.digests.SHA1Digest;
import co.org.bouncy.crypto.macs.HMac;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.crypto.params.ParametersWithIV;

public class PKCS5S2ParametersGenerator extends PBEParametersGenerator
{

    public PKCS5S2ParametersGenerator()
    {
        this(((Digest) (new SHA1Digest())));
    }

    public PKCS5S2ParametersGenerator(Digest digest)
    {
        hMac = new HMac(digest);
        state = new byte[hMac.getMacSize()];
    }

    private void F(byte S[], int c, byte iBuf[], byte out[], int outOff)
    {
        if(c == 0)
            throw new IllegalArgumentException("iteration count must be at least 1.");
        if(S != null)
            hMac.update(S, 0, S.length);
        hMac.update(iBuf, 0, iBuf.length);
        hMac.doFinal(state, 0);
        System.arraycopy(state, 0, out, outOff, state.length);
        for(int count = 1; count < c; count++)
        {
            hMac.update(state, 0, state.length);
            hMac.doFinal(state, 0);
            for(int j = 0; j != state.length; j++)
                out[outOff + j] ^= state[j];

        }

    }

    private byte[] generateDerivedKey(int dkLen)
    {
        int hLen = hMac.getMacSize();
        int l = ((dkLen + hLen) - 1) / hLen;
        byte iBuf[] = new byte[4];
        byte outBytes[] = new byte[l * hLen];
        int outPos = 0;
        CipherParameters param = new KeyParameter(password);
        hMac.init(param);
        for(int i = 1; i <= l; i++)
        {
            for(int pos = 3; ++iBuf[pos] == 0; pos--);
            F(salt, iterationCount, iBuf, outBytes, outPos);
            outPos += hLen;
        }

        return outBytes;
    }

    public CipherParameters generateDerivedParameters(int keySize)
    {
        keySize /= 8;
        byte dKey[] = generateDerivedKey(keySize);
        return new KeyParameter(dKey, 0, keySize);
    }

    public CipherParameters generateDerivedParameters(int keySize, int ivSize)
    {
        keySize /= 8;
        ivSize /= 8;
        byte dKey[] = generateDerivedKey(keySize + ivSize);
        return new ParametersWithIV(new KeyParameter(dKey, 0, keySize), dKey, keySize, ivSize);
    }

    public CipherParameters generateDerivedMacParameters(int keySize)
    {
        return generateDerivedParameters(keySize);
    }

    private Mac hMac;
    private byte state[];
}
