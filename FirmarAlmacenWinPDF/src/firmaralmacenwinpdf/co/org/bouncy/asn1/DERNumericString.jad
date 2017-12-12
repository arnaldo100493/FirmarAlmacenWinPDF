// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DERNumericString.java

package co.org.bouncy.asn1;

import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.Strings;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Primitive, ASN1String, ASN1TaggedObject, ASN1OctetString, 
//            StreamUtil, ASN1OutputStream

public class DERNumericString extends ASN1Primitive
    implements ASN1String
{

    public static DERNumericString getInstance(Object obj)
    {
        if(obj == null || (obj instanceof DERNumericString))
            return (DERNumericString)obj;
        if(obj instanceof byte[])
            try
            {
                return (DERNumericString)fromByteArray((byte[])(byte[])obj);
            }
            catch(Exception e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("encoding error in getInstance: ").append(e.toString()).toString());
            }
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    public static DERNumericString getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        ASN1Primitive o = obj.getObject();
        if(explicit || (o instanceof DERNumericString))
            return getInstance(o);
        else
            return new DERNumericString(ASN1OctetString.getInstance(o).getOctets());
    }

    DERNumericString(byte string[])
    {
        this.string = string;
    }

    public DERNumericString(String string)
    {
        this(string, false);
    }

    public DERNumericString(String string, boolean validate)
    {
        if(validate && !isNumericString(string))
        {
            throw new IllegalArgumentException("string contains illegal characters");
        } else
        {
            this.string = Strings.toByteArray(string);
            return;
        }
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
        out.writeEncoded(18, string);
    }

    public int hashCode()
    {
        return Arrays.hashCode(string);
    }

    boolean asn1Equals(ASN1Primitive o)
    {
        if(!(o instanceof DERNumericString))
        {
            return false;
        } else
        {
            DERNumericString s = (DERNumericString)o;
            return Arrays.areEqual(string, s.string);
        }
    }

    public static boolean isNumericString(String str)
    {
        for(int i = str.length() - 1; i >= 0; i--)
        {
            char ch = str.charAt(i);
            if(ch > '\177')
                return false;
            if(('0' > ch || ch > '9') && ch != ' ')
                return false;
        }

        return true;
    }

    private byte string[];
}
