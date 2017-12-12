// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RSAPrivateKeyStructure.java

package co.org.bouncy.asn1.pkcs;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;
import java.util.Enumeration;

/**
 * @deprecated Class RSAPrivateKeyStructure is deprecated
 */

public class RSAPrivateKeyStructure extends ASN1Object
{

    public static RSAPrivateKeyStructure getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static RSAPrivateKeyStructure getInstance(Object obj)
    {
        if(obj instanceof RSAPrivateKeyStructure)
            return (RSAPrivateKeyStructure)obj;
        if(obj instanceof ASN1Sequence)
            return new RSAPrivateKeyStructure((ASN1Sequence)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("unknown object in factory: ").append(obj.getClass().getName()).toString());
    }

    public RSAPrivateKeyStructure(BigInteger modulus, BigInteger publicExponent, BigInteger privateExponent, BigInteger prime1, BigInteger prime2, BigInteger exponent1, BigInteger exponent2, 
            BigInteger coefficient)
    {
        otherPrimeInfos = null;
        version = 0;
        this.modulus = modulus;
        this.publicExponent = publicExponent;
        this.privateExponent = privateExponent;
        this.prime1 = prime1;
        this.prime2 = prime2;
        this.exponent1 = exponent1;
        this.exponent2 = exponent2;
        this.coefficient = coefficient;
    }

    public RSAPrivateKeyStructure(ASN1Sequence seq)
    {
        otherPrimeInfos = null;
        Enumeration e = seq.getObjects();
        BigInteger v = ((ASN1Integer)e.nextElement()).getValue();
        if(v.intValue() != 0 && v.intValue() != 1)
            throw new IllegalArgumentException("wrong version for RSA private key");
        version = v.intValue();
        modulus = ((ASN1Integer)e.nextElement()).getValue();
        publicExponent = ((ASN1Integer)e.nextElement()).getValue();
        privateExponent = ((ASN1Integer)e.nextElement()).getValue();
        prime1 = ((ASN1Integer)e.nextElement()).getValue();
        prime2 = ((ASN1Integer)e.nextElement()).getValue();
        exponent1 = ((ASN1Integer)e.nextElement()).getValue();
        exponent2 = ((ASN1Integer)e.nextElement()).getValue();
        coefficient = ((ASN1Integer)e.nextElement()).getValue();
        if(e.hasMoreElements())
            otherPrimeInfos = (ASN1Sequence)e.nextElement();
    }

    public int getVersion()
    {
        return version;
    }

    public BigInteger getModulus()
    {
        return modulus;
    }

    public BigInteger getPublicExponent()
    {
        return publicExponent;
    }

    public BigInteger getPrivateExponent()
    {
        return privateExponent;
    }

    public BigInteger getPrime1()
    {
        return prime1;
    }

    public BigInteger getPrime2()
    {
        return prime2;
    }

    public BigInteger getExponent1()
    {
        return exponent1;
    }

    public BigInteger getExponent2()
    {
        return exponent2;
    }

    public BigInteger getCoefficient()
    {
        return coefficient;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer(version));
        v.add(new ASN1Integer(getModulus()));
        v.add(new ASN1Integer(getPublicExponent()));
        v.add(new ASN1Integer(getPrivateExponent()));
        v.add(new ASN1Integer(getPrime1()));
        v.add(new ASN1Integer(getPrime2()));
        v.add(new ASN1Integer(getExponent1()));
        v.add(new ASN1Integer(getExponent2()));
        v.add(new ASN1Integer(getCoefficient()));
        if(otherPrimeInfos != null)
            v.add(otherPrimeInfos);
        return new DERSequence(v);
    }

    private int version;
    private BigInteger modulus;
    private BigInteger publicExponent;
    private BigInteger privateExponent;
    private BigInteger prime1;
    private BigInteger prime2;
    private BigInteger exponent1;
    private BigInteger exponent2;
    private BigInteger coefficient;
    private ASN1Sequence otherPrimeInfos;
}
