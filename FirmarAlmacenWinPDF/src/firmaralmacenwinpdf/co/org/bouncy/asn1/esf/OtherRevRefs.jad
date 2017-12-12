// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OtherRevRefs.java

package co.org.bouncy.asn1.esf;

import co.org.bouncy.asn1.*;
import java.io.IOException;

public class OtherRevRefs extends ASN1Object
{

    public static OtherRevRefs getInstance(Object obj)
    {
        if(obj instanceof OtherRevRefs)
            return (OtherRevRefs)obj;
        if(obj != null)
            return new OtherRevRefs(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private OtherRevRefs(ASN1Sequence seq)
    {
        if(seq.size() != 2)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        otherRevRefType = new ASN1ObjectIdentifier(((ASN1ObjectIdentifier)seq.getObjectAt(0)).getId());
        try
        {
            otherRevRefs = ASN1Primitive.fromByteArray(seq.getObjectAt(1).toASN1Primitive().getEncoded("DER"));
        }
        catch(IOException e)
        {
            throw new IllegalStateException();
        }
    }

    public OtherRevRefs(ASN1ObjectIdentifier otherRevRefType, ASN1Encodable otherRevRefs)
    {
        this.otherRevRefType = otherRevRefType;
        this.otherRevRefs = otherRevRefs;
    }

    public ASN1ObjectIdentifier getOtherRevRefType()
    {
        return otherRevRefType;
    }

    public ASN1Encodable getOtherRevRefs()
    {
        return otherRevRefs;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(otherRevRefType);
        v.add(otherRevRefs);
        return new DERSequence(v);
    }

    private ASN1ObjectIdentifier otherRevRefType;
    private ASN1Encodable otherRevRefs;
}
