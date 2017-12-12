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

static class SECNamedCurves$31 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        int m = 409;
        int k = 87;
        BigInteger a = BigInteger.valueOf(1L);
        BigInteger b = SECNamedCurves.access$000("0021A5C2C8EE9FEB5C4B9A753B7B476B7FD6422EF1F3DD674761FA99D6AC27C8A9A197B272822F6CD57A55AA4F50AE317B13545F");
        byte S[] = Hex.decode("4099B5A457F9D69F79213D094C4BCD4D4262210B");
        BigInteger n = SECNamedCurves.access$000("010000000000000000000000000000000000000000000000000001E2AAD6A612F33307BE5FA47C3C9E052F838164CD37D9A21173");
        BigInteger h = BigInteger.valueOf(2L);
        ECCurve curve = new co.org.bouncy.math.ec.ECCurve.F2m(m, k, a, b, n, h);
        co.org.bouncy.math.ec.ECPoint G = curve.decodePoint(Hex.decode("04015D4860D088DDB3496B0C6064756260441CDE4AF1771D4DB01FFE5B34E59703DC255A868A1180515603AEAB60794E54BB7996A70061B1CFAB6BE5F32BBFA78324ED106A7636B9C5A7BD198D0158AA4F5488D08F38514F1FDF4B4F40D2181B3681C364BA0273C706"));
        return new X9ECParameters(curve, G, n, h, S);
    }

    SECNamedCurves$31()
    {
    }
}
