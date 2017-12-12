// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DERGeneralString.java

package co.org.bouncy.asn1;

import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.Strings;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Primitive, ASN1OctetString, ASN1String, ASN1TaggedObject, 
//            StreamUtil, ASN1OutputStream

public class DERGeneralString extends ASN1Primitive
    implements ASN1String
{

    public static DERGeneralString getInstance(Object obj)
    {
        if(obj == null || (obj instanceof DERGeneralString))
            return (DERGeneralString)obj;
        if(obj instanceof byte[])
            try
            {
                return (DERGeneralString)fromByteArray((byte[])(byte[])obj);
            }
            catch(Exception e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("encoding error in getInstance: ").append(e.toString()).toString());
            }
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    public static DERGeneralString getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        ASN1Primitive o = obj.getObject();
        if(explicit || (o instanceof DERGeneralString))
            return getInstance(o);
        else
            return new DERGeneralString(((ASN1OctetString)o).getOctets());
    }

    DERGeneralString(byte string[])
    {
        this.string = string;
    }

    public DERGeneralString(String string)
    {
        this.string = Strings.toByteArray(string);
    }

    public String getString()
    {
        return Strings.fromByteArray(string);
    }

    public String toString()
    {
        return getString();
    }

    public byte[] getOctets()
    {
        return Arrays.clone(string);
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
        out.writeEncoded(27, string);
    }

    public int hashCode()
    {
        return Arrays.hashCode(string);
    }

    boolean asn1Equals(ASN1Primitive o)
    {
        if(!(o instanceof DERGeneralString))
        {
            return false;
        } else
        {
            DERGeneralString s = (DERGeneralString)o;
            return Arrays.areEqual(string, s.string);
        }
    }

    private byte string[];
}
