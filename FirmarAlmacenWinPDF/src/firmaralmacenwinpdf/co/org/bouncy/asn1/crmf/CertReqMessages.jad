// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertReqMessages.java

package co.org.bouncy.asn1.crmf;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.crmf:
//            CertReqMsg

public class CertReqMessages extends ASN1Object
{

    private CertReqMessages(ASN1Sequence seq)
    {
        content = seq;
    }

    public static CertReqMessages getInstance(Object o)
    {
        if(o instanceof CertReqMessages)
            return (CertReqMessages)o;
        if(o != null)
            return new CertReqMessages(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public CertReqMessages(CertReqMsg msg)
    {
        content = new DERSequence(msg);
    }

    public CertReqMessages(CertReqMsg msgs[])
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        for(int i = 0; i < msgs.length; i++)
            v.add(msgs[i]);

        content = new DERSequence(v);
    }

    public CertReqMsg[] toCertReqMsgArray()
    {
        CertReqMsg result[] = new CertReqMsg[content.size()];
        for(int i = 0; i != result.length; i++)
            result[i] = CertReqMsg.getInstance(content.getObjectAt(i));

        return result;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return content;
    }

    private ASN1Sequence content;
}
