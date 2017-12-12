// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CommitmentTypeQualifier.java

package co.org.bouncy.asn1.esf;

import co.org.bouncy.asn1.*;

public class CommitmentTypeQualifier extends ASN1Object
{

    public CommitmentTypeQualifier(ASN1ObjectIdentifier commitmentTypeIdentifier)
    {
        this(commitmentTypeIdentifier, null);
    }

    public CommitmentTypeQualifier(ASN1ObjectIdentifier commitmentTypeIdentifier, ASN1Encodable qualifier)
    {
        this.commitmentTypeIdentifier = commitmentTypeIdentifier;
        this.qualifier = qualifier;
    }

    private CommitmentTypeQualifier(ASN1Sequence as)
    {
        commitmentTypeIdentifier = (ASN1ObjectIdentifier)as.getObjectAt(0);
        if(as.size() > 1)
            qualifier = as.getObjectAt(1);
    }

    public static CommitmentTypeQualifier getInstance(Object as)
    {
        if(as instanceof CommitmentTypeQualifier)
            return (CommitmentTypeQualifier)as;
        if(as != null)
            return new CommitmentTypeQualifier(ASN1Sequence.getInstance(as));
        else
            return null;
    }

    public ASN1ObjectIdentifier getCommitmentTypeIdentifier()
    {
        return commitmentTypeIdentifier;
    }

    public ASN1Encodable getQualifier()
    {
        return qualifier;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector dev = new ASN1EncodableVector();
        dev.add(commitmentTypeIdentifier);
        if(qualifier != null)
            dev.add(qualifier);
        return new DERSequence(dev);
    }

    private ASN1ObjectIdentifier commitmentTypeIdentifier;
    private ASN1Encodable qualifier;
}
