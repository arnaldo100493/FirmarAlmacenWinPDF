// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PrivateKeyUsagePeriod.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;

public class PrivateKeyUsagePeriod extends ASN1Object
{

    public static PrivateKeyUsagePeriod getInstance(Object obj)
    {
        if(obj instanceof PrivateKeyUsagePeriod)
            return (PrivateKeyUsagePeriod)obj;
        if(obj != null)
            return new PrivateKeyUsagePeriod(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private PrivateKeyUsagePeriod(ASN1Sequence seq)
    {
        Enumeration en = seq.getObjects();
        do
        {
            if(!en.hasMoreElements())
                break;
            ASN1TaggedObject tObj = (ASN1TaggedObject)en.nextElement();
            if(tObj.getTagNo() == 0)
                _notBefore = DERGeneralizedTime.getInstance(tObj, false);
            else
            if(tObj.getTagNo() == 1)
                _notAfter = DERGeneralizedTime.getInstance(tObj, false);
        } while(true);
    }

    public DERGeneralizedTime getNotBefore()
    {
        return _notBefore;
    }

    public DERGeneralizedTime getNotAfter()
    {
        return _notAfter;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(_notBefore != null)
            v.add(new DERTaggedObject(false, 0, _notBefore));
        if(_notAfter != null)
            v.add(new DERTaggedObject(false, 1, _notAfter));
        return new DERSequence(v);
    }

    private DERGeneralizedTime _notBefore;
    private DERGeneralizedTime _notAfter;
}
