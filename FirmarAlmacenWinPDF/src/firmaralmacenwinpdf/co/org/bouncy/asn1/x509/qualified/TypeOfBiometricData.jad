// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TypeOfBiometricData.java

package co.org.bouncy.asn1.x509.qualified;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;

public class TypeOfBiometricData extends ASN1Object
    implements ASN1Choice
{

    public static TypeOfBiometricData getInstance(Object obj)
    {
        if(obj == null || (obj instanceof TypeOfBiometricData))
            return (TypeOfBiometricData)obj;
        if(obj instanceof ASN1Integer)
        {
            ASN1Integer predefinedBiometricTypeObj = ASN1Integer.getInstance(obj);
            int predefinedBiometricType = predefinedBiometricTypeObj.getValue().intValue();
            return new TypeOfBiometricData(predefinedBiometricType);
        }
        if(obj instanceof ASN1ObjectIdentifier)
        {
            ASN1ObjectIdentifier BiometricDataID = ASN1ObjectIdentifier.getInstance(obj);
            return new TypeOfBiometricData(BiometricDataID);
        } else
        {
            throw new IllegalArgumentException("unknown object in getInstance");
        }
    }

    public TypeOfBiometricData(int predefinedBiometricType)
    {
        if(predefinedBiometricType == 0 || predefinedBiometricType == 1)
            obj = new ASN1Integer(predefinedBiometricType);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("unknow PredefinedBiometricType : ").append(predefinedBiometricType).toString());
    }

    public TypeOfBiometricData(ASN1ObjectIdentifier BiometricDataID)
    {
        obj = BiometricDataID;
    }

    public boolean isPredefined()
    {
        return obj instanceof ASN1Integer;
    }

    public int getPredefinedBiometricType()
    {
        return ((ASN1Integer)obj).getValue().intValue();
    }

    public ASN1ObjectIdentifier getBiometricDataOid()
    {
        return (ASN1ObjectIdentifier)obj;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return obj.toASN1Primitive();
    }

    public static final int PICTURE = 0;
    public static final int HANDWRITTEN_SIGNATURE = 1;
    ASN1Encodable obj;
}
