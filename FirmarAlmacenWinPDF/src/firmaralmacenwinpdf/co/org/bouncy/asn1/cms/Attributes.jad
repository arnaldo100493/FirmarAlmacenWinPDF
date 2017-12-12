// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Attributes.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.cms:
//            Attribute

public class Attributes extends ASN1Object
{

    private Attributes(ASN1Set set)
    {
        attributes = set;
    }

    public Attributes(ASN1EncodableVector v)
    {
        attributes = new DLSet(v);
    }

    public static Attributes getInstance(Object obj)
    {
        if(obj instanceof Attributes)
            return (Attributes)obj;
        if(obj != null)
            return new Attributes(ASN1Set.getInstance(obj));
        else
            return null;
    }

    public Attribute[] getAttributes()
    {
        Attribute rv[] = new Attribute[attributes.size()];
        for(int i = 0; i != rv.length; i++)
            rv[i] = Attribute.getInstance(attributes.getObjectAt(i));

        return rv;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return attributes;
    }

    private ASN1Set attributes;
}
