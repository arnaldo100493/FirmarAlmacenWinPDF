// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ContentInfo.java

package co.org.bouncy.asn1.pkcs;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.pkcs:
//            PKCSObjectIdentifiers

public class ContentInfo extends ASN1Object
    implements PKCSObjectIdentifiers
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

    private ContentInfo(ASN1Sequence seq)
    {
        isBer = true;
        Enumeration e = seq.getObjects();
        contentType = (ASN1ObjectIdentifier)e.nextElement();
        if(e.hasMoreElements())
            content = ((ASN1TaggedObject)e.nextElement()).getObject();
        isBer = seq instanceof BERSequence;
    }

    public ContentInfo(ASN1ObjectIdentifier contentType, ASN1Encodable content)
    {
        isBer = true;
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
            v.add(new BERTaggedObject(true, 0, content));
        if(isBer)
            return new BERSequence(v);
        else
            return new DLSequence(v);
    }

    private ASN1ObjectIdentifier contentType;
    private ASN1Encodable content;
    private boolean isBer;
}
