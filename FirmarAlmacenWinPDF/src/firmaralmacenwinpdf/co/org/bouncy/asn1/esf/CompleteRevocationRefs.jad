// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CompleteRevocationRefs.java

package co.org.bouncy.asn1.esf;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.esf:
//            CrlOcspRef

public class CompleteRevocationRefs extends ASN1Object
{

    public static CompleteRevocationRefs getInstance(Object obj)
    {
        if(obj instanceof CompleteRevocationRefs)
            return (CompleteRevocationRefs)obj;
        if(obj != null)
            return new CompleteRevocationRefs(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private CompleteRevocationRefs(ASN1Sequence seq)
    {
        for(Enumeration seqEnum = seq.getObjects(); seqEnum.hasMoreElements(); CrlOcspRef.getInstance(seqEnum.nextElement()));
        crlOcspRefs = seq;
    }

    public CompleteRevocationRefs(CrlOcspRef crlOcspRefs[])
    {
        this.crlOcspRefs = new DERSequence(crlOcspRefs);
    }

    public CrlOcspRef[] getCrlOcspRefs()
    {
        CrlOcspRef result[] = new CrlOcspRef[crlOcspRefs.size()];
        for(int idx = 0; idx < result.length; idx++)
            result[idx] = CrlOcspRef.getInstance(crlOcspRefs.getObjectAt(idx));

        return result;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return crlOcspRefs;
    }

    private ASN1Sequence crlOcspRefs;
}
