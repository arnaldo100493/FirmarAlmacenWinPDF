// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertConfirmContent.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.cmp:
//            CertStatus

public class CertConfirmContent extends ASN1Object
{

    private CertConfirmContent(ASN1Sequence seq)
    {
        content = seq;
    }

    public static CertConfirmContent getInstance(Object o)
    {
        if(o instanceof CertConfirmContent)
            return (CertConfirmContent)o;
        if(o != null)
            return new CertConfirmContent(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public CertStatus[] toCertStatusArray()
    {
        CertStatus result[] = new CertStatus[content.size()];
        for(int i = 0; i != result.length; i++)
            result[i] = CertStatus.getInstance(content.getObjectAt(i));

        return result;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return content;
    }

    private ASN1Sequence content;
}
