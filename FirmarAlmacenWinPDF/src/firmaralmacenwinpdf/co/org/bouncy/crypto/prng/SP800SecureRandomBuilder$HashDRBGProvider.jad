// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SP800SecureRandomBuilder.java

package co.org.bouncy.crypto.prng;

import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.prng.drbg.HashSP800DRBG;
import co.org.bouncy.crypto.prng.drbg.SP80090DRBG;

// Referenced classes of package co.org.bouncy.crypto.prng:
//            DRBGProvider, SP800SecureRandomBuilder, EntropySource

private static class SP800SecureRandomBuilder$HashDRBGProvider
    implements DRBGProvider
{

    public SP80090DRBG get(EntropySource entropySource)
    {
        return new HashSP800DRBG(digest, securityStrength, entropySource, personalizationString, nonce);
    }

    private final Digest digest;
    private final byte nonce[];
    private final byte personalizationString[];
    private final int securityStrength;

    public SP800SecureRandomBuilder$HashDRBGProvider(Digest digest, byte nonce[], byte personalizationString[], int securityStrength)
    {
        this.digest = digest;
        this.nonce = nonce;
        this.personalizationString = personalizationString;
        this.securityStrength = securityStrength;
    }
}
