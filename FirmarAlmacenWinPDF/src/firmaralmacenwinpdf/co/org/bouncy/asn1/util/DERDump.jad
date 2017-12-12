// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DERDump.java

package co.org.bouncy.asn1.util;

import co.org.bouncy.asn1.ASN1Encodable;
import co.org.bouncy.asn1.ASN1Primitive;

// Referenced classes of package co.org.bouncy.asn1.util:
//            ASN1Dump

/**
 * @deprecated Class DERDump is deprecated
 */

public class DERDump extends ASN1Dump
{

    public DERDump()
    {
    }

    public static String dumpAsString(ASN1Primitive obj)
    {
        StringBuffer buf = new StringBuffer();
        _dumpAsString("", false, obj, buf);
        return buf.toString();
    }

    public static String dumpAsString(ASN1Encodable obj)
    {
        StringBuffer buf = new StringBuffer();
        _dumpAsString("", false, obj.toASN1Primitive(), buf);
        return buf.toString();
    }
}
