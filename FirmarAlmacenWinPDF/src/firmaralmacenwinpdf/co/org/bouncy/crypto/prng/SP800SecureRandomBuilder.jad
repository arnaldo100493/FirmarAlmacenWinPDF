// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SP800SecureRandomBuilder.java

package co.org.bouncy.crypto.prng;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.prng.drbg.CTRSP800DRBG;
import co.org.bouncy.crypto.prng.drbg.DualECSP800DRBG;
import co.org.bouncy.crypto.prng.drbg.HMacSP800DRBG;
import co.org.bouncy.crypto.prng.drbg.HashSP800DRBG;
import co.org.bouncy.crypto.prng.drbg.SP80090DRBG;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.crypto.prng:
//            BasicEntropySourceProvider, SP800SecureRandom, EntropySourceProvider, DRBGProvider, 
//            EntropySource

public class SP800SecureRandomBuilder
{
    private static class CTRDRBGProvider
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

        public CTRDRBGProvider(BlockCipher blockCipher, int keySizeInBits, byte nonce[], byte personalizationString[], int securityStrength)
        {
            this.blockCipher = blockCipher;
            this.keySizeInBits = keySizeInBits;
            this.nonce = nonce;
            this.personalizationString = personalizationString;
            this.securityStrength = securityStrength;
        }
    }

    private static class HMacDRBGProvider
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

        public HMacDRBGProvider(Mac hMac, byte nonce[], byte personalizationString[], int securityStrength)
        {
            this.hMac = hMac;
            this.nonce = nonce;
            this.personalizationString = personalizationString;
            this.securityStrength = securityStrength;
        }
    }

    private static class DualECDRBGProvider
        implements DRBGProvider
    {

        public SP80090DRBG get(EntropySource entropySource)
        {
            return new DualECSP800DRBG(digest, securityStrength, entropySource, personalizationString, nonce);
        }

        private final Digest digest;
        private final byte nonce[];
        private final byte personalizationString[];
        private final int securityStrength;

        public DualECDRBGProvider(Digest digest, byte nonce[], byte personalizationString[], int securityStrength)
        {
            this.digest = digest;
            this.nonce = nonce;
            this.personalizationString = personalizationString;
            this.securityStrength = securityStrength;
        }
    }

    private static class HashDRBGProvider
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

        public HashDRBGProvider(Digest digest, byte nonce[], byte personalizationString[], int securityStrength)
        {
            this.digest = digest;
            this.nonce = nonce;
            this.personalizationString = personalizationString;
            this.securityStrength = securityStrength;
        }
    }


    public SP800SecureRandomBuilder()
    {
        this(new SecureRandom(), false);
    }

    public SP800SecureRandomBuilder(SecureRandom entropySource, boolean predictionResistant)
    {
        securityStrength = 256;
        entropyBitsRequired = 256;
        random = entropySource;
        entropySourceProvider = new BasicEntropySourceProvider(random, predictionResistant);
    }

    public SP800SecureRandomBuilder(EntropySourceProvider entropySourceProvider)
    {
        securityStrength = 256;
        entropyBitsRequired = 256;
        random = null;
        this.entropySourceProvider = entropySourceProvider;
    }

    public SP800SecureRandomBuilder setPersonalizationString(byte personalizationString[])
    {
        this.personalizationString = personalizationString;
        return this;
    }

    public SP800SecureRandomBuilder setSecurityStrength(int securityStrength)
    {
        this.securityStrength = securityStrength;
        return this;
    }

    public SP800SecureRandomBuilder setEntropyBitsRequired(int entropyBitsRequired)
    {
        this.entropyBitsRequired = entropyBitsRequired;
        return this;
    }

    public SP800SecureRandom buildHash(Digest digest, byte nonce[], boolean predictionResistant)
    {
        return new SP800SecureRandom(random, entropySourceProvider.get(entropyBitsRequired), new HashDRBGProvider(digest, nonce, personalizationString, securityStrength), predictionResistant);
    }

    public SP800SecureRandom buildCTR(BlockCipher cipher, int keySizeInBits, byte nonce[], boolean predictionResistant)
    {
        return new SP800SecureRandom(random, entropySourceProvider.get(entropyBitsRequired), new CTRDRBGProvider(cipher, keySizeInBits, nonce, personalizationString, securityStrength), predictionResistant);
    }

    public SP800SecureRandom buildHMAC(Mac hMac, byte nonce[], boolean predictionResistant)
    {
        return new SP800SecureRandom(random, entropySourceProvider.get(entropyBitsRequired), new HMacDRBGProvider(hMac, nonce, personalizationString, securityStrength), predictionResistant);
    }

    public SP800SecureRandom buildDualEC(Digest digest, byte nonce[], boolean predictionResistant)
    {
        return new SP800SecureRandom(random, entropySourceProvider.get(entropyBitsRequired), new DualECDRBGProvider(digest, nonce, personalizationString, securityStrength), predictionResistant);
    }

    private final SecureRandom random;
    private final EntropySourceProvider entropySourceProvider;
    private byte personalizationString[];
    private int securityStrength;
    private int entropyBitsRequired;
}
