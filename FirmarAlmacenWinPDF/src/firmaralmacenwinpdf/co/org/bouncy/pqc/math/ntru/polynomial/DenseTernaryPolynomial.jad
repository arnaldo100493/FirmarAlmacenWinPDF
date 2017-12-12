// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DenseTernaryPolynomial.java

package co.org.bouncy.pqc.math.ntru.polynomial;

import co.org.bouncy.pqc.math.ntru.util.Util;
import co.org.bouncy.util.Arrays;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.pqc.math.ntru.polynomial:
//            IntegerPolynomial, LongPolynomial5, TernaryPolynomial

public class DenseTernaryPolynomial extends IntegerPolynomial
    implements TernaryPolynomial
{

    DenseTernaryPolynomial(int N)
    {
        super(N);
        checkTernarity();
    }

    public DenseTernaryPolynomial(IntegerPolynomial intPoly)
    {
        this(intPoly.coeffs);
    }

    public DenseTernaryPolynomial(int coeffs[])
    {
        super(coeffs);
        checkTernarity();
    }

    private void checkTernarity()
    {
        for(int i = 0; i != coeffs.length; i++)
        {
            int c = coeffs[i];
            if(c < -1 || c > 1)
                throw new IllegalStateException((new StringBuilder()).append("Illegal value: ").append(c).append(", must be one of {-1, 0, 1}").toString());
        }

    }

    public static DenseTernaryPolynomial generateRandom(int N, int numOnes, int numNegOnes, SecureRandom random)
    {
        int coeffs[] = Util.generateRandomTernary(N, numOnes, numNegOnes, random);
        return new DenseTernaryPolynomial(coeffs);
    }

    public static DenseTernaryPolynomial generateRandom(int N, SecureRandom random)
    {
        DenseTernaryPolynomial poly = new DenseTernaryPolynomial(N);
        for(int i = 0; i < N; i++)
            poly.coeffs[i] = random.nextInt(3) - 1;

        return poly;
    }

    public IntegerPolynomial mult(IntegerPolynomial poly2, int modulus)
    {
        if(modulus == 2048)
        {
            IntegerPolynomial poly2Pos = (IntegerPolynomial)poly2.clone();
            poly2Pos.modPositive(2048);
            LongPolynomial5 poly5 = new LongPolynomial5(poly2Pos);
            return poly5.mult(this).toIntegerPolynomial();
        } else
        {
            return super.mult(poly2, modulus);
        }
    }

    public int[] getOnes()
    {
        int N = coeffs.length;
        int ones[] = new int[N];
        int onesIdx = 0;
        for(int i = 0; i < N; i++)
        {
            int c = coeffs[i];
            if(c == 1)
                ones[onesIdx++] = i;
        }

        return Arrays.copyOf(ones, onesIdx);
    }

    public int[] getNegOnes()
    {
        int N = coeffs.length;
        int negOnes[] = new int[N];
        int negOnesIdx = 0;
        for(int i = 0; i < N; i++)
        {
            int c = coeffs[i];
            if(c == -1)
                negOnes[negOnesIdx++] = i;
        }

        return Arrays.copyOf(negOnes, negOnesIdx);
    }

    public int size()
    {
        return coeffs.length;
    }
}
