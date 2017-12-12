// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SRP6Util.java

package co.org.bouncy.crypto.agreement.srp;

import co.org.bouncy.crypto.CryptoException;
import co.org.bouncy.crypto.Digest;
import co.org.bouncy.util.BigIntegers;
import java.math.BigInteger;
import java.security.SecureRandom;

public class SRP6Util
{

    public SRP6Util()
    {
    }

    public static BigInteger calculateK(Digest digest, BigInteger N, BigInteger g)
    {
        return hashPaddedPair(digest, N, N, g);
    }

    public static BigInteger calculateU(Digest digest, BigInteger N, BigInteger A, BigInteger B)
    {
        return hashPaddedPair(digest, N, A, B);
    }

    public static BigInteger calculateX(Digest digest, BigInteger N, byte salt[], byte identity[], byte password[])
    {
        byte output[] = new byte[digest.getDigestSize()];
        digest.update(identity, 0, identity.length);
        digest.update((byte)58);
        digest.update(password, 0, password.length);
        digest.doFinal(output, 0);
        digest.update(salt, 0, salt.length);
        digest.update(output, 0, output.length);
        digest.doFinal(output, 0);
        return new BigInteger(1, output);
    }

    public static BigInteger generatePrivateValue(Digest digest, BigInteger N, BigInteger g, SecureRandom random)
    {
        int minBits = Math.min(256, N.bitLength() / 2);
        BigInteger min = ONE.shiftLeft(minBits - 1);
        BigInteger max = N.subtract(ONE);
        return BigIntegers.createRandomInRange(min, max, random);
    }

    public static BigInteger validatePublicValue(BigInteger N, BigInteger val)
        throws CryptoException
    {
        val = val.mod(N);
        if(val.equals(ZERO))
            throw new CryptoException("Invalid public value: 0");
        else
            return val;
    }

    private static BigInteger hashPaddedPair(Digest digest, BigInteger N, BigInteger n1, BigInteger n2)
    {
        int padLength = (N.bitLength() + 7) / 8;
        byte n1_bytes[] = getPadded(n1, padLength);
        byte n2_bytes[] = getPadded(n2, padLength);
        digest.update(n1_bytes, 0, n1_bytes.length);
        digest.update(n2_bytes, 0, n2_bytes.length);
        byte output[] = new byte[digest.getDigestSize()];
        digest.doFinal(output, 0);
        return new BigInteger(1, output);
    }

    private static byte[] getPadded(BigInteger n, int length)
    {
        byte bs[] = BigIntegers.asUnsignedByteArray(n);
        if(bs.length < length)
        {
            byte tmp[] = new byte[length];
            System.arraycopy(bs, 0, tmp, length - bs.length, bs.length);
            bs = tmp;
        }
        return bs;
    }

    private static BigInteger ZERO = BigInteger.valueOf(0L);
    private static BigInteger ONE = BigInteger.valueOf(1L);

}
