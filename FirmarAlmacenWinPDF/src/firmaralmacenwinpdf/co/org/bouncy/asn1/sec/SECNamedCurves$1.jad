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

static class SECNamedCurves$1 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        BigInteger p = SECNamedCurves.access$000("DB7C2ABF62E35E668076BEAD208B");
        BigInteger a = SECNamedCurves.access$000("DB7C2ABF62E35E668076BEAD2088");
        BigInteger b = SECNamedCurves.access$000("659EF8BA043916EEDE8911702B22");
        byte S[] = Hex.decode("00F50B028E4D696E676875615175290472783FB1");
        BigInteger n = SECNamedCurves.access$000("DB7C2ABF62E35E7628DFAC6561C5");
        BigInteger h = BigInteger.valueOf(1L);
        ECCurve curve = new co.org.bouncy.math.ec.ECCurve.Fp(p, a, b);
        co.org.bouncy.math.ec.ECPoint G = curve.decodePoint(Hex.decode("0409487239995A5EE76B55F9C2F098A89CE5AF8724C0A23E0E0FF77500"));
        return new X9ECParameters(curve, G, n, h, S);
    }

    SECNamedCurves$1()
    {
    }
}
