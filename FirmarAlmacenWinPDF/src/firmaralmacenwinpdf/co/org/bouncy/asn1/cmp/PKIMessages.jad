// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKIMessages.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.cmp:
//            PKIMessage

public class PKIMessages extends ASN1Object
{

    private PKIMessages(ASN1Sequence seq)
    {
        content = seq;
    }

    public static PKIMessages getInstance(Object o)
    {
        if(o instanceof PKIMessages)
            return (PKIMessages)o;
        if(o != null)
            return new PKIMessages(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public PKIMessages(PKIMessage msg)
    {
        content = new DERSequence(msg);
    }

    public PKIMessages(PKIMessage msgs[])
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        for(int i = 0; i < msgs.length; i++)
            v.add(msgs[i]);

        content = new DERSequence(v);
    }

    public PKIMessage[] toPKIMessageArray()
    {
        PKIMessage result[] = new PKIMessage[content.size()];
        for(int i = 0; i != result.length; i++)
            result[i] = PKIMessage.getInstance(content.getObjectAt(i));

        return result;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return content;
    }

    private ASN1Sequence content;
}
