// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FpNafMultiplier.java

package co.org.bouncy.math.ec;

import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.math.ec:
//            ECMultiplier, ECPoint, PreCompInfo

class FpNafMultiplier
    implements ECMultiplier
{

    FpNafMultiplier()
    {
    }

    public ECPoint multiply(ECPoint p, BigInteger k, PreCompInfo preCompInfo)
    {
        BigInteger e = k;
        BigInteger h = e.multiply(BigInteger.valueOf(3L));
        ECPoint neg = p.negate();
        ECPoint R = p;
        for(int i = h.bitLength() - 2; i > 0; i--)
        {
            R = R.twice();
            boolean hBit = h.testBit(i);
            boolean eBit = e.testBit(i);
            if(hBit != eBit)
                R = R.add(hBit ? p : neg);
        }

        return R;
    }
}
