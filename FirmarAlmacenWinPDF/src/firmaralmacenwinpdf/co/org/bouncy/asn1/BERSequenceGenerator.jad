// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BERSequenceGenerator.java

package co.org.bouncy.asn1;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.asn1:
//            BERGenerator, BEROutputStream, ASN1Encodable, ASN1Primitive

public class BERSequenceGenerator extends BERGenerator
{

    public BERSequenceGenerator(OutputStream out)
        throws IOException
    {
        super(out);
        writeBERHeader(48);
    }

    public BERSequenceGenerator(OutputStream out, int tagNo, boolean isExplicit)
        throws IOException
    {
        super(out, tagNo, isExplicit);
        writeBERHeader(48);
    }

    public void addObject(ASN1Encodable object)
        throws IOException
    {
        object.toASN1Primitive().encode(new BEROutputStream(_out));
    }

    public void close()
        throws IOException
    {
        writeBEREnd();
    }
}
