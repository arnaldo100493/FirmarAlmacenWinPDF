// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RSAKeyEncapsulation.java

package co.org.bouncy.crypto.kems;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.util.BigIntegers;
import java.math.BigInteger;
import java.security.SecureRandom;

public class RSAKeyEncapsulation
    implements KeyEncapsulation
{

    public RSAKeyEncapsulation(DerivationFunction kdf, SecureRandom rnd)
    {
        this.kdf = kdf;
        this.rnd = rnd;
    }

    public void init(CipherParameters key)
        throws IllegalArgumentException
    {
        if(!(key instanceof RSAKeyParameters))
        {
            throw new IllegalArgumentException("RSA key required");
        } else
        {
            this.key = (RSAKeyParameters)key;
            return;
        }
    }

    public CipherParameters encrypt(byte out[], int outOff, int keyLen)
        throws IllegalArgumentException
    {
        if(key.isPrivate())
        {
            throw new IllegalArgumentException("Public key required for encryption");
        } else
        {
            BigInteger n = key.getModulus();
            BigInteger e = key.getExponent();
            BigInteger r = BigIntegers.createRandomInRange(ZERO, n.subtract(ONE), rnd);
            byte R[] = BigIntegers.asUnsignedByteArray((n.bitLength() + 7) / 8, r);
            BigInteger c = r.modPow(e, n);
            byte C[] = BigIntegers.asUnsignedByteArray((n.bitLength() + 7) / 8, c);
            System.arraycopy(C, 0, out, outOff, C.length);
            kdf.init(new KDFParameters(R, null));
            byte K[] = new byte[keyLen];
            kdf.generateBytes(K, 0, K.length);
            return new KeyParameter(K);
        }
    }

    public CipherParameters encrypt(byte out[], int keyLen)
    {
        return encrypt(out, 0, keyLen);
    }

    public CipherParameters decrypt(byte in[], int inOff, int inLen, int keyLen)
        throws IllegalArgumentException
    {
        if(!key.isPrivate())
        {
            throw new IllegalArgumentException("Private key required for decryption");
        } else
        {
            BigInteger n = key.getModulus();
            BigInteger d = key.getExponent();
            byte C[] = new byte[inLen];
            System.arraycopy(in, inOff, C, 0, C.length);
            BigInteger c = new BigInteger(1, C);
            BigInteger r = c.modPow(d, n);
            byte R[] = BigIntegers.asUnsignedByteArray((n.bitLength() + 7) / 8, r);
            kdf.init(new KDFParameters(R, null));
            byte K[] = new byte[keyLen];
            kdf.generateBytes(K, 0, K.length);
            return new KeyParameter(K);
        }
    }

    public CipherParameters decrypt(byte in[], int keyLen)
    {
        return decrypt(in, 0, in.length, keyLen);
    }

    private static final BigInteger ZERO = BigInteger.valueOf(0L);
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private DerivationFunction kdf;
    private SecureRandom rnd;
    private RSAKeyParameters key;

}
