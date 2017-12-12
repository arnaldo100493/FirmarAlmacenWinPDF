// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaggedPdfReaderTool.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.*;
import co.com.pdf.text.xml.XMLUtil;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            MarkedContentRenderFilter, SimpleTextExtractionStrategy, FilteredTextRenderListener, RenderFilter, 
//            PdfContentStreamProcessor

public class TaggedPdfReaderTool
{

    public TaggedPdfReaderTool()
    {
    }

    public void convertToXml(PdfReader reader, OutputStream os, String charset)
        throws IOException
    {
        this.reader = reader;
        OutputStreamWriter outs = new OutputStreamWriter(os, charset);
        out = new PrintWriter(outs);
        PdfDictionary catalog = reader.getCatalog();
        PdfDictionary struct = catalog.getAsDict(PdfName.STRUCTTREEROOT);
        if(struct == null)
        {
            throw new IOException(MessageLocalization.getComposedMessage("no.structtreeroot.found", new Object[0]));
        } else
        {
            inspectChild(struct.getDirectObject(PdfName.K));
            out.flush();
            out.close();
            return;
        }
    }

    public void convertToXml(PdfReader reader, OutputStream os)
        throws IOException
    {
        convertToXml(reader, os, Charset.defaultCharset().name());
    }

    public void inspectChild(PdfObject k)
        throws IOException
    {
        if(k == null)
            return;
        if(k instanceof PdfArray)
            inspectChildArray((PdfArray)k);
        else
        if(k instanceof PdfDictionary)
            inspectChildDictionary((PdfDictionary)k);
    }

    public void inspectChildArray(PdfArray k)
        throws IOException
    {
        if(k == null)
            return;
        for(int i = 0; i < k.size(); i++)
            inspectChild(k.getDirectObject(i));

    }

    public void inspectChildDictionary(PdfDictionary k)
        throws IOException
    {
        inspectChildDictionary(k, false);
    }

    public void inspectChildDictionary(PdfDictionary k, boolean inspectAttributes)
        throws IOException
    {
        if(k == null)
            return;
        PdfName s = k.getAsName(PdfName.S);
        if(s != null)
        {
            String tagN = PdfName.decodeName(s.toString());
            String tag = fixTagName(tagN);
            out.print("<");
            out.print(tag);
            if(inspectAttributes)
            {
                PdfDictionary a = k.getAsDict(PdfName.A);
                if(a != null)
                {
                    Set keys = a.getKeys();
                    for(Iterator i$ = keys.iterator(); i$.hasNext(); out.print("\""))
                    {
                        PdfName key = (PdfName)i$.next();
                        out.print(' ');
                        PdfObject value = a.get(key);
                        value = PdfReader.getPdfObject(value);
                        out.print(xmlName(key));
                        out.print("=\"");
                        out.print(value.toString());
                    }

                }
            }
            out.print(">");
            PdfDictionary dict = k.getAsDict(PdfName.PG);
            if(dict != null)
                parseTag(tagN, k.getDirectObject(PdfName.K), dict);
            inspectChild(k.getDirectObject(PdfName.K));
            out.print("</");
            out.print(tag);
            out.println(">");
        } else
        {
            inspectChild(k.getDirectObject(PdfName.K));
        }
    }

    protected String xmlName(PdfName name)
    {
        String xmlName = name.toString().replaceFirst("/", "");
        xmlName = (new StringBuilder()).append(Character.toLowerCase(xmlName.charAt(0))).append(xmlName.substring(1)).toString();
        return xmlName;
    }

    private static String fixTagName(String tag)
    {
        StringBuilder sb = new StringBuilder();
        for(int k = 0; k < tag.length(); k++)
        {
            char c = tag.charAt(k);
            boolean nameStart = c == ':' || c >= 'A' && c <= 'Z' || c == '_' || c >= 'a' && c <= 'z' || c >= '\300' && c <= '\326' || c >= '\330' && c <= '\366' || c >= '\370' && c <= '\u02FF' || c >= '\u0370' && c <= '\u037D' || c >= '\u037F' && c <= '\u1FFF' || c >= '\u200C' && c <= '\u200D' || c >= '\u2070' && c <= '\u218F' || c >= '\u2C00' && c <= '\u2FEF' || c >= '\u3001' && c <= '\uD7FF' || c >= '\uF900' && c <= '\uFDCF' || c >= '\uFDF0' && c <= '\uFFFD';
            boolean nameMiddle = c == '-' || c == '.' || c >= '0' && c <= '9' || c == '\267' || c >= '\u0300' && c <= '\u036F' || c >= '\u203F' && c <= '\u2040' || nameStart;
            if(k == 0)
            {
                if(!nameStart)
                    c = '_';
            } else
            if(!nameMiddle)
                c = '-';
            sb.append(c);
        }

        return sb.toString();
    }

    public void parseTag(String tag, PdfObject object, PdfDictionary page)
        throws IOException
    {
        if(object instanceof PdfNumber)
        {
            PdfNumber mcid = (PdfNumber)object;
            RenderFilter filter = new MarkedContentRenderFilter(mcid.intValue());
            TextExtractionStrategy strategy = new SimpleTextExtractionStrategy();
            FilteredTextRenderListener listener = new FilteredTextRenderListener(strategy, new RenderFilter[] {
                filter
            });
            PdfContentStreamProcessor processor = new PdfContentStreamProcessor(listener);
            processor.processContent(PdfReader.getPageContent(page), page.getAsDict(PdfName.RESOURCES));
            out.print(XMLUtil.escapeXML(listener.getResultantText(), true));
        } else
        if(object instanceof PdfArray)
        {
            PdfArray arr = (PdfArray)object;
            int n = arr.size();
            for(int i = 0; i < n; i++)
            {
                parseTag(tag, arr.getPdfObject(i), page);
                if(i < n - 1)
                    out.println();
            }

        } else
        if(object instanceof PdfDictionary)
        {
            PdfDictionary mcr = (PdfDictionary)object;
            parseTag(tag, mcr.getDirectObject(PdfName.MCID), mcr.getAsDict(PdfName.PG));
        }
    }

    protected PdfReader reader;
    protected PrintWriter out;
}
