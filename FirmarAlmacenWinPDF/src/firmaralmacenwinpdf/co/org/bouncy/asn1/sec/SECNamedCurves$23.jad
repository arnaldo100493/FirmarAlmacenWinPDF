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

static class SECNamedCurves$23 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        int m = 193;
        int k = 15;
        BigInteger a = SECNamedCurves.access$000("0017858FEB7A98975169E171F77B4087DE098AC8A911DF7B01");
        BigInteger b = SECNamedCurves.access$000("00FDFB49BFE6C3A89FACADAA7A1E5BBC7CC1C2E5D831478814");
        byte S[] = Hex.decode("103FAEC74D696E676875615175777FC5B191EF30");
        BigInteger n = SECNamedCurves.access$000("01000000000000000000000000C7F34A778F443ACC920EBA49");
        BigInteger h = BigInteger.valueOf(2L);
        ECCurve curve = new co.org.bouncy.math.ec.ECCurve.F2m(m, k, a, b, n, h);
        co.org.bouncy.math.ec.ECPoint G = curve.decodePoint(Hex.decode("0401F481BC5F0FF84A74AD6CDF6FDEF4BF6179625372D8C0C5E10025E399F2903712CCF3EA9E3A1AD17FB0B3201B6AF7CE1B05"));
        return new X9ECParameters(curve, G, n, h, S);
    }

    SECNamedCurves$23()
    {
    }
}
