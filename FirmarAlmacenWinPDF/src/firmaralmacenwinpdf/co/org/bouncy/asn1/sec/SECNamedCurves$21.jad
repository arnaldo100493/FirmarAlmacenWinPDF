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

static class SECNamedCurves$21 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        int m = 163;
        int k1 = 3;
        int k2 = 6;
        int k3 = 7;
        BigInteger a = SECNamedCurves.access$000("07B6882CAAEFA84F9554FF8428BD88E246D2782AE2");
        BigInteger b = SECNamedCurves.access$000("0713612DCDDCB40AAB946BDA29CA91F73AF958AFD9");
        byte S[] = Hex.decode("24B7B137C8A14D696E6768756151756FD0DA2E5C");
        BigInteger n = SECNamedCurves.access$000("03FFFFFFFFFFFFFFFFFFFF48AAB689C29CA710279B");
        BigInteger h = BigInteger.valueOf(2L);
        ECCurve curve = new co.org.bouncy.math.ec.ECCurve.F2m(m, k1, k2, k3, a, b, n, h);
        co.org.bouncy.math.ec.ECPoint G = curve.decodePoint(Hex.decode("040369979697AB43897789566789567F787A7876A65400435EDB42EFAFB2989D51FEFCE3C80988F41FF883"));
        return new X9ECParameters(curve, G, n, h, S);
    }

    SECNamedCurves$21()
    {
    }
}
