// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyUsage.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            Extension, Extensions

public class KeyUsage extends ASN1Object
{

    public static KeyUsage getInstance(Object obj)
    {
        if(obj instanceof KeyUsage)
            return (KeyUsage)obj;
        if(obj != null)
            return new KeyUsage(DERBitString.getInstance(obj));
        else
            return null;
    }

    public static KeyUsage fromExtensions(Extensions extensions)
    {
        return getInstance(extensions.getExtensionParsedValue(Extension.keyUsage));
    }

    public KeyUsage(int usage)
    {
        bitString = new DERBitString(usage);
    }

    private KeyUsage(DERBitString bitString)
    {
        this.bitString = bitString;
    }

    public byte[] getBytes()
    {
        return bitString.getBytes();
    }

    public int getPadBits()
    {
        return bitString.getPadBits();
    }

    public String toString()
    {
        byte data[] = bitString.getBytes();
        if(data.length == 1)
            return (new StringBuilder()).append("KeyUsage: 0x").append(Integer.toHexString(data[0] & 0xff)).toString();
        else
            return (new StringBuilder()).append("KeyUsage: 0x").append(Integer.toHexString((data[1] & 0xff) << 8 | data[0] & 0xff)).toString();
    }

    public ASN1Primitive toASN1Primitive()
    {
        return bitString;
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
    private DERBitString bitString;
}
