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

static class SECNamedCurves$7 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        BigInteger p = SECNamedCurves.access$000("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFAC73");
        BigInteger a = SECNamedCurves.access$000("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFAC70");
        BigInteger b = SECNamedCurves.access$000("B4E134D3FB59EB8BAB57274904664D5AF50388BA");
        byte S[] = Hex.decode("B99B99B099B323E02709A4D696E6768756151751");
        BigInteger n = SECNamedCurves.access$000("0100000000000000000000351EE786A818F3A1A16B");
        BigInteger h = BigInteger.valueOf(1L);
        ECCurve curve = new co.org.bouncy.math.ec.ECCurve.Fp(p, a, b);
        co.org.bouncy.math.ec.ECPoint G = curve.decodePoint(Hex.decode("0452DCB034293A117E1F4FF11B30F7199D3144CE6DFEAFFEF2E331F296E071FA0DF9982CFEA7D43F2E"));
        return new X9ECParameters(curve, G, n, h, S);
    }

    SECNamedCurves$7()
    {
    }
}
