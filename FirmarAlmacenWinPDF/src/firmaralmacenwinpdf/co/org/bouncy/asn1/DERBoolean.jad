// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DERBoolean.java

package co.org.bouncy.asn1;

import co.org.bouncy.util.Arrays;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Primitive, ASN1Boolean, ASN1OctetString, ASN1TaggedObject, 
//            ASN1OutputStream

public class DERBoolean extends ASN1Primitive
{

    public static ASN1Boolean getInstance(Object obj)
    {
        if(obj == null || (obj instanceof ASN1Boolean))
            return (ASN1Boolean)obj;
        if(obj instanceof DERBoolean)
            return ((DERBoolean)obj).isTrue() ? TRUE : FALSE;
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    public static ASN1Boolean getInstance(boolean value)
    {
        return value ? TRUE : FALSE;
    }

    public static ASN1Boolean getInstance(int value)
    {
        return value == 0 ? FALSE : TRUE;
    }

    public static ASN1Boolean getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        ASN1Primitive o = obj.getObject();
        if(explicit || (o instanceof DERBoolean))
            return getInstance(o);
        else
            return ASN1Boolean.fromOctetString(((ASN1OctetString)o).getOctets());
    }

    DERBoolean(byte value[])
    {
        if(value.length != 1)
            throw new IllegalArgumentException("byte value should have 1 byte in it");
        if(value[0] == 0)
            this.value = FALSE_VALUE;
        else
        if(value[0] == 255)
            this.value = TRUE_VALUE;
        else
            this.value = Arrays.clone(value);
    }

    /**
     * @deprecated Method DERBoolean is deprecated
     */

    public DERBoolean(boolean value)
    {
        this.value = value ? TRUE_VALUE : FALSE_VALUE;
    }

    public boolean isTrue()
    {
        return value[0] != 0;
    }

    boolean isConstructed()
    {
        return false;
    }

    int encodedLength()
    {
        return 3;
    }

    void encode(ASN1OutputStream out)
        throws IOException
    {
        out.writeEncoded(1, value);
    }

    protected boolean asn1Equals(ASN1Primitive o)
    {
        if(o == null || !(o instanceof DERBoolean))
            return false;
        else
            return value[0] == ((DERBoolean)o).value[0];
    }

    public int hashCode()
    {
        return value[0];
    }

    public String toString()
    {
        return value[0] == 0 ? "FALSE" : "TRUE";
    }

    static ASN1Boolean fromOctetString(byte value[])
    {
        if(value.length != 1)
            throw new IllegalArgumentException("byte value should have 1 byte in it");
        if(value[0] == 0)
            return FALSE;
        if(value[0] == 255)
            return TRUE;
        else
            return new ASN1Boolean(value);
    }

    private static final byte TRUE_VALUE[] = {
        -1
    };
    private static final byte FALSE_VALUE[] = {
        0
    };
    private byte value[];
    public static final ASN1Boolean FALSE = new ASN1Boolean(false);
    public static final ASN1Boolean TRUE = new ASN1Boolean(true);

}
