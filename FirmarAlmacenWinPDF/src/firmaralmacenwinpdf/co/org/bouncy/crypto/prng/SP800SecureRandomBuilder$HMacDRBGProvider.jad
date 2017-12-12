// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SP800SecureRandomBuilder.java

package co.org.bouncy.crypto.prng;

import co.org.bouncy.crypto.Mac;
import co.org.bouncy.crypto.prng.drbg.HMacSP800DRBG;
import co.org.bouncy.crypto.prng.drbg.SP80090DRBG;

// Referenced classes of package co.org.bouncy.crypto.prng:
//            DRBGProvider, SP800SecureRandomBuilder, EntropySource

private static class SP800SecureRandomBuilder$HMacDRBGProvider
    implements DRBGProvider
{

    public SP80090DRBG get(EntropySource entropySource)
    {
        return new HMacSP800DRBG(hMac, securityStrength, entropySource, personalizationString, nonce);
    }

    private final Mac hMac;
    private final byte nonce[];
    private final byte personalizationString[];
    private final int securityStrength;

    public SP800SecureRandomBuilder$HMacDRBGProvider(Mac hMac, byte nonce[], byte personalizationString[], int securityStrength)
    {
        this.hMac = hMac;
        this.nonce = nonce;
        this.personalizationString = personalizationString;
        this.securityStrength = securityStrength;
    }
}
