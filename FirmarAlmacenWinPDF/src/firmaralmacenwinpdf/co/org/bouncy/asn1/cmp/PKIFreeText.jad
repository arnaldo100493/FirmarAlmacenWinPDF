// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKIFreeText.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;

public class PKIFreeText extends ASN1Object
{

    public static PKIFreeText getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static PKIFreeText getInstance(Object obj)
    {
        if(obj instanceof PKIFreeText)
            return (PKIFreeText)obj;
        if(obj != null)
            return new PKIFreeText(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private PKIFreeText(ASN1Sequence seq)
    {
        for(Enumeration e = seq.getObjects(); e.hasMoreElements();)
            if(!(e.nextElement() instanceof DERUTF8String))
                throw new IllegalArgumentException("attempt to insert non UTF8 STRING into PKIFreeText");

        strings = seq;
    }

    public PKIFreeText(DERUTF8String p)
    {
        strings = new DERSequence(p);
    }

    public PKIFreeText(String p)
    {
        this(new DERUTF8String(p));
    }

    public PKIFreeText(DERUTF8String strs[])
    {
        strings = new DERSequence(strs);
    }

    public PKIFreeText(String strs[])
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        for(int i = 0; i < strs.length; i++)
            v.add(new DERUTF8String(strs[i]));

        strings = new DERSequence(v);
    }

    public int size()
    {
        return strings.size();
    }

    public DERUTF8String getStringAt(int i)
    {
        return (DERUTF8String)strings.getObjectAt(i);
    }

    public ASN1Primitive toASN1Primitive()
    {
        return strings;
    }

    ASN1Sequence strings;
}
