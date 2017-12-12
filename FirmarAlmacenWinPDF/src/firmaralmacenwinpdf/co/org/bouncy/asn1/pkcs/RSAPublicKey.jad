// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RSAPublicKey.java

package co.org.bouncy.asn1.pkcs;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;
import java.util.Enumeration;

public class RSAPublicKey extends ASN1Object
{

    public static RSAPublicKey getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static RSAPublicKey getInstance(Object obj)
    {
        if(obj instanceof RSAPublicKey)
            return (RSAPublicKey)obj;
        if(obj != null)
            return new RSAPublicKey(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public RSAPublicKey(BigInteger modulus, BigInteger publicExponent)
    {
        this.modulus = modulus;
        this.publicExponent = publicExponent;
    }

    private RSAPublicKey(ASN1Sequence seq)
    {
        if(seq.size() != 2)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        } else
        {
            Enumeration e = seq.getObjects();
            modulus = ASN1Integer.getInstance(e.nextElement()).getPositiveValue();
            publicExponent = ASN1Integer.getInstance(e.nextElement()).getPositiveValue();
            return;
        }
    }

    public BigInteger getModulus()
    {
        return modulus;
    }

    public BigInteger getPublicExponent()
    {
        return publicExponent;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer(getModulus()));
        v.add(new ASN1Integer(getPublicExponent()));
        return new DERSequence(v);
    }

    private BigInteger modulus;
    private BigInteger publicExponent;
}
