// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECPointUtil.java

package co.org.bouncy.jce;

import co.org.bouncy.math.ec.ECCurve;
import co.org.bouncy.math.ec.ECFieldElement;
import java.security.spec.ECFieldF2m;
import java.security.spec.ECFieldFp;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;

public class ECPointUtil
{

    public ECPointUtil()
    {
    }

    public static ECPoint decodePoint(EllipticCurve curve, byte encoded[])
    {
        ECCurve c = null;
        if(curve.getField() instanceof ECFieldFp)
        {
            c = new co.org.bouncy.math.ec.ECCurve.Fp(((ECFieldFp)curve.getField()).getP(), curve.getA(), curve.getB());
        } else
        {
            int k[] = ((ECFieldF2m)curve.getField()).getMidTermsOfReductionPolynomial();
            if(k.length == 3)
                c = new co.org.bouncy.math.ec.ECCurve.F2m(((ECFieldF2m)curve.getField()).getM(), k[2], k[1], k[0], curve.getA(), curve.getB());
            else
                c = new co.org.bouncy.math.ec.ECCurve.F2m(((ECFieldF2m)curve.getField()).getM(), k[0], curve.getA(), curve.getB());
        }
        co.org.bouncy.math.ec.ECPoint p = c.decodePoint(encoded);
        return new ECPoint(p.getX().toBigInteger(), p.getY().toBigInteger());
    }
}
