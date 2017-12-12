// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DERNull.java

package co.org.bouncy.asn1;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Null, ASN1OutputStream

public class DERNull extends ASN1Null
{

    /**
     * @deprecated Method DERNull is deprecated
     */

    public DERNull()
    {
    }

    boolean isConstructed()
    {
        return false;
    }

    int encodedLength()
    {
        return 2;
    }

    void encode(ASN1OutputStream out)
        throws IOException
    {
        out.writeEncoded(5, zeroBytes);
    }

    public static final DERNull INSTANCE = new DERNull();
    private static final byte zeroBytes[] = new byte[0];

}
