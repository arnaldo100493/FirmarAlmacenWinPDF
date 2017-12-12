// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HMacSP800DRBG.java

package co.org.bouncy.crypto.prng.drbg;

import co.org.bouncy.crypto.Mac;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.crypto.prng.EntropySource;
import co.org.bouncy.util.Arrays;

// Referenced classes of package co.org.bouncy.crypto.prng.drbg:
//            SP80090DRBG, Utils

public class HMacSP800DRBG
    implements SP80090DRBG
{

    public HMacSP800DRBG(Mac hMac, int securityStrength, EntropySource entropySource, byte personalizationString[], byte nonce[])
    {
        if(securityStrength > Utils.getMaxSecurityStrength(hMac))
            throw new IllegalArgumentException("Requested security strength is not supported by the derivation function");
        if(entropySource.entropySize() < securityStrength)
        {
            throw new IllegalArgumentException("Not enough entropy for security strength required");
        } else
        {
            _entropySource = entropySource;
            _hMac = hMac;
            byte entropy[] = entropySource.getEntropy();
            byte seedMaterial[] = Arrays.concatenate(entropy, nonce, personalizationString);
            _K = new byte[hMac.getMacSize()];
            _V = new byte[_K.length];
            Arrays.fill(_V, (byte)1);
            hmac_DRBG_Update(seedMaterial);
            _reseedCounter = 1L;
            return;
        }
    }

    private void hmac_DRBG_Update(byte seedMaterial[])
    {
        hmac_DRBG_Update_Func(seedMaterial, (byte)0);
        if(seedMaterial != null)
            hmac_DRBG_Update_Func(seedMaterial, (byte)1);
    }

    private void hmac_DRBG_Update_Func(byte seedMaterial[], byte vValue)
    {
        _hMac.init(new KeyParameter(_K));
        _hMac.update(_V, 0, _V.length);
        _hMac.update(vValue);
        if(seedMaterial != null)
            _hMac.update(seedMaterial, 0, seedMaterial.length);
        _hMac.doFinal(_K, 0);
        _hMac.init(new KeyParameter(_K));
        _hMac.update(_V, 0, _V.length);
        _hMac.doFinal(_V, 0);
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
            hmac_DRBG_Update(additionalInput);
        byte rv[] = new byte[output.length];
        int m = output.length / _V.length;
        _hMac.init(new KeyParameter(_K));
        for(int i = 0; i < m; i++)
        {
            _hMac.update(_V, 0, _V.length);
            _hMac.doFinal(_V, 0);
            System.arraycopy(_V, 0, rv, i * _V.length, _V.length);
        }

        if(m * _V.length < rv.length)
        {
            _hMac.update(_V, 0, _V.length);
            _hMac.doFinal(_V, 0);
            System.arraycopy(_V, 0, rv, m * _V.length, rv.length - m * _V.length);
        }
        hmac_DRBG_Update(additionalInput);
        _reseedCounter++;
        System.arraycopy(rv, 0, output, 0, output.length);
        return numberOfBits;
    }

    public void reseed(byte additionalInput[])
    {
        byte entropy[] = _entropySource.getEntropy();
        byte seedMaterial[] = Arrays.concatenate(entropy, additionalInput);
        hmac_DRBG_Update(seedMaterial);
        _reseedCounter = 1L;
    }

    private static final long RESEED_MAX = 0x800000000000L;
    private static final int MAX_BITS_REQUEST = 0x40000;
    private byte _K[];
    private byte _V[];
    private long _reseedCounter;
    private EntropySource _entropySource;
    private Mac _hMac;
}
