// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECIESKeyEncapsulation.java

package co.org.bouncy.crypto.kems;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.math.ec.*;
import co.org.bouncy.util.BigIntegers;
import java.math.BigInteger;
import java.security.SecureRandom;

public class ECIESKeyEncapsulation
    implements KeyEncapsulation
{

    public ECIESKeyEncapsulation(DerivationFunction kdf, SecureRandom rnd)
    {
        this.kdf = kdf;
        this.rnd = rnd;
        CofactorMode = false;
        OldCofactorMode = false;
        SingleHashMode = false;
    }

    public ECIESKeyEncapsulation(DerivationFunction kdf, SecureRandom rnd, boolean cofactorMode, boolean oldCofactorMode, boolean singleHashMode)
    {
        this.kdf = kdf;
        this.rnd = rnd;
        CofactorMode = cofactorMode;
        OldCofactorMode = oldCofactorMode;
        SingleHashMode = singleHashMode;
    }

    public void init(CipherParameters key)
        throws IllegalArgumentException
    {
        if(!(key instanceof ECKeyParameters))
        {
            throw new IllegalArgumentException("EC key required");
        } else
        {
            this.key = (ECKeyParameters)key;
            return;
        }
    }

    public CipherParameters encrypt(byte out[], int outOff, int keyLen)
        throws IllegalArgumentException
    {
        if(!(key instanceof ECPublicKeyParameters))
            throw new IllegalArgumentException("Public key required for encryption");
        BigInteger n = key.getParameters().getN();
        BigInteger h = key.getParameters().getH();
        BigInteger r = BigIntegers.createRandomInRange(ONE, n, rnd);
        ECPoint gTilde = key.getParameters().getG().multiply(r);
        byte C[] = gTilde.getEncoded();
        System.arraycopy(C, 0, out, outOff, C.length);
        BigInteger rPrime;
        if(CofactorMode)
            rPrime = r.multiply(h).mod(n);
        else
            rPrime = r;
        ECPoint hTilde = ((ECPublicKeyParameters)key).getQ().multiply(rPrime);
        int PEHlen = (key.getParameters().getCurve().getFieldSize() + 7) / 8;
        byte PEH[] = BigIntegers.asUnsignedByteArray(PEHlen, hTilde.getX().toBigInteger());
        byte kdfInput[];
        if(SingleHashMode)
        {
            kdfInput = new byte[C.length + PEH.length];
            System.arraycopy(C, 0, kdfInput, 0, C.length);
            System.arraycopy(PEH, 0, kdfInput, C.length, PEH.length);
        } else
        {
            kdfInput = PEH;
        }
        kdf.init(new KDFParameters(kdfInput, null));
        byte K[] = new byte[keyLen];
        kdf.generateBytes(K, 0, K.length);
        return new KeyParameter(K);
    }

    public CipherParameters encrypt(byte out[], int keyLen)
    {
        return encrypt(out, 0, keyLen);
    }

    public CipherParameters decrypt(byte in[], int inOff, int inLen, int keyLen)
        throws IllegalArgumentException
    {
        if(!(key instanceof ECPrivateKeyParameters))
            throw new IllegalArgumentException("Private key required for encryption");
        BigInteger n = key.getParameters().getN();
        BigInteger h = key.getParameters().getH();
        byte C[] = new byte[inLen];
        System.arraycopy(in, inOff, C, 0, inLen);
        ECPoint gTilde = key.getParameters().getCurve().decodePoint(C);
        ECPoint gHat;
        if(CofactorMode || OldCofactorMode)
            gHat = gTilde.multiply(h);
        else
            gHat = gTilde;
        BigInteger xHat;
        if(CofactorMode)
            xHat = ((ECPrivateKeyParameters)key).getD().multiply(h.modInverse(n)).mod(n);
        else
            xHat = ((ECPrivateKeyParameters)key).getD();
        ECPoint hTilde = gHat.multiply(xHat);
        int PEHlen = (key.getParameters().getCurve().getFieldSize() + 7) / 8;
        byte PEH[] = BigIntegers.asUnsignedByteArray(PEHlen, hTilde.getX().toBigInteger());
        byte kdfInput[];
        if(SingleHashMode)
        {
            kdfInput = new byte[C.length + PEH.length];
            System.arraycopy(C, 0, kdfInput, 0, C.length);
            System.arraycopy(PEH, 0, kdfInput, C.length, PEH.length);
        } else
        {
            kdfInput = PEH;
        }
        kdf.init(new KDFParameters(kdfInput, null));
        byte K[] = new byte[keyLen];
        kdf.generateBytes(K, 0, K.length);
        return new KeyParameter(K);
    }

    public CipherParameters decrypt(byte in[], int keyLen)
    {
        return decrypt(in, 0, in.length, keyLen);
    }

    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private DerivationFunction kdf;
    private SecureRandom rnd;
    private ECKeyParameters key;
    private boolean CofactorMode;
    private boolean OldCofactorMode;
    private boolean SingleHashMode;

}
