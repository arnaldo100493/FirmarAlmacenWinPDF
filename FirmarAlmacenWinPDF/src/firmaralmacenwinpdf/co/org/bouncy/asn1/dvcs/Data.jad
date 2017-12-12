// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Data.java

package co.org.bouncy.asn1.dvcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.DigestInfo;

// Referenced classes of package co.org.bouncy.asn1.dvcs:
//            TargetEtcChain

public class Data extends ASN1Object
    implements ASN1Choice
{

    public Data(byte messageBytes[])
    {
        message = new DEROctetString(messageBytes);
    }

    public Data(ASN1OctetString message)
    {
        this.message = message;
    }

    public Data(DigestInfo messageImprint)
    {
        this.messageImprint = messageImprint;
    }

    public Data(TargetEtcChain cert)
    {
        certs = new DERSequence(cert);
    }

    public Data(TargetEtcChain certs[])
    {
        this.certs = new DERSequence(certs);
    }

    private Data(ASN1Sequence certs)
    {
        this.certs = certs;
    }

    public static Data getInstance(Object obj)
    {
        if(obj instanceof Data)
            return (Data)obj;
        if(obj instanceof ASN1OctetString)
            return new Data((ASN1OctetString)obj);
        if(obj instanceof ASN1Sequence)
            return new Data(DigestInfo.getInstance(obj));
        if(obj instanceof ASN1TaggedObject)
            return new Data(ASN1Sequence.getInstance((ASN1TaggedObject)obj, false));
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown object submitted to getInstance: ").append(obj.getClass().getName()).toString());
    }

    public static Data getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(obj.getObject());
    }

    public ASN1Primitive toASN1Primitive()
    {
        if(message != null)
            return message.toASN1Primitive();
        if(messageImprint != null)
            return messageImprint.toASN1Primitive();
        else
            return new DERTaggedObject(false, 0, certs);
    }

    public String toString()
    {
        if(message != null)
            return (new StringBuilder()).append("Data {\n").append(message).append("}\n").toString();
        if(messageImprint != null)
            return (new StringBuilder()).append("Data {\n").append(messageImprint).append("}\n").toString();
        else
            return (new StringBuilder()).append("Data {\n").append(certs).append("}\n").toString();
    }

    public ASN1OctetString getMessage()
    {
        return message;
    }

    public DigestInfo getMessageImprint()
    {
        return messageImprint;
    }

    public TargetEtcChain[] getCerts()
    {
        if(certs == null)
            return null;
        TargetEtcChain tmp[] = new TargetEtcChain[certs.size()];
        for(int i = 0; i != tmp.length; i++)
            tmp[i] = TargetEtcChain.getInstance(certs.getObjectAt(i));

        return tmp;
    }

    private ASN1OctetString message;
    private DigestInfo messageImprint;
    private ASN1Sequence certs;
}
