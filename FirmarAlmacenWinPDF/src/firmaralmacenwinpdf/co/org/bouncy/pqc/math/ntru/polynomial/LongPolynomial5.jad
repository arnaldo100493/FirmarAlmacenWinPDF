// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LongPolynomial5.java

package co.org.bouncy.pqc.math.ntru.polynomial;

import co.org.bouncy.util.Arrays;

// Referenced classes of package co.org.bouncy.pqc.math.ntru.polynomial:
//            IntegerPolynomial, TernaryPolynomial

public class LongPolynomial5
{

    public LongPolynomial5(IntegerPolynomial p)
    {
        numCoeffs = p.coeffs.length;
        coeffs = new long[(numCoeffs + 4) / 5];
        int cIdx = 0;
        int shift = 0;
        for(int i = 0; i < numCoeffs; i++)
        {
            coeffs[cIdx] |= (long)p.coeffs[i] << shift;
            if((shift += 12) >= 60)
            {
                shift = 0;
                cIdx++;
            }
        }

    }

    private LongPolynomial5(long coeffs[], int numCoeffs)
    {
        this.coeffs = coeffs;
        this.numCoeffs = numCoeffs;
    }

    public LongPolynomial5 mult(TernaryPolynomial poly2)
    {
        long prod[][] = new long[5][(coeffs.length + (poly2.size() + 4) / 5) - 1];
        int ones[] = poly2.getOnes();
        for(int idx = 0; idx != ones.length; idx++)
        {
            int pIdx = ones[idx];
            int cIdx = pIdx / 5;
            int m = pIdx - cIdx * 5;
            for(int i = 0; i < coeffs.length; i++)
            {
                prod[m][cIdx] = prod[m][cIdx] + coeffs[i] & 0x7ff7ff7ff7ff7ffL;
                cIdx++;
            }

        }

        int negOnes[] = poly2.getNegOnes();
        for(int idx = 0; idx != negOnes.length; idx++)
        {
            int pIdx = negOnes[idx];
            int cIdx = pIdx / 5;
            int m = pIdx - cIdx * 5;
            for(int i = 0; i < coeffs.length; i++)
            {
                prod[m][cIdx] = (0x800800800800800L + prod[m][cIdx]) - coeffs[i] & 0x7ff7ff7ff7ff7ffL;
                cIdx++;
            }

        }

        long cCoeffs[] = Arrays.copyOf(prod[0], prod[0].length + 1);
        for(int m = 1; m <= 4; m++)
        {
            int shift = m * 12;
            int shift60 = 60 - shift;
            long mask = (1L << shift60) - 1L;
            int pLen = prod[m].length;
            for(int i = 0; i < pLen; i++)
            {
                long upper = prod[m][i] >> shift60;
                long lower = prod[m][i] & mask;
                cCoeffs[i] = cCoeffs[i] + (lower << shift) & 0x7ff7ff7ff7ff7ffL;
                int nextIdx = i + 1;
                cCoeffs[nextIdx] = cCoeffs[nextIdx] + upper & 0x7ff7ff7ff7ff7ffL;
            }

        }

        int shift = 12 * (numCoeffs % 5);
        for(int cIdx = coeffs.length - 1; cIdx < cCoeffs.length; cIdx++)
        {
            long iCoeff;
            int newIdx;
            if(cIdx == coeffs.length - 1)
            {
                iCoeff = numCoeffs != 5 ? cCoeffs[cIdx] >> shift : 0L;
                newIdx = 0;
            } else
            {
                iCoeff = cCoeffs[cIdx];
                newIdx = cIdx * 5 - numCoeffs;
            }
            int base = newIdx / 5;
            int m = newIdx - base * 5;
            long lower = iCoeff << 12 * m;
            long upper = iCoeff >> 12 * (5 - m);
            cCoeffs[base] = cCoeffs[base] + lower & 0x7ff7ff7ff7ff7ffL;
            int base1 = base + 1;
            if(base1 < coeffs.length)
                cCoeffs[base1] = cCoeffs[base1] + upper & 0x7ff7ff7ff7ff7ffL;
        }

        return new LongPolynomial5(cCoeffs, numCoeffs);
    }

    public IntegerPolynomial toIntegerPolynomial()
    {
        int intCoeffs[] = new int[numCoeffs];
        int cIdx = 0;
        int shift = 0;
        for(int i = 0; i < numCoeffs; i++)
        {
            intCoeffs[i] = (int)(coeffs[cIdx] >> shift & 2047L);
            if((shift += 12) >= 60)
            {
                shift = 0;
                cIdx++;
            }
        }

        return new IntegerPolynomial(intCoeffs);
    }

    private long coeffs[];
    private int numCoeffs;
}
