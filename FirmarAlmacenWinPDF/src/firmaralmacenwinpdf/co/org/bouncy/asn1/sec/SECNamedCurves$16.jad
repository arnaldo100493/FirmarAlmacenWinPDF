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

static class SECNamedCurves$16 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        int m = 113;
        int k = 9;
        BigInteger a = SECNamedCurves.access$000("003088250CA6E7C7FE649CE85820F7");
        BigInteger b = SECNamedCurves.access$000("00E8BEE4D3E2260744188BE0E9C723");
        byte S[] = Hex.decode("10E723AB14D696E6768756151756FEBF8FCB49A9");
        BigInteger n = SECNamedCurves.access$000("0100000000000000D9CCEC8A39E56F");
        BigInteger h = BigInteger.valueOf(2L);
        ECCurve curve = new co.org.bouncy.math.ec.ECCurve.F2m(m, k, a, b, n, h);
        co.org.bouncy.math.ec.ECPoint G = curve.decodePoint(Hex.decode("04009D73616F35F4AB1407D73562C10F00A52830277958EE84D1315ED31886"));
        return new X9ECParameters(curve, G, n, h, S);
    }

    SECNamedCurves$16()
    {
    }
}
