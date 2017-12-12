// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ASN1Primitive.java

package co.org.bouncy.asn1;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Object, ASN1InputStream, ASN1Encodable, ASN1OutputStream

public abstract class ASN1Primitive extends ASN1Object
{

    ASN1Primitive()
    {
    }

    public static ASN1Primitive fromByteArray(byte data[])
        throws IOException
    {
        ASN1InputStream aIn = new ASN1InputStream(data);
        try
        {
            return aIn.readObject();
        }
        catch(ClassCastException e)
        {
            throw new IOException("cannot recognise object in stream");
        }
    }

    public final boolean equals(Object o)
    {
        if(this == o)
            return true;
        else
            return (o instanceof ASN1Encodable) && asn1Equals(((ASN1Encodable)o).toASN1Primitive());
    }

    public ASN1Primitive toASN1Primitive()
    {
        return this;
    }

    ASN1Primitive toDERObject()
    {
        return this;
    }

    ASN1Primitive toDLObject()
    {
        return this;
    }

    public abstract int hashCode();

    abstract boolean isConstructed();

    abstract int encodedLength()
        throws IOException;

    abstract void encode(ASN1OutputStream asn1outputstream)
        throws IOException;

    abstract boolean asn1Equals(ASN1Primitive asn1primitive);
}
