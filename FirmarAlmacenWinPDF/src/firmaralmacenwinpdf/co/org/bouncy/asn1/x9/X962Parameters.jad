// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X962Parameters.java

package co.org.bouncy.asn1.x9;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.x9:
//            X9ECParameters

public class X962Parameters extends ASN1Object
    implements ASN1Choice
{

    public static X962Parameters getInstance(Object obj)
    {
        if(obj == null || (obj instanceof X962Parameters))
            return (X962Parameters)obj;
        if(obj instanceof ASN1Primitive)
            return new X962Parameters((ASN1Primitive)obj);
        else
            throw new IllegalArgumentException("unknown object in getInstance()");
    }

    public static X962Parameters getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(obj.getObject());
    }

    public X962Parameters(X9ECParameters ecParameters)
    {
        params = null;
        params = ecParameters.toASN1Primitive();
    }

    public X962Parameters(ASN1ObjectIdentifier namedCurve)
    {
        params = null;
        params = namedCurve;
    }

    public X962Parameters(ASN1Primitive obj)
    {
        params = null;
        params = obj;
    }

    public boolean isNamedCurve()
    {
        return params instanceof ASN1ObjectIdentifier;
    }

    public boolean isImplicitlyCA()
    {
        return params instanceof ASN1Null;
    }

    public ASN1Primitive getParameters()
    {
        return params;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return params;
    }

    private ASN1Primitive params;
}
