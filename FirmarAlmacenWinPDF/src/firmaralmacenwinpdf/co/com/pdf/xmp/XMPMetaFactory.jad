// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMPMetaFactory.java

package co.com.pdf.xmp;

import co.com.pdf.xmp.impl.XMPMetaImpl;
import co.com.pdf.xmp.impl.XMPMetaParser;
import co.com.pdf.xmp.impl.XMPSchemaRegistryImpl;
import co.com.pdf.xmp.impl.XMPSerializerHelper;
import co.com.pdf.xmp.options.ParseOptions;
import co.com.pdf.xmp.options.SerializeOptions;
import java.io.*;

// Referenced classes of package co.com.pdf.xmp:
//            XMPException, XMPSchemaRegistry, XMPVersionInfo, XMPMeta

public final class XMPMetaFactory
{

    private XMPMetaFactory()
    {
    }

    public static XMPSchemaRegistry getSchemaRegistry()
    {
        return schema;
    }

    public static XMPMeta create()
    {
        return new XMPMetaImpl();
    }

    public static XMPMeta parse(InputStream in)
        throws XMPException
    {
        return parse(in, null);
    }

    public static XMPMeta parse(InputStream in, ParseOptions options)
        throws XMPException
    {
        return XMPMetaParser.parse(in, options);
    }

    public static XMPMeta parseFromString(String packet)
        throws XMPException
    {
        return parseFromString(packet, null);
    }

    public static XMPMeta parseFromString(String packet, ParseOptions options)
        throws XMPException
    {
        return XMPMetaParser.parse(packet, options);
    }

    public static XMPMeta parseFromBuffer(byte buffer[])
        throws XMPException
    {
        return parseFromBuffer(buffer, null);
    }

    public static XMPMeta parseFromBuffer(byte buffer[], ParseOptions options)
        throws XMPException
    {
        return XMPMetaParser.parse(buffer, options);
    }

    public static void serialize(XMPMeta xmp, OutputStream out)
        throws XMPException
    {
        serialize(xmp, out, null);
    }

    public static void serialize(XMPMeta xmp, OutputStream out, SerializeOptions options)
        throws XMPException
    {
        assertImplementation(xmp);
        XMPSerializerHelper.serialize((XMPMetaImpl)xmp, out, options);
    }

    public static byte[] serializeToBuffer(XMPMeta xmp, SerializeOptions options)
        throws XMPException
    {
        assertImplementation(xmp);
        return XMPSerializerHelper.serializeToBuffer((XMPMetaImpl)xmp, options);
    }

    public static String serializeToString(XMPMeta xmp, SerializeOptions options)
        throws XMPException
    {
        assertImplementation(xmp);
        return XMPSerializerHelper.serializeToString((XMPMetaImpl)xmp, options);
    }

    private static void assertImplementation(XMPMeta xmp)
    {
        if(!(xmp instanceof XMPMetaImpl))
            throw new UnsupportedOperationException("The serializing service works onlywith the XMPMeta implementation of this library");
        else
            return;
    }

    public static void reset()
    {
        schema = new XMPSchemaRegistryImpl();
    }

    public static synchronized XMPVersionInfo getVersionInfo()
    {
        if(versionInfo == null)
            try
            {
                int major = 5;
                int minor = 1;
                int micro = 0;
                int engBuild = 3;
                boolean debug = false;
                String message = "Adobe XMP Core 5.1.0-jc003";
                versionInfo = new XMPVersionInfo() {

                    public int getMajor()
                    {
                        return 5;
                    }

                    public int getMinor()
                    {
                        return 1;
                    }

                    public int getMicro()
                    {
                        return 0;
                    }

                    public boolean isDebug()
                    {
                        return false;
                    }

                    public int getBuild()
                    {
                        return 3;
                    }

                    public String getMessage()
                    {
                        return "Adobe XMP Core 5.1.0-jc003";
                    }

                    public String toString()
                    {
                        return "Adobe XMP Core 5.1.0-jc003";
                    }

                }
;
            }
            catch(Throwable e)
            {
                System.out.println(e);
            }
        return versionInfo;
    }

    private static XMPSchemaRegistry schema = new XMPSchemaRegistryImpl();
    private static XMPVersionInfo versionInfo = null;

}
