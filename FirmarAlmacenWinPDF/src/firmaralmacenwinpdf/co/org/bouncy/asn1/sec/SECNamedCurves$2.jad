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

static class SECNamedCurves$2 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        BigInteger p = SECNamedCurves.access$000("DB7C2ABF62E35E668076BEAD208B");
        BigInteger a = SECNamedCurves.access$000("6127C24C05F38A0AAAF65C0EF02C");
        BigInteger b = SECNamedCurves.access$000("51DEF1815DB5ED74FCC34C85D709");
        byte S[] = Hex.decode("002757A1114D696E6768756151755316C05E0BD4");
        BigInteger n = SECNamedCurves.access$000("36DF0AAFD8B8D7597CA10520D04B");
        BigInteger h = BigInteger.valueOf(4L);
        ECCurve curve = new co.org.bouncy.math.ec.ECCurve.Fp(p, a, b);
        co.org.bouncy.math.ec.ECPoint G = curve.decodePoint(Hex.decode("044BA30AB5E892B4E1649DD0928643ADCD46F5882E3747DEF36E956E97"));
        return new X9ECParameters(curve, G, n, h, S);
    }

    SECNamedCurves$2()
    {
    }
}
