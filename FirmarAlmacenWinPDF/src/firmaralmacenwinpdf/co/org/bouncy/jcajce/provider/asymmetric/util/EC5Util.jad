// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EC5Util.java

package co.org.bouncy.jcajce.provider.asymmetric.util;

import co.org.bouncy.jce.spec.ECNamedCurveParameterSpec;
import co.org.bouncy.jce.spec.ECNamedCurveSpec;
import co.org.bouncy.math.ec.ECCurve;
import co.org.bouncy.math.ec.ECFieldElement;
import java.math.BigInteger;
import java.security.spec.ECField;
import java.security.spec.ECFieldF2m;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.util:
//            ECUtil

public class EC5Util
{

    public EC5Util()
    {
    }

    public static EllipticCurve convertCurve(ECCurve curve, byte seed[])
    {
        if(curve instanceof co.org.bouncy.math.ec.ECCurve.Fp)
            return new EllipticCurve(new ECFieldFp(((co.org.bouncy.math.ec.ECCurve.Fp)curve).getQ()), curve.getA().toBigInteger(), curve.getB().toBigInteger(), null);
        co.org.bouncy.math.ec.ECCurve.F2m curveF2m = (co.org.bouncy.math.ec.ECCurve.F2m)curve;
        if(curveF2m.isTrinomial())
        {
            int ks[] = {
                curveF2m.getK1()
            };
            return new EllipticCurve(new ECFieldF2m(curveF2m.getM(), ks), curve.getA().toBigInteger(), curve.getB().toBigInteger(), null);
        } else
        {
            int ks[] = {
                curveF2m.getK3(), curveF2m.getK2(), curveF2m.getK1()
            };
            return new EllipticCurve(new ECFieldF2m(curveF2m.getM(), ks), curve.getA().toBigInteger(), curve.getB().toBigInteger(), null);
        }
    }

    public static ECCurve convertCurve(EllipticCurve ec)
    {
        ECField field = ec.getField();
        BigInteger a = ec.getA();
        BigInteger b = ec.getB();
        if(field instanceof ECFieldFp)
        {
            return new co.org.bouncy.math.ec.ECCurve.Fp(((ECFieldFp)field).getP(), a, b);
        } else
        {
            ECFieldF2m fieldF2m = (ECFieldF2m)field;
            int m = fieldF2m.getM();
            int ks[] = ECUtil.convertMidTerms(fieldF2m.getMidTermsOfReductionPolynomial());
            return new co.org.bouncy.math.ec.ECCurve.F2m(m, ks[0], ks[1], ks[2], a, b);
        }
    }

    public static ECParameterSpec convertSpec(EllipticCurve ellipticCurve, co.org.bouncy.jce.spec.ECParameterSpec spec)
    {
        if(spec instanceof ECNamedCurveParameterSpec)
            return new ECNamedCurveSpec(((ECNamedCurveParameterSpec)spec).getName(), ellipticCurve, new ECPoint(spec.getG().getX().toBigInteger(), spec.getG().getY().toBigInteger()), spec.getN(), spec.getH());
        else
            return new ECParameterSpec(ellipticCurve, new ECPoint(spec.getG().getX().toBigInteger(), spec.getG().getY().toBigInteger()), spec.getN(), spec.getH().intValue());
    }

    public static co.org.bouncy.jce.spec.ECParameterSpec convertSpec(ECParameterSpec ecSpec, boolean withCompression)
    {
        ECCurve curve = convertCurve(ecSpec.getCurve());
        return new co.org.bouncy.jce.spec.ECParameterSpec(curve, convertPoint(curve, ecSpec.getGenerator(), withCompression), ecSpec.getOrder(), BigInteger.valueOf(ecSpec.getCofactor()), ecSpec.getCurve().getSeed());
    }

    public static co.org.bouncy.math.ec.ECPoint convertPoint(ECParameterSpec ecSpec, ECPoint point, boolean withCompression)
    {
        return convertPoint(convertCurve(ecSpec.getCurve()), point, withCompression);
    }

    public static co.org.bouncy.math.ec.ECPoint convertPoint(ECCurve curve, ECPoint point, boolean withCompression)
    {
        return curve.createPoint(point.getAffineX(), point.getAffineY(), withCompression);
    }
}
