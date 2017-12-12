// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECAlgorithms.java

package co.org.bouncy.math.ec;

import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.math.ec:
//            ECCurve, ECPoint

public class ECAlgorithms
{

    public ECAlgorithms()
    {
    }

    public static ECPoint sumOfTwoMultiplies(ECPoint P, BigInteger a, ECPoint Q, BigInteger b)
    {
        ECCurve c = P.getCurve();
        if(!c.equals(Q.getCurve()))
            throw new IllegalArgumentException("P and Q must be on same curve");
        if(c instanceof ECCurve.F2m)
        {
            ECCurve.F2m f2mCurve = (ECCurve.F2m)c;
            if(f2mCurve.isKoblitz())
                return P.multiply(a).add(Q.multiply(b));
        }
        return implShamirsTrick(P, a, Q, b);
    }

    public static ECPoint shamirsTrick(ECPoint P, BigInteger k, ECPoint Q, BigInteger l)
    {
        if(!P.getCurve().equals(Q.getCurve()))
            throw new IllegalArgumentException("P and Q must be on same curve");
        else
            return implShamirsTrick(P, k, Q, l);
    }

    private static ECPoint implShamirsTrick(ECPoint P, BigInteger k, ECPoint Q, BigInteger l)
    {
        int m = Math.max(k.bitLength(), l.bitLength());
        ECPoint Z = P.add(Q);
        ECPoint R = P.getCurve().getInfinity();
        for(int i = m - 1; i >= 0; i--)
        {
            R = R.twice();
            if(k.testBit(i))
            {
                if(l.testBit(i))
                    R = R.add(Z);
                else
                    R = R.add(P);
                continue;
            }
            if(l.testBit(i))
                R = R.add(Q);
        }

        return R;
    }
}
