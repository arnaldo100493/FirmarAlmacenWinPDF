// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CscaMasterList.java

package co.org.bouncy.asn1.icao;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.Certificate;
import java.math.BigInteger;

public class CscaMasterList extends ASN1Object
{

    public static CscaMasterList getInstance(Object obj)
    {
        if(obj instanceof CscaMasterList)
            return (CscaMasterList)obj;
        if(obj != null)
            return new CscaMasterList(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private CscaMasterList(ASN1Sequence seq)
    {
        version = new ASN1Integer(0L);
        if(seq == null || seq.size() == 0)
            throw new IllegalArgumentException("null or empty sequence passed.");
        if(seq.size() != 2)
            throw new IllegalArgumentException((new StringBuilder()).append("Incorrect sequence size: ").append(seq.size()).toString());
        version = ASN1Integer.getInstance(seq.getObjectAt(0));
        ASN1Set certSet = ASN1Set.getInstance(seq.getObjectAt(1));
        certList = new Certificate[certSet.size()];
        for(int i = 0; i < certList.length; i++)
            certList[i] = Certificate.getInstance(certSet.getObjectAt(i));

    }

    public CscaMasterList(Certificate certStructs[])
    {
        version = new ASN1Integer(0L);
        certList = copyCertList(certStructs);
    }

    public int getVersion()
    {
        return version.getValue().intValue();
    }

    public Certificate[] getCertStructs()
    {
        return copyCertList(certList);
    }

    private Certificate[] copyCertList(Certificate orig[])
    {
        Certificate certs[] = new Certificate[orig.length];
        for(int i = 0; i != certs.length; i++)
            certs[i] = orig[i];

        return certs;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector seq = new ASN1EncodableVector();
        seq.add(version);
        ASN1EncodableVector certSet = new ASN1EncodableVector();
        for(int i = 0; i < certList.length; i++)
            certSet.add(certList[i]);

        seq.add(new DERSet(certSet));
        return new DERSequence(seq);
    }

    private ASN1Integer version;
    private Certificate certList[];
}
