// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ParameterAsserts.java

package co.com.pdf.xmp.impl;

import co.com.pdf.xmp.*;

// Referenced classes of package co.com.pdf.xmp.impl:
//            XMPMetaImpl

class ParameterAsserts
    implements XMPConst
{

    private ParameterAsserts()
    {
    }

    public static void assertArrayName(String arrayName)
        throws XMPException
    {
        if(arrayName == null || arrayName.length() == 0)
            throw new XMPException("Empty array name", 4);
        else
            return;
    }

    public static void assertPropName(String propName)
        throws XMPException
    {
        if(propName == null || propName.length() == 0)
            throw new XMPException("Empty property name", 4);
        else
            return;
    }

    public static void assertSchemaNS(String schemaNS)
        throws XMPException
    {
        if(schemaNS == null || schemaNS.length() == 0)
            throw new XMPException("Empty schema namespace URI", 4);
        else
            return;
    }

    public static void assertPrefix(String prefix)
        throws XMPException
    {
        if(prefix == null || prefix.length() == 0)
            throw new XMPException("Empty prefix", 4);
        else
            return;
    }

    public static void assertSpecificLang(String specificLang)
        throws XMPException
    {
        if(specificLang == null || specificLang.length() == 0)
            throw new XMPException("Empty specific language", 4);
        else
            return;
    }

    public static void assertStructName(String structName)
        throws XMPException
    {
        if(structName == null || structName.length() == 0)
            throw new XMPException("Empty array name", 4);
        else
            return;
    }

    public static void assertNotNull(Object param)
        throws XMPException
    {
        if(param == null)
            throw new XMPException("Parameter must not be null", 4);
        if((param instanceof String) && ((String)param).length() == 0)
            throw new XMPException("Parameter must not be null or empty", 4);
        else
            return;
    }

    public static void assertImplementation(XMPMeta xmp)
        throws XMPException
    {
        if(xmp == null)
            throw new XMPException("Parameter must not be null", 4);
        if(!(xmp instanceof XMPMetaImpl))
            throw new XMPException("The XMPMeta-object is not compatible with this implementation", 4);
        else
            return;
    }
}
