// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECNamedCurveParameterSpec.java

package co.org.bouncy.jce.spec;

import co.org.bouncy.math.ec.ECCurve;
import co.org.bouncy.math.ec.ECPoint;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.jce.spec:
//            ECParameterSpec

public class ECNamedCurveParameterSpec extends ECParameterSpec
{

    public ECNamedCurveParameterSpec(String name, ECCurve curve, ECPoint G, BigInteger n)
    {
        super(curve, G, n);
        this.name = name;
    }

    public ECNamedCurveParameterSpec(String name, ECCurve curve, ECPoint G, BigInteger n, BigInteger h)
    {
        super(curve, G, n, h);
        this.name = name;
    }

    public ECNamedCurveParameterSpec(String name, ECCurve curve, ECPoint G, BigInteger n, BigInteger h, byte seed[])
    {
        super(curve, G, n, h, seed);
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    private String name;
}
