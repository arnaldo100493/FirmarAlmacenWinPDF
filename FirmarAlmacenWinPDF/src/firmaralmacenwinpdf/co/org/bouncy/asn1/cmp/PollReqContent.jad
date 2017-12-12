// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PollReqContent.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;

public class PollReqContent extends ASN1Object
{

    private PollReqContent(ASN1Sequence seq)
    {
        content = seq;
    }

    public static PollReqContent getInstance(Object o)
    {
        if(o instanceof PollReqContent)
            return (PollReqContent)o;
        if(o != null)
            return new PollReqContent(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public PollReqContent(ASN1Integer certReqId)
    {
        this(((ASN1Sequence) (new DERSequence(new DERSequence(certReqId)))));
    }

    public ASN1Integer[][] getCertReqIds()
    {
        ASN1Integer result[][] = new ASN1Integer[content.size()][];
        for(int i = 0; i != result.length; i++)
            result[i] = sequenceToASN1IntegerArray((ASN1Sequence)content.getObjectAt(i));

        return result;
    }

    private static ASN1Integer[] sequenceToASN1IntegerArray(ASN1Sequence seq)
    {
        ASN1Integer result[] = new ASN1Integer[seq.size()];
        for(int i = 0; i != result.length; i++)
            result[i] = ASN1Integer.getInstance(seq.getObjectAt(i));

        return result;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return content;
    }

    private ASN1Sequence content;
}
