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

static class SECNamedCurves$20 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        int m = 163;
        int k1 = 3;
        int k2 = 6;
        int k3 = 7;
        BigInteger a = BigInteger.valueOf(1L);
        BigInteger b = BigInteger.valueOf(1L);
        byte S[] = null;
        BigInteger n = SECNamedCurves.access$000("04000000000000000000020108A2E0CC0D99F8A5EF");
        BigInteger h = BigInteger.valueOf(2L);
        ECCurve curve = new co.org.bouncy.math.ec.ECCurve.F2m(m, k1, k2, k3, a, b, n, h);
        co.org.bouncy.math.ec.ECPoint G = curve.decodePoint(Hex.decode("0402FE13C0537BBC11ACAA07D793DE4E6D5E5C94EEE80289070FB05D38FF58321F2E800536D538CCDAA3D9"));
        return new X9ECParameters(curve, G, n, h, S);
    }

    SECNamedCurves$20()
    {
    }
}
