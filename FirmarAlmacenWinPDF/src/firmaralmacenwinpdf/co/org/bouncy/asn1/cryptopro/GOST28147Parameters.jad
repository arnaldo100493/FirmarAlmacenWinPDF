// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GOST28147Parameters.java

package co.org.bouncy.asn1.cryptopro;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;

public class GOST28147Parameters extends ASN1Object
{

    public static GOST28147Parameters getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static GOST28147Parameters getInstance(Object obj)
    {
        if(obj == null || (obj instanceof GOST28147Parameters))
            return (GOST28147Parameters)obj;
        if(obj instanceof ASN1Sequence)
            return new GOST28147Parameters((ASN1Sequence)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid GOST3410Parameter: ").append(obj.getClass().getName()).toString());
    }

    public GOST28147Parameters(ASN1Sequence seq)
    {
        Enumeration e = seq.getObjects();
        iv = (ASN1OctetString)e.nextElement();
        paramSet = (ASN1ObjectIdentifier)e.nextElement();
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(iv);
        v.add(paramSet);
        return new DERSequence(v);
    }

    ASN1OctetString iv;
    ASN1ObjectIdentifier paramSet;
}
