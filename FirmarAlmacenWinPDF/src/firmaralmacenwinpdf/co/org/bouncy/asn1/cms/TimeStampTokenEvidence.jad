// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TimeStampTokenEvidence.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.cms:
//            TimeStampAndCRL

public class TimeStampTokenEvidence extends ASN1Object
{

    public TimeStampTokenEvidence(TimeStampAndCRL timeStampAndCRLs[])
    {
        this.timeStampAndCRLs = timeStampAndCRLs;
    }

    public TimeStampTokenEvidence(TimeStampAndCRL timeStampAndCRL)
    {
        timeStampAndCRLs = new TimeStampAndCRL[1];
        timeStampAndCRLs[0] = timeStampAndCRL;
    }

    private TimeStampTokenEvidence(ASN1Sequence seq)
    {
        timeStampAndCRLs = new TimeStampAndCRL[seq.size()];
        int count = 0;
        for(Enumeration en = seq.getObjects(); en.hasMoreElements();)
            timeStampAndCRLs[count++] = TimeStampAndCRL.getInstance(en.nextElement());

    }

    public static TimeStampTokenEvidence getInstance(ASN1TaggedObject tagged, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(tagged, explicit));
    }

    public static TimeStampTokenEvidence getInstance(Object obj)
    {
        if(obj instanceof TimeStampTokenEvidence)
            return (TimeStampTokenEvidence)obj;
        if(obj != null)
            return new TimeStampTokenEvidence(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public TimeStampAndCRL[] toTimeStampAndCRLArray()
    {
        return timeStampAndCRLs;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        for(int i = 0; i != timeStampAndCRLs.length; i++)
            v.add(timeStampAndCRLs[i]);

        return new DERSequence(v);
    }

    private TimeStampAndCRL timeStampAndCRLs[];
}
