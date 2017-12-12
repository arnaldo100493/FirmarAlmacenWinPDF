// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DERT61String.java

package co.org.bouncy.asn1;

import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.Strings;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Primitive, ASN1String, ASN1TaggedObject, ASN1OctetString, 
//            StreamUtil, ASN1OutputStream

public class DERT61String extends ASN1Primitive
    implements ASN1String
{

    public static DERT61String getInstance(Object obj)
    {
        if(obj == null || (obj instanceof DERT61String))
            return (DERT61String)obj;
        if(obj instanceof byte[])
            try
            {
                return (DERT61String)fromByteArray((byte[])(byte[])obj);
            }
            catch(Exception e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("encoding error in getInstance: ").append(e.toString()).toString());
            }
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    public static DERT61String getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        ASN1Primitive o = obj.getObject();
        if(explicit || (o instanceof DERT61String))
            return getInstance(o);
        else
            return new DERT61String(ASN1OctetString.getInstance(o).getOctets());
    }

    public DERT61String(byte string[])
    {
        this.string = string;
    }

    public DERT61String(String string)
    {
        this(Strings.toByteArray(string));
    }

    public String getString()
    {
        return Strings.fromByteArray(string);
    }

    public String toString()
    {
        return getString();
    }

    boolean isConstructed()
    {
        return false;
    }

    int encodedLength()
    {
        return 1 + StreamUtil.calculateBodyLength(string.length) + string.length;
    }

    void encode(ASN1OutputStream out)
        throws IOException
    {
        out.writeEncoded(20, string);
    }

    public byte[] getOctets()
    {
        return Arrays.clone(string);
    }

    boolean asn1Equals(ASN1Primitive o)
    {
        if(!(o instanceof DERT61String))
            return false;
        else
            return Arrays.areEqual(string, ((DERT61String)o).string);
    }

    public int hashCode()
    {
        return Arrays.hashCode(string);
    }

    private byte string[];
}
