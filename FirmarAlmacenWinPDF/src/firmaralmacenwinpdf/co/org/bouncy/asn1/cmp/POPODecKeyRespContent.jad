// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   POPODecKeyRespContent.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;

public class POPODecKeyRespContent extends ASN1Object
{

    private POPODecKeyRespContent(ASN1Sequence seq)
    {
        content = seq;
    }

    public static POPODecKeyRespContent getInstance(Object o)
    {
        if(o instanceof POPODecKeyRespContent)
            return (POPODecKeyRespContent)o;
        if(o != null)
            return new POPODecKeyRespContent(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public ASN1Integer[] toASN1IntegerArray()
    {
        ASN1Integer result[] = new ASN1Integer[content.size()];
        for(int i = 0; i != result.length; i++)
            result[i] = ASN1Integer.getInstance(content.getObjectAt(i));

        return result;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return content;
    }

    private ASN1Sequence content;
}
