// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSAParametersGenerator.java

package co.org.bouncy.crypto.generators;

import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.digests.SHA1Digest;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.BigIntegers;
import co.org.bouncy.util.encoders.Hex;
import java.math.BigInteger;
import java.security.SecureRandom;

public class DSAParametersGenerator
{

    public DSAParametersGenerator()
    {
        this(((Digest) (new SHA1Digest())));
    }

    public DSAParametersGenerator(Digest digest)
    {
        this.digest = digest;
    }

    public void init(int size, int certainty, SecureRandom random)
    {
        use186_3 = false;
        L = size;
        N = getDefaultN(size);
        this.certainty = certainty;
        this.random = random;
    }

    public void init(DSAParameterGenerationParameters params)
    {
        use186_3 = true;
        L = params.getL();
        N = params.getN();
        certainty = params.getCertainty();
        random = params.getRandom();
        usageIndex = params.getUsageIndex();
        if(L < 1024 || L > 3072 || L % 1024 != 0)
            throw new IllegalArgumentException("L values must be between 1024 and 3072 and a multiple of 1024");
        if(L == 1024 && N != 160)
            throw new IllegalArgumentException("N must be 160 for L = 1024");
        if(L == 2048 && N != 224 && N != 256)
            throw new IllegalArgumentException("N must be 224 or 256 for L = 2048");
        if(L == 3072 && N != 256)
            throw new IllegalArgumentException("N must be 256 for L = 3072");
        if(digest.getDigestSize() * 8 < N)
            throw new IllegalStateException("Digest output size too small for value of N");
        else
            return;
    }

    public DSAParameters generateParameters()
    {
        return use186_3 ? generateParameters_FIPS186_3() : generateParameters_FIPS186_2();
    }

    private DSAParameters generateParameters_FIPS186_2()
    {
        byte seed[] = new byte[20];
        byte part1[] = new byte[20];
        byte part2[] = new byte[20];
        byte u[] = new byte[20];
        int n = (L - 1) / 160;
        byte w[] = new byte[L / 8];
        if(!(digest instanceof SHA1Digest))
            throw new IllegalStateException("can only use SHA-1 for generating FIPS 186-2 parameters");
        do
        {
            BigInteger q;
            do
            {
                random.nextBytes(seed);
                hash(digest, seed, part1);
                System.arraycopy(seed, 0, part2, 0, seed.length);
                inc(part2);
                hash(digest, part2, part2);
                for(int i = 0; i != u.length; i++)
                    u[i] = (byte)(part1[i] ^ part2[i]);

                u[0] |= 0x80;
                u[19] |= 1;
                q = new BigInteger(1, u);
            } while(!q.isProbablePrime(certainty));
            byte offset[] = Arrays.clone(seed);
            inc(offset);
            int counter = 0;
            while(counter < 4096) 
            {
                for(int k = 0; k < n; k++)
                {
                    inc(offset);
                    hash(digest, offset, part1);
                    System.arraycopy(part1, 0, w, w.length - (k + 1) * part1.length, part1.length);
                }

                inc(offset);
                hash(digest, offset, part1);
                System.arraycopy(part1, part1.length - (w.length - n * part1.length), w, 0, w.length - n * part1.length);
                w[0] |= 0x80;
                BigInteger x = new BigInteger(1, w);
                BigInteger c = x.mod(q.shiftLeft(1));
                BigInteger p = x.subtract(c.subtract(ONE));
                if(p.bitLength() == L && p.isProbablePrime(certainty))
                {
                    BigInteger g = calculateGenerator_FIPS186_2(p, q, random);
                    return new DSAParameters(p, q, g, new DSAValidationParameters(seed, counter));
                }
                counter++;
            }
        } while(true);
    }

    private static BigInteger calculateGenerator_FIPS186_2(BigInteger p, BigInteger q, SecureRandom r)
    {
        BigInteger e = p.subtract(ONE).divide(q);
        BigInteger pSub2 = p.subtract(TWO);
        BigInteger g;
        do
        {
            BigInteger h = BigIntegers.createRandomInRange(TWO, pSub2, r);
            g = h.modPow(e, p);
        } while(g.bitLength() <= 1);
        return g;
    }

