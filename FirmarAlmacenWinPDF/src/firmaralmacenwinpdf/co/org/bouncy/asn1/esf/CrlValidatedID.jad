// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CrlValidatedID.java

package co.org.bouncy.asn1.esf;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.esf:
//            OtherHash, CrlIdentifier

public class CrlValidatedID extends ASN1Object
{

    public static CrlValidatedID getInstance(Object obj)
    {
        if(obj instanceof CrlValidatedID)
            return (CrlValidatedID)obj;
        if(obj != null)
            return new CrlValidatedID(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private CrlValidatedID(ASN1Sequence seq)
    {
        if(seq.size() < 1 || seq.size() > 2)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        crlHash = OtherHash.getInstance(seq.getObjectAt(0));
        if(seq.size() > 1)
            crlIdentifier = CrlIdentifier.getInstance(seq.getObjectAt(1));
    }

    public CrlValidatedID(OtherHash crlHash)
    {
        this(crlHash, null);
    }

    public CrlValidatedID(OtherHash crlHash, CrlIdentifier crlIdentifier)
    {
        this.crlHash = crlHash;
        this.crlIdentifier = crlIdentifier;
    }

    public OtherHash getCrlHash()
    {
        return crlHash;
    }

    public CrlIdentifier getCrlIdentifier()
    {
        return crlIdentifier;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(crlHash.toASN1Primitive());
        if(null != crlIdentifier)
            v.add(crlIdentifier.toASN1Primitive());
        return new DERSequence(v);
    }

    private OtherHash crlHash;
    private CrlIdentifier crlIdentifier;
}
