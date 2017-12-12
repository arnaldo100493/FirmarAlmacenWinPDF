// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Permutation.java

package co.org.bouncy.pqc.math.linearalgebra;

import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.pqc.math.linearalgebra:
//            IntUtils, LittleEndianConversions, IntegerFunctions, RandUtils

public class Permutation
{

    public Permutation(int n)
    {
        if(n <= 0)
            throw new IllegalArgumentException("invalid length");
        perm = new int[n];
        for(int i = n - 1; i >= 0; i--)
            perm[i] = i;

    }

    public Permutation(int perm[])
    {
        if(!isPermutation(perm))
        {
            throw new IllegalArgumentException("array is not a permutation vector");
        } else
        {
            this.perm = IntUtils.clone(perm);
            return;
        }
    }

    public Permutation(byte enc[])
    {
        if(enc.length <= 4)
            throw new IllegalArgumentException("invalid encoding");
        int n = LittleEndianConversions.OS2IP(enc, 0);
        int size = IntegerFunctions.ceilLog256(n - 1);
        if(enc.length != 4 + n * size)
            throw new IllegalArgumentException("invalid encoding");
        perm = new int[n];
        for(int i = 0; i < n; i++)
            perm[i] = LittleEndianConversions.OS2IP(enc, 4 + i * size, size);

        if(!isPermutation(perm))
            throw new IllegalArgumentException("invalid encoding");
        else
            return;
    }

    public Permutation(int n, SecureRandom sr)
    {
        if(n <= 0)
            throw new IllegalArgumentException("invalid length");
        perm = new int[n];
        int help[] = new int[n];
        for(int i = 0; i < n; i++)
            help[i] = i;

        int k = n;
        for(int j = 0; j < n; j++)
        {
            int i = RandUtils.nextInt(sr, k);
            k--;
            perm[j] = help[i];
            help[i] = help[k];
        }

    }

    public byte[] getEncoded()
    {
        int n = perm.length;
        int size = IntegerFunctions.ceilLog256(n - 1);
        byte result[] = new byte[4 + n * size];
        LittleEndianConversions.I2OSP(n, result, 0);
        for(int i = 0; i < n; i++)
            LittleEndianConversions.I2OSP(perm[i], result, 4 + i * size, size);

        return result;
    }

    public int[] getVector()
    {
        return IntUtils.clone(perm);
    }

    public Permutation computeInverse()
    {
        Permutation result = new Permutation(perm.length);
        for(int i = perm.length - 1; i >= 0; i--)
            result.perm[perm[i]] = i;

        return result;
    }

    public Permutation rightMultiply(Permutation p)
    {
        if(p.perm.length != perm.length)
            throw new IllegalArgumentException("length mismatch");
        Permutation result = new Permutation(perm.length);
        for(int i = perm.length - 1; i >= 0; i--)
            result.perm[i] = perm[p.perm[i]];

        return result;
    }

    public boolean equals(Object other)
    {
        if(!(other instanceof Permutation))
        {
            return false;
        } else
        {
            Permutation otherPerm = (Permutation)other;
            return IntUtils.equals(perm, otherPerm.perm);
        }
    }

    public String toString()
    {
        String result = (new StringBuilder()).append("[").append(perm[0]).toString();
        for(int i = 1; i < perm.length; i++)
            result = (new StringBuilder()).append(result).append(", ").append(perm[i]).toString();

        result = (new StringBuilder()).append(result).append("]").toString();
        return result;
    }

    public int hashCode()
    {
        return perm.hashCode();
    }

    private boolean isPermutation(int perm[])
    {
        int n = perm.length;
        boolean onlyOnce[] = new boolean[n];
        for(int i = 0; i < n; i++)
        {
            if(perm[i] < 0 || perm[i] >= n || onlyOnce[perm[i]])
                return false;
            onlyOnce[perm[i]] = true;
        }

        return true;
    }

    private int perm[];
}
