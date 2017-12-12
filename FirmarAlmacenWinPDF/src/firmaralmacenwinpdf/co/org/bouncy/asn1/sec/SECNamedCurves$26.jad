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

static class SECNamedCurves$26 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        int m = 233;
        int k = 74;
        BigInteger a = BigInteger.valueOf(1L);
        BigInteger b = SECNamedCurves.access$000("0066647EDE6C332C7F8C0923BB58213B333B20E9CE4281FE115F7D8F90AD");
        byte S[] = Hex.decode("74D59FF07F6B413D0EA14B344B20A2DB049B50C3");
        BigInteger n = SECNamedCurves.access$000("01000000000000000000000000000013E974E72F8A6922031D2603CFE0D7");
        BigInteger h = BigInteger.valueOf(2L);
        ECCurve curve = new co.org.bouncy.math.ec.ECCurve.F2m(m, k, a, b, n, h);
        co.org.bouncy.math.ec.ECPoint G = curve.decodePoint(Hex.decode("0400FAC9DFCBAC8313BB2139F1BB755FEF65BC391F8B36F8F8EB7371FD558B01006A08A41903350678E58528BEBF8A0BEFF867A7CA36716F7E01F81052"));
        return new X9ECParameters(curve, G, n, h, S);
    }

    SECNamedCurves$26()
    {
    }
}
