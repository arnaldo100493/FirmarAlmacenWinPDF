// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PBEParameter.java

package co.org.bouncy.asn1.pkcs;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;

public class PBEParameter extends ASN1Object
{

    public PBEParameter(byte salt[], int iterations)
    {
        if(salt.length != 8)
        {
            throw new IllegalArgumentException("salt length must be 8");
        } else
        {
            this.salt = new DEROctetString(salt);
            this.iterations = new ASN1Integer(iterations);
            return;
        }
    }

    private PBEParameter(ASN1Sequence seq)
    {
        salt = (ASN1OctetString)seq.getObjectAt(0);
        iterations = (ASN1Integer)seq.getObjectAt(1);
    }

    public static PBEParameter getInstance(Object obj)
    {
        if(obj instanceof PBEParameter)
            return (PBEParameter)obj;
        if(obj != null)
            return new PBEParameter(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public BigInteger getIterationCount()
    {
        return iterations.getValue();
    }

    public byte[] getSalt()
    {
        return salt.getOctets();
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(salt);
        v.add(iterations);
        return new DERSequence(v);
    }

    ASN1Integer iterations;
    ASN1OctetString salt;
}
