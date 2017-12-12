// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SECNamedCurves.java

package co.org.bouncy.asn1.sec;

import co.org.bouncy.asn1.x9.X9ECParameters;
import co.org.bouncy.asn1.x9.X9ECParametersHolder;
import co.org.bouncy.math.ec.ECConstants;
import co.org.bouncy.math.ec.ECCurve;
import co.org.bouncy.util.encoders.Hex;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.asn1.sec:
//            SECNamedCurves

static class SECNamedCurves$10 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        BigInteger p = SECNamedCurves.access$000("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFE56D");
        BigInteger a = ECConstants.ZERO;
        BigInteger b = BigInteger.valueOf(5L);
        byte S[] = null;
        BigInteger n = SECNamedCurves.access$000("010000000000000000000000000001DCE8D2EC6184CAF0A971769FB1F7");
        BigInteger h = BigInteger.valueOf(1L);
        ECCurve curve = new co.org.bouncy.math.ec.ECCurve.Fp(p, a, b);
        co.org.bouncy.math.ec.ECPoint G = curve.decodePoint(Hex.decode("04A1455B334DF099DF30FC28A169A467E9E47075A90F7E650EB6B7A45C7E089FED7FBA344282CAFBD6F7E319F7C0B0BD59E2CA4BDB556D61A5"));
        return new X9ECParameters(curve, G, n, h, S);
    }

    SECNamedCurves$10()
    {
    }
}
