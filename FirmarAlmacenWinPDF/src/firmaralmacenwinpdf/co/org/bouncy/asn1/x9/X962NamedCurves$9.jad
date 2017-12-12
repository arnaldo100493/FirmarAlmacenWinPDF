// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X962NamedCurves.java

package co.org.bouncy.asn1.x9;

import co.org.bouncy.math.ec.ECCurve;
import co.org.bouncy.util.encoders.Hex;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.asn1.x9:
//            X9ECParametersHolder, X9ECParameters, X962NamedCurves

static class X962NamedCurves$9 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        BigInteger c2m163v2n = new BigInteger("03FFFFFFFFFFFFFFFFFFFDF64DE1151ADBB78F10A7", 16);
        BigInteger c2m163v2h = BigInteger.valueOf(2L);
        ECCurve c2m163v2 = new co.org.bouncy.math.ec.ECCurve.F2m(163, 1, 2, 8, new BigInteger("0108B39E77C4B108BED981ED0E890E117C511CF072", 16), new BigInteger("0667ACEB38AF4E488C407433FFAE4F1C811638DF20", 16), c2m163v2n, c2m163v2h);
        return new X9ECParameters(c2m163v2, c2m163v2.decodePoint(Hex.decode("030024266E4EB5106D0A964D92C4860E2671DB9B6CC5")), c2m163v2n, c2m163v2h, null);
    }

    X962NamedCurves$9()
    {
    }
}
