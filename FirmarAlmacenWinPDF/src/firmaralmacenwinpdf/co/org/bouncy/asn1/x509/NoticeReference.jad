// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NoticeReference.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            DisplayText

public class NoticeReference extends ASN1Object
{

    private static ASN1EncodableVector convertVector(Vector numbers)
    {
        ASN1EncodableVector av = new ASN1EncodableVector();
        ASN1Integer di;
        for(Enumeration it = numbers.elements(); it.hasMoreElements(); av.add(di))
        {
            Object o = it.nextElement();
            if(o instanceof BigInteger)
            {
                di = new ASN1Integer((BigInteger)o);
                continue;
            }
            if(o instanceof Integer)
                di = new ASN1Integer(((Integer)o).intValue());
            else
                throw new IllegalArgumentException();
        }

        return av;
    }

    public NoticeReference(String organization, Vector numbers)
    {
        this(organization, convertVector(numbers));
    }

    public NoticeReference(String organization, ASN1EncodableVector noticeNumbers)
    {
        this(new DisplayText(organization), noticeNumbers);
    }

    public NoticeReference(DisplayText organization, ASN1EncodableVector noticeNumbers)
    {
        this.organization = organization;
        this.noticeNumbers = new DERSequence(noticeNumbers);
    }

    private NoticeReference(ASN1Sequence as)
    {
        if(as.size() != 2)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(as.size()).toString());
        } else
        {
            organization = DisplayText.getInstance(as.getObjectAt(0));
            noticeNumbers = ASN1Sequence.getInstance(as.getObjectAt(1));
            return;
        }
    }

    public static NoticeReference getInstance(Object as)
    {
        if(as instanceof NoticeReference)
            return (NoticeReference)as;
        if(as != null)
            return new NoticeReference(ASN1Sequence.getInstance(as));
        else
            return null;
    }

    public DisplayText getOrganization()
    {
        return organization;
    }

    public ASN1Integer[] getNoticeNumbers()
    {
        ASN1Integer tmp[] = new ASN1Integer[noticeNumbers.size()];
        for(int i = 0; i != noticeNumbers.size(); i++)
            tmp[i] = ASN1Integer.getInstance(noticeNumbers.getObjectAt(i));

        return tmp;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector av = new ASN1EncodableVector();
        av.add(organization);
        av.add(noticeNumbers);
        return new DERSequence(av);
    }

    private DisplayText organization;
    private ASN1Sequence noticeNumbers;
}
