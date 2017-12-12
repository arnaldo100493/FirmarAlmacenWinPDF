// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ContentInfo.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.cms:
//            CMSObjectIdentifiers

public class ContentInfo extends ASN1Object
    implements CMSObjectIdentifiers
{

    public static ContentInfo getInstance(Object obj)
    {
        if(obj instanceof ContentInfo)
            return (ContentInfo)obj;
        if(obj != null)
            return new ContentInfo(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public static ContentInfo getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    /**
     * @deprecated Method ContentInfo is deprecated
     */

    public ContentInfo(ASN1Sequence seq)
    {
        if(seq.size() < 1 || seq.size() > 2)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        contentType = (ASN1ObjectIdentifier)seq.getObjectAt(0);
        if(seq.size() > 1)
        {
            ASN1TaggedObject tagged = (ASN1TaggedObject)seq.getObjectAt(1);
            if(!tagged.isExplicit() || tagged.getTagNo() != 0)
                throw new IllegalArgumentException("Bad tag for 'content'");
            content = tagged.getObject();
        }
    }

    public ContentInfo(ASN1ObjectIdentifier contentType, ASN1Encodable content)
    {
        this.contentType = contentType;
        this.content = content;
    }

    public ASN1ObjectIdentifier getContentType()
    {
        return contentType;
    }

    public ASN1Encodable getContent()
    {
        return content;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(contentType);
        if(content != null)
            v.add(new BERTaggedObject(0, content));
        return new BERSequence(v);
    }

    private ASN1ObjectIdentifier contentType;
    private ASN1Encodable content;
}
