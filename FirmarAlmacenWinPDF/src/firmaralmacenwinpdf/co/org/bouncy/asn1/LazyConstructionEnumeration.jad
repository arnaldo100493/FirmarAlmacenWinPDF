// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LazyConstructionEnumeration.java

package co.org.bouncy.asn1;

import java.io.IOException;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1InputStream, ASN1ParsingException

class LazyConstructionEnumeration
    implements Enumeration
{

    public LazyConstructionEnumeration(byte encoded[])
    {
        aIn = new ASN1InputStream(encoded, true);
        nextObj = readObject();
    }

    public boolean hasMoreElements()
    {
        return nextObj != null;
    }

    public Object nextElement()
    {
        Object o = nextObj;
        nextObj = readObject();
        return o;
    }

    private Object readObject()
    {
        try
        {
            return aIn.readObject();
        }
        catch(IOException e)
        {
            throw new ASN1ParsingException((new StringBuilder()).append("malformed DER construction: ").append(e).toString(), e);
        }
    }

    private ASN1InputStream aIn;
    private Object nextObj;
}
