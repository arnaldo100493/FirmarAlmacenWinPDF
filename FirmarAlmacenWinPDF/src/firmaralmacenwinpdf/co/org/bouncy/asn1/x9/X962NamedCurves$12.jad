// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X962NamedCurves.java

package co.org.bouncy.asn1.x9;

import co.org.bouncy.math.ec.ECCurve;
import co.org.bouncy.util.encoders.Hex;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.asn1.x9:
//            X9ECParametersHolder, X9ECParameters, X962NamedCurves

static class X962NamedCurves$12 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        BigInteger c2m191v1n = new BigInteger("40000000000000000000000004A20E90C39067C893BBB9A5", 16);
        BigInteger c2m191v1h = BigInteger.valueOf(2L);
        ECCurve c2m191v1 = new co.org.bouncy.math.ec.ECCurve.F2m(191, 9, new BigInteger("2866537B676752636A68F56554E12640276B649EF7526267", 16), new BigInteger("2E45EF571F00786F67B0081B9495A3D95462F5DE0AA185EC", 16), c2m191v1n, c2m191v1h);
        return new X9ECParameters(c2m191v1, c2m191v1.decodePoint(Hex.decode("0236B3DAF8A23206F9C4F299D7B21A9C369137F2C84AE1AA0D")), c2m191v1n, c2m191v1h, Hex.decode("4E13CA542744D696E67687561517552F279A8C84"));
    }

    X962NamedCurves$12()
    {
    }
}
