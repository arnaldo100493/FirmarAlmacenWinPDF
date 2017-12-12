// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmpWriter.java

package co.com.pdf.text.xml.xmp;

import co.com.pdf.text.Version;
import co.com.pdf.text.pdf.*;
import co.com.pdf.xmp.*;
import co.com.pdf.xmp.options.PropertyOptions;
import co.com.pdf.xmp.options.SerializeOptions;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

// Referenced classes of package co.com.pdf.text.xml.xmp:
//            XmpSchema

public class XmpWriter
{

    public XmpWriter(OutputStream os, String utfEncoding, int extraSpace)
        throws IOException
    {
        outputStream = os;
        serializeOptions = new SerializeOptions();
        if("UTF-16BE".equals(utfEncoding) || "UTF-16".equals(utfEncoding))
            serializeOptions.setEncodeUTF16BE(true);
        else
        if("UTF-16LE".equals(utfEncoding))
            serializeOptions.setEncodeUTF16LE(true);
        serializeOptions.setPadding(extraSpace);
        xmpMeta = XMPMetaFactory.create();
        xmpMeta.setObjectName("xmpmeta");
        xmpMeta.setObjectName("");
        try
        {
            xmpMeta.setProperty("http://purl.org/dc/elements/1.1/", "format", "application/pdf");
            xmpMeta.setProperty("http://ns.adobe.com/pdf/1.3/", "Producer", Version.getInstance().getVersion());
        }
        catch(XMPException xmpExc) { }
    }

    public XmpWriter(OutputStream os)
        throws IOException
    {
        this(os, "UTF-8", 2000);
    }

