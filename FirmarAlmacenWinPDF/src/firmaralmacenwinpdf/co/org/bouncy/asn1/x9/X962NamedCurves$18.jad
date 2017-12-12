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

static class X962NamedCurves$18 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        BigInteger c2m239v3n = new BigInteger("0CCCCCCCCCCCCCCCCCCCCCCCCCCCCCAC4912D2D9DF903EF9888B8A0E4CFF", 16);
        BigInteger c2m239v3h = BigInteger.valueOf(10L);
        ECCurve c2m239v3 = new co.org.bouncy.math.ec.ECCurve.F2m(239, 36, new BigInteger("01238774666A67766D6676F778E676B66999176666E687666D8766C66A9F", 16), new BigInteger("6A941977BA9F6A435199ACFC51067ED587F519C5ECB541B8E44111DE1D40", 16), c2m239v3n, c2m239v3h);
        return new X9ECParameters(c2m239v3, c2m239v3.decodePoint(Hex.decode("0370F6E9D04D289C4E89913CE3530BFDE903977D42B146D539BF1BDE4E9C92")), c2m239v3n, c2m239v3h, null);
    }

    X962NamedCurves$18()
    {
    }
}
