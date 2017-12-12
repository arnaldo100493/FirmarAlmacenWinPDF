// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CRLAnnContent.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.CertificateList;

public class CRLAnnContent extends ASN1Object
{

    private CRLAnnContent(ASN1Sequence seq)
    {
        content = seq;
    }

    public static CRLAnnContent getInstance(Object o)
    {
        if(o instanceof CRLAnnContent)
            return (CRLAnnContent)o;
        if(o != null)
            return new CRLAnnContent(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public CRLAnnContent(CertificateList crl)
    {
        content = new DERSequence(crl);
    }

    public CertificateList[] getCertificateLists()
    {
        CertificateList result[] = new CertificateList[content.size()];
        for(int i = 0; i != result.length; i++)
            result[i] = CertificateList.getInstance(content.getObjectAt(i));

        return result;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return content;
    }

    private ASN1Sequence content;
}
