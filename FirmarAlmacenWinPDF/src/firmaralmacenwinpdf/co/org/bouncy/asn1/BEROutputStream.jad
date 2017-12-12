// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BEROutputStream.java

package co.org.bouncy.asn1;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.asn1:
//            DEROutputStream, ASN1Primitive, ASN1Encodable

public class BEROutputStream extends DEROutputStream
{

    public BEROutputStream(OutputStream os)
    {
        super(os);
    }

    public void writeObject(Object obj)
        throws IOException
    {
        if(obj == null)
            writeNull();
        else
        if(obj instanceof ASN1Primitive)
            ((ASN1Primitive)obj).encode(this);
        else
        if(obj instanceof ASN1Encodable)
            ((ASN1Encodable)obj).toASN1Primitive().encode(this);
        else
            throw new IOException("object not BEREncodable");
    }
}
