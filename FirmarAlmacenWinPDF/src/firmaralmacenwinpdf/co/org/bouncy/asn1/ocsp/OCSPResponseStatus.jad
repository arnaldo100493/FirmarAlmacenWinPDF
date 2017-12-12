// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OCSPResponseStatus.java

package co.org.bouncy.asn1.ocsp;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;

public class OCSPResponseStatus extends ASN1Object
{

    public OCSPResponseStatus(int value)
    {
        this(new ASN1Enumerated(value));
    }

    private OCSPResponseStatus(ASN1Enumerated value)
    {
        this.value = value;
    }

    public static OCSPResponseStatus getInstance(Object obj)
    {
        if(obj instanceof OCSPResponseStatus)
            return (OCSPResponseStatus)obj;
        if(obj != null)
            return new OCSPResponseStatus(ASN1Enumerated.getInstance(obj));
        else
            return null;
    }

    public BigInteger getValue()
    {
        return value.getValue();
    }

    public ASN1Primitive toASN1Primitive()
    {
        return value;
    }

    public static final int SUCCESSFUL = 0;
    public static final int MALFORMED_REQUEST = 1;
    public static final int INTERNAL_ERROR = 2;
    public static final int TRY_LATER = 3;
    public static final int SIG_REQUIRED = 5;
    public static final int UNAUTHORIZED = 6;
    private ASN1Enumerated value;
}
