// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BigIntPolynomial.java

package co.org.bouncy.pqc.math.ntru.polynomial;

import co.org.bouncy.util.Arrays;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;

// Referenced classes of package co.org.bouncy.pqc.math.ntru.polynomial:
//            BigDecimalPolynomial, IntegerPolynomial, Constants

public class BigIntPolynomial
{

    BigIntPolynomial(int N)
    {
        coeffs = new BigInteger[N];
        for(int i = 0; i < N; i++)
            coeffs[i] = Constants.BIGINT_ZERO;

    }

    BigIntPolynomial(BigInteger coeffs[])
    {
        this.coeffs = coeffs;
    }

    public BigIntPolynomial(IntegerPolynomial p)
    {
        coeffs = new BigInteger[p.coeffs.length];
        for(int i = 0; i < coeffs.length; i++)
            coeffs[i] = BigInteger.valueOf(p.coeffs[i]);

    }

    static BigIntPolynomial generateRandomSmall(int N, int numOnes, int numNegOnes)
    {
        List coeffs = new ArrayList();
        for(int i = 0; i < numOnes; i++)
            coeffs.add(Constants.BIGINT_ONE);

        for(int i = 0; i < numNegOnes; i++)
            coeffs.add(BigInteger.valueOf(-1L));

        for(; coeffs.size() < N; coeffs.add(Constants.BIGINT_ZERO));
        Collections.shuffle(coeffs, new SecureRandom());
        BigIntPolynomial poly = new BigIntPolynomial(N);
        for(int i = 0; i < coeffs.size(); i++)
            poly.coeffs[i] = (BigInteger)coeffs.get(i);

        return poly;
    }

    public BigIntPolynomial mult(BigIntPolynomial poly2)
    {
        int N = coeffs.length;
        if(poly2.coeffs.length != N)
            throw new IllegalArgumentException("Number of coefficients must be the same");
        BigIntPolynomial c = multRecursive(poly2);
        if(c.coeffs.length > N)
        {
            for(int k = N; k < c.coeffs.length; k++)
                c.coeffs[k - N] = c.coeffs[k - N].add(c.coeffs[k]);

            c.coeffs = Arrays.copyOf(c.coeffs, N);
        }
        return c;
    }

    private BigIntPolynomial multRecursive(BigIntPolynomial poly2)
    {
        BigInteger a[] = coeffs;
        BigInteger b[] = poly2.coeffs;
        int n = poly2.coeffs.length;
        if(n <= 1)
        {
            BigInteger c[] = Arrays.clone(coeffs);
            for(int i = 0; i < coeffs.length; i++)
                c[i] = c[i].multiply(poly2.coeffs[0]);

            return new BigIntPolynomial(c);
        }
        int n1 = n / 2;
        BigIntPolynomial a1 = new BigIntPolynomial(Arrays.copyOf(a, n1));
        BigIntPolynomial a2 = new BigIntPolynomial(Arrays.copyOfRange(a, n1, n));
        BigIntPolynomial b1 = new BigIntPolynomial(Arrays.copyOf(b, n1));
        BigIntPolynomial b2 = new BigIntPolynomial(Arrays.copyOfRange(b, n1, n));
        BigIntPolynomial A = (BigIntPolynomial)a1.clone();
        A.add(a2);
        BigIntPolynomial B = (BigIntPolynomial)b1.clone();
        B.add(b2);
        BigIntPolynomial c1 = a1.multRecursive(b1);
        BigIntPolynomial c2 = a2.multRecursive(b2);
        BigIntPolynomial c3 = A.multRecursive(B);
        c3.sub(c1);
        c3.sub(c2);
        BigIntPolynomial c = new BigIntPolynomial(2 * n - 1);
        for(int i = 0; i < c1.coeffs.length; i++)
            c.coeffs[i] = c1.coeffs[i];

        for(int i = 0; i < c3.coeffs.length; i++)
            c.coeffs[n1 + i] = c.coeffs[n1 + i].add(c3.coeffs[i]);

        for(int i = 0; i < c2.coeffs.length; i++)
            c.coeffs[2 * n1 + i] = c.coeffs[2 * n1 + i].add(c2.coeffs[i]);

        return c;
    }

