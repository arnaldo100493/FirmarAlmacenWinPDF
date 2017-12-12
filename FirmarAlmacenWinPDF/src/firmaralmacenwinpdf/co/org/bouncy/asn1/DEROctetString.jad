// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DEROctetString.java

package co.org.bouncy.asn1;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1OctetString, ASN1Encodable, ASN1Primitive, StreamUtil, 
//            ASN1OutputStream, DEROutputStream

public class DEROctetString extends ASN1OctetString
{

    public DEROctetString(byte string[])
    {
        super(string);
    }

    public DEROctetString(ASN1Encodable obj)
        throws IOException
    {
        super(obj.toASN1Primitive().getEncoded("DER"));
    }

    boolean isConstructed()
    {
        return false;
    }

    int encodedLength()
    {
        return 1 + StreamUtil.calculateBodyLength(string.length) + string.length;
    }

    void encode(ASN1OutputStream out)
        throws IOException
    {
        out.writeEncoded(4, string);
    }

    static void encode(DEROutputStream derOut, byte bytes[])
        throws IOException
    {
        derOut.writeEncoded(4, bytes);
    }
}
