// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CrlOcspRef.java

package co.org.bouncy.asn1.esf;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.esf:
//            CrlListID, OcspListID, OtherRevRefs

public class CrlOcspRef extends ASN1Object
{

    public static CrlOcspRef getInstance(Object obj)
    {
        if(obj instanceof CrlOcspRef)
            return (CrlOcspRef)obj;
        if(obj != null)
            return new CrlOcspRef(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private CrlOcspRef(ASN1Sequence seq)
    {
        Enumeration e = seq.getObjects();
        do
        {
            if(!e.hasMoreElements())
                break;
            DERTaggedObject o = (DERTaggedObject)e.nextElement();
            switch(o.getTagNo())
            {
            case 0: // '\0'
                crlids = CrlListID.getInstance(o.getObject());
                break;

            case 1: // '\001'
                ocspids = OcspListID.getInstance(o.getObject());
                break;

            case 2: // '\002'
                otherRev = OtherRevRefs.getInstance(o.getObject());
                break;

            default:
                throw new IllegalArgumentException("illegal tag");
            }
        } while(true);
    }

    public CrlOcspRef(CrlListID crlids, OcspListID ocspids, OtherRevRefs otherRev)
    {
        this.crlids = crlids;
        this.ocspids = ocspids;
        this.otherRev = otherRev;
    }

    public CrlListID getCrlids()
    {
        return crlids;
    }

    public OcspListID getOcspids()
    {
        return ocspids;
    }

    public OtherRevRefs getOtherRev()
    {
        return otherRev;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(null != crlids)
            v.add(new DERTaggedObject(true, 0, crlids.toASN1Primitive()));
        if(null != ocspids)
            v.add(new DERTaggedObject(true, 1, ocspids.toASN1Primitive()));
        if(null != otherRev)
            v.add(new DERTaggedObject(true, 2, otherRev.toASN1Primitive()));
        return new DERSequence(v);
    }

    private CrlListID crlids;
    private OcspListID ocspids;
    private OtherRevRefs otherRev;
}
