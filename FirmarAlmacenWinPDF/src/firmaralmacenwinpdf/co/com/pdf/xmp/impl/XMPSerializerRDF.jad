// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMPSerializerRDF.java

package co.com.pdf.xmp.impl;

import co.com.pdf.xmp.*;
import co.com.pdf.xmp.options.PropertyOptions;
import co.com.pdf.xmp.options.SerializeOptions;
import java.io.*;
import java.util.*;

// Referenced classes of package co.com.pdf.xmp.impl:
//            CountOutputStream, XMPMetaImpl, XMPNode, QName, 
//            XMPNodeUtils, Utils

public class XMPSerializerRDF
{

    public XMPSerializerRDF()
    {
        unicodeSize = 1;
    }

    public void serialize(XMPMeta xmp, OutputStream out, SerializeOptions options)
        throws XMPException
    {
        try
        {
            outputStream = new CountOutputStream(out);
            writer = new OutputStreamWriter(outputStream, options.getEncoding());
            this.xmp = (XMPMetaImpl)xmp;
            this.options = options;
            padding = options.getPadding();
            writer = new OutputStreamWriter(outputStream, options.getEncoding());
            checkOptionsConsistence();
            String tailStr = serializeAsRDF();
            writer.flush();
            addPadding(tailStr.length());
            write(tailStr);
            writer.flush();
            outputStream.close();
        }
        catch(IOException e)
        {
            throw new XMPException("Error writing to the OutputStream", 0);
        }
    }

    private void addPadding(int tailLength)
        throws XMPException, IOException
    {
        if(options.getExactPacketLength())
        {
            int minSize = outputStream.getBytesWritten() + tailLength * unicodeSize;
            if(minSize > padding)
                throw new XMPException("Can't fit into specified packet size", 107);
            padding -= minSize;
        }
        padding /= unicodeSize;
        int newlineLen = options.getNewline().length();
        if(padding >= newlineLen)
        {
            for(padding -= newlineLen; padding >= 100 + newlineLen; padding -= 100 + newlineLen)
            {
                writeChars(100, ' ');
                writeNewline();
            }

            writeChars(padding, ' ');
            writeNewline();
        } else
        {
            writeChars(padding, ' ');
        }
    }

    protected void checkOptionsConsistence()
        throws XMPException
    {
        if(options.getEncodeUTF16BE() | options.getEncodeUTF16LE())
            unicodeSize = 2;
        if(options.getExactPacketLength())
        {
            if(options.getOmitPacketWrapper() | options.getIncludeThumbnailPad())
                throw new XMPException("Inconsistent options for exact size serialize", 103);
            if((options.getPadding() & unicodeSize - 1) != 0)
                throw new XMPException("Exact size must be a multiple of the Unicode element", 103);
        } else
        if(options.getReadOnlyPacket())
        {
            if(options.getOmitPacketWrapper() | options.getIncludeThumbnailPad())
                throw new XMPException("Inconsistent options for read-only packet", 103);
            padding = 0;
        } else
        if(options.getOmitPacketWrapper())
        {
            if(options.getIncludeThumbnailPad())
                throw new XMPException("Inconsistent options for non-packet serialize", 103);
            padding = 0;
        } else
        {
            if(padding == 0)
                padding = 2048 * unicodeSize;
            if(options.getIncludeThumbnailPad() && !xmp.doesPropertyExist("http://ns.adobe.com/xap/1.0/", "Thumbnails"))
                padding += 10000 * unicodeSize;
        }
    }

