// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OcspListID.java

package co.org.bouncy.asn1.esf;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.esf:
//            OcspResponsesID

public class OcspListID extends ASN1Object
{

    public static OcspListID getInstance(Object obj)
    {
        if(obj instanceof OcspListID)
            return (OcspListID)obj;
        if(obj != null)
            return new OcspListID(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private OcspListID(ASN1Sequence seq)
    {
        if(seq.size() != 1)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        ocspResponses = (ASN1Sequence)seq.getObjectAt(0);
        for(Enumeration e = ocspResponses.getObjects(); e.hasMoreElements(); OcspResponsesID.getInstance(e.nextElement()));
    }

    public OcspListID(OcspResponsesID ocspResponses[])
    {
        this.ocspResponses = new DERSequence(ocspResponses);
    }

    public OcspResponsesID[] getOcspResponses()
    {
        OcspResponsesID result[] = new OcspResponsesID[ocspResponses.size()];
        for(int idx = 0; idx < result.length; idx++)
            result[idx] = OcspResponsesID.getInstance(ocspResponses.getObjectAt(idx));

        return result;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return new DERSequence(ocspResponses);
    }

    private ASN1Sequence ocspResponses;
}
