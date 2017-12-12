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

static class SECNamedCurves$4 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        BigInteger p = SECNamedCurves.access$000("FFFFFFFDFFFFFFFFFFFFFFFFFFFFFFFF");
        BigInteger a = SECNamedCurves.access$000("D6031998D1B3BBFEBF59CC9BBFF9AEE1");
        BigInteger b = SECNamedCurves.access$000("5EEEFCA380D02919DC2C6558BB6D8A5D");
        byte S[] = Hex.decode("004D696E67687561517512D8F03431FCE63B88F4");
        BigInteger n = SECNamedCurves.access$000("3FFFFFFF7FFFFFFFBE0024720613B5A3");
        BigInteger h = BigInteger.valueOf(4L);
        ECCurve curve = new co.org.bouncy.math.ec.ECCurve.Fp(p, a, b);
        co.org.bouncy.math.ec.ECPoint G = curve.decodePoint(Hex.decode("047B6AA5D85E572983E6FB32A7CDEBC14027B6916A894D3AEE7106FE805FC34B44"));
        return new X9ECParameters(curve, G, n, h, S);
    }

    SECNamedCurves$4()
    {
    }
}
