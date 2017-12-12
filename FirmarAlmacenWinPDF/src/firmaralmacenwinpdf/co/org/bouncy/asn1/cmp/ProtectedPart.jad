// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProtectedPart.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.cmp:
//            PKIHeader, PKIBody

public class ProtectedPart extends ASN1Object
{

    private ProtectedPart(ASN1Sequence seq)
    {
        header = PKIHeader.getInstance(seq.getObjectAt(0));
        body = PKIBody.getInstance(seq.getObjectAt(1));
    }

    public static ProtectedPart getInstance(Object o)
    {
        if(o instanceof ProtectedPart)
            return (ProtectedPart)o;
        if(o != null)
            return new ProtectedPart(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public ProtectedPart(PKIHeader header, PKIBody body)
    {
        this.header = header;
        this.body = body;
    }

    public PKIHeader getHeader()
    {
        return header;
    }

    public PKIBody getBody()
    {
        return body;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(header);
        v.add(body);
        return new DERSequence(v);
    }

    private PKIHeader header;
    private PKIBody body;
}
