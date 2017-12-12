// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IDEACBCPar.java

package co.org.bouncy.asn1.misc;

import co.org.bouncy.asn1.*;

public class IDEACBCPar extends ASN1Object
{

    public static IDEACBCPar getInstance(Object o)
    {
        if(o instanceof IDEACBCPar)
            return (IDEACBCPar)o;
        if(o != null)
            return new IDEACBCPar(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public IDEACBCPar(byte iv[])
    {
        this.iv = new DEROctetString(iv);
    }

    public IDEACBCPar(ASN1Sequence seq)
    {
        if(seq.size() == 1)
            iv = (ASN1OctetString)seq.getObjectAt(0);
        else
            iv = null;
    }

    public byte[] getIV()
    {
        if(iv != null)
            return iv.getOctets();
        else
            return null;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(iv != null)
            v.add(iv);
        return new DERSequence(v);
    }

    ASN1OctetString iv;
}
