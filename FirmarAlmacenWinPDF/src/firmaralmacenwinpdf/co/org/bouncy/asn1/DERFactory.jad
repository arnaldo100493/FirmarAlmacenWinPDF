// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DERFactory.java

package co.org.bouncy.asn1;


// Referenced classes of package co.org.bouncy.asn1:
//            DLSequence, DLSet, DERSequence, DERSet, 
//            ASN1Sequence, ASN1Set, ASN1EncodableVector

class DERFactory
{

    DERFactory()
    {
    }

    static ASN1Sequence createSequence(ASN1EncodableVector v)
    {
        return ((ASN1Sequence) (v.size() >= 1 ? new DLSequence(v) : EMPTY_SEQUENCE));
    }

    static ASN1Set createSet(ASN1EncodableVector v)
    {
        return ((ASN1Set) (v.size() >= 1 ? new DLSet(v) : EMPTY_SET));
    }

    static final ASN1Sequence EMPTY_SEQUENCE = new DERSequence();
    static final ASN1Set EMPTY_SET = new DERSet();

}
