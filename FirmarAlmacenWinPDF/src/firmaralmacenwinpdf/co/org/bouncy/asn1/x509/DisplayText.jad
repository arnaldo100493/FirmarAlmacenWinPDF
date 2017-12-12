// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DisplayText.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

public class DisplayText extends ASN1Object
    implements ASN1Choice
{

    public DisplayText(int type, String text)
    {
        if(text.length() > 200)
            text = text.substring(0, 200);
        contentType = type;
        switch(type)
        {
        case 0: // '\0'
            contents = new DERIA5String(text);
            break;

        case 2: // '\002'
            contents = new DERUTF8String(text);
            break;

        case 3: // '\003'
            contents = new DERVisibleString(text);
            break;

        case 1: // '\001'
            contents = new DERBMPString(text);
            break;

        default:
            contents = new DERUTF8String(text);
            break;
        }
    }

    public DisplayText(String text)
    {
        if(text.length() > 200)
            text = text.substring(0, 200);
        contentType = 2;
        contents = new DERUTF8String(text);
    }

    private DisplayText(ASN1String de)
    {
        contents = de;
    }

    public static DisplayText getInstance(Object obj)
    {
        if(obj instanceof ASN1String)
            return new DisplayText((ASN1String)obj);
        if(obj == null || (obj instanceof DisplayText))
            return (DisplayText)obj;
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    public static DisplayText getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(obj.getObject());
    }

    public ASN1Primitive toASN1Primitive()
    {
        return (ASN1Primitive)contents;
    }

    public String getString()
    {
        return contents.getString();
    }

    public static final int CONTENT_TYPE_IA5STRING = 0;
    public static final int CONTENT_TYPE_BMPSTRING = 1;
    public static final int CONTENT_TYPE_UTF8STRING = 2;
    public static final int CONTENT_TYPE_VISIBLESTRING = 3;
    public static final int DISPLAY_TEXT_MAXIMUM_SIZE = 200;
    int contentType;
    ASN1String contents;
}
