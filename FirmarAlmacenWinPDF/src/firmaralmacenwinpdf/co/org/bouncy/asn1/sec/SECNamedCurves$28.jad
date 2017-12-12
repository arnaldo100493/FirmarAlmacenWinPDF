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

static class SECNamedCurves$28 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        int m = 283;
        int k1 = 5;
        int k2 = 7;
        int k3 = 12;
        BigInteger a = ECConstants.ZERO;
        BigInteger b = BigInteger.valueOf(1L);
        byte S[] = null;
        BigInteger n = SECNamedCurves.access$000("01FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFE9AE2ED07577265DFF7F94451E061E163C61");
        BigInteger h = BigInteger.valueOf(4L);
        ECCurve curve = new co.org.bouncy.math.ec.ECCurve.F2m(m, k1, k2, k3, a, b, n, h);
        co.org.bouncy.math.ec.ECPoint G = curve.decodePoint(Hex.decode("040503213F78CA44883F1A3B8162F188E553CD265F23C1567A16876913B0C2AC245849283601CCDA380F1C9E318D90F95D07E5426FE87E45C0E8184698E45962364E34116177DD2259"));
        return new X9ECParameters(curve, G, n, h, S);
    }

    SECNamedCurves$28()
    {
    }
}
