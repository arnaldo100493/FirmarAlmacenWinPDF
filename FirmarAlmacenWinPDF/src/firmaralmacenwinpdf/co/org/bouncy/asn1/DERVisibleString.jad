// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DERVisibleString.java

package co.org.bouncy.asn1;

import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.Strings;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Primitive, ASN1String, ASN1TaggedObject, ASN1OctetString, 
//            StreamUtil, ASN1OutputStream

public class DERVisibleString extends ASN1Primitive
    implements ASN1String
{

    public static DERVisibleString getInstance(Object obj)
    {
        if(obj == null || (obj instanceof DERVisibleString))
            return (DERVisibleString)obj;
        if(obj instanceof byte[])
            try
            {
                return (DERVisibleString)fromByteArray((byte[])(byte[])obj);
            }
            catch(Exception e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("encoding error in getInstance: ").append(e.toString()).toString());
            }
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    public static DERVisibleString getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        ASN1Primitive o = obj.getObject();
        if(explicit || (o instanceof DERVisibleString))
            return getInstance(o);
        else
            return new DERVisibleString(ASN1OctetString.getInstance(o).getOctets());
    }

    DERVisibleString(byte string[])
    {
        this.string = string;
    }

    public DERVisibleString(String string)
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
        out.writeEncoded(26, string);
    }

    boolean asn1Equals(ASN1Primitive o)
    {
        if(!(o instanceof DERVisibleString))
            return false;
        else
            return Arrays.areEqual(string, ((DERVisibleString)o).string);
    }

    public int hashCode()
    {
        return Arrays.hashCode(string);
    }

    private byte string[];
}
