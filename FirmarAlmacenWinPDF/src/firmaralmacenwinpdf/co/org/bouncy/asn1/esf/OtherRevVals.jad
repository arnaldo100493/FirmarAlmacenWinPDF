// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OtherRevVals.java

package co.org.bouncy.asn1.esf;

import co.org.bouncy.asn1.*;
import java.io.IOException;

public class OtherRevVals extends ASN1Object
{

    public static OtherRevVals getInstance(Object obj)
    {
        if(obj instanceof OtherRevVals)
            return (OtherRevVals)obj;
        if(obj != null)
            return new OtherRevVals(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private OtherRevVals(ASN1Sequence seq)
    {
        if(seq.size() != 2)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        otherRevValType = (ASN1ObjectIdentifier)seq.getObjectAt(0);
        try
        {
            otherRevVals = ASN1Primitive.fromByteArray(seq.getObjectAt(1).toASN1Primitive().getEncoded("DER"));
        }
        catch(IOException e)
        {
            throw new IllegalStateException();
        }
    }

    public OtherRevVals(ASN1ObjectIdentifier otherRevValType, ASN1Encodable otherRevVals)
    {
        this.otherRevValType = otherRevValType;
        this.otherRevVals = otherRevVals;
    }

    public ASN1ObjectIdentifier getOtherRevValType()
    {
        return otherRevValType;
    }

    public ASN1Encodable getOtherRevVals()
    {
        return otherRevVals;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(otherRevValType);
        v.add(otherRevVals);
        return new DERSequence(v);
    }

    private ASN1ObjectIdentifier otherRevValType;
    private ASN1Encodable otherRevVals;
}