    private DSAParameters generateParameters_FIPS186_3()
    {
        Digest d = digest;
        int outlen = d.getDigestSize() * 8;
        int seedlen = N;
        byte seed[] = new byte[seedlen / 8];
        int n = (L - 1) / outlen;
        int b = (L - 1) % outlen;
        byte output[] = new byte[d.getDigestSize()];
        do
        {
            BigInteger q;
            do
            {
                random.nextBytes(seed);
                hash(d, seed, output);
                BigInteger U = (new BigInteger(1, output)).mod(ONE.shiftLeft(N - 1));
                q = ONE.shiftLeft(N - 1).add(U).add(ONE).subtract(U.mod(TWO));
            } while(!q.isProbablePrime(certainty));
            byte offset[] = Arrays.clone(seed);
            int counterLimit = 4 * L;
            int counter = 0;
            while(counter < counterLimit) 
            {
                BigInteger W = ZERO;
                int j = 0;
                for(int exp = 0; j <= n; exp += outlen)
                {
                    inc(offset);
                    hash(d, offset, output);
                    BigInteger Vj = new BigInteger(1, output);
                    if(j == n)
                        Vj = Vj.mod(ONE.shiftLeft(b));
                    W = W.add(Vj.shiftLeft(exp));
                    j++;
                }

                BigInteger X = W.add(ONE.shiftLeft(L - 1));
                BigInteger c = X.mod(q.shiftLeft(1));
                BigInteger p = X.subtract(c.subtract(ONE));
                if(p.bitLength() == L && p.isProbablePrime(certainty))
                {
                    BigInteger g;
                    if(usageIndex >= 0)
                    {
                        g = calculateGenerator_FIPS186_3_Verifiable(d, p, q, seed, usageIndex);
                        if(g != null)
                            return new DSAParameters(p, q, g, new DSAValidationParameters(seed, counter, usageIndex));
                    }
                    g = calculateGenerator_FIPS186_3_Unverifiable(p, q, random);
                    return new DSAParameters(p, q, g, new DSAValidationParameters(seed, counter));
                }
                counter++;
            }
        } while(true);
    }

    private static BigInteger calculateGenerator_FIPS186_3_Unverifiable(BigInteger p, BigInteger q, SecureRandom r)
    {
        return calculateGenerator_FIPS186_2(p, q, r);
    }

    private static BigInteger calculateGenerator_FIPS186_3_Verifiable(Digest d, BigInteger p, BigInteger q, byte seed[], int index)
    {
        BigInteger e = p.subtract(ONE).divide(q);
        byte ggen[] = Hex.decode("6767656E");
        byte U[] = new byte[seed.length + ggen.length + 1 + 2];
        System.arraycopy(seed, 0, U, 0, seed.length);
        System.arraycopy(ggen, 0, U, seed.length, ggen.length);
        U[U.length - 3] = (byte)index;
        byte w[] = new byte[d.getDigestSize()];
        for(int count = 1; count < 0x10000; count++)
        {
            inc(U);
            hash(d, U, w);
            BigInteger W = new BigInteger(1, w);
            BigInteger g = W.modPow(e, p);
            if(g.compareTo(TWO) >= 0)
                return g;
        }

        return null;
    }

    private static void hash(Digest d, byte input[], byte output[])
    {
        d.update(input, 0, input.length);
        d.doFinal(output, 0);
    }

    private static int getDefaultN(int L)
    {
        return L <= 1024 ? '\240' : 256;
    }

    private static void inc(byte buf[])
    {
        int i = buf.length - 1;
        do
        {
            if(i < 0)
                break;
            byte b = (byte)(buf[i] + 1 & 0xff);
            buf[i] = b;
            if(b != 0)
                break;
            i--;
        } while(true);
    }

    private Digest digest;
    private int L;
    private int N;
    private int certainty;
    private SecureRandom random;
    private static final BigInteger ZERO = BigInteger.valueOf(0L);
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private static final BigInteger TWO = BigInteger.valueOf(2L);
    private boolean use186_3;
    private int usageIndex;

}
