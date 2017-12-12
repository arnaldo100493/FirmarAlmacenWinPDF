// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleNamedDestination.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.xml.XMLUtil;
import co.com.pdf.text.xml.simpleparser.*;
import java.io.*;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            IntHashtable, PdfArray, PdfName, PdfNumber, 
//            PdfDictionary, PdfReader, PdfWriter, PRIndirectReference, 
//            PdfIndirectReference, PdfObject, PdfNull, PdfIndirectObject, 
//            PdfNameTree

public final class SimpleNamedDestination
    implements SimpleXMLDocHandler
{

    private SimpleNamedDestination()
    {
    }

    public static HashMap getNamedDestination(PdfReader reader, boolean fromNames)
    {
        IntHashtable pages = new IntHashtable();
        int numPages = reader.getNumberOfPages();
        for(int k = 1; k <= numPages; k++)
            pages.put(reader.getPageOrigRef(k).getNumber(), k);

        HashMap names = fromNames ? reader.getNamedDestinationFromNames() : reader.getNamedDestinationFromStrings();
        HashMap n2 = new HashMap(names.size());
        for(Iterator i$ = names.entrySet().iterator(); i$.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
            PdfArray arr = (PdfArray)entry.getValue();
            StringBuffer s = new StringBuffer();
            try
            {
                s.append(pages.get(arr.getAsIndirectObject(0).getNumber()));
                s.append(' ').append(arr.getPdfObject(1).toString().substring(1));
                for(int k = 2; k < arr.size(); k++)
                    s.append(' ').append(arr.getPdfObject(k).toString());

                n2.put(entry.getKey(), s.toString());
            }
            catch(Exception e) { }
        }

        return n2;
    }

    public static void exportToXML(HashMap names, OutputStream out, String encoding, boolean onlyASCII)
        throws IOException
    {
        String jenc = IanaEncodings.getJavaEncoding(encoding);
        Writer wrt = new BufferedWriter(new OutputStreamWriter(out, jenc));
        exportToXML(names, wrt, encoding, onlyASCII);
    }

    public static void exportToXML(HashMap names, Writer wrt, String encoding, boolean onlyASCII)
        throws IOException
    {
        wrt.write("<?xml version=\"1.0\" encoding=\"");
        wrt.write(XMLUtil.escapeXML(encoding, onlyASCII));
        wrt.write("\"?>\n<Destination>\n");
        for(Iterator i$ = names.entrySet().iterator(); i$.hasNext(); wrt.write("</Name>\n"))
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
            String key = (String)entry.getKey();
            String value = (String)entry.getValue();
            wrt.write("  <Name Page=\"");
            wrt.write(XMLUtil.escapeXML(value, onlyASCII));
            wrt.write("\">");
            wrt.write(XMLUtil.escapeXML(escapeBinaryString(key), onlyASCII));
        }

        wrt.write("</Destination>\n");
        wrt.flush();
    }

    public static HashMap importFromXML(InputStream in)
        throws IOException
    {
        SimpleNamedDestination names = new SimpleNamedDestination();
        SimpleXMLParser.parse(names, in);
        return names.xmlNames;
    }

    public static HashMap importFromXML(Reader in)
        throws IOException
    {
        SimpleNamedDestination names = new SimpleNamedDestination();
        SimpleXMLParser.parse(names, in);
        return names.xmlNames;
    }

    static PdfArray createDestinationArray(String value, PdfWriter writer)
    {
        PdfArray ar = new PdfArray();
        StringTokenizer tk = new StringTokenizer(value);
        int n = Integer.parseInt(tk.nextToken());
        ar.add(writer.getPageReference(n));
        if(!tk.hasMoreTokens())
        {
            ar.add(PdfName.XYZ);
            ar.add(new float[] {
                0.0F, 10000F, 0.0F
            });
        } else
        {
            String fn = tk.nextToken();
            if(fn.startsWith("/"))
                fn = fn.substring(1);
            ar.add(new PdfName(fn));
            for(int k = 0; k < 4 && tk.hasMoreTokens(); k++)
            {
                fn = tk.nextToken();
                if(fn.equals("null"))
                    ar.add(PdfNull.PDFNULL);
                else
                    ar.add(new PdfNumber(fn));
            }

        }
        return ar;
    }

    public static PdfDictionary outputNamedDestinationAsNames(HashMap names, PdfWriter writer)
    {
        PdfDictionary dic = new PdfDictionary();
        for(Iterator i$ = names.entrySet().iterator(); i$.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
            try
            {
                String key = (String)entry.getKey();
                String value = (String)entry.getValue();
                PdfArray ar = createDestinationArray(value, writer);
                PdfName kn = new PdfName(key);
                dic.put(kn, ar);
            }
            catch(Exception e) { }
        }

        return dic;
    }

    public static PdfDictionary outputNamedDestinationAsStrings(HashMap names, PdfWriter writer)
        throws IOException
    {
        HashMap n2 = new HashMap(names.size());
        for(Iterator i$ = names.entrySet().iterator(); i$.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
            try
            {
                String value = (String)entry.getValue();
                PdfArray ar = createDestinationArray(value, writer);
                n2.put(entry.getKey(), writer.addToBody(ar).getIndirectReference());
            }
            catch(Exception e) { }
        }

        return PdfNameTree.writeTree(n2, writer);
    }

    public static String escapeBinaryString(String s)
    {
        StringBuffer buf = new StringBuffer();
        char cc[] = s.toCharArray();
        int len = cc.length;
        for(int k = 0; k < len; k++)
        {
            char c = cc[k];
            if(c < ' ')
            {
                buf.append('\\');
                String octal = (new StringBuilder()).append("00").append(Integer.toOctalString(c)).toString();
                buf.append(octal.substring(octal.length() - 3));
                continue;
            }
            if(c == '\\')
                buf.append("\\\\");
            else
                buf.append(c);
        }

        return buf.toString();
    }

    public static String unEscapeBinaryString(String s)
    {
        StringBuffer buf = new StringBuffer();
        char cc[] = s.toCharArray();
        int len = cc.length;
        for(int k = 0; k < len; k++)
        {
            char c = cc[k];
            if(c == '\\')
            {
                if(++k >= len)
                {
                    buf.append('\\');
                    break;
                }
                c = cc[k];
                if(c >= '0' && c <= '7')
                {
                    int n = c - 48;
                    k++;
                    int j = 0;
                    do
                    {
                        if(j >= 2 || k >= len)
                            break;
                        c = cc[k];
                        if(c < '0' || c > '7')
                            break;
                        k++;
                        n = (n * 8 + c) - 48;
                        j++;
                    } while(true);
                    k--;
                    buf.append((char)n);
                } else
                {
                    buf.append(c);
                }
            } else
            {
                buf.append(c);
            }
        }

        return buf.toString();
    }

    public void endDocument()
    {
    }

    public void endElement(String tag)
    {
        if(tag.equals("Destination"))
            if(xmlLast == null && xmlNames != null)
                return;
            else
                throw new RuntimeException(MessageLocalization.getComposedMessage("destination.end.tag.out.of.place", new Object[0]));
        if(!tag.equals("Name"))
            throw new RuntimeException(MessageLocalization.getComposedMessage("invalid.end.tag.1", new Object[] {
                tag
            }));
        if(xmlLast == null || xmlNames == null)
            throw new RuntimeException(MessageLocalization.getComposedMessage("name.end.tag.out.of.place", new Object[0]));
        if(!xmlLast.containsKey("Page"))
        {
            throw new RuntimeException(MessageLocalization.getComposedMessage("page.attribute.missing", new Object[0]));
        } else
        {
            xmlNames.put(unEscapeBinaryString((String)xmlLast.get("Name")), xmlLast.get("Page"));
            xmlLast = null;
            return;
        }
    }

    public void startDocument()
    {
    }

    public void startElement(String tag, Map h)
    {
        if(xmlNames == null)
            if(tag.equals("Destination"))
            {
                xmlNames = new HashMap();
                return;
            } else
            {
                throw new RuntimeException(MessageLocalization.getComposedMessage("root.element.is.not.destination", new Object[0]));
            }
        if(!tag.equals("Name"))
            throw new RuntimeException(MessageLocalization.getComposedMessage("tag.1.not.allowed", new Object[] {
                tag
            }));
        if(xmlLast != null)
        {
            throw new RuntimeException(MessageLocalization.getComposedMessage("nested.tags.are.not.allowed", new Object[0]));
        } else
        {
            xmlLast = new HashMap(h);
            xmlLast.put("Name", "");
            return;
        }
    }

    public void text(String str)
    {
        if(xmlLast == null)
        {
            return;
        } else
        {
            String name = (String)xmlLast.get("Name");
            name = (new StringBuilder()).append(name).append(str).toString();
            xmlLast.put("Name", name);
            return;
        }
    }

    private HashMap xmlNames;
    private HashMap xmlLast;
}
