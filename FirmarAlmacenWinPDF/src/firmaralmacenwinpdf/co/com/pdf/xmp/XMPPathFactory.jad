// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMPPathFactory.java

package co.com.pdf.xmp;

import co.com.pdf.xmp.impl.Utils;
import co.com.pdf.xmp.impl.xpath.XMPPath;
import co.com.pdf.xmp.impl.xpath.XMPPathParser;
import co.com.pdf.xmp.impl.xpath.XMPPathSegment;

// Referenced classes of package co.com.pdf.xmp:
//            XMPException

public final class XMPPathFactory
{

    private XMPPathFactory()
    {
    }

    public static String composeArrayItemPath(String arrayName, int itemIndex)
        throws XMPException
    {
        if(itemIndex > 0)
            return (new StringBuilder()).append(arrayName).append('[').append(itemIndex).append(']').toString();
        if(itemIndex == -1)
            return (new StringBuilder()).append(arrayName).append("[last()]").toString();
        else
            throw new XMPException("Array index must be larger than zero", 104);
    }

    public static String composeStructFieldPath(String fieldNS, String fieldName)
        throws XMPException
    {
        assertFieldNS(fieldNS);
        assertFieldName(fieldName);
        XMPPath fieldPath = XMPPathParser.expandXPath(fieldNS, fieldName);
        if(fieldPath.size() != 2)
            throw new XMPException("The field name must be simple", 102);
        else
            return (new StringBuilder()).append('/').append(fieldPath.getSegment(1).getName()).toString();
    }

    public static String composeQualifierPath(String qualNS, String qualName)
        throws XMPException
    {
        assertQualNS(qualNS);
        assertQualName(qualName);
        XMPPath qualPath = XMPPathParser.expandXPath(qualNS, qualName);
        if(qualPath.size() != 2)
            throw new XMPException("The qualifier name must be simple", 102);
        else
            return (new StringBuilder()).append("/?").append(qualPath.getSegment(1).getName()).toString();
    }

    public static String composeLangSelector(String arrayName, String langName)
    {
        return (new StringBuilder()).append(arrayName).append("[?xml:lang=\"").append(Utils.normalizeLangValue(langName)).append("\"]").toString();
    }

    public static String composeFieldSelector(String arrayName, String fieldNS, String fieldName, String fieldValue)
        throws XMPException
    {
        XMPPath fieldPath = XMPPathParser.expandXPath(fieldNS, fieldName);
        if(fieldPath.size() != 2)
            throw new XMPException("The fieldName name must be simple", 102);
        else
            return (new StringBuilder()).append(arrayName).append('[').append(fieldPath.getSegment(1).getName()).append("=\"").append(fieldValue).append("\"]").toString();
    }

    private static void assertQualNS(String qualNS)
        throws XMPException
    {
        if(qualNS == null || qualNS.length() == 0)
            throw new XMPException("Empty qualifier namespace URI", 101);
        else
            return;
    }

    private static void assertQualName(String qualName)
        throws XMPException
    {
        if(qualName == null || qualName.length() == 0)
            throw new XMPException("Empty qualifier name", 102);
        else
            return;
    }

    private static void assertFieldNS(String fieldNS)
        throws XMPException
    {
        if(fieldNS == null || fieldNS.length() == 0)
            throw new XMPException("Empty field namespace URI", 101);
        else
            return;
    }

    private static void assertFieldName(String fieldName)
        throws XMPException
    {
        if(fieldName == null || fieldName.length() == 0)
            throw new XMPException("Empty f name", 102);
        else
            return;
    }
}
