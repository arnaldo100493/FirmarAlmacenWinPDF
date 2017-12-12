// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HashSP800DRBG.java

package co.org.bouncy.crypto.prng.drbg;

import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.prng.EntropySource;
import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.Integers;
import java.util.Hashtable;

// Referenced classes of package co.org.bouncy.crypto.prng.drbg:
//            SP80090DRBG, Utils

public class HashSP800DRBG
    implements SP80090DRBG
{

    public HashSP800DRBG(Digest digest, int securityStrength, EntropySource entropySource, byte personalizationString[], byte nonce[])
    {
        if(securityStrength > Utils.getMaxSecurityStrength(digest))
            throw new IllegalArgumentException("Requested security strength is not supported by the derivation function");
        if(entropySource.entropySize() < securityStrength)
        {
            throw new IllegalArgumentException("Not enough entropy for security strength required");
        } else
        {
            _digest = digest;
            _entropySource = entropySource;
            _securityStrength = securityStrength;
            _seedLength = ((Integer)seedlens.get(digest.getAlgorithmName())).intValue();
            byte entropy[] = entropySource.getEntropy();
            byte seedMaterial[] = Arrays.concatenate(entropy, nonce, personalizationString);
            byte seed[] = Utils.hash_df(_digest, seedMaterial, _seedLength);
            _V = seed;
            byte subV[] = new byte[_V.length + 1];
            System.arraycopy(_V, 0, subV, 1, _V.length);
            _C = Utils.hash_df(_digest, subV, _seedLength);
            _reseedCounter = 1L;
            return;
        }
    }

    public int generate(byte output[], byte additionalInput[], boolean predictionResistant)
    {
        int numberOfBits = output.length * 8;
        if(numberOfBits > 0x40000)
            throw new IllegalArgumentException("Number of bits per request limited to 262144");
        if(_reseedCounter > 0x800000000000L)
            return -1;
        if(predictionResistant)
        {
            reseed(additionalInput);
            additionalInput = null;
        }
        if(additionalInput != null)
        {
            byte newInput[] = new byte[1 + _V.length + additionalInput.length];
            newInput[0] = 2;
            System.arraycopy(_V, 0, newInput, 1, _V.length);
            System.arraycopy(additionalInput, 0, newInput, 1 + _V.length, additionalInput.length);
            byte w[] = hash(newInput);
            addTo(_V, w);
        }
        byte rv[] = hashgen(_V, numberOfBits);
        byte subH[] = new byte[_V.length + 1];
        System.arraycopy(_V, 0, subH, 1, _V.length);
        subH[0] = 3;
        byte H[] = hash(subH);
        addTo(_V, H);
        addTo(_V, _C);
        byte c[] = new byte[4];
        c[0] = (byte)(int)(_reseedCounter >> 24);
        c[1] = (byte)(int)(_reseedCounter >> 16);
        c[2] = (byte)(int)(_reseedCounter >> 8);
        c[3] = (byte)(int)_reseedCounter;
        addTo(_V, c);
        _reseedCounter++;
        System.arraycopy(rv, 0, output, 0, output.length);
        return numberOfBits;
    }

    private void addTo(byte longer[], byte shorter[])
    {
        int carry = 0;
        for(int i = 1; i <= shorter.length; i++)
        {
            int res = (longer[longer.length - i] & 0xff) + (shorter[shorter.length - i] & 0xff) + carry;
            carry = res <= 255 ? 0 : 1;
            longer[longer.length - i] = (byte)res;
        }

        for(int i = shorter.length + 1; i <= longer.length; i++)
        {
            int res = (longer[longer.length - i] & 0xff) + carry;
            carry = res <= 255 ? 0 : 1;
            longer[longer.length - i] = (byte)res;
        }

    }

    public void reseed(byte additionalInput[])
    {
        byte entropy[] = _entropySource.getEntropy();
        byte seedMaterial[] = Arrays.concatenate(ONE, _V, entropy, additionalInput);
        byte seed[] = Utils.hash_df(_digest, seedMaterial, _seedLength);
        _V = seed;
        byte subV[] = new byte[_V.length + 1];
        subV[0] = 0;
        System.arraycopy(_V, 0, subV, 1, _V.length);
        _C = Utils.hash_df(_digest, subV, _seedLength);
        _reseedCounter = 1L;
    }

    private byte[] hash(byte input[])
    {
        _digest.update(input, 0, input.length);
        byte hash[] = new byte[_digest.getDigestSize()];
        _digest.doFinal(hash, 0);
        return hash;
    }

    private byte[] hashgen(byte input[], int lengthInBits)
    {
        int digestSize = _digest.getDigestSize();
        int m = lengthInBits / 8 / digestSize;
        byte data[] = new byte[input.length];
        System.arraycopy(input, 0, data, 0, input.length);
        byte W[] = new byte[lengthInBits / 8];
        for(int i = 0; i <= m; i++)
        {
            byte dig[] = hash(data);
            int bytesToCopy = W.length - i * dig.length <= dig.length ? W.length - i * dig.length : dig.length;
            System.arraycopy(dig, 0, W, i * dig.length, bytesToCopy);
            addTo(data, ONE);
        }

        return W;
    }

    private static final byte ONE[] = {
        1
    };
    private static final long RESEED_MAX = 0x800000000000L;
    private static final int MAX_BITS_REQUEST = 0x40000;
    private static final Hashtable seedlens;
    private Digest _digest;
    private byte _V[];
    private byte _C[];
    private long _reseedCounter;
    private EntropySource _entropySource;
    private int _securityStrength;
    private int _seedLength;

    static 
    {
        seedlens = new Hashtable();
        seedlens.put("SHA-1", Integers.valueOf(440));
        seedlens.put("SHA-224", Integers.valueOf(440));
        seedlens.put("SHA-256", Integers.valueOf(440));
        seedlens.put("SHA-512/256", Integers.valueOf(440));
        seedlens.put("SHA-512/224", Integers.valueOf(440));
        seedlens.put("SHA-384", Integers.valueOf(888));
        seedlens.put("SHA-512", Integers.valueOf(888));
    }
}
