// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ContentHints.java

package co.org.bouncy.asn1.ess;

import co.org.bouncy.asn1.*;

public class ContentHints extends ASN1Object
{

    public static ContentHints getInstance(Object o)
    {
        if(o instanceof ContentHints)
            return (ContentHints)o;
        if(o != null)
            return new ContentHints(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    private ContentHints(ASN1Sequence seq)
    {
        ASN1Encodable field = seq.getObjectAt(0);
        if(field.toASN1Primitive() instanceof DERUTF8String)
        {
            contentDescription = DERUTF8String.getInstance(field);
            contentType = ASN1ObjectIdentifier.getInstance(seq.getObjectAt(1));
        } else
        {
            contentType = ASN1ObjectIdentifier.getInstance(seq.getObjectAt(0));
        }
    }

    /**
     * @deprecated Method ContentHints is deprecated
     */

    public ContentHints(DERObjectIdentifier contentType)
    {
        this(new ASN1ObjectIdentifier(contentType.getId()));
    }

    /**
     * @deprecated Method ContentHints is deprecated
     */

    public ContentHints(DERObjectIdentifier contentType, DERUTF8String contentDescription)
    {
        this(new ASN1ObjectIdentifier(contentType.getId()), contentDescription);
    }

    public ContentHints(ASN1ObjectIdentifier contentType)
    {
        this.contentType = contentType;
        contentDescription = null;
    }

    public ContentHints(ASN1ObjectIdentifier contentType, DERUTF8String contentDescription)
    {
        this.contentType = contentType;
        this.contentDescription = contentDescription;
    }

    public ASN1ObjectIdentifier getContentType()
    {
        return contentType;
    }

    public DERUTF8String getContentDescription()
    {
        return contentDescription;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(contentDescription != null)
            v.add(contentDescription);
        v.add(contentType);
        return new DERSequence(v);
    }

    private DERUTF8String contentDescription;
    private ASN1ObjectIdentifier contentType;
}
