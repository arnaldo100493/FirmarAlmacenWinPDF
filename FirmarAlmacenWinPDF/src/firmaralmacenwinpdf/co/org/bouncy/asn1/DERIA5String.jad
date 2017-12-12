// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DERIA5String.java

package co.org.bouncy.asn1;

import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.Strings;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Primitive, ASN1OctetString, ASN1String, ASN1TaggedObject, 
//            StreamUtil, ASN1OutputStream

public class DERIA5String extends ASN1Primitive
    implements ASN1String
{

    public static DERIA5String getInstance(Object obj)
    {
        if(obj == null || (obj instanceof DERIA5String))
            return (DERIA5String)obj;
        if(obj instanceof byte[])
            try
            {
                return (DERIA5String)fromByteArray((byte[])(byte[])obj);
            }
            catch(Exception e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("encoding error in getInstance: ").append(e.toString()).toString());
            }
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    public static DERIA5String getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        ASN1Primitive o = obj.getObject();
        if(explicit || (o instanceof DERIA5String))
            return getInstance(o);
        else
            return new DERIA5String(((ASN1OctetString)o).getOctets());
    }

    DERIA5String(byte string[])
    {
        this.string = string;
    }

    public DERIA5String(String string)
    {
        this(string, false);
    }

    public DERIA5String(String string, boolean validate)
    {
        if(string == null)
            throw new NullPointerException("string cannot be null");
        if(validate && !isIA5String(string))
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
        out.writeEncoded(22, string);
    }

    public int hashCode()
    {
        return Arrays.hashCode(string);
    }

    boolean asn1Equals(ASN1Primitive o)
    {
        if(!(o instanceof DERIA5String))
        {
            return false;
        } else
        {
            DERIA5String s = (DERIA5String)o;
            return Arrays.areEqual(string, s.string);
        }
    }

    public static boolean isIA5String(String str)
    {
        for(int i = str.length() - 1; i >= 0; i--)
        {
            char ch = str.charAt(i);
            if(ch > '\177')
                return false;
        }

        return true;
    }

    private byte string[];
}
