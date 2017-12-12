// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DVCSRequest.java

package co.org.bouncy.asn1.dvcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.GeneralName;

// Referenced classes of package co.org.bouncy.asn1.dvcs:
//            DVCSRequestInformation, Data

public class DVCSRequest extends ASN1Object
{

    public DVCSRequest(DVCSRequestInformation requestInformation, Data data)
    {
        this(requestInformation, data, null);
    }

    public DVCSRequest(DVCSRequestInformation requestInformation, Data data, GeneralName transactionIdentifier)
    {
        this.requestInformation = requestInformation;
        this.data = data;
        this.transactionIdentifier = transactionIdentifier;
    }

    private DVCSRequest(ASN1Sequence seq)
    {
        requestInformation = DVCSRequestInformation.getInstance(seq.getObjectAt(0));
        data = Data.getInstance(seq.getObjectAt(1));
        if(seq.size() > 2)
            transactionIdentifier = GeneralName.getInstance(seq.getObjectAt(2));
    }

    public static DVCSRequest getInstance(Object obj)
    {
        if(obj instanceof DVCSRequest)
            return (DVCSRequest)obj;
        if(obj != null)
            return new DVCSRequest(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public static DVCSRequest getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(requestInformation);
        v.add(data);
        if(transactionIdentifier != null)
            v.add(transactionIdentifier);
        return new DERSequence(v);
    }

    public String toString()
    {
        return (new StringBuilder()).append("DVCSRequest {\nrequestInformation: ").append(requestInformation).append("\n").append("data: ").append(data).append("\n").append(transactionIdentifier == null ? "" : (new StringBuilder()).append("transactionIdentifier: ").append(transactionIdentifier).append("\n").toString()).append("}\n").toString();
    }

    public Data getData()
    {
        return data;
    }

    public DVCSRequestInformation getRequestInformation()
    {
        return requestInformation;
    }

    public GeneralName getTransactionIdentifier()
    {
        return transactionIdentifier;
    }

    private DVCSRequestInformation requestInformation;
    private Data data;
    private GeneralName transactionIdentifier;
}
