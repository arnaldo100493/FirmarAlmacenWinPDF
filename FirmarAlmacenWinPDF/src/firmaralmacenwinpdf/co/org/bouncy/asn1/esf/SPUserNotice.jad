// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SPUserNotice.java

package co.org.bouncy.asn1.esf;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.DisplayText;
import co.org.bouncy.asn1.x509.NoticeReference;
import java.util.Enumeration;

public class SPUserNotice extends ASN1Object
{

    public static SPUserNotice getInstance(Object obj)
    {
        if(obj instanceof SPUserNotice)
            return (SPUserNotice)obj;
        if(obj != null)
            return new SPUserNotice(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private SPUserNotice(ASN1Sequence seq)
    {
        for(Enumeration e = seq.getObjects(); e.hasMoreElements();)
        {
            ASN1Encodable object = (ASN1Encodable)e.nextElement();
            if((object instanceof DisplayText) || (object instanceof ASN1String))
                explicitText = DisplayText.getInstance(object);
            else
            if((object instanceof NoticeReference) || (object instanceof ASN1Sequence))
                noticeRef = NoticeReference.getInstance(object);
            else
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid element in 'SPUserNotice': ").append(object.getClass().getName()).toString());
        }

    }

    public SPUserNotice(NoticeReference noticeRef, DisplayText explicitText)
    {
        this.noticeRef = noticeRef;
        this.explicitText = explicitText;
    }

    public NoticeReference getNoticeRef()
    {
        return noticeRef;
    }

    public DisplayText getExplicitText()
    {
        return explicitText;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(noticeRef != null)
            v.add(noticeRef);
        if(explicitText != null)
            v.add(explicitText);
        return new DERSequence(v);
    }

    private NoticeReference noticeRef;
    private DisplayText explicitText;
}
