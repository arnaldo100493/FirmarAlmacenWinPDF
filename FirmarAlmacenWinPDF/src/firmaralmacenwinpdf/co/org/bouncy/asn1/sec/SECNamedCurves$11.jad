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

static class SECNamedCurves$11 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        BigInteger p = SECNamedCurves.access$000("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000000001");
        BigInteger a = SECNamedCurves.access$000("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFE");
        BigInteger b = SECNamedCurves.access$000("B4050A850C04B3ABF54132565044B0B7D7BFD8BA270B39432355FFB4");
        byte S[] = Hex.decode("BD71344799D5C7FCDC45B59FA3B9AB8F6A948BC5");
        BigInteger n = SECNamedCurves.access$000("FFFFFFFFFFFFFFFFFFFFFFFFFFFF16A2E0B8F03E13DD29455C5C2A3D");
        BigInteger h = BigInteger.valueOf(1L);
        ECCurve curve = new co.org.bouncy.math.ec.ECCurve.Fp(p, a, b);
        co.org.bouncy.math.ec.ECPoint G = curve.decodePoint(Hex.decode("04B70E0CBD6BB4BF7F321390B94A03C1D356C21122343280D6115C1D21BD376388B5F723FB4C22DFE6CD4375A05A07476444D5819985007E34"));
        return new X9ECParameters(curve, G, n, h, S);
    }

    SECNamedCurves$11()
    {
    }
}
