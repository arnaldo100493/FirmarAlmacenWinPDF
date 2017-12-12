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

static class X962NamedCurves$15 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        BigInteger c2m208w1n = new BigInteger("0101BAF95C9723C57B6C21DA2EFF2D5ED588BDD5717E212F9D", 16);
        BigInteger c2m208w1h = BigInteger.valueOf(65096L);
        ECCurve c2m208w1 = new co.org.bouncy.math.ec.ECCurve.F2m(208, 1, 2, 83, new BigInteger("0", 16), new BigInteger("00C8619ED45A62E6212E1160349E2BFA844439FAFC2A3FD1638F9E", 16), c2m208w1n, c2m208w1h);
        return new X9ECParameters(c2m208w1, c2m208w1.decodePoint(Hex.decode("0289FDFBE4ABE193DF9559ECF07AC0CE78554E2784EB8C1ED1A57A")), c2m208w1n, c2m208w1h, null);
    }

    X962NamedCurves$15()
    {
    }
}
