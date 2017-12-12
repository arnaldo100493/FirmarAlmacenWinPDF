// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BERFactory.java

package co.org.bouncy.asn1;


// Referenced classes of package co.org.bouncy.asn1:
//            BERSequence, BERSet, ASN1EncodableVector

class BERFactory
{

    BERFactory()
    {
    }

    static BERSequence createSequence(ASN1EncodableVector v)
    {
        return v.size() >= 1 ? new BERSequence(v) : EMPTY_SEQUENCE;
    }

    static BERSet createSet(ASN1EncodableVector v)
    {
        return v.size() >= 1 ? new BERSet(v) : EMPTY_SET;
    }

    static final BERSequence EMPTY_SEQUENCE = new BERSequence();
    static final BERSet EMPTY_SET = new BERSet();

}
