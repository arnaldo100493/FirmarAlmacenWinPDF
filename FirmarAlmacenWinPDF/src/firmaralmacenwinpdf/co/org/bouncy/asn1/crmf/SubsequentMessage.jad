// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SubsequentMessage.java

package co.org.bouncy.asn1.crmf;

import co.org.bouncy.asn1.ASN1Integer;

public class SubsequentMessage extends ASN1Integer
{

    private SubsequentMessage(int value)
    {
        super(value);
    }

    public static SubsequentMessage valueOf(int value)
    {
        if(value == 0)
            return encrCert;
        if(value == 1)
            return challengeResp;
        else
            throw new IllegalArgumentException((new StringBuilder()).append("unknown value: ").append(value).toString());
    }

    public static final SubsequentMessage encrCert = new SubsequentMessage(0);
    public static final SubsequentMessage challengeResp = new SubsequentMessage(1);

}