    private String serializeAsRDF()
        throws IOException, XMPException
    {
        int level = 0;
        if(!options.getOmitPacketWrapper())
        {
            writeIndent(level);
            write("<?xpacket begin=\"\uFEFF\" id=\"W5M0MpCehiHzreSzNTczkc9d\"?>");
            writeNewline();
        }
        if(!options.getOmitXmpMetaElement())
        {
            writeIndent(level);
            write("<x:xmpmeta xmlns:x=\"adobe:ns:meta/\" x:xmptk=\"");
            if(!options.getOmitVersionAttribute())
                write(XMPMetaFactory.getVersionInfo().getMessage());
            write("\">");
            writeNewline();
            level++;
        }
        writeIndent(level);
        write("<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">");
        writeNewline();
        if(options.getUseCanonicalFormat())
            serializeCanonicalRDFSchemas(level);
        else
            serializeCompactRDFSchemas(level);
        writeIndent(level);
        write("</rdf:RDF>");
        writeNewline();
        if(!options.getOmitXmpMetaElement())
        {
            level--;
            writeIndent(level);
            write("</x:xmpmeta>");
            writeNewline();
        }
        String tailStr = "";
        if(!options.getOmitPacketWrapper())
        {
            for(level = options.getBaseIndent(); level > 0; level--)
                tailStr = (new StringBuilder()).append(tailStr).append(options.getIndent()).toString();

            tailStr = (new StringBuilder()).append(tailStr).append("<?xpacket end=\"").toString();
            tailStr = (new StringBuilder()).append(tailStr).append(options.getReadOnlyPacket() ? 'r' : 'w').toString();
            tailStr = (new StringBuilder()).append(tailStr).append("\"?>").toString();
        }
        return tailStr;
    }

    private void serializeCanonicalRDFSchemas(int level)
        throws IOException, XMPException
    {
        if(xmp.getRoot().getChildrenLength() > 0)
        {
            startOuterRDFDescription(xmp.getRoot(), level);
            XMPNode currSchema;
            for(Iterator it = xmp.getRoot().iterateChildren(); it.hasNext(); serializeCanonicalRDFSchema(currSchema, level))
                currSchema = (XMPNode)it.next();

            endOuterRDFDescription(level);
        } else
        {
            writeIndent(level + 1);
            write("<rdf:Description rdf:about=");
            writeTreeName();
            write("/>");
            writeNewline();
        }
    }

    private void writeTreeName()
        throws IOException
    {
        write(34);
        String name = xmp.getRoot().getName();
        if(name != null)
            appendNodeValue(name, true);
        write(34);
    }

    private void serializeCompactRDFSchemas(int level)
        throws IOException, XMPException
    {
        writeIndent(level + 1);
        write("<rdf:Description rdf:about=");
        writeTreeName();
        Set usedPrefixes = new HashSet();
        usedPrefixes.add("xml");
        usedPrefixes.add("rdf");
        XMPNode schema;
        for(Iterator it = xmp.getRoot().iterateChildren(); it.hasNext(); declareUsedNamespaces(schema, usedPrefixes, level + 3))
            schema = (XMPNode)it.next();

        boolean allAreAttrs = true;
        for(Iterator it = xmp.getRoot().iterateChildren(); it.hasNext();)
        {
            XMPNode schema = (XMPNode)it.next();
            allAreAttrs &= serializeCompactRDFAttrProps(schema, level + 2);
        }

        if(!allAreAttrs)
        {
            write(62);
            writeNewline();
        } else
        {
            write("/>");
            writeNewline();
            return;
        }
        XMPNode schema;
        for(Iterator it = xmp.getRoot().iterateChildren(); it.hasNext(); serializeCompactRDFElementProps(schema, level + 2))
            schema = (XMPNode)it.next();

        writeIndent(level + 1);
        write("</rdf:Description>");
        writeNewline();
    }

    private boolean serializeCompactRDFAttrProps(XMPNode parentNode, int indent)
        throws IOException
    {
        boolean allAreAttrs = true;
        for(Iterator it = parentNode.iterateChildren(); it.hasNext();)
        {
            XMPNode prop = (XMPNode)it.next();
            if(canBeRDFAttrProp(prop))
            {
                writeNewline();
                writeIndent(indent);
                write(prop.getName());
                write("=\"");
                appendNodeValue(prop.getValue(), true);
                write(34);
            } else
            {
                allAreAttrs = false;
            }
        }

        return allAreAttrs;
    }

    private void serializeCompactRDFElementProps(XMPNode parentNode, int indent)
        throws IOException, XMPException
    {
        Iterator it = parentNode.iterateChildren();
        do
        {
            if(!it.hasNext())
                break;
            XMPNode node = (XMPNode)it.next();
            if(!canBeRDFAttrProp(node))
            {
                boolean emitEndTag = true;
                boolean indentEndTag = true;
                String elemName = node.getName();
                if("[]".equals(elemName))
                    elemName = "rdf:li";
                writeIndent(indent);
                write(60);
                write(elemName);
                boolean hasGeneralQualifiers = false;
                boolean hasRDFResourceQual = false;
                for(Iterator iq = node.iterateQualifier(); iq.hasNext();)
                {
                    XMPNode qualifier = (XMPNode)iq.next();
                    if(!RDF_ATTR_QUALIFIER.contains(qualifier.getName()))
                    {
                        hasGeneralQualifiers = true;
                    } else
                    {
                        hasRDFResourceQual = "rdf:resource".equals(qualifier.getName());
                        write(32);
                        write(qualifier.getName());
                        write("=\"");
                        appendNodeValue(qualifier.getValue(), true);
                        write(34);
                    }
                }

                if(hasGeneralQualifiers)
                    serializeCompactRDFGeneralQualifier(indent, node);
                else
                if(!node.getOptions().isCompositeProperty())
                {
                    Object result[] = serializeCompactRDFSimpleProp(node);
                    emitEndTag = ((Boolean)result[0]).booleanValue();
                    indentEndTag = ((Boolean)result[1]).booleanValue();
                } else
                if(node.getOptions().isArray())
                    serializeCompactRDFArrayProp(node, indent);
                else
                    emitEndTag = serializeCompactRDFStructProp(node, indent, hasRDFResourceQual);
                if(emitEndTag)
                {
                    if(indentEndTag)
                        writeIndent(indent);
                    write("</");
                    write(elemName);
                    write(62);
                    writeNewline();
                }
            }
        } while(true);
    }

    private Object[] serializeCompactRDFSimpleProp(XMPNode node)
        throws IOException
    {
        Boolean emitEndTag = Boolean.TRUE;
        Boolean indentEndTag = Boolean.TRUE;
        if(node.getOptions().isURI())
        {
            write(" rdf:resource=\"");
            appendNodeValue(node.getValue(), true);
            write("\"/>");
            writeNewline();
            emitEndTag = Boolean.FALSE;
        } else
        if(node.getValue() == null || node.getValue().length() == 0)
        {
            write("/>");
            writeNewline();
            emitEndTag = Boolean.FALSE;
        } else
        {
            write(62);
            appendNodeValue(node.getValue(), false);
            indentEndTag = Boolean.FALSE;
        }
        return (new Object[] {
            emitEndTag, indentEndTag
        });
    }

    private void serializeCompactRDFArrayProp(XMPNode node, int indent)
        throws IOException, XMPException
    {
        write(62);
        writeNewline();
        emitRDFArrayTag(node, true, indent + 1);
        if(node.getOptions().isArrayAltText())
            XMPNodeUtils.normalizeLangArray(node);
        serializeCompactRDFElementProps(node, indent + 2);
        emitRDFArrayTag(node, false, indent + 1);
    }

    private boolean serializeCompactRDFStructProp(XMPNode node, int indent, boolean hasRDFResourceQual)
        throws XMPException, IOException
    {
        boolean hasAttrFields = false;
        boolean hasElemFields = false;
        boolean emitEndTag = true;
        Iterator ic = node.iterateChildren();
        do
        {
            if(!ic.hasNext())
                break;
            XMPNode field = (XMPNode)ic.next();
            if(canBeRDFAttrProp(field))
                hasAttrFields = true;
            else
                hasElemFields = true;
        } while(!hasAttrFields || !hasElemFields);
        if(hasRDFResourceQual && hasElemFields)
            throw new XMPException("Can't mix rdf:resource qualifier and element fields", 202);
        if(!node.hasChildren())
        {
            write(" rdf:parseType=\"Resource\"/>");
            writeNewline();
            emitEndTag = false;
        } else
        if(!hasElemFields)
        {
            serializeCompactRDFAttrProps(node, indent + 1);
            write("/>");
            writeNewline();
            emitEndTag = false;
        } else
        if(!hasAttrFields)
        {
            write(" rdf:parseType=\"Resource\">");
            writeNewline();
            serializeCompactRDFElementProps(node, indent + 1);
        } else
        {
            write(62);
            writeNewline();
            writeIndent(indent + 1);
            write("<rdf:Description");
            serializeCompactRDFAttrProps(node, indent + 2);
            write(">");
            writeNewline();
            serializeCompactRDFElementProps(node, indent + 1);
            writeIndent(indent + 1);
            write("</rdf:Description>");
            writeNewline();
        }
        return emitEndTag;
    }

    private void serializeCompactRDFGeneralQualifier(int indent, XMPNode node)
        throws IOException, XMPException
    {
        write(" rdf:parseType=\"Resource\">");
        writeNewline();
        serializeCanonicalRDFProperty(node, false, true, indent + 1);
        XMPNode qualifier;
        for(Iterator iq = node.iterateQualifier(); iq.hasNext(); serializeCanonicalRDFProperty(qualifier, false, false, indent + 1))
            qualifier = (XMPNode)iq.next();

    }

    private void serializeCanonicalRDFSchema(XMPNode schemaNode, int level)
        throws IOException, XMPException
    {
        XMPNode propNode;
        for(Iterator it = schemaNode.iterateChildren(); it.hasNext(); serializeCanonicalRDFProperty(propNode, options.getUseCanonicalFormat(), false, level + 2))
            propNode = (XMPNode)it.next();

    }

    private void declareUsedNamespaces(XMPNode node, Set usedPrefixes, int indent)
        throws IOException
    {
        if(node.getOptions().isSchemaNode())
        {
            String prefix = node.getValue().substring(0, node.getValue().length() - 1);
            declareNamespace(prefix, node.getName(), usedPrefixes, indent);
        } else
        if(node.getOptions().isStruct())
        {
            XMPNode field;
            for(Iterator it = node.iterateChildren(); it.hasNext(); declareNamespace(field.getName(), null, usedPrefixes, indent))
                field = (XMPNode)it.next();

        }
        XMPNode child;
        for(Iterator it = node.iterateChildren(); it.hasNext(); declareUsedNamespaces(child, usedPrefixes, indent))
            child = (XMPNode)it.next();

        XMPNode qualifier;
        for(Iterator it = node.iterateQualifier(); it.hasNext(); declareUsedNamespaces(qualifier, usedPrefixes, indent))
        {
            qualifier = (XMPNode)it.next();
            declareNamespace(qualifier.getName(), null, usedPrefixes, indent);
        }

    }

    private void declareNamespace(String prefix, String namespace, Set usedPrefixes, int indent)
        throws IOException
    {
        if(namespace == null)
        {
            QName qname = new QName(prefix);
            if(qname.hasPrefix())
            {
                prefix = qname.getPrefix();
                namespace = XMPMetaFactory.getSchemaRegistry().getNamespaceURI((new StringBuilder()).append(prefix).append(":").toString());
                declareNamespace(prefix, namespace, usedPrefixes, indent);
            } else
            {
                return;
            }
        }
        if(!usedPrefixes.contains(prefix))
        {
            writeNewline();
            writeIndent(indent);
            write("xmlns:");
            write(prefix);
            write("=\"");
            write(namespace);
            write(34);
            usedPrefixes.add(prefix);
        }
    }

    private void startOuterRDFDescription(XMPNode schemaNode, int level)
        throws IOException
    {
        writeIndent(level + 1);
        write("<rdf:Description rdf:about=");
        writeTreeName();
        Set usedPrefixes = new HashSet();
        usedPrefixes.add("xml");
        usedPrefixes.add("rdf");
        declareUsedNamespaces(schemaNode, usedPrefixes, level + 3);
        write(62);
        writeNewline();
    }

    private void endOuterRDFDescription(int level)
        throws IOException
    {
        writeIndent(level + 1);
        write("</rdf:Description>");
        writeNewline();
    }

    private void serializeCanonicalRDFProperty(XMPNode node, boolean useCanonicalRDF, boolean emitAsRDFValue, int indent)
        throws IOException, XMPException
    {
        boolean emitEndTag = true;
        boolean indentEndTag = true;
        String elemName = node.getName();
        if(emitAsRDFValue)
            elemName = "rdf:value";
        else
        if("[]".equals(elemName))
            elemName = "rdf:li";
        writeIndent(indent);
        write(60);
        write(elemName);
        boolean hasGeneralQualifiers = false;
        boolean hasRDFResourceQual = false;
        Iterator it = node.iterateQualifier();
        do
        {
            if(!it.hasNext())
                break;
            XMPNode qualifier = (XMPNode)it.next();
            if(!RDF_ATTR_QUALIFIER.contains(qualifier.getName()))
            {
                hasGeneralQualifiers = true;
            } else
            {
                hasRDFResourceQual = "rdf:resource".equals(qualifier.getName());
                if(!emitAsRDFValue)
                {
                    write(32);
                    write(qualifier.getName());
                    write("=\"");
                    appendNodeValue(qualifier.getValue(), true);
                    write(34);
                }
            }
        } while(true);
        if(hasGeneralQualifiers && !emitAsRDFValue)
        {
            if(hasRDFResourceQual)
                throw new XMPException("Can't mix rdf:resource and general qualifiers", 202);
            if(useCanonicalRDF)
            {
                write(">");
                writeNewline();
                indent++;
                writeIndent(indent);
                write("<rdf:Description");
                write(">");
            } else
            {
                write(" rdf:parseType=\"Resource\">");
            }
            writeNewline();
            serializeCanonicalRDFProperty(node, useCanonicalRDF, true, indent + 1);
            it = node.iterateQualifier();
            do
            {
                if(!it.hasNext())
                    break;
                XMPNode qualifier = (XMPNode)it.next();
                if(!RDF_ATTR_QUALIFIER.contains(qualifier.getName()))
                    serializeCanonicalRDFProperty(qualifier, useCanonicalRDF, false, indent + 1);
            } while(true);
            if(useCanonicalRDF)
            {
                writeIndent(indent);
                write("</rdf:Description>");
                writeNewline();
                indent--;
            }
        } else
        if(!node.getOptions().isCompositeProperty())
        {
            if(node.getOptions().isURI())
            {
                write(" rdf:resource=\"");
                appendNodeValue(node.getValue(), true);
                write("\"/>");
                writeNewline();
                emitEndTag = false;
            } else
            if(node.getValue() == null || "".equals(node.getValue()))
            {
                write("/>");
                writeNewline();
                emitEndTag = false;
            } else
            {
                write(62);
                appendNodeValue(node.getValue(), false);
                indentEndTag = false;
            }
        } else
        if(node.getOptions().isArray())
        {
            write(62);
            writeNewline();
            emitRDFArrayTag(node, true, indent + 1);
            if(node.getOptions().isArrayAltText())
                XMPNodeUtils.normalizeLangArray(node);
            XMPNode child;
            for(it = node.iterateChildren(); it.hasNext(); serializeCanonicalRDFProperty(child, useCanonicalRDF, false, indent + 2))
                child = (XMPNode)it.next();

            emitRDFArrayTag(node, false, indent + 1);
        } else
        if(!hasRDFResourceQual)
        {
            if(!node.hasChildren())
            {
                if(useCanonicalRDF)
                {
                    write(">");
                    writeNewline();
                    writeIndent(indent + 1);
                    write("<rdf:Description/>");
                } else
                {
                    write(" rdf:parseType=\"Resource\"/>");
                    emitEndTag = false;
                }
                writeNewline();
            } else
            {
                if(useCanonicalRDF)
                {
                    write(">");
                    writeNewline();
                    indent++;
                    writeIndent(indent);
                    write("<rdf:Description");
                    write(">");
                } else
                {
                    write(" rdf:parseType=\"Resource\">");
                }
                writeNewline();
                XMPNode child;
                for(it = node.iterateChildren(); it.hasNext(); serializeCanonicalRDFProperty(child, useCanonicalRDF, false, indent + 1))
                    child = (XMPNode)it.next();

                if(useCanonicalRDF)
                {
                    writeIndent(indent);
                    write("</rdf:Description>");
                    writeNewline();
                    indent--;
                }
            }
        } else
        {
            for(it = node.iterateChildren(); it.hasNext(); write(34))
            {
                XMPNode child = (XMPNode)it.next();
                if(!canBeRDFAttrProp(child))
                    throw new XMPException("Can't mix rdf:resource and complex fields", 202);
                writeNewline();
                writeIndent(indent + 1);
                write(32);
                write(child.getName());
                write("=\"");
                appendNodeValue(child.getValue(), true);
            }

            write("/>");
            writeNewline();
            emitEndTag = false;
        }
        if(emitEndTag)
        {
            if(indentEndTag)
                writeIndent(indent);
            write("</");
            write(elemName);
            write(62);
            writeNewline();
        }
    }

    private void emitRDFArrayTag(XMPNode arrayNode, boolean isStartTag, int indent)
        throws IOException
    {
        if(isStartTag || arrayNode.hasChildren())
        {
            writeIndent(indent);
            write(isStartTag ? "<rdf:" : "</rdf:");
            if(arrayNode.getOptions().isArrayAlternate())
                write("Alt");
            else
            if(arrayNode.getOptions().isArrayOrdered())
                write("Seq");
            else
                write("Bag");
            if(isStartTag && !arrayNode.hasChildren())
                write("/>");
            else
                write(">");
            writeNewline();
        }
    }

    private void appendNodeValue(String value, boolean forAttribute)
        throws IOException
    {
        if(value == null)
            value = "";
        write(Utils.escapeXML(value, forAttribute, true));
    }

    private boolean canBeRDFAttrProp(XMPNode node)
    {
        return !node.hasQualifier() && !node.getOptions().isURI() && !node.getOptions().isCompositeProperty() && !"[]".equals(node.getName());
    }

    private void writeIndent(int times)
        throws IOException
    {
        for(int i = options.getBaseIndent() + times; i > 0; i--)
            writer.write(options.getIndent());

    }

    private void write(int c)
        throws IOException
    {
        writer.write(c);
    }

    private void write(String str)
        throws IOException
    {
        writer.write(str);
    }

    private void writeChars(int number, char c)
        throws IOException
    {
        for(; number > 0; number--)
            writer.write(c);

    }

    private void writeNewline()
        throws IOException
    {
        writer.write(options.getNewline());
    }

    private static final int DEFAULT_PAD = 2048;
    private static final String PACKET_HEADER = "<?xpacket begin=\"\uFEFF\" id=\"W5M0MpCehiHzreSzNTczkc9d\"?>";
    private static final String PACKET_TRAILER = "<?xpacket end=\"";
    private static final String PACKET_TRAILER2 = "\"?>";
    private static final String RDF_XMPMETA_START = "<x:xmpmeta xmlns:x=\"adobe:ns:meta/\" x:xmptk=\"";
    private static final String RDF_XMPMETA_END = "</x:xmpmeta>";
    private static final String RDF_RDF_START = "<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">";
    private static final String RDF_RDF_END = "</rdf:RDF>";
    private static final String RDF_SCHEMA_START = "<rdf:Description rdf:about=";
    private static final String RDF_SCHEMA_END = "</rdf:Description>";
    private static final String RDF_STRUCT_START = "<rdf:Description";
    private static final String RDF_STRUCT_END = "</rdf:Description>";
    private static final String RDF_EMPTY_STRUCT = "<rdf:Description/>";
    static final Set RDF_ATTR_QUALIFIER = new HashSet(Arrays.asList(new String[] {
        "xml:lang", "rdf:resource", "rdf:ID", "rdf:bagID", "rdf:nodeID"
    }));
    private XMPMetaImpl xmp;
    private CountOutputStream outputStream;
    private OutputStreamWriter writer;
    private SerializeOptions options;
    private int unicodeSize;
    private int padding;

}
