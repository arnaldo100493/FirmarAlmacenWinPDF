// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserNotice.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            DisplayText, NoticeReference

public class UserNotice extends ASN1Object
{

    public UserNotice(NoticeReference noticeRef, DisplayText explicitText)
    {
        this.noticeRef = noticeRef;
        this.explicitText = explicitText;
    }

    public UserNotice(NoticeReference noticeRef, String str)
    {
        this(noticeRef, new DisplayText(str));
    }

    private UserNotice(ASN1Sequence as)
    {
        if(as.size() == 2)
        {
            noticeRef = NoticeReference.getInstance(as.getObjectAt(0));
            explicitText = DisplayText.getInstance(as.getObjectAt(1));
        } else
        if(as.size() == 1)
        {
            if(as.getObjectAt(0).toASN1Primitive() instanceof ASN1Sequence)
                noticeRef = NoticeReference.getInstance(as.getObjectAt(0));
            else
                explicitText = DisplayText.getInstance(as.getObjectAt(0));
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(as.size()).toString());
        }
    }

    public static UserNotice getInstance(Object obj)
    {
        if(obj instanceof UserNotice)
            return (UserNotice)obj;
        if(obj != null)
            return new UserNotice(ASN1Sequence.getInstance(obj));
        else
            return null;
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
        ASN1EncodableVector av = new ASN1EncodableVector();
        if(noticeRef != null)
            av.add(noticeRef);
        if(explicitText != null)
            av.add(explicitText);
        return new DERSequence(av);
    }

    private NoticeReference noticeRef;
    private DisplayText explicitText;
}
