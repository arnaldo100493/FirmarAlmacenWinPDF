// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BERSequence.java

package co.org.bouncy.asn1;

import java.io.IOException;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Sequence, ASN1Encodable, ASN1Primitive, ASN1OutputStream, 
//            ASN1EncodableVector

public class BERSequence extends ASN1Sequence
{

    public BERSequence()
    {
    }

    public BERSequence(ASN1Encodable obj)
    {
        super(obj);
    }

    public BERSequence(ASN1EncodableVector v)
    {
        super(v);
    }

    public BERSequence(ASN1Encodable array[])
    {
        super(array);
    }

    int encodedLength()
        throws IOException
    {
        int length = 0;
        for(Enumeration e = getObjects(); e.hasMoreElements();)
            length += ((ASN1Encodable)e.nextElement()).toASN1Primitive().encodedLength();

        return 2 + length + 2;
    }

    void encode(ASN1OutputStream out)
        throws IOException
    {
        out.write(48);
        out.write(128);
        for(Enumeration e = getObjects(); e.hasMoreElements(); out.writeObject((ASN1Encodable)e.nextElement()));
        out.write(0);
        out.write(0);
    }
}
