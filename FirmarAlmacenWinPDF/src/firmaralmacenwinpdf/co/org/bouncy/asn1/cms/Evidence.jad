// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Evidence.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.cms:
//            TimeStampTokenEvidence

public class Evidence extends ASN1Object
    implements ASN1Choice
{

    public Evidence(TimeStampTokenEvidence tstEvidence)
    {
        this.tstEvidence = tstEvidence;
    }

    private Evidence(ASN1TaggedObject tagged)
    {
        if(tagged.getTagNo() == 0)
            tstEvidence = TimeStampTokenEvidence.getInstance(tagged, false);
    }

    public static Evidence getInstance(Object obj)
    {
        if(obj == null || (obj instanceof Evidence))
            return (Evidence)obj;
        if(obj instanceof ASN1TaggedObject)
            return new Evidence(ASN1TaggedObject.getInstance(obj));
        else
            throw new IllegalArgumentException("unknown object in getInstance");
    }

    public TimeStampTokenEvidence getTstEvidence()
    {
        return tstEvidence;
    }

    public ASN1Primitive toASN1Primitive()
    {
        if(tstEvidence != null)
            return new DERTaggedObject(false, 0, tstEvidence);
        else
            return null;
    }

    private TimeStampTokenEvidence tstEvidence;
}
