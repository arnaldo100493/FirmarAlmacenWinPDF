// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X9ECParameters.java

package co.org.bouncy.asn1.x9;

import co.org.bouncy.asn1.*;
import co.org.bouncy.math.ec.ECCurve;
import co.org.bouncy.math.ec.ECPoint;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.asn1.x9:
//            X9Curve, X9FieldID, X9ECPoint, X9ObjectIdentifiers

public class X9ECParameters extends ASN1Object
    implements X9ObjectIdentifiers
{

    private X9ECParameters(ASN1Sequence seq)
    {
        if(!(seq.getObjectAt(0) instanceof ASN1Integer) || !((ASN1Integer)seq.getObjectAt(0)).getValue().equals(ONE))
            throw new IllegalArgumentException("bad version in X9ECParameters");
        X9Curve x9c = new X9Curve(new X9FieldID((ASN1Sequence)seq.getObjectAt(1)), (ASN1Sequence)seq.getObjectAt(2));
        curve = x9c.getCurve();
        g = (new X9ECPoint(curve, (ASN1OctetString)seq.getObjectAt(3))).getPoint();
        n = ((ASN1Integer)seq.getObjectAt(4)).getValue();
        seed = x9c.getSeed();
        if(seq.size() == 6)
            h = ((ASN1Integer)seq.getObjectAt(5)).getValue();
    }

    public static X9ECParameters getInstance(Object obj)
    {
        if(obj instanceof X9ECParameters)
            return (X9ECParameters)obj;
        if(obj != null)
            return new X9ECParameters(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public X9ECParameters(ECCurve curve, ECPoint g, BigInteger n)
    {
        this(curve, g, n, ONE, null);
    }

    public X9ECParameters(ECCurve curve, ECPoint g, BigInteger n, BigInteger h)
    {
        this(curve, g, n, h, null);
    }

    public X9ECParameters(ECCurve curve, ECPoint g, BigInteger n, BigInteger h, byte seed[])
    {
        this.curve = curve;
        this.g = g;
        this.n = n;
        this.h = h;
        this.seed = seed;
        if(curve instanceof co.org.bouncy.math.ec.ECCurve.Fp)
            fieldID = new X9FieldID(((co.org.bouncy.math.ec.ECCurve.Fp)curve).getQ());
        else
        if(curve instanceof co.org.bouncy.math.ec.ECCurve.F2m)
        {
            co.org.bouncy.math.ec.ECCurve.F2m curveF2m = (co.org.bouncy.math.ec.ECCurve.F2m)curve;
            fieldID = new X9FieldID(curveF2m.getM(), curveF2m.getK1(), curveF2m.getK2(), curveF2m.getK3());
        }
    }

    public ECCurve getCurve()
    {
        return curve;
    }

    public ECPoint getG()
    {
        return g;
    }

    public BigInteger getN()
    {
        return n;
    }

    public BigInteger getH()
    {
        if(h == null)
            return ONE;
        else
            return h;
    }

    public byte[] getSeed()
    {
        return seed;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer(1L));
        v.add(fieldID);
        v.add(new X9Curve(curve, seed));
        v.add(new X9ECPoint(g));
        v.add(new ASN1Integer(n));
        if(h != null)
            v.add(new ASN1Integer(h));
        return new DERSequence(v);
    }

    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private X9FieldID fieldID;
    private ECCurve curve;
    private ECPoint g;
    private BigInteger n;
    private BigInteger h;
    private byte seed[];

}
