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

static class SECNamedCurves$24 extends X9ECParametersHolder
{

    protected X9ECParameters createParameters()
    {
        int m = 193;
        int k = 15;
        BigInteger a = SECNamedCurves.access$000("0163F35A5137C2CE3EA6ED8667190B0BC43ECD69977702709B");
        BigInteger b = SECNamedCurves.access$000("00C9BB9E8927D4D64C377E2AB2856A5B16E3EFB7F61D4316AE");
        byte S[] = Hex.decode("10B7B4D696E676875615175137C8A16FD0DA2211");
        BigInteger n = SECNamedCurves.access$000("010000000000000000000000015AAB561B005413CCD4EE99D5");
        BigInteger h = BigInteger.valueOf(2L);
        ECCurve curve = new co.org.bouncy.math.ec.ECCurve.F2m(m, k, a, b, n, h);
        co.org.bouncy.math.ec.ECPoint G = curve.decodePoint(Hex.decode("0400D9B67D192E0367C803F39E1A7E82CA14A651350AAE617E8F01CE94335607C304AC29E7DEFBD9CA01F596F927224CDECF6C"));
        return new X9ECParameters(curve, G, n, h, S);
    }

    SECNamedCurves$24()
    {
    }
}
