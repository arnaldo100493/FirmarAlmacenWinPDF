// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSTU4145ECBinary.java

package co.org.bouncy.asn1.ua;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x9.X9IntegerConverter;
import co.org.bouncy.crypto.params.ECDomainParameters;
import co.org.bouncy.math.ec.ECCurve;
import co.org.bouncy.math.ec.ECFieldElement;
import co.org.bouncy.util.Arrays;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.asn1.ua:
//            DSTU4145BinaryField, DSTU4145PointEncoder

public class DSTU4145ECBinary extends ASN1Object
{

    public DSTU4145ECBinary(ECDomainParameters params)
    {
        version = BigInteger.valueOf(0L);
        if(!(params.getCurve() instanceof co.org.bouncy.math.ec.ECCurve.F2m))
        {
            throw new IllegalArgumentException("only binary domain is possible");
        } else
        {
            co.org.bouncy.math.ec.ECCurve.F2m curve = (co.org.bouncy.math.ec.ECCurve.F2m)params.getCurve();
            f = new DSTU4145BinaryField(curve.getM(), curve.getK1(), curve.getK2(), curve.getK3());
            a = new ASN1Integer(curve.getA().toBigInteger());
            X9IntegerConverter converter = new X9IntegerConverter();
            b = new DEROctetString(converter.integerToBytes(curve.getB().toBigInteger(), converter.getByteLength(curve)));
            n = new ASN1Integer(params.getN());
            bp = new DEROctetString(DSTU4145PointEncoder.encodePoint(params.getG()));
            return;
        }
    }

    private DSTU4145ECBinary(ASN1Sequence seq)
    {
        version = BigInteger.valueOf(0L);
        int index = 0;
        if(seq.getObjectAt(index) instanceof ASN1TaggedObject)
        {
            ASN1TaggedObject taggedVersion = (ASN1TaggedObject)seq.getObjectAt(index);
            if(taggedVersion.isExplicit() && 0 == taggedVersion.getTagNo())
            {
                version = ASN1Integer.getInstance(taggedVersion.getLoadedObject()).getValue();
                index++;
            } else
            {
                throw new IllegalArgumentException("object parse error");
            }
        }
        f = DSTU4145BinaryField.getInstance(seq.getObjectAt(index));
        index++;
        a = ASN1Integer.getInstance(seq.getObjectAt(index));
        index++;
        b = ASN1OctetString.getInstance(seq.getObjectAt(index));
        index++;
        n = ASN1Integer.getInstance(seq.getObjectAt(index));
        index++;
        bp = ASN1OctetString.getInstance(seq.getObjectAt(index));
    }

    public static DSTU4145ECBinary getInstance(Object obj)
    {
        if(obj instanceof DSTU4145ECBinary)
            return (DSTU4145ECBinary)obj;
        if(obj != null)
            return new DSTU4145ECBinary(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public DSTU4145BinaryField getField()
    {
        return f;
    }

    public BigInteger getA()
    {
        return a.getValue();
    }

    public byte[] getB()
    {
        return Arrays.clone(b.getOctets());
    }

    public BigInteger getN()
    {
        return n.getValue();
    }

    public byte[] getG()
    {
        return Arrays.clone(bp.getOctets());
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(0 != version.compareTo(BigInteger.valueOf(0L)))
            v.add(new DERTaggedObject(true, 0, new ASN1Integer(version)));
        v.add(f);
        v.add(a);
        v.add(b);
        v.add(n);
        v.add(bp);
        return new DERSequence(v);
    }

    BigInteger version;
    DSTU4145BinaryField f;
    ASN1Integer a;
    ASN1OctetString b;
    ASN1Integer n;
    ASN1OctetString bp;
}
