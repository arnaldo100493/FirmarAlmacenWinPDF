// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DEROutputStream.java

package co.org.bouncy.asn1;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1OutputStream, ASN1Encodable, ASN1Primitive

public class DEROutputStream extends ASN1OutputStream
{

    public DEROutputStream(OutputStream os)
    {
        super(os);
    }

    public void writeObject(ASN1Encodable obj)
        throws IOException
    {
        if(obj != null)
            obj.toASN1Primitive().toDERObject().encode(this);
        else
            throw new IOException("null object detected");
    }

    ASN1OutputStream getDERSubStream()
    {
        return this;
    }

    ASN1OutputStream getDLSubStream()
    {
        return this;
    }
}
