// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HKDFBytesGenerator.java

package co.org.bouncy.crypto.generators;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.macs.HMac;
import co.org.bouncy.crypto.params.HKDFParameters;
import co.org.bouncy.crypto.params.KeyParameter;

public class HKDFBytesGenerator
    implements DerivationFunction
{

    public HKDFBytesGenerator(Digest hash)
    {
        hMacHash = new HMac(hash);
        hashLen = hash.getDigestSize();
    }

    public void init(DerivationParameters param)
    {
        if(!(param instanceof HKDFParameters))
            throw new IllegalArgumentException("HKDF parameters required for HKDFBytesGenerator");
        HKDFParameters params = (HKDFParameters)param;
        if(params.skipExtract())
            hMacHash.init(new KeyParameter(params.getIKM()));
        else
            hMacHash.init(extract(params.getSalt(), params.getIKM()));
        info = params.getInfo();
        generatedBytes = 0;
        currentT = new byte[hashLen];
    }

    private KeyParameter extract(byte salt[], byte ikm[])
    {
        hMacHash.init(new KeyParameter(ikm));
        if(salt == null)
            hMacHash.init(new KeyParameter(new byte[hashLen]));
        else
            hMacHash.init(new KeyParameter(salt));
        hMacHash.update(ikm, 0, ikm.length);
        byte prk[] = new byte[hashLen];
        hMacHash.doFinal(prk, 0);
        return new KeyParameter(prk);
    }

    private void expandNext()
        throws DataLengthException
    {
        int n = generatedBytes / hashLen + 1;
        if(n >= 256)
            throw new DataLengthException("HKDF cannot generate more than 255 blocks of HashLen size");
        if(generatedBytes != 0)
            hMacHash.update(currentT, 0, hashLen);
        hMacHash.update(info, 0, info.length);
        hMacHash.update((byte)n);
        hMacHash.doFinal(currentT, 0);
    }

    public Digest getDigest()
    {
        return hMacHash.getUnderlyingDigest();
    }

    public int generateBytes(byte out[], int outOff, int len)
        throws DataLengthException, IllegalArgumentException
    {
        if(generatedBytes + len > 255 * hashLen)
            throw new DataLengthException("HKDF may only be used for 255 * HashLen bytes of output");
        if(generatedBytes % hashLen == 0)
            expandNext();
        int toGenerate = len;
        int posInT = generatedBytes % hashLen;
        int leftInT = hashLen - generatedBytes % hashLen;
        int toCopy = Math.min(leftInT, toGenerate);
        System.arraycopy(currentT, posInT, out, outOff, toCopy);
        generatedBytes += toCopy;
        toGenerate -= toCopy;
        for(outOff += toCopy; toGenerate > 0; outOff += toCopy)
        {
            expandNext();
            toCopy = Math.min(hashLen, toGenerate);
            System.arraycopy(currentT, 0, out, outOff, toCopy);
            generatedBytes += toCopy;
            toGenerate -= toCopy;
        }

        return len;
    }

    private HMac hMacHash;
    private int hashLen;
    private byte info[];
    private byte currentT[];
    private int generatedBytes;
}
