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

static class SECNamedCurves$17 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        int m = 113;
        int k = 9;
        BigInteger a = SECNamedCurves.access$000("00689918DBEC7E5A0DD6DFC0AA55C7");
        BigInteger b = SECNamedCurves.access$000("0095E9A9EC9B297BD4BF36E059184F");
        byte S[] = Hex.decode("10C0FB15760860DEF1EEF4D696E676875615175D");
        BigInteger n = SECNamedCurves.access$000("010000000000000108789B2496AF93");
        BigInteger h = BigInteger.valueOf(2L);
        ECCurve curve = new co.org.bouncy.math.ec.ECCurve.F2m(m, k, a, b, n, h);
        co.org.bouncy.math.ec.ECPoint G = curve.decodePoint(Hex.decode("0401A57A6A7B26CA5EF52FCDB816479700B3ADC94ED1FE674C06E695BABA1D"));
        return new X9ECParameters(curve, G, n, h, S);
    }

    SECNamedCurves$17()
    {
    }
}
