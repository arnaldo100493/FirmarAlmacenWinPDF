// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Util.java

package co.org.bouncy.pqc.math.ntru.util;

import co.org.bouncy.pqc.math.ntru.euclid.IntEuclidean;
import co.org.bouncy.pqc.math.ntru.polynomial.*;
import co.org.bouncy.util.Integers;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.*;

public class Util
{

    public Util()
    {
    }

    public static int invert(int n, int modulus)
    {
        n %= modulus;
        if(n < 0)
            n += modulus;
        return IntEuclidean.calculate(n, modulus).x;
    }

    public static int pow(int a, int b, int modulus)
    {
        int p = 1;
        for(int i = 0; i < b; i++)
            p = (p * a) % modulus;

        return p;
    }

    public static long pow(long a, int b, long modulus)
    {
        long p = 1L;
        for(int i = 0; i < b; i++)
            p = (p * a) % modulus;

        return p;
    }

    public static TernaryPolynomial generateRandomTernary(int N, int numOnes, int numNegOnes, boolean sparse, SecureRandom random)
    {
        if(sparse)
            return SparseTernaryPolynomial.generateRandom(N, numOnes, numNegOnes, random);
        else
            return DenseTernaryPolynomial.generateRandom(N, numOnes, numNegOnes, random);
    }

    public static int[] generateRandomTernary(int N, int numOnes, int numNegOnes, SecureRandom random)
    {
        Integer one = Integers.valueOf(1);
        Integer minusOne = Integers.valueOf(-1);
        Integer zero = Integers.valueOf(0);
        List list = new ArrayList();
        for(int i = 0; i < numOnes; i++)
            list.add(one);

        for(int i = 0; i < numNegOnes; i++)
            list.add(minusOne);

        for(; list.size() < N; list.add(zero));
        Collections.shuffle(list, random);
        int arr[] = new int[N];
        for(int i = 0; i < N; i++)
            arr[i] = ((Integer)list.get(i)).intValue();

        return arr;
    }

    public static boolean is64BitJVM()
    {
        if(!IS_64_BITNESS_KNOWN)
        {
            String arch = System.getProperty("os.arch");
            String sunModel = System.getProperty("sun.arch.data.model");
            IS_64_BIT_JVM = "amd64".equals(arch) || "x86_64".equals(arch) || "ppc64".equals(arch) || "64".equals(sunModel);
            IS_64_BITNESS_KNOWN = true;
        }
        return IS_64_BIT_JVM;
    }

    public static byte[] readFullLength(InputStream is, int length)
        throws IOException
    {
        byte arr[] = new byte[length];
        if(is.read(arr) != arr.length)
            throw new IOException("Not enough bytes to read.");
        else
            return arr;
    }

    private static volatile boolean IS_64_BITNESS_KNOWN;
    private static volatile boolean IS_64_BIT_JVM;
}
