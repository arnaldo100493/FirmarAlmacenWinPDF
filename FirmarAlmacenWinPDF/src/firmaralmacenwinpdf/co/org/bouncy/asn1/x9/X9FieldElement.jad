// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X9FieldElement.java

package co.org.bouncy.asn1.x9;

import co.org.bouncy.asn1.*;
import co.org.bouncy.math.ec.ECFieldElement;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.asn1.x9:
//            X9IntegerConverter

public class X9FieldElement extends ASN1Object
{

    public X9FieldElement(ECFieldElement f)
    {
        this.f = f;
    }

    public X9FieldElement(BigInteger p, ASN1OctetString s)
    {
        this(((ECFieldElement) (new co.org.bouncy.math.ec.ECFieldElement.Fp(p, new BigInteger(1, s.getOctets())))));
    }

    public X9FieldElement(int m, int k1, int k2, int k3, ASN1OctetString s)
    {
        this(((ECFieldElement) (new co.org.bouncy.math.ec.ECFieldElement.F2m(m, k1, k2, k3, new BigInteger(1, s.getOctets())))));
    }

    public ECFieldElement getValue()
    {
        return f;
    }

    public ASN1Primitive toASN1Primitive()
    {
        int byteCount = converter.getByteLength(f);
        byte paddedBigInteger[] = converter.integerToBytes(f.toBigInteger(), byteCount);
        return new DEROctetString(paddedBigInteger);
    }

    protected ECFieldElement f;
    private static X9IntegerConverter converter = new X9IntegerConverter();

}
