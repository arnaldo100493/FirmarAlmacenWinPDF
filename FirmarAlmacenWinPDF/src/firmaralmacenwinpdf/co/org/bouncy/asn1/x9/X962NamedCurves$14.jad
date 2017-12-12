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

static class X962NamedCurves$14 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        BigInteger c2m191v3n = new BigInteger("155555555555555555555555610C0B196812BFB6288A3EA3", 16);
        BigInteger c2m191v3h = BigInteger.valueOf(6L);
        ECCurve c2m191v3 = new co.org.bouncy.math.ec.ECCurve.F2m(191, 9, new BigInteger("6C01074756099122221056911C77D77E77A777E7E7E77FCB", 16), new BigInteger("71FE1AF926CF847989EFEF8DB459F66394D90F32AD3F15E8", 16), c2m191v3n, c2m191v3h);
        return new X9ECParameters(c2m191v3, c2m191v3.decodePoint(Hex.decode("03375D4CE24FDE434489DE8746E71786015009E66E38A926DD")), c2m191v3n, c2m191v3h, null);
    }

    X962NamedCurves$14()
    {
    }
}
