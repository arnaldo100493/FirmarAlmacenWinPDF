// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CommitmentTypeIndication.java

package co.org.bouncy.asn1.esf;

import co.org.bouncy.asn1.*;

public class CommitmentTypeIndication extends ASN1Object
{

    private CommitmentTypeIndication(ASN1Sequence seq)
    {
        commitmentTypeId = (ASN1ObjectIdentifier)seq.getObjectAt(0);
        if(seq.size() > 1)
            commitmentTypeQualifier = (ASN1Sequence)seq.getObjectAt(1);
    }

    public CommitmentTypeIndication(ASN1ObjectIdentifier commitmentTypeId)
    {
        this.commitmentTypeId = commitmentTypeId;
    }

    public CommitmentTypeIndication(ASN1ObjectIdentifier commitmentTypeId, ASN1Sequence commitmentTypeQualifier)
    {
        this.commitmentTypeId = commitmentTypeId;
        this.commitmentTypeQualifier = commitmentTypeQualifier;
    }

    public static CommitmentTypeIndication getInstance(Object obj)
    {
        if(obj == null || (obj instanceof CommitmentTypeIndication))
            return (CommitmentTypeIndication)obj;
        else
            return new CommitmentTypeIndication(ASN1Sequence.getInstance(obj));
    }

    public ASN1ObjectIdentifier getCommitmentTypeId()
    {
        return commitmentTypeId;
    }

    public ASN1Sequence getCommitmentTypeQualifier()
    {
        return commitmentTypeQualifier;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(commitmentTypeId);
        if(commitmentTypeQualifier != null)
            v.add(commitmentTypeQualifier);
        return new DERSequence(v);
    }

    private ASN1ObjectIdentifier commitmentTypeId;
    private ASN1Sequence commitmentTypeQualifier;
}
