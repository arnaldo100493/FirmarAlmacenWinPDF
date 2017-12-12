// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DLOutputStream.java

package co.org.bouncy.asn1;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1OutputStream, ASN1Encodable, ASN1Primitive

public class DLOutputStream extends ASN1OutputStream
{

    public DLOutputStream(OutputStream os)
    {
        super(os);
    }

    public void writeObject(ASN1Encodable obj)
        throws IOException
    {
        if(obj != null)
            obj.toASN1Primitive().toDLObject().encode(this);
        else
            throw new IOException("null object detected");
    }
}
