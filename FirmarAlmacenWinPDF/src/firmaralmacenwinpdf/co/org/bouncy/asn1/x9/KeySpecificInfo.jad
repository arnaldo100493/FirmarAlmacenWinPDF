// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeySpecificInfo.java

package co.org.bouncy.asn1.x9;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;

public class KeySpecificInfo extends ASN1Object
{

    public KeySpecificInfo(ASN1ObjectIdentifier algorithm, ASN1OctetString counter)
    {
        this.algorithm = algorithm;
        this.counter = counter;
    }

    public KeySpecificInfo(ASN1Sequence seq)
    {
        Enumeration e = seq.getObjects();
        algorithm = (ASN1ObjectIdentifier)e.nextElement();
        counter = (ASN1OctetString)e.nextElement();
    }

    public ASN1ObjectIdentifier getAlgorithm()
    {
        return algorithm;
    }

    public ASN1OctetString getCounter()
    {
        return counter;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(algorithm);
        v.add(counter);
        return new DERSequence(v);
    }

    private ASN1ObjectIdentifier algorithm;
    private ASN1OctetString counter;
}
