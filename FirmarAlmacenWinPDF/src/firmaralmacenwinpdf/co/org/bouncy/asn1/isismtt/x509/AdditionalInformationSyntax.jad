// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AdditionalInformationSyntax.java

package co.org.bouncy.asn1.isismtt.x509;

import co.org.bouncy.asn1.ASN1Object;
import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.x500.DirectoryString;

public class AdditionalInformationSyntax extends ASN1Object
{

    public static AdditionalInformationSyntax getInstance(Object obj)
    {
        if(obj instanceof AdditionalInformationSyntax)
            return (AdditionalInformationSyntax)obj;
        if(obj != null)
            return new AdditionalInformationSyntax(DirectoryString.getInstance(obj));
        else
            return null;
    }

    private AdditionalInformationSyntax(DirectoryString information)
    {
        this.information = information;
    }

    public AdditionalInformationSyntax(String information)
    {
        this(new DirectoryString(information));
    }

    public DirectoryString getInformation()
    {
        return information;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return information.toASN1Primitive();
    }

    private DirectoryString information;
}
