// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CAKeyUpdAnnContent.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.cmp:
//            CMPCertificate

public class CAKeyUpdAnnContent extends ASN1Object
{

    private CAKeyUpdAnnContent(ASN1Sequence seq)
    {
        oldWithNew = CMPCertificate.getInstance(seq.getObjectAt(0));
        newWithOld = CMPCertificate.getInstance(seq.getObjectAt(1));
        newWithNew = CMPCertificate.getInstance(seq.getObjectAt(2));
    }

    public static CAKeyUpdAnnContent getInstance(Object o)
    {
        if(o instanceof CAKeyUpdAnnContent)
            return (CAKeyUpdAnnContent)o;
        if(o != null)
            return new CAKeyUpdAnnContent(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public CAKeyUpdAnnContent(CMPCertificate oldWithNew, CMPCertificate newWithOld, CMPCertificate newWithNew)
    {
        this.oldWithNew = oldWithNew;
        this.newWithOld = newWithOld;
        this.newWithNew = newWithNew;
    }

    public CMPCertificate getOldWithNew()
    {
        return oldWithNew;
    }

    public CMPCertificate getNewWithOld()
    {
        return newWithOld;
    }

    public CMPCertificate getNewWithNew()
    {
        return newWithNew;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(oldWithNew);
        v.add(newWithOld);
        v.add(newWithNew);
        return new DERSequence(v);
    }

    private CMPCertificate oldWithNew;
    private CMPCertificate newWithOld;
    private CMPCertificate newWithNew;
}
