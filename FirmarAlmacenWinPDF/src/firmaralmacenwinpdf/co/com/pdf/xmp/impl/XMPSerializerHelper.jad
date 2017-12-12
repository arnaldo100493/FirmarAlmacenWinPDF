// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMPSerializerHelper.java

package co.com.pdf.xmp.impl;

import co.com.pdf.xmp.XMPException;
import co.com.pdf.xmp.options.SerializeOptions;
import java.io.*;

// Referenced classes of package co.com.pdf.xmp.impl:
//            XMPSerializerRDF, XMPMetaImpl

public class XMPSerializerHelper
{

    public XMPSerializerHelper()
    {
    }

    public static void serialize(XMPMetaImpl xmp, OutputStream out, SerializeOptions options)
        throws XMPException
    {
        options = options == null ? new SerializeOptions() : options;
        if(options.getSort())
            xmp.sort();
        (new XMPSerializerRDF()).serialize(xmp, out, options);
    }

    public static String serializeToString(XMPMetaImpl xmp, SerializeOptions options)
        throws XMPException
    {
        options = options == null ? new SerializeOptions() : options;
        options.setEncodeUTF16BE(true);
        ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
        serialize(xmp, out, options);
        try
        {
            return out.toString(options.getEncoding());
        }
        catch(UnsupportedEncodingException e)
        {
            return out.toString();
        }
    }

    public static byte[] serializeToBuffer(XMPMetaImpl xmp, SerializeOptions options)
        throws XMPException
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
        serialize(xmp, out, options);
        return out.toByteArray();
    }
}
