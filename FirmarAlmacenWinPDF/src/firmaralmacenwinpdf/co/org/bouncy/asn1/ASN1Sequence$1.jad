// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ASN1Sequence.java

package co.org.bouncy.asn1;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Sequence, ASN1Set, ASN1SequenceParser, ASN1Encodable, 
//            ASN1Primitive

class ASN1Sequence$1
    implements ASN1SequenceParser
{

    public ASN1Encodable readObject()
        throws IOException
    {
        if(index == max)
            return null;
        ASN1Encodable obj = getObjectAt(index++);
        if(obj instanceof ASN1Sequence)
            return ((ASN1Sequence)obj).parser();
        if(obj instanceof ASN1Set)
            return ((ASN1Set)obj).parser();
        else
            return obj;
    }

    public ASN1Primitive getLoadedObject()
    {
        return val$outer;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return val$outer;
    }

    private final int max;
    private int index;
    final ASN1Sequence val$outer;
    final ASN1Sequence this$0;

    ASN1Sequence$1()
    {
        this$0 = final_asn1sequence;
        val$outer = ASN1Sequence.this;
        super();
        max = size();
    }
}
