// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DirectoryString.java

package co.org.bouncy.asn1.x500;

import co.org.bouncy.asn1.*;

public class DirectoryString extends ASN1Object
    implements ASN1Choice, ASN1String
{

    public static DirectoryString getInstance(Object o)
    {
        if(o == null || (o instanceof DirectoryString))
            return (DirectoryString)o;
        if(o instanceof DERT61String)
            return new DirectoryString((DERT61String)o);
        if(o instanceof DERPrintableString)
            return new DirectoryString((DERPrintableString)o);
        if(o instanceof DERUniversalString)
            return new DirectoryString((DERUniversalString)o);
        if(o instanceof DERUTF8String)
            return new DirectoryString((DERUTF8String)o);
        if(o instanceof DERBMPString)
            return new DirectoryString((DERBMPString)o);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(o.getClass().getName()).toString());
    }

    public static DirectoryString getInstance(ASN1TaggedObject o, boolean explicit)
    {
        if(!explicit)
            throw new IllegalArgumentException("choice item must be explicitly tagged");
        else
            return getInstance(o.getObject());
    }

    private DirectoryString(DERT61String string)
    {
        this.string = string;
    }

    private DirectoryString(DERPrintableString string)
    {
        this.string = string;
    }

    private DirectoryString(DERUniversalString string)
    {
        this.string = string;
    }

    private DirectoryString(DERUTF8String string)
    {
        this.string = string;
    }

    private DirectoryString(DERBMPString string)
    {
        this.string = string;
    }

    public DirectoryString(String string)
    {
        this.string = new DERUTF8String(string);
    }

    public String getString()
    {
        return string.getString();
    }

    public String toString()
    {
        return string.getString();
    }

    public ASN1Primitive toASN1Primitive()
    {
        return ((ASN1Encodable)string).toASN1Primitive();
    }

    private ASN1String string;
}
