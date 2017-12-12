// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SPuri.java

package co.org.bouncy.asn1.esf;

import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.DERIA5String;

public class SPuri
{

    public static SPuri getInstance(Object obj)
    {
        if(obj instanceof SPuri)
            return (SPuri)obj;
        if(obj instanceof DERIA5String)
            return new SPuri(DERIA5String.getInstance(obj));
        else
            return null;
    }

    public SPuri(DERIA5String uri)
    {
        this.uri = uri;
    }

    public DERIA5String getUri()
    {
        return uri;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return uri.toASN1Primitive();
    }

    private DERIA5String uri;
}
