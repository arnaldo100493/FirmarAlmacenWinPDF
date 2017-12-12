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

static class SECNamedCurves$9 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        BigInteger p = SECNamedCurves.access$000("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFFFFFFFFFF");
        BigInteger a = SECNamedCurves.access$000("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFFFFFFFFFC");
        BigInteger b = SECNamedCurves.access$000("64210519E59C80E70FA7E9AB72243049FEB8DEECC146B9B1");
        byte S[] = Hex.decode("3045AE6FC8422F64ED579528D38120EAE12196D5");
        BigInteger n = SECNamedCurves.access$000("FFFFFFFFFFFFFFFFFFFFFFFF99DEF836146BC9B1B4D22831");
        BigInteger h = BigInteger.valueOf(1L);
        ECCurve curve = new co.org.bouncy.math.ec.ECCurve.Fp(p, a, b);
        co.org.bouncy.math.ec.ECPoint G = curve.decodePoint(Hex.decode("04188DA80EB03090F67CBF20EB43A18800F4FF0AFD82FF101207192B95FFC8DA78631011ED6B24CDD573F977A11E794811"));
        return new X9ECParameters(curve, G, n, h, S);
    }

    SECNamedCurves$9()
    {
    }
}
