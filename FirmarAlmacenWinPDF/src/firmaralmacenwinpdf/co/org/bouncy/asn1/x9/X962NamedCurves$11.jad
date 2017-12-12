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

static class X962NamedCurves$11 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        BigInteger c2m176w1n = new BigInteger("010092537397ECA4F6145799D62B0A19CE06FE26AD", 16);
        BigInteger c2m176w1h = BigInteger.valueOf(65390L);
        ECCurve c2m176w1 = new co.org.bouncy.math.ec.ECCurve.F2m(176, 1, 2, 43, new BigInteger("00E4E6DB2995065C407D9D39B8D0967B96704BA8E9C90B", 16), new BigInteger("005DDA470ABE6414DE8EC133AE28E9BBD7FCEC0AE0FFF2", 16), c2m176w1n, c2m176w1h);
        return new X9ECParameters(c2m176w1, c2m176w1.decodePoint(Hex.decode("038D16C2866798B600F9F08BB4A8E860F3298CE04A5798")), c2m176w1n, c2m176w1h, null);
    }

    X962NamedCurves$11()
    {
    }
}
