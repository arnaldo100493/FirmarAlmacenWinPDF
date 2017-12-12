// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReferenceMultiplier.java

package co.org.bouncy.math.ec;

import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.math.ec:
//            ECMultiplier, ECPoint, ECCurve, PreCompInfo

class ReferenceMultiplier
    implements ECMultiplier
{

    ReferenceMultiplier()
    {
    }

    public ECPoint multiply(ECPoint p, BigInteger k, PreCompInfo preCompInfo)
    {
        ECPoint q = p.getCurve().getInfinity();
        int t = k.bitLength();
        for(int i = 0; i < t; i++)
        {
            if(k.testBit(i))
                q = q.add(p);
            p = p.twice();
        }

        return q;
    }
}
