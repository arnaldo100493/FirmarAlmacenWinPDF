// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECNamedCurveSpec.java

package co.org.bouncy.jce.spec;

import co.org.bouncy.math.ec.ECCurve;
import co.org.bouncy.math.ec.ECFieldElement;
import java.math.BigInteger;
import java.security.spec.ECFieldF2m;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;

public class ECNamedCurveSpec extends ECParameterSpec
{

    private static EllipticCurve convertCurve(ECCurve curve, byte seed[])
    {
        if(curve instanceof co.org.bouncy.math.ec.ECCurve.Fp)
            return new EllipticCurve(new ECFieldFp(((co.org.bouncy.math.ec.ECCurve.Fp)curve).getQ()), curve.getA().toBigInteger(), curve.getB().toBigInteger(), seed);
        co.org.bouncy.math.ec.ECCurve.F2m curveF2m = (co.org.bouncy.math.ec.ECCurve.F2m)curve;
        if(curveF2m.isTrinomial())
        {
            int ks[] = {
                curveF2m.getK1()
            };
            return new EllipticCurve(new ECFieldF2m(curveF2m.getM(), ks), curve.getA().toBigInteger(), curve.getB().toBigInteger(), seed);
        } else
        {
            int ks[] = {
                curveF2m.getK3(), curveF2m.getK2(), curveF2m.getK1()
            };
            return new EllipticCurve(new ECFieldF2m(curveF2m.getM(), ks), curve.getA().toBigInteger(), curve.getB().toBigInteger(), seed);
        }
    }

    private static ECPoint convertPoint(co.org.bouncy.math.ec.ECPoint g)
    {
        return new ECPoint(g.getX().toBigInteger(), g.getY().toBigInteger());
    }

    public ECNamedCurveSpec(String name, ECCurve curve, co.org.bouncy.math.ec.ECPoint g, BigInteger n)
    {
        super(convertCurve(curve, null), convertPoint(g), n, 1);
        this.name = name;
    }

    public ECNamedCurveSpec(String name, EllipticCurve curve, ECPoint g, BigInteger n)
    {
        super(curve, g, n, 1);
        this.name = name;
    }

    public ECNamedCurveSpec(String name, ECCurve curve, co.org.bouncy.math.ec.ECPoint g, BigInteger n, BigInteger h)
    {
        super(convertCurve(curve, null), convertPoint(g), n, h.intValue());
        this.name = name;
    }

    public ECNamedCurveSpec(String name, EllipticCurve curve, ECPoint g, BigInteger n, BigInteger h)
    {
        super(curve, g, n, h.intValue());
        this.name = name;
    }

    public ECNamedCurveSpec(String name, ECCurve curve, co.org.bouncy.math.ec.ECPoint g, BigInteger n, BigInteger h, byte seed[])
    {
        super(convertCurve(curve, seed), convertPoint(g), n, h.intValue());
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    private String name;
}
