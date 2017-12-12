// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TBSCertList.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            TBSCertList, Time, Extensions

public static class TBSCertList$CRLEntry extends ASN1Object
{

    public static TBSCertList$CRLEntry getInstance(Object o)
    {
        if(o instanceof TBSCertList$CRLEntry)
            return (TBSCertList$CRLEntry)o;
        if(o != null)
            return new TBSCertList$CRLEntry(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public ASN1Integer getUserCertificate()
    {
        return ASN1Integer.getInstance(seq.getObjectAt(0));
    }

    public Time getRevocationDate()
    {
        return Time.getInstance(seq.getObjectAt(1));
    }

    public Extensions getExtensions()
    {
        if(crlEntryExtensions == null && seq.size() == 3)
            crlEntryExtensions = Extensions.getInstance(seq.getObjectAt(2));
        return crlEntryExtensions;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return seq;
    }

    public boolean hasExtensions()
    {
        return seq.size() == 3;
    }

    ASN1Sequence seq;
    Extensions crlEntryExtensions;

    private TBSCertList$CRLEntry(ASN1Sequence seq)
    {
        if(seq.size() < 2 || seq.size() > 3)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        } else
        {
            this.seq = seq;
            return;
        }
    }
}
