// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TSTInfo.java

package co.org.bouncy.asn1.tsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.Extensions;
import co.org.bouncy.asn1.x509.GeneralName;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.tsp:
//            Accuracy, MessageImprint

public class TSTInfo extends ASN1Object
{

    public static TSTInfo getInstance(Object o)
    {
        if(o instanceof TSTInfo)
            return (TSTInfo)o;
        if(o != null)
            return new TSTInfo(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    private TSTInfo(ASN1Sequence seq)
    {
        Enumeration e = seq.getObjects();
        version = ASN1Integer.getInstance(e.nextElement());
        tsaPolicyId = ASN1ObjectIdentifier.getInstance(e.nextElement());
        messageImprint = MessageImprint.getInstance(e.nextElement());
        serialNumber = ASN1Integer.getInstance(e.nextElement());
        genTime = ASN1GeneralizedTime.getInstance(e.nextElement());
        ordering = ASN1Boolean.getInstance(false);
        do
        {
            if(!e.hasMoreElements())
                break;
            ASN1Object o = (ASN1Object)e.nextElement();
            if(o instanceof ASN1TaggedObject)
            {
                DERTaggedObject tagged = (DERTaggedObject)o;
                switch(tagged.getTagNo())
                {
                case 0: // '\0'
                    tsa = GeneralName.getInstance(tagged, true);
                    break;

                case 1: // '\001'
                    extensions = Extensions.getInstance(tagged, false);
                    break;

                default:
                    throw new IllegalArgumentException((new StringBuilder()).append("Unknown tag value ").append(tagged.getTagNo()).toString());
                }
            } else
            if((o instanceof ASN1Sequence) || (o instanceof Accuracy))
                accuracy = Accuracy.getInstance(o);
            else
            if(o instanceof ASN1Boolean)
                ordering = ASN1Boolean.getInstance(o);
            else
            if(o instanceof ASN1Integer)
                nonce = ASN1Integer.getInstance(o);
        } while(true);
    }

    public TSTInfo(ASN1ObjectIdentifier tsaPolicyId, MessageImprint messageImprint, ASN1Integer serialNumber, ASN1GeneralizedTime genTime, Accuracy accuracy, ASN1Boolean ordering, ASN1Integer nonce, 
            GeneralName tsa, Extensions extensions)
    {
        version = new ASN1Integer(1L);
        this.tsaPolicyId = tsaPolicyId;
        this.messageImprint = messageImprint;
        this.serialNumber = serialNumber;
        this.genTime = genTime;
        this.accuracy = accuracy;
        this.ordering = ordering;
        this.nonce = nonce;
        this.tsa = tsa;
        this.extensions = extensions;
    }

    public ASN1Integer getVersion()
    {
        return version;
    }

    public MessageImprint getMessageImprint()
    {
        return messageImprint;
    }

    public ASN1ObjectIdentifier getPolicy()
    {
        return tsaPolicyId;
    }

    public ASN1Integer getSerialNumber()
    {
        return serialNumber;
    }

    public Accuracy getAccuracy()
    {
        return accuracy;
    }

    public ASN1GeneralizedTime getGenTime()
    {
        return genTime;
    }

    public ASN1Boolean getOrdering()
    {
        return ordering;
    }

    public ASN1Integer getNonce()
    {
        return nonce;
    }

    public GeneralName getTsa()
    {
        return tsa;
    }

    public Extensions getExtensions()
    {
        return extensions;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector seq = new ASN1EncodableVector();
        seq.add(version);
        seq.add(tsaPolicyId);
        seq.add(messageImprint);
        seq.add(serialNumber);
        seq.add(genTime);
        if(accuracy != null)
            seq.add(accuracy);
        if(ordering != null && ordering.isTrue())
            seq.add(ordering);
        if(nonce != null)
            seq.add(nonce);
        if(tsa != null)
            seq.add(new DERTaggedObject(true, 0, tsa));
        if(extensions != null)
            seq.add(new DERTaggedObject(false, 1, extensions));
        return new DERSequence(seq);
    }

    private ASN1Integer version;
    private ASN1ObjectIdentifier tsaPolicyId;
    private MessageImprint messageImprint;
    private ASN1Integer serialNumber;
    private ASN1GeneralizedTime genTime;
    private Accuracy accuracy;
    private ASN1Boolean ordering;
    private ASN1Integer nonce;
    private GeneralName tsa;
    private Extensions extensions;
}
