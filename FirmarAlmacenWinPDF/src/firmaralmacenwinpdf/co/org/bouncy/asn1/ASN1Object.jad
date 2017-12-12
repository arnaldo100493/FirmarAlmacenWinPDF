// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ASN1Object.java

package co.org.bouncy.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1OutputStream, DEROutputStream, DLOutputStream, ASN1Encodable, 
//            ASN1Primitive

public abstract class ASN1Object
    implements ASN1Encodable
{

    public ASN1Object()
    {
    }

    public byte[] getEncoded()
        throws IOException
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        ASN1OutputStream aOut = new ASN1OutputStream(bOut);
        aOut.writeObject(this);
        return bOut.toByteArray();
    }

    public byte[] getEncoded(String encoding)
        throws IOException
    {
        if(encoding.equals("DER"))
        {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            DEROutputStream dOut = new DEROutputStream(bOut);
            dOut.writeObject(this);
            return bOut.toByteArray();
        }
        if(encoding.equals("DL"))
        {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            DLOutputStream dOut = new DLOutputStream(bOut);
            dOut.writeObject(this);
            return bOut.toByteArray();
        } else
        {
            return getEncoded();
        }
    }

    public int hashCode()
    {
        return toASN1Primitive().hashCode();
    }

    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if(!(o instanceof ASN1Encodable))
        {
            return false;
        } else
        {
            ASN1Encodable other = (ASN1Encodable)o;
            return toASN1Primitive().equals(other.toASN1Primitive());
        }
    }

    /**
     * @deprecated Method toASN1Object is deprecated
     */

    public ASN1Primitive toASN1Object()
    {
        return toASN1Primitive();
    }

    protected static boolean hasEncodedTagValue(Object obj, int tagValue)
    {
        return (obj instanceof byte[]) && ((byte[])(byte[])obj)[0] == tagValue;
    }

    public abstract ASN1Primitive toASN1Primitive();
}
