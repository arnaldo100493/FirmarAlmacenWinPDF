// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DERUTF8String.java

package co.org.bouncy.asn1;

import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.Strings;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Primitive, ASN1String, ASN1TaggedObject, ASN1OctetString, 
//            StreamUtil, ASN1OutputStream

public class DERUTF8String extends ASN1Primitive
    implements ASN1String
{

    public static DERUTF8String getInstance(Object obj)
    {
        if(obj == null || (obj instanceof DERUTF8String))
            return (DERUTF8String)obj;
        if(obj instanceof byte[])
            try
            {
                return (DERUTF8String)fromByteArray((byte[])(byte[])obj);
            }
            catch(Exception e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("encoding error in getInstance: ").append(e.toString()).toString());
            }
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    public static DERUTF8String getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        ASN1Primitive o = obj.getObject();
        if(explicit || (o instanceof DERUTF8String))
            return getInstance(o);
        else
            return new DERUTF8String(ASN1OctetString.getInstance(o).getOctets());
    }

    DERUTF8String(byte string[])
    {
        this.string = string;
    }

    public DERUTF8String(String string)
    {
        this.string = Strings.toUTF8ByteArray(string);
    }

    public String getString()
    {
        return Strings.fromUTF8ByteArray(string);
    }

    public String toString()
    {
        return getString();
    }

    public int hashCode()
    {
        return Arrays.hashCode(string);
    }

    boolean asn1Equals(ASN1Primitive o)
    {
        if(!(o instanceof DERUTF8String))
        {
            return false;
        } else
        {
            DERUTF8String s = (DERUTF8String)o;
            return Arrays.areEqual(string, s.string);
        }
    }

    boolean isConstructed()
    {
        return false;
    }

    int encodedLength()
        throws IOException
    {
        return 1 + StreamUtil.calculateBodyLength(string.length) + string.length;
    }

    void encode(ASN1OutputStream out)
        throws IOException
    {
        out.writeEncoded(12, string);
    }

    private byte string[];
}
