// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509KeyUsage.java

package co.org.bouncy.jce;

import co.org.bouncy.asn1.ASN1Object;
import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.x509.KeyUsage;

public class X509KeyUsage extends ASN1Object
{

    public X509KeyUsage(int usage)
    {
        this.usage = 0;
        this.usage = usage;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return (new KeyUsage(usage)).toASN1Primitive();
    }

    public static final int digitalSignature = 128;
    public static final int nonRepudiation = 64;
    public static final int keyEncipherment = 32;
    public static final int dataEncipherment = 16;
    public static final int keyAgreement = 8;
    public static final int keyCertSign = 4;
    public static final int cRLSign = 2;
    public static final int encipherOnly = 1;
    public static final int decipherOnly = 32768;
    private int usage;
}
