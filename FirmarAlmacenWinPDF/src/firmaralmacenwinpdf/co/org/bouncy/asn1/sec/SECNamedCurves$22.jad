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

static class SECNamedCurves$22 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        int m = 163;
        int k1 = 3;
        int k2 = 6;
        int k3 = 7;
        BigInteger a = BigInteger.valueOf(1L);
        BigInteger b = SECNamedCurves.access$000("020A601907B8C953CA1481EB10512F78744A3205FD");
        byte S[] = Hex.decode("85E25BFE5C86226CDB12016F7553F9D0E693A268");
        BigInteger n = SECNamedCurves.access$000("040000000000000000000292FE77E70C12A4234C33");
        BigInteger h = BigInteger.valueOf(2L);
        ECCurve curve = new co.org.bouncy.math.ec.ECCurve.F2m(m, k1, k2, k3, a, b, n, h);
        co.org.bouncy.math.ec.ECPoint G = curve.decodePoint(Hex.decode("0403F0EBA16286A2D57EA0991168D4994637E8343E3600D51FBC6C71A0094FA2CDD545B11C5C0C797324F1"));
        return new X9ECParameters(curve, G, n, h, S);
    }

    SECNamedCurves$22()
    {
    }
}