    void add(BigIntPolynomial b, BigInteger modulus)
    {
        add(b);
        mod(modulus);
    }

    public void add(BigIntPolynomial b)
    {
        if(b.coeffs.length > coeffs.length)
        {
            int N = coeffs.length;
            coeffs = Arrays.copyOf(coeffs, b.coeffs.length);
            for(int i = N; i < coeffs.length; i++)
                coeffs[i] = Constants.BIGINT_ZERO;

        }
        for(int i = 0; i < b.coeffs.length; i++)
            coeffs[i] = coeffs[i].add(b.coeffs[i]);

    }

    public void sub(BigIntPolynomial b)
    {
        if(b.coeffs.length > coeffs.length)
        {
            int N = coeffs.length;
            coeffs = Arrays.copyOf(coeffs, b.coeffs.length);
            for(int i = N; i < coeffs.length; i++)
                coeffs[i] = Constants.BIGINT_ZERO;

        }
        for(int i = 0; i < b.coeffs.length; i++)
            coeffs[i] = coeffs[i].subtract(b.coeffs[i]);

    }

    public void mult(BigInteger factor)
    {
        for(int i = 0; i < coeffs.length; i++)
            coeffs[i] = coeffs[i].multiply(factor);

    }

    void mult(int factor)
    {
        mult(BigInteger.valueOf(factor));
    }

    public void div(BigInteger divisor)
    {
        BigInteger d = divisor.add(Constants.BIGINT_ONE).divide(BigInteger.valueOf(2L));
        for(int i = 0; i < coeffs.length; i++)
        {
            coeffs[i] = coeffs[i].compareTo(Constants.BIGINT_ZERO) <= 0 ? coeffs[i].add(d.negate()) : coeffs[i].add(d);
            coeffs[i] = coeffs[i].divide(divisor);
        }

    }

    public BigDecimalPolynomial div(BigDecimal divisor, int decimalPlaces)
    {
        BigInteger max = maxCoeffAbs();
        int coeffLength = (int)((double)max.bitLength() * LOG_10_2) + 1;
        BigDecimal factor = Constants.BIGDEC_ONE.divide(divisor, coeffLength + decimalPlaces + 1, 6);
        BigDecimalPolynomial p = new BigDecimalPolynomial(coeffs.length);
        for(int i = 0; i < coeffs.length; i++)
            p.coeffs[i] = (new BigDecimal(coeffs[i])).multiply(factor).setScale(decimalPlaces, 6);

        return p;
    }

    public int getMaxCoeffLength()
    {
        return (int)((double)maxCoeffAbs().bitLength() * LOG_10_2) + 1;
    }

    private BigInteger maxCoeffAbs()
    {
        BigInteger max = coeffs[0].abs();
        for(int i = 1; i < coeffs.length; i++)
        {
            BigInteger coeff = coeffs[i].abs();
            if(coeff.compareTo(max) > 0)
                max = coeff;
        }

        return max;
    }

    public void mod(BigInteger modulus)
    {
        for(int i = 0; i < coeffs.length; i++)
            coeffs[i] = coeffs[i].mod(modulus);

    }

    BigInteger sumCoeffs()
    {
        BigInteger sum = Constants.BIGINT_ZERO;
        for(int i = 0; i < coeffs.length; i++)
            sum = sum.add(coeffs[i]);

        return sum;
    }

    public Object clone()
    {
        return new BigIntPolynomial((BigInteger[])coeffs.clone());
    }

    public int hashCode()
    {
        int prime = 31;
        int result = 1;
        result = 31 * result + Arrays.hashCode(coeffs);
        return result;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        BigIntPolynomial other = (BigIntPolynomial)obj;
        return Arrays.areEqual(coeffs, other.coeffs);
    }

    public BigInteger[] getCoeffs()
    {
        return Arrays.clone(coeffs);
    }

    private static final double LOG_10_2 = Math.log10(2D);
    BigInteger coeffs[];

}
