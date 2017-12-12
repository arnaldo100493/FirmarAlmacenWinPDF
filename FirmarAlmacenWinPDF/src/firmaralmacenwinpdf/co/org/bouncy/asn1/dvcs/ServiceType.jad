// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServiceType.java

package co.org.bouncy.asn1.dvcs;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;

public class ServiceType extends ASN1Object
{

    public ServiceType(int value)
    {
        this.value = new ASN1Enumerated(value);
    }

    private ServiceType(ASN1Enumerated value)
    {
        this.value = value;
    }

    public static ServiceType getInstance(Object obj)
    {
        if(obj instanceof ServiceType)
            return (ServiceType)obj;
        if(obj != null)
            return new ServiceType(ASN1Enumerated.getInstance(obj));
        else
            return null;
    }

    public static ServiceType getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Enumerated.getInstance(obj, explicit));
    }

    public BigInteger getValue()
    {
        return value.getValue();
    }

    public ASN1Primitive toASN1Primitive()
    {
        return value;
    }

    public String toString()
    {
        int num = value.getValue().intValue();
        return (new StringBuilder()).append("").append(num).append(num != CPD.getValue().intValue() ? num != VSD.getValue().intValue() ? num != VPKC.getValue().intValue() ? num != CCPD.getValue().intValue() ? "?" : "(CCPD)" : "(VPKC)" : "(VSD)" : "(CPD)").toString();
    }

    public static final ServiceType CPD = new ServiceType(1);
    public static final ServiceType VSD = new ServiceType(2);
    public static final ServiceType VPKC = new ServiceType(3);
    public static final ServiceType CCPD = new ServiceType(4);
    private ASN1Enumerated value;

}
