// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DERPrintableString.java

package co.org.bouncy.asn1;

import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.Strings;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Primitive, ASN1String, ASN1TaggedObject, ASN1OctetString, 
//            StreamUtil, ASN1OutputStream

public class DERPrintableString extends ASN1Primitive
    implements ASN1String
{

    public static DERPrintableString getInstance(Object obj)
    {
        if(obj == null || (obj instanceof DERPrintableString))
            return (DERPrintableString)obj;
        if(obj instanceof byte[])
            try
            {
                return (DERPrintableString)fromByteArray((byte[])(byte[])obj);
            }
            catch(Exception e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("encoding error in getInstance: ").append(e.toString()).toString());
            }
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    public static DERPrintableString getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        ASN1Primitive o = obj.getObject();
        if(explicit || (o instanceof DERPrintableString))
            return getInstance(o);
        else
            return new DERPrintableString(ASN1OctetString.getInstance(o).getOctets());
    }

    DERPrintableString(byte string[])
    {
        this.string = string;
    }

    public DERPrintableString(String string)
    {
        this(string, false);
    }

    public DERPrintableString(String string, boolean validate)
    {
        if(validate && !isPrintableString(string))
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
        out.writeEncoded(19, string);
    }

    public int hashCode()
    {
        return Arrays.hashCode(string);
    }

    boolean asn1Equals(ASN1Primitive o)
    {
        if(!(o instanceof DERPrintableString))
        {
            return false;
        } else
        {
            DERPrintableString s = (DERPrintableString)o;
            return Arrays.areEqual(string, s.string);
        }
    }

    public String toString()
    {
        return getString();
    }

    public static boolean isPrintableString(String str)
    {
        int i = str.length() - 1;
        do
            if(i >= 0)
            {
                char ch = str.charAt(i);
                if(ch > '\177')
                    return false;
                if(('a' > ch || ch > 'z') && ('A' > ch || ch > 'Z') && ('0' > ch || ch > '9'))
                    switch(ch)
                    {
                    case 33: // '!'
                    case 34: // '"'
                    case 35: // '#'
                    case 36: // '$'
                    case 37: // '%'
                    case 38: // '&'
                    case 42: // '*'
                    case 48: // '0'
                    case 49: // '1'
                    case 50: // '2'
                    case 51: // '3'
                    case 52: // '4'
                    case 53: // '5'
                    case 54: // '6'
                    case 55: // '7'
                    case 56: // '8'
                    case 57: // '9'
                    case 59: // ';'
                    case 60: // '<'
                    case 62: // '>'
                    default:
                        return false;

                    case 32: // ' '
                    case 39: // '\''
                    case 40: // '('
                    case 41: // ')'
                    case 43: // '+'
                    case 44: // ','
                    case 45: // '-'
                    case 46: // '.'
                    case 47: // '/'
                    case 58: // ':'
                    case 61: // '='
                    case 63: // '?'
                        break;
                    }
                i--;
            } else
            {
                return true;
            }
        while(true);
    }

    private byte string[];
}
