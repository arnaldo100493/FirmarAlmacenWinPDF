// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SP800SecureRandomBuilder.java

package co.org.bouncy.crypto.prng;

import co.org.bouncy.crypto.BlockCipher;
import co.org.bouncy.crypto.prng.drbg.CTRSP800DRBG;
import co.org.bouncy.crypto.prng.drbg.SP80090DRBG;

// Referenced classes of package co.org.bouncy.crypto.prng:
//            DRBGProvider, SP800SecureRandomBuilder, EntropySource

private static class SP800SecureRandomBuilder$CTRDRBGProvider
    implements DRBGProvider
{

    public SP80090DRBG get(EntropySource entropySource)
    {
        return new CTRSP800DRBG(blockCipher, keySizeInBits, securityStrength, entropySource, personalizationString, nonce);
    }

    private final BlockCipher blockCipher;
    private final int keySizeInBits;
    private final byte nonce[];
    private final byte personalizationString[];
    private final int securityStrength;

    public SP800SecureRandomBuilder$CTRDRBGProvider(BlockCipher blockCipher, int keySizeInBits, byte nonce[], byte personalizationString[], int securityStrength)
    {
        this.blockCipher = blockCipher;
        this.keySizeInBits = keySizeInBits;
        this.nonce = nonce;
        this.personalizationString = personalizationString;
        this.securityStrength = securityStrength;
    }
}
