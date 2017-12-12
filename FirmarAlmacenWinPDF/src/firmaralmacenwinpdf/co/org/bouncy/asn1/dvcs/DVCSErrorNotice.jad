// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DVCSErrorNotice.java

package co.org.bouncy.asn1.dvcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cmp.PKIStatusInfo;
import co.org.bouncy.asn1.x509.GeneralName;

public class DVCSErrorNotice extends ASN1Object
{

    public DVCSErrorNotice(PKIStatusInfo status)
    {
        this(status, null);
    }

    public DVCSErrorNotice(PKIStatusInfo status, GeneralName transactionIdentifier)
    {
        transactionStatus = status;
        this.transactionIdentifier = transactionIdentifier;
    }

    private DVCSErrorNotice(ASN1Sequence seq)
    {
        transactionStatus = PKIStatusInfo.getInstance(seq.getObjectAt(0));
        if(seq.size() > 1)
            transactionIdentifier = GeneralName.getInstance(seq.getObjectAt(1));
    }

    public static DVCSErrorNotice getInstance(Object obj)
    {
        if(obj instanceof DVCSErrorNotice)
            return (DVCSErrorNotice)obj;
        if(obj != null)
            return new DVCSErrorNotice(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public static DVCSErrorNotice getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(transactionStatus);
        if(transactionIdentifier != null)
            v.add(transactionIdentifier);
        return new DERSequence(v);
    }

    public String toString()
    {
        return (new StringBuilder()).append("DVCSErrorNotice {\ntransactionStatus: ").append(transactionStatus).append("\n").append(transactionIdentifier == null ? "" : (new StringBuilder()).append("transactionIdentifier: ").append(transactionIdentifier).append("\n").toString()).append("}\n").toString();
    }

    public PKIStatusInfo getTransactionStatus()
    {
        return transactionStatus;
    }

    public GeneralName getTransactionIdentifier()
    {
        return transactionIdentifier;
    }

    private PKIStatusInfo transactionStatus;
    private GeneralName transactionIdentifier;
}
