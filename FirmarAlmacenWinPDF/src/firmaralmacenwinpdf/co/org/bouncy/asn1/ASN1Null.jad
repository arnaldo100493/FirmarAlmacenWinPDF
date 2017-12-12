// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ASN1Null.java

package co.org.bouncy.asn1;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Primitive, ASN1OutputStream

public abstract class ASN1Null extends ASN1Primitive
{

    /**
     * @deprecated Method ASN1Null is deprecated
     */

    public ASN1Null()
    {
    }

    public static ASN1Null getInstance(Object o)
    {
        if(o instanceof ASN1Null)
            return (ASN1Null)o;
        if(o != null)
            try
            {
                return getInstance(ASN1Primitive.fromByteArray((byte[])(byte[])o));
            }
            catch(IOException e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("failed to construct NULL from byte[]: ").append(e.getMessage()).toString());
            }
            catch(ClassCastException e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("unknown object in getInstance(): ").append(o.getClass().getName()).toString());
            }
        else
            return null;
    }

    public int hashCode()
    {
        return -1;
    }

    boolean asn1Equals(ASN1Primitive o)
    {
        return o instanceof ASN1Null;
    }

    abstract void encode(ASN1OutputStream asn1outputstream)
        throws IOException;

    public String toString()
    {
        return "NULL";
    }
}
