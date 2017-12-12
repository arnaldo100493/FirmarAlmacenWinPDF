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

static class X962NamedCurves$20 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        BigInteger c2m304w1n = new BigInteger("0101D556572AABAC800101D556572AABAC8001022D5C91DD173F8FB561DA6899164443051D", 16);
        BigInteger c2m304w1h = BigInteger.valueOf(65070L);
        ECCurve c2m304w1 = new co.org.bouncy.math.ec.ECCurve.F2m(304, 1, 2, 11, new BigInteger("00FD0D693149A118F651E6DCE6802085377E5F882D1B510B44160074C1288078365A0396C8E681", 16), new BigInteger("00BDDB97E555A50A908E43B01C798EA5DAA6788F1EA2794EFCF57166B8C14039601E55827340BE", 16), c2m304w1n, c2m304w1h);
        return new X9ECParameters(c2m304w1, c2m304w1.decodePoint(Hex.decode("02197B07845E9BE2D96ADB0F5F3C7F2CFFBD7A3EB8B6FEC35C7FD67F26DDF6285A644F740A2614")), c2m304w1n, c2m304w1h, null);
    }

    X962NamedCurves$20()
    {
    }
}
