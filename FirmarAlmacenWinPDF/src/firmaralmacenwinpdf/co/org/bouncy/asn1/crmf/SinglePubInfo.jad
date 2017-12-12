// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SinglePubInfo.java

package co.org.bouncy.asn1.crmf;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.GeneralName;

public class SinglePubInfo extends ASN1Object
{

    private SinglePubInfo(ASN1Sequence seq)
    {
        pubMethod = ASN1Integer.getInstance(seq.getObjectAt(0));
        if(seq.size() == 2)
            pubLocation = GeneralName.getInstance(seq.getObjectAt(1));
    }

    public static SinglePubInfo getInstance(Object o)
    {
        if(o instanceof SinglePubInfo)
            return (SinglePubInfo)o;
        if(o != null)
            return new SinglePubInfo(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public GeneralName getPubLocation()
    {
        return pubLocation;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(pubMethod);
        if(pubLocation != null)
            v.add(pubLocation);
        return new DERSequence(v);
    }

    private ASN1Integer pubMethod;
    private GeneralName pubLocation;
}
