// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GenRepContent.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.cmp:
//            InfoTypeAndValue

public class GenRepContent extends ASN1Object
{

    private GenRepContent(ASN1Sequence seq)
    {
        content = seq;
    }

    public static GenRepContent getInstance(Object o)
    {
        if(o instanceof GenRepContent)
            return (GenRepContent)o;
        if(o != null)
            return new GenRepContent(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public GenRepContent(InfoTypeAndValue itv)
    {
        content = new DERSequence(itv);
    }

    public GenRepContent(InfoTypeAndValue itv[])
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        for(int i = 0; i < itv.length; i++)
            v.add(itv[i]);

        content = new DERSequence(v);
    }

    public InfoTypeAndValue[] toInfoTypeAndValueArray()
    {
        InfoTypeAndValue result[] = new InfoTypeAndValue[content.size()];
        for(int i = 0; i != result.length; i++)
            result[i] = InfoTypeAndValue.getInstance(content.getObjectAt(i));

        return result;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return content;
    }

    private ASN1Sequence content;
}
