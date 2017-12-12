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

static class X962NamedCurves$16 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        BigInteger c2m239v1n = new BigInteger("2000000000000000000000000000000F4D42FFE1492A4993F1CAD666E447", 16);
        BigInteger c2m239v1h = BigInteger.valueOf(4L);
        ECCurve c2m239v1 = new co.org.bouncy.math.ec.ECCurve.F2m(239, 36, new BigInteger("32010857077C5431123A46B808906756F543423E8D27877578125778AC76", 16), new BigInteger("790408F2EEDAF392B012EDEFB3392F30F4327C0CA3F31FC383C422AA8C16", 16), c2m239v1n, c2m239v1h);
        return new X9ECParameters(c2m239v1, c2m239v1.decodePoint(Hex.decode("0257927098FA932E7C0A96D3FD5B706EF7E5F5C156E16B7E7C86038552E91D")), c2m239v1n, c2m239v1h, null);
    }

    X962NamedCurves$16()
    {
    }
}
