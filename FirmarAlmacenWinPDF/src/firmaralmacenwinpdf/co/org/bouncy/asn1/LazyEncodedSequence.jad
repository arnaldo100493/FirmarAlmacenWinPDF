// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LazyEncodedSequence.java

package co.org.bouncy.asn1;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Sequence, LazyConstructionEnumeration, StreamUtil, ASN1Primitive, 
//            ASN1OutputStream, ASN1Encodable

class LazyEncodedSequence extends ASN1Sequence
{

    LazyEncodedSequence(byte encoded[])
        throws IOException
    {
        this.encoded = encoded;
    }

    private void parse()
    {
        for(Enumeration en = new LazyConstructionEnumeration(encoded); en.hasMoreElements(); seq.addElement(en.nextElement()));
        encoded = null;
    }

    public synchronized ASN1Encodable getObjectAt(int index)
    {
        if(encoded != null)
            parse();
        return super.getObjectAt(index);
    }

    public synchronized Enumeration getObjects()
    {
        if(encoded == null)
            return super.getObjects();
        else
            return new LazyConstructionEnumeration(encoded);
    }

    public synchronized int size()
    {
        if(encoded != null)
            parse();
        return super.size();
    }

    ASN1Primitive toDERObject()
    {
        if(encoded != null)
            parse();
        return super.toDERObject();
    }

    ASN1Primitive toDLObject()
    {
        if(encoded != null)
            parse();
        return super.toDLObject();
    }

    int encodedLength()
        throws IOException
    {
        if(encoded != null)
            return 1 + StreamUtil.calculateBodyLength(encoded.length) + encoded.length;
        else
            return super.toDLObject().encodedLength();
    }

    void encode(ASN1OutputStream out)
        throws IOException
    {
        if(encoded != null)
            out.writeEncoded(48, encoded);
        else
            super.toDLObject().encode(out);
    }

    private byte encoded[];
}