    public XmpWriter(OutputStream os, PdfDictionary info)
        throws IOException
    {
        this(os);
        if(info != null)
        {
            Iterator i$ = info.getKeys().iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                PdfName pdfName = (PdfName)i$.next();
                PdfName key = pdfName;
                PdfObject obj = info.get(key);
                if(obj != null && obj.isString())
                {
                    String value = ((PdfString)obj).toUnicodeString();
                    try
                    {
                        addDocInfoProperty(key, value);
                    }
                    catch(XMPException xmpExc)
                    {
                        throw new IOException(xmpExc.getMessage());
                    }
                }
            } while(true);
        }
    }

    public XmpWriter(OutputStream os, Map info)
        throws IOException
    {
        this(os);
        if(info != null)
        {
            Iterator i$ = info.entrySet().iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
                String key = (String)entry.getKey();
                String value = (String)entry.getValue();
                if(value != null)
                    try
                    {
                        addDocInfoProperty(key, value);
                    }
                    catch(XMPException xmpExc)
                    {
                        throw new IOException(xmpExc.getMessage());
                    }
            } while(true);
        }
    }

    public XMPMeta getXmpMeta()
    {
        return xmpMeta;
    }

    public void setReadOnly()
    {
        serializeOptions.setReadOnlyPacket(true);
    }

    public void setAbout(String about)
    {
        xmpMeta.setObjectName(about);
    }

    /**
     * @deprecated Method addRdfDescription is deprecated
     */

    public void addRdfDescription(String xmlns, String content)
        throws IOException
    {
        try
        {
            String str = (new StringBuilder()).append("<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"><rdf:Description rdf:about=\"").append(xmpMeta.getObjectName()).append("\" ").append(xmlns).append(">").append(content).append("</rdf:Description></rdf:RDF>\n").toString();
            XMPMeta extMeta = XMPMetaFactory.parseFromString(str);
            XMPUtils.appendProperties(extMeta, xmpMeta, true, true);
        }
        catch(XMPException xmpExc)
        {
            throw new IOException(xmpExc.getMessage());
        }
    }

    /**
     * @deprecated Method addRdfDescription is deprecated
     */

    public void addRdfDescription(XmpSchema s)
        throws IOException
    {
        try
        {
            String str = (new StringBuilder()).append("<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"><rdf:Description rdf:about=\"").append(xmpMeta.getObjectName()).append("\" ").append(s.getXmlns()).append(">").append(s.toString()).append("</rdf:Description></rdf:RDF>\n").toString();
            XMPMeta extMeta = XMPMetaFactory.parseFromString(str);
            XMPUtils.appendProperties(extMeta, xmpMeta, true, true);
        }
        catch(XMPException xmpExc)
        {
            throw new IOException(xmpExc.getMessage());
        }
    }

    public void setProperty(String schemaNS, String propName, Object value)
        throws XMPException
    {
        xmpMeta.setProperty(schemaNS, propName, value);
    }

    public void appendArrayItem(String schemaNS, String arrayName, String value)
        throws XMPException
    {
        xmpMeta.appendArrayItem(schemaNS, arrayName, new PropertyOptions(512), value, null);
    }

    public void appendOrderedArrayItem(String schemaNS, String arrayName, String value)
        throws XMPException
    {
        xmpMeta.appendArrayItem(schemaNS, arrayName, new PropertyOptions(1024), value, null);
    }

    public void appendAlternateArrayItem(String schemaNS, String arrayName, String value)
        throws XMPException
    {
        xmpMeta.appendArrayItem(schemaNS, arrayName, new PropertyOptions(2048), value, null);
    }

    public void serialize(OutputStream externalOutputStream)
        throws XMPException
    {
        XMPMetaFactory.serialize(xmpMeta, externalOutputStream, serializeOptions);
    }

    public void close()
        throws IOException
    {
        if(outputStream == null)
            return;
        try
        {
            XMPMetaFactory.serialize(xmpMeta, outputStream, serializeOptions);
            outputStream = null;
        }
        catch(XMPException xmpExc)
        {
            throw new IOException(xmpExc.getMessage());
        }
    }

    public void addDocInfoProperty(Object key, String value)
        throws XMPException
    {
        if(key instanceof String)
            key = new PdfName((String)key);
        if(PdfName.TITLE.equals(key))
            xmpMeta.setLocalizedText("http://purl.org/dc/elements/1.1/", "title", "x-default", "x-default", value);
        else
        if(PdfName.AUTHOR.equals(key))
            xmpMeta.appendArrayItem("http://purl.org/dc/elements/1.1/", "creator", new PropertyOptions(1024), value, null);
        else
        if(PdfName.SUBJECT.equals(key))
        {
            xmpMeta.appendArrayItem("http://purl.org/dc/elements/1.1/", "subject", new PropertyOptions(512), value, null);
            xmpMeta.setLocalizedText("http://purl.org/dc/elements/1.1/", "description", "x-default", "x-default", value);
        } else
        if(PdfName.KEYWORDS.equals(key))
            xmpMeta.setProperty("http://ns.adobe.com/pdf/1.3/", "Keywords", value);
        else
        if(PdfName.PRODUCER.equals(key))
            xmpMeta.setProperty("http://ns.adobe.com/pdf/1.3/", "Producer", value);
        else
        if(PdfName.CREATOR.equals(key))
            xmpMeta.setProperty("http://ns.adobe.com/xap/1.0/", "CreatorTool", value);
        else
        if(PdfName.CREATIONDATE.equals(key))
            xmpMeta.setProperty("http://ns.adobe.com/xap/1.0/", "CreateDate", PdfDate.getW3CDate(value));
        else
        if(PdfName.MODDATE.equals(key))
            xmpMeta.setProperty("http://ns.adobe.com/xap/1.0/", "ModifyDate", PdfDate.getW3CDate(value));
    }

    public static final String UTF8 = "UTF-8";
    public static final String UTF16 = "UTF-16";
    public static final String UTF16BE = "UTF-16BE";
    public static final String UTF16LE = "UTF-16LE";
    protected XMPMeta xmpMeta;
    protected OutputStream outputStream;
    protected SerializeOptions serializeOptions;
}
