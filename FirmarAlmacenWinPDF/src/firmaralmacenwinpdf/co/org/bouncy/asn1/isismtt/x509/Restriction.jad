// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Restriction.java

package co.org.bouncy.asn1.isismtt.x509;

import co.org.bouncy.asn1.ASN1Object;
import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.x500.DirectoryString;

public class Restriction extends ASN1Object
{

    public static Restriction getInstance(Object obj)
    {
        if(obj instanceof Restriction)
            return (Restriction)obj;
        if(obj != null)
            return new Restriction(DirectoryString.getInstance(obj));
        else
            return null;
    }

    private Restriction(DirectoryString restriction)
    {
        this.restriction = restriction;
    }

    public Restriction(String restriction)
    {
        this.restriction = new DirectoryString(restriction);
    }

    public DirectoryString getRestriction()
    {
        return restriction;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return restriction.toASN1Primitive();
    }

    private DirectoryString restriction;
}
