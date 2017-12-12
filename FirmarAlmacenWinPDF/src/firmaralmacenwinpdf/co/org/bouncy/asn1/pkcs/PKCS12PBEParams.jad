// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKCS12PBEParams.java

package co.org.bouncy.asn1.pkcs;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;

public class PKCS12PBEParams extends ASN1Object
{

    public PKCS12PBEParams(byte salt[], int iterations)
    {
        iv = new DEROctetString(salt);
        this.iterations = new ASN1Integer(iterations);
    }

    private PKCS12PBEParams(ASN1Sequence seq)
    {
        iv = (ASN1OctetString)seq.getObjectAt(0);
        iterations = ASN1Integer.getInstance(seq.getObjectAt(1));
    }

    public static PKCS12PBEParams getInstance(Object obj)
    {
        if(obj instanceof PKCS12PBEParams)
            return (PKCS12PBEParams)obj;
        if(obj != null)
            return new PKCS12PBEParams(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public BigInteger getIterations()
    {
        return iterations.getValue();
    }

    public byte[] getIV()
    {
        return iv.getOctets();
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(iv);
        v.add(iterations);
        return new DERSequence(v);
    }

    ASN1Integer iterations;
    ASN1OctetString iv;
}
