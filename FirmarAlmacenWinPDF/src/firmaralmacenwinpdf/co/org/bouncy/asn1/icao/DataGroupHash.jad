// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataGroupHash.java

package co.org.bouncy.asn1.icao;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;
import java.util.Enumeration;

public class DataGroupHash extends ASN1Object
{

    public static DataGroupHash getInstance(Object obj)
    {
        if(obj instanceof DataGroupHash)
            return (DataGroupHash)obj;
        if(obj != null)
            return new DataGroupHash(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private DataGroupHash(ASN1Sequence seq)
    {
        Enumeration e = seq.getObjects();
        dataGroupNumber = ASN1Integer.getInstance(e.nextElement());
        dataGroupHashValue = ASN1OctetString.getInstance(e.nextElement());
    }

    public DataGroupHash(int dataGroupNumber, ASN1OctetString dataGroupHashValue)
    {
        this.dataGroupNumber = new ASN1Integer(dataGroupNumber);
        this.dataGroupHashValue = dataGroupHashValue;
    }

    public int getDataGroupNumber()
    {
        return dataGroupNumber.getValue().intValue();
    }

    public ASN1OctetString getDataGroupHashValue()
    {
        return dataGroupHashValue;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector seq = new ASN1EncodableVector();
        seq.add(dataGroupNumber);
        seq.add(dataGroupHashValue);
        return new DERSequence(seq);
    }

    ASN1Integer dataGroupNumber;
    ASN1OctetString dataGroupHashValue;
}
