// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ASN1OctetString.java

package co.org.bouncy.asn1;

import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.encoders.Hex;
import java.io.*;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Primitive, ASN1Encodable, DEROctetString, ASN1OctetStringParser, 
//            ASN1TaggedObject, ASN1Sequence, BEROctetString, ASN1OutputStream

public abstract class ASN1OctetString extends ASN1Primitive
    implements ASN1OctetStringParser
{

    public static ASN1OctetString getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        ASN1Primitive o = obj.getObject();
        if(explicit || (o instanceof ASN1OctetString))
            return getInstance(o);
        else
            return BEROctetString.fromSequence(ASN1Sequence.getInstance(o));
    }

    public static ASN1OctetString getInstance(Object obj)
    {
        if(obj == null || (obj instanceof ASN1OctetString))
            return (ASN1OctetString)obj;
        if(obj instanceof byte[])
            try
            {
                return getInstance(ASN1Primitive.fromByteArray((byte[])(byte[])obj));
            }
            catch(IOException e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("failed to construct OCTET STRING from byte[]: ").append(e.getMessage()).toString());
            }
        if(obj instanceof ASN1Encodable)
        {
            ASN1Primitive primitive = ((ASN1Encodable)obj).toASN1Primitive();
            if(primitive instanceof ASN1OctetString)
                return (ASN1OctetString)primitive;
        }
        throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    public ASN1OctetString(byte string[])
    {
        if(string == null)
        {
            throw new NullPointerException("string cannot be null");
        } else
        {
            this.string = string;
            return;
        }
    }

    public InputStream getOctetStream()
    {
        return new ByteArrayInputStream(string);
    }

    public ASN1OctetStringParser parser()
    {
        return this;
    }

    public byte[] getOctets()
    {
        return string;
    }

    public int hashCode()
    {
        return Arrays.hashCode(getOctets());
    }

    boolean asn1Equals(ASN1Primitive o)
    {
        if(!(o instanceof ASN1OctetString))
        {
            return false;
        } else
        {
            ASN1OctetString other = (ASN1OctetString)o;
            return Arrays.areEqual(string, other.string);
        }
    }

    public ASN1Primitive getLoadedObject()
    {
        return toASN1Primitive();
    }

    ASN1Primitive toDERObject()
    {
        return new DEROctetString(string);
    }

    ASN1Primitive toDLObject()
    {
        return new DEROctetString(string);
    }

    abstract void encode(ASN1OutputStream asn1outputstream)
        throws IOException;

    public String toString()
    {
        return (new StringBuilder()).append("#").append(new String(Hex.encode(string))).toString();
    }

    byte string[];
}
