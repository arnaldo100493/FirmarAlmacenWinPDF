// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SECNamedCurves.java

package co.org.bouncy.asn1.sec;

import co.org.bouncy.asn1.x9.X9ECParameters;
import co.org.bouncy.asn1.x9.X9ECParametersHolder;
import co.org.bouncy.math.ec.ECCurve;
import co.org.bouncy.util.encoders.Hex;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.asn1.sec:
//            SECNamedCurves

static class SECNamedCurves$19 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        int m = 131;
        int k1 = 2;
        int k2 = 3;
        int k3 = 8;
        BigInteger a = SECNamedCurves.access$000("03E5A88919D7CAFCBF415F07C2176573B2");
        BigInteger b = SECNamedCurves.access$000("04B8266A46C55657AC734CE38F018F2192");
        byte S[] = Hex.decode("985BD3ADBAD4D696E676875615175A21B43A97E3");
        BigInteger n = SECNamedCurves.access$000("0400000000000000016954A233049BA98F");
        BigInteger h = BigInteger.valueOf(2L);
        ECCurve curve = new co.org.bouncy.math.ec.ECCurve.F2m(m, k1, k2, k3, a, b, n, h);
        co.org.bouncy.math.ec.ECPoint G = curve.decodePoint(Hex.decode("040356DCD8F2F95031AD652D23951BB366A80648F06D867940A5366D9E265DE9EB240F"));
        return new X9ECParameters(curve, G, n, h, S);
    }

    SECNamedCurves$19()
    {
    }
}
