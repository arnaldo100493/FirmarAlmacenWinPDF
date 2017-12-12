// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SECNamedCurves.java

package co.org.bouncy.asn1.sec;

import co.org.bouncy.asn1.x9.X9ECParameters;
import co.org.bouncy.asn1.x9.X9ECParametersHolder;
import co.org.bouncy.math.ec.ECConstants;
import co.org.bouncy.math.ec.ECCurve;
import co.org.bouncy.util.encoders.Hex;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.asn1.sec:
//            SECNamedCurves

static class SECNamedCurves$25 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        int m = 233;
        int k = 74;
        BigInteger a = ECConstants.ZERO;
        BigInteger b = BigInteger.valueOf(1L);
        byte S[] = null;
        BigInteger n = SECNamedCurves.access$000("8000000000000000000000000000069D5BB915BCD46EFB1AD5F173ABDF");
        BigInteger h = BigInteger.valueOf(4L);
        ECCurve curve = new co.org.bouncy.math.ec.ECCurve.F2m(m, k, a, b, n, h);
        co.org.bouncy.math.ec.ECPoint G = curve.decodePoint(Hex.decode("04017232BA853A7E731AF129F22FF4149563A419C26BF50A4C9D6EEFAD612601DB537DECE819B7F70F555A67C427A8CD9BF18AEB9B56E0C11056FAE6A3"));
        return new X9ECParameters(curve, G, n, h, S);
    }

    SECNamedCurves$25()
    {
    }
}
