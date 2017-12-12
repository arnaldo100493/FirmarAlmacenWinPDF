// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKIStatus.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;

public class PKIStatus extends ASN1Object
{

    private PKIStatus(int value)
    {
        this(new ASN1Integer(value));
    }

    private PKIStatus(ASN1Integer value)
    {
        this.value = value;
    }

    public static PKIStatus getInstance(Object o)
    {
        if(o instanceof PKIStatus)
            return (PKIStatus)o;
        if(o != null)
            return new PKIStatus(ASN1Integer.getInstance(o));
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

    public static final int GRANTED = 0;
    public static final int GRANTED_WITH_MODS = 1;
    public static final int REJECTION = 2;
    public static final int WAITING = 3;
    public static final int REVOCATION_WARNING = 4;
    public static final int REVOCATION_NOTIFICATION = 5;
    public static final int KEY_UPDATE_WARNING = 6;
    public static final PKIStatus granted = new PKIStatus(0);
    public static final PKIStatus grantedWithMods = new PKIStatus(1);
    public static final PKIStatus rejection = new PKIStatus(2);
    public static final PKIStatus waiting = new PKIStatus(3);
    public static final PKIStatus revocationWarning = new PKIStatus(4);
    public static final PKIStatus revocationNotification = new PKIStatus(5);
    public static final PKIStatus keyUpdateWaiting = new PKIStatus(6);
    private ASN1Integer value;

}
