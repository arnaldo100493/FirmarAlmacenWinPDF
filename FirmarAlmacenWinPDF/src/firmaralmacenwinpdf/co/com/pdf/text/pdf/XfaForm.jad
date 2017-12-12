// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XfaForm.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.ExceptionConverter;
import co.com.pdf.text.xml.XmlDomWriter;
import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfArray, PRStream, PdfStream, 
//            PdfReader, PdfObject, PdfWriter, PdfString, 
//            PdfName, PdfIndirectObject, AcroFields

public class XfaForm
{
    public static class Xml2SomTemplate extends Xml2Som
    {

        public String getFieldType(String s)
        {
            Node n = (Node)name2Node.get(s);
            if(n == null)
                return null;
            if("exclGroup".equals(n.getLocalName()))
                return "exclGroup";
            Node ui;
            for(ui = n.getFirstChild(); ui != null && (ui.getNodeType() != 1 || !"ui".equals(ui.getLocalName())); ui = ui.getNextSibling());
            if(ui == null)
                return null;
            for(Node type = ui.getFirstChild(); type != null; type = type.getNextSibling())
                if(type.getNodeType() == 1 && (!"extras".equals(type.getLocalName()) || !"picture".equals(type.getLocalName())))
                    return type.getLocalName();

            return null;
        }

        private void processTemplate(Node n, HashMap ff)
        {
            if(ff == null)
                ff = new HashMap();
            HashMap ss = new HashMap();
            for(Node n2 = n.getFirstChild(); n2 != null; n2 = n2.getNextSibling())
            {
                if(n2.getNodeType() != 1)
                    continue;
                String s = n2.getLocalName();
                if("subform".equals(s))
                {
                    Node name = n2.getAttributes().getNamedItem("name");
                    String nn = "#subform";
                    boolean annon = true;
                    if(name != null)
                    {
                        nn = escapeSom(name.getNodeValue());
                        annon = false;
                    }
                    Integer i;
                    if(annon)
                    {
                        i = Integer.valueOf(anform);
                        anform++;
                    } else
                    {
                        i = (Integer)ss.get(nn);
                        if(i == null)
                            i = Integer.valueOf(0);
                        else
                            i = Integer.valueOf(i.intValue() + 1);
                        ss.put(nn, i);
                    }
                    stack.push((new StringBuilder()).append(nn).append("[").append(i.toString()).append("]").toString());
                    templateLevel++;
                    if(annon)
                        processTemplate(n2, ff);
                    else
                        processTemplate(n2, null);
                    templateLevel--;
                    stack.pop();
                    continue;
                }
                if("field".equals(s) || "exclGroup".equals(s))
                {
                    Node name = n2.getAttributes().getNamedItem("name");
                    if(name == null)
                        continue;
                    String nn = escapeSom(name.getNodeValue());
                    Integer i = (Integer)ff.get(nn);
                    if(i == null)
                        i = Integer.valueOf(0);
                    else
                        i = Integer.valueOf(i.intValue() + 1);
                    ff.put(nn, i);
                    stack.push((new StringBuilder()).append(nn).append("[").append(i.toString()).append("]").toString());
                    String unstack = printStack();
                    order.add(unstack);
                    inverseSearchAdd(unstack);
                    name2Node.put(unstack, n2);
                    stack.pop();
                    continue;
                }
                if(dynamicForm || templateLevel <= 0 || !"occur".equals(s))
                    continue;
                int initial = 1;
                int min = 1;
                int max = 1;
                Node a = n2.getAttributes().getNamedItem("initial");
                if(a != null)
                    try
                    {
                        initial = Integer.parseInt(a.getNodeValue().trim());
                    }
                    catch(Exception e) { }
                a = n2.getAttributes().getNamedItem("min");
                if(a != null)
                    try
                    {
                        min = Integer.parseInt(a.getNodeValue().trim());
                    }
                    catch(Exception e) { }
                a = n2.getAttributes().getNamedItem("max");
                if(a != null)
                    try
                    {
                        max = Integer.parseInt(a.getNodeValue().trim());
                    }
                    catch(Exception e) { }
                if(initial != min || min != max)
                    dynamicForm = true;
            }

        }

        public boolean isDynamicForm()
        {
            return dynamicForm;
        }

        public void setDynamicForm(boolean dynamicForm)
        {
            this.dynamicForm = dynamicForm;
        }

        private boolean dynamicForm;
        private int templateLevel;

        public Xml2SomTemplate(Node n)
        {
            order = new ArrayList();
            name2Node = new HashMap();
            stack = new Stack2();
            anform = 0;
            templateLevel = 0;
            inverseSearch = new HashMap();
            processTemplate(n, null);
        }
    }

    public static class AcroFieldsSearch extends Xml2Som
    {

        public HashMap getAcroShort2LongName()
        {
            return acroShort2LongName;
        }

        public void setAcroShort2LongName(HashMap acroShort2LongName)
        {
            this.acroShort2LongName = acroShort2LongName;
        }

        private HashMap acroShort2LongName;

        public AcroFieldsSearch(Collection items)
        {
            inverseSearch = new HashMap();
            acroShort2LongName = new HashMap();
            String itemName;
            String itemShort;
            for(Iterator i$ = items.iterator(); i$.hasNext(); inverseSearchAdd(inverseSearch, splitParts(itemShort), itemName))
            {
                String string = (String)i$.next();
                itemName = string;
                itemShort = getShortName(itemName);
                acroShort2LongName.put(itemShort, itemName);
            }

        }
    }

    public static class Xml2SomDatasets extends Xml2Som
    {

        public Node insertNode(Node n, String shortName)
        {
            Stack2 stack = splitParts(shortName);
            Document doc = n.getOwnerDocument();
            Node n2 = null;
            for(n = n.getFirstChild(); n.getNodeType() != 1; n = n.getNextSibling());
            for(int k = 0; k < stack.size(); k++)
            {
                String part = (String)stack.get(k);
                int idx = part.lastIndexOf('[');
                String name = part.substring(0, idx);
                idx = Integer.parseInt(part.substring(idx + 1, part.length() - 1));
                int found = -1;
                for(n2 = n.getFirstChild(); n2 != null; n2 = n2.getNextSibling())
                {
                    if(n2.getNodeType() != 1)
                        continue;
                    String s = escapeSom(n2.getLocalName());
                    if(s.equals(name) && ++found == idx)
                        break;
                }

                for(; found < idx; found++)
                {
                    n2 = doc.createElementNS(null, name);
                    n2 = n.appendChild(n2);
                    Node attr = doc.createAttributeNS("http://www.xfa.org/schema/xfa-data/1.0/", "dataNode");
                    attr.setNodeValue("dataGroup");
                    n2.getAttributes().setNamedItemNS(attr);
                }

                n = n2;
            }

            inverseSearchAdd(inverseSearch, stack, shortName);
            name2Node.put(shortName, n2);
            order.add(shortName);
            return n2;
        }

        private static boolean hasChildren(Node n)
        {
            Node dataNodeN = n.getAttributes().getNamedItemNS("http://www.xfa.org/schema/xfa-data/1.0/", "dataNode");
            if(dataNodeN != null)
            {
                String dataNode = dataNodeN.getNodeValue();
                if("dataGroup".equals(dataNode))
                    return true;
                if("dataValue".equals(dataNode))
                    return false;
            }
            if(!n.hasChildNodes())
                return false;
            for(Node n2 = n.getFirstChild(); n2 != null; n2 = n2.getNextSibling())
                if(n2.getNodeType() == 1)
                    return true;

            return false;
        }

        private void processDatasetsInternal(Node n)
        {
            if(n != null)
            {
                HashMap ss = new HashMap();
                for(Node n2 = n.getFirstChild(); n2 != null; n2 = n2.getNextSibling())
                {
                    if(n2.getNodeType() != 1)
                        continue;
                    String s = escapeSom(n2.getLocalName());
                    Integer i = (Integer)ss.get(s);
                    if(i == null)
                        i = Integer.valueOf(0);
                    else
                        i = Integer.valueOf(i.intValue() + 1);
                    ss.put(s, i);
                    if(hasChildren(n2))
                    {
                        stack.push((new StringBuilder()).append(s).append("[").append(i.toString()).append("]").toString());
                        processDatasetsInternal(n2);
                        stack.pop();
                    } else
                    {
                        stack.push((new StringBuilder()).append(s).append("[").append(i.toString()).append("]").toString());
                        String unstack = printStack();
                        order.add(unstack);
                        inverseSearchAdd(unstack);
                        name2Node.put(unstack, n2);
                        stack.pop();
                    }
                }

            }
        }

        public Xml2SomDatasets(Node n)
        {
            order = new ArrayList();
            name2Node = new HashMap();
            stack = new Stack2();
            anform = 0;
            inverseSearch = new HashMap();
            processDatasetsInternal(n);
        }
    }

    public static class Xml2Som
    {

        public static String escapeSom(String s)
        {
            if(s == null)
                return "";
            int idx = s.indexOf('.');
            if(idx < 0)
                return s;
            StringBuffer sb = new StringBuffer();
            int last = 0;
            for(; idx >= 0; idx = s.indexOf('.', idx + 1))
            {
                sb.append(s.substring(last, idx));
                sb.append('\\');
                last = idx;
            }

            sb.append(s.substring(last));
            return sb.toString();
        }

        public static String unescapeSom(String s)
        {
            int idx = s.indexOf('\\');
            if(idx < 0)
                return s;
            StringBuffer sb = new StringBuffer();
            int last = 0;
            for(; idx >= 0; idx = s.indexOf('\\', idx + 1))
            {
                sb.append(s.substring(last, idx));
                last = idx + 1;
            }

            sb.append(s.substring(last));
            return sb.toString();
        }

        protected String printStack()
        {
            if(stack.empty())
                return "";
            StringBuffer s = new StringBuffer();
            for(int k = 0; k < stack.size(); k++)
                s.append('.').append((String)stack.get(k));

            return s.substring(1);
        }

        public static String getShortName(String s)
        {
            int idx = s.indexOf(".#subform[");
            if(idx < 0)
                return s;
            int last = 0;
            StringBuffer sb = new StringBuffer();
            for(; idx >= 0; idx = s.indexOf(".#subform[", last))
            {
                sb.append(s.substring(last, idx));
                idx = s.indexOf("]", idx + 10);
                if(idx < 0)
                    return sb.toString();
                last = idx + 1;
            }

            sb.append(s.substring(last));
            return sb.toString();
        }

        public void inverseSearchAdd(String unstack)
        {
            inverseSearchAdd(inverseSearch, stack, unstack);
        }

        public static void inverseSearchAdd(HashMap inverseSearch, Stack2 stack, String unstack)
        {
            String last = (String)stack.peek();
            InverseStore store = (InverseStore)inverseSearch.get(last);
            if(store == null)
            {
                store = new InverseStore();
                inverseSearch.put(last, store);
            }
            for(int k = stack.size() - 2; k >= 0; k--)
            {
                last = (String)stack.get(k);
                int idx = store.part.indexOf(last);
                InverseStore store2;
                if(idx < 0)
                {
                    store.part.add(last);
                    store2 = new InverseStore();
                    store.follow.add(store2);
                } else
                {
                    store2 = (InverseStore)store.follow.get(idx);
                }
                store = store2;
            }

            store.part.add("");
            store.follow.add(unstack);
        }

        public String inverseSearchGlobal(ArrayList parts)
        {
            if(parts.isEmpty())
                return null;
            InverseStore store = (InverseStore)inverseSearch.get(parts.get(parts.size() - 1));
            if(store == null)
                return null;
            for(int k = parts.size() - 2; k >= 0; k--)
            {
                String part = (String)parts.get(k);
                int idx = store.part.indexOf(part);
                if(idx < 0)
                    if(store.isSimilar(part))
                        return null;
                    else
                        return store.getDefaultName();
                store = (InverseStore)store.follow.get(idx);
            }

            return store.getDefaultName();
        }

        public static Stack2 splitParts(String name)
        {
            for(; name.startsWith("."); name = name.substring(1));
            Stack2 parts = new Stack2();
            int last = 0;
            int pos = 0;
            String part;
            do
            {
                pos = last;
                do
                {
                    pos = name.indexOf('.', pos);
                    if(pos < 0 || name.charAt(pos - 1) != '\\')
                        break;
                    pos++;
                } while(true);
                if(pos < 0)
                    break;
                part = name.substring(last, pos);
                if(!part.endsWith("]"))
                    part = (new StringBuilder()).append(part).append("[0]").toString();
                parts.add(part);
                last = pos + 1;
            } while(true);
            part = name.substring(last);
            if(!part.endsWith("]"))
                part = (new StringBuilder()).append(part).append("[0]").toString();
            parts.add(part);
            return parts;
        }

        public ArrayList getOrder()
        {
            return order;
        }

        public void setOrder(ArrayList order)
        {
            this.order = order;
        }

        public HashMap getName2Node()
        {
            return name2Node;
        }

        public void setName2Node(HashMap name2Node)
        {
            this.name2Node = name2Node;
        }

        public HashMap getInverseSearch()
        {
            return inverseSearch;
        }

        public void setInverseSearch(HashMap inverseSearch)
        {
            this.inverseSearch = inverseSearch;
        }

        protected ArrayList order;
        protected HashMap name2Node;
        protected HashMap inverseSearch;
        protected Stack2 stack;
        protected int anform;

        public Xml2Som()
        {
        }
    }

    public static class Stack2 extends ArrayList
    {

        public Object peek()
        {
            if(size() == 0)
                throw new EmptyStackException();
            else
                return get(size() - 1);
        }

        public Object pop()
        {
            if(size() == 0)
            {
                throw new EmptyStackException();
            } else
            {
                Object ret = get(size() - 1);
                remove(size() - 1);
                return ret;
            }
        }

        public Object push(Object item)
        {
            add(item);
            return item;
        }

        public boolean empty()
        {
            return size() == 0;
        }

        private static final long serialVersionUID = 0x9897098898ffc494L;

        public Stack2()
        {
        }
    }

    public static class InverseStore
    {

        public String getDefaultName()
        {
            InverseStore store = this;
            do
            {
                Object obj = store.follow.get(0);
                if(obj instanceof String)
                    return (String)obj;
                store = (InverseStore)obj;
            } while(true);
        }

        public boolean isSimilar(String name)
        {
            int idx = name.indexOf('[');
            name = name.substring(0, idx + 1);
            for(int k = 0; k < part.size(); k++)
                if(((String)part.get(k)).startsWith(name))
                    return true;

            return false;
        }

        protected ArrayList part;
        protected ArrayList follow;

        public InverseStore()
        {
            part = new ArrayList();
            follow = new ArrayList();
        }
    }


    public XfaForm()
    {
    }

    public static PdfObject getXfaObject(PdfReader reader)
    {
        PdfDictionary af = (PdfDictionary)PdfReader.getPdfObjectRelease(reader.getCatalog().get(PdfName.ACROFORM));
        if(af == null)
            return null;
        else
            return PdfReader.getPdfObjectRelease(af.get(PdfName.XFA));
    }

    public XfaForm(PdfReader reader)
        throws IOException, ParserConfigurationException, SAXException
    {
        this.reader = reader;
        PdfObject xfa = getXfaObject(reader);
        if(xfa == null)
        {
            xfaPresent = false;
            return;
        }
        xfaPresent = true;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        if(xfa.isArray())
        {
            PdfArray ar = (PdfArray)xfa;
            for(int k = 1; k < ar.size(); k += 2)
            {
                PdfObject ob = ar.getDirectObject(k);
                if(ob instanceof PRStream)
                {
                    byte b[] = PdfReader.getStreamBytes((PRStream)ob);
                    bout.write(b);
                }
            }

        } else
        if(xfa instanceof PRStream)
        {
            byte b[] = PdfReader.getStreamBytes((PRStream)xfa);
            bout.write(b);
        }
        bout.close();
        DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
        fact.setNamespaceAware(true);
        DocumentBuilder db = fact.newDocumentBuilder();
        domDocument = db.parse(new ByteArrayInputStream(bout.toByteArray()));
        extractNodes();
    }

    private void extractNodes()
    {
        Map xfaNodes = extractXFANodes(domDocument);
        if(xfaNodes.containsKey("template"))
        {
            templateNode = (Node)xfaNodes.get("template");
            templateSom = new Xml2SomTemplate(templateNode);
        }
        if(xfaNodes.containsKey("datasets"))
        {
            datasetsNode = (Node)xfaNodes.get("datasets");
            datasetsSom = new Xml2SomDatasets(datasetsNode.getFirstChild());
        }
        if(datasetsNode == null)
            createDatasetsNode(domDocument.getFirstChild());
    }

    public static Map extractXFANodes(Document domDocument)
    {
        Map xfaNodes = new HashMap();
        Node n;
        for(n = domDocument.getFirstChild(); n.getChildNodes().getLength() == 0; n = n.getNextSibling());
        for(n = n.getFirstChild(); n != null; n = n.getNextSibling())
            if(n.getNodeType() == 1)
            {
                String s = n.getLocalName();
                xfaNodes.put(s, n);
            }

        return xfaNodes;
    }

    private void createDatasetsNode(Node n)
    {
        for(; n.getChildNodes().getLength() == 0; n = n.getNextSibling());
        if(n != null)
        {
            Element e = n.getOwnerDocument().createElement("xfa:datasets");
            e.setAttribute("xmlns:xfa", "http://www.xfa.org/schema/xfa-data/1.0/");
            datasetsNode = e;
            n.appendChild(datasetsNode);
        }
    }

    public static void setXfa(XfaForm form, PdfReader reader, PdfWriter writer)
        throws IOException
    {
        PdfDictionary af = (PdfDictionary)PdfReader.getPdfObjectRelease(reader.getCatalog().get(PdfName.ACROFORM));
        if(af == null)
            return;
        PdfObject xfa = getXfaObject(reader);
        if(xfa.isArray())
        {
            PdfArray ar = (PdfArray)xfa;
            int t = -1;
            int d = -1;
            for(int k = 0; k < ar.size(); k += 2)
            {
                PdfString s = ar.getAsString(k);
                if("template".equals(s.toString()))
                    t = k + 1;
                if("datasets".equals(s.toString()))
                    d = k + 1;
            }

            if(t > -1 && d > -1)
            {
                reader.killXref(ar.getAsIndirectObject(t));
                reader.killXref(ar.getAsIndirectObject(d));
                PdfStream tStream = new PdfStream(serializeDoc(form.templateNode));
                tStream.flateCompress(writer.getCompressionLevel());
                ar.set(t, writer.addToBody(tStream).getIndirectReference());
                PdfStream dStream = new PdfStream(serializeDoc(form.datasetsNode));
                dStream.flateCompress(writer.getCompressionLevel());
                ar.set(d, writer.addToBody(dStream).getIndirectReference());
                af.put(PdfName.XFA, new PdfArray(ar));
                return;
            }
        }
        reader.killXref(af.get(PdfName.XFA));
        PdfStream str = new PdfStream(serializeDoc(form.domDocument));
        str.flateCompress(writer.getCompressionLevel());
        PdfIndirectReference ref = writer.addToBody(str).getIndirectReference();
        af.put(PdfName.XFA, ref);
    }

    public void setXfa(PdfWriter writer)
        throws IOException
    {
        setXfa(this, reader, writer);
    }

    public static byte[] serializeDoc(Node n)
        throws IOException
    {
        XmlDomWriter xw = new XmlDomWriter();
        ByteArrayOutputStream fout = new ByteArrayOutputStream();
        xw.setOutput(fout, null);
        xw.setCanonical(false);
        xw.write(n);
        fout.close();
        return fout.toByteArray();
    }

    public boolean isXfaPresent()
    {
        return xfaPresent;
    }

    public Document getDomDocument()
    {
        return domDocument;
    }

    public String findFieldName(String name, AcroFields af)
    {
        Map items = af.getFields();
        if(items.containsKey(name))
            return name;
        if(acroFieldsSom == null)
            if(items.isEmpty() && xfaPresent)
                acroFieldsSom = new AcroFieldsSearch(datasetsSom.getName2Node().keySet());
            else
                acroFieldsSom = new AcroFieldsSearch(items.keySet());
        if(acroFieldsSom.getAcroShort2LongName().containsKey(name))
            return (String)acroFieldsSom.getAcroShort2LongName().get(name);
        else
            return acroFieldsSom.inverseSearchGlobal(Xml2Som.splitParts(name));
    }

    public String findDatasetsName(String name)
    {
        if(datasetsSom.getName2Node().containsKey(name))
            return name;
        else
            return datasetsSom.inverseSearchGlobal(Xml2Som.splitParts(name));
    }

    public Node findDatasetsNode(String name)
    {
        if(name == null)
            return null;
        name = findDatasetsName(name);
        if(name == null)
            return null;
        else
            return (Node)datasetsSom.getName2Node().get(name);
    }

    public static String getNodeText(Node n)
    {
        if(n == null)
            return "";
        else
            return getNodeText(n, "");
    }

    private static String getNodeText(Node n, String name)
    {
        for(Node n2 = n.getFirstChild(); n2 != null; n2 = n2.getNextSibling())
        {
            if(n2.getNodeType() == 1)
            {
                name = getNodeText(n2, name);
                continue;
            }
            if(n2.getNodeType() == 3)
                name = (new StringBuilder()).append(name).append(n2.getNodeValue()).toString();
        }

        return name;
    }

    public void setNodeText(Node n, String text)
    {
        if(n == null)
            return;
        for(Node nc = null; (nc = n.getFirstChild()) != null;)
            n.removeChild(nc);

        if(n.getAttributes().getNamedItemNS("http://www.xfa.org/schema/xfa-data/1.0/", "dataNode") != null)
            n.getAttributes().removeNamedItemNS("http://www.xfa.org/schema/xfa-data/1.0/", "dataNode");
        n.appendChild(domDocument.createTextNode(text));
        changed = true;
    }

    public void setXfaPresent(boolean xfaPresent)
    {
        this.xfaPresent = xfaPresent;
    }

    public void setDomDocument(Document domDocument)
    {
        this.domDocument = domDocument;
        extractNodes();
    }

    public PdfReader getReader()
    {
        return reader;
    }

    public void setReader(PdfReader reader)
    {
        this.reader = reader;
    }

    public boolean isChanged()
    {
        return changed;
    }

    public void setChanged(boolean changed)
    {
        this.changed = changed;
    }

    public Xml2SomTemplate getTemplateSom()
    {
        return templateSom;
    }

    public void setTemplateSom(Xml2SomTemplate templateSom)
    {
        this.templateSom = templateSom;
    }

    public Xml2SomDatasets getDatasetsSom()
    {
        return datasetsSom;
    }

    public void setDatasetsSom(Xml2SomDatasets datasetsSom)
    {
        this.datasetsSom = datasetsSom;
    }

    public AcroFieldsSearch getAcroFieldsSom()
    {
        return acroFieldsSom;
    }

    public void setAcroFieldsSom(AcroFieldsSearch acroFieldsSom)
    {
        this.acroFieldsSom = acroFieldsSom;
    }

    public Node getDatasetsNode()
    {
        return datasetsNode;
    }

    public void fillXfaForm(File file)
        throws IOException
    {
        fillXfaForm(file, false);
    }

    public void fillXfaForm(File file, boolean readOnly)
        throws IOException
    {
        fillXfaForm(((InputStream) (new FileInputStream(file))), readOnly);
    }

    public void fillXfaForm(InputStream is)
        throws IOException
    {
        fillXfaForm(is, false);
    }

    public void fillXfaForm(InputStream is, boolean readOnly)
        throws IOException
    {
        fillXfaForm(new InputSource(is), readOnly);
    }

    public void fillXfaForm(InputSource is)
        throws IOException
    {
        fillXfaForm(is, false);
    }

    public void fillXfaForm(InputSource is, boolean readOnly)
        throws IOException
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try
        {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document newdoc = db.parse(is);
            fillXfaForm(((Node) (newdoc.getDocumentElement())), readOnly);
        }
        catch(ParserConfigurationException e)
        {
            throw new ExceptionConverter(e);
        }
        catch(SAXException e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public void fillXfaForm(Node node)
    {
        fillXfaForm(node, false);
    }

    public void fillXfaForm(Node node, boolean readOnly)
    {
        if(readOnly)
        {
            NodeList nodeList = domDocument.getElementsByTagName("field");
            for(int i = 0; i < nodeList.getLength(); i++)
                ((Element)nodeList.item(i)).setAttribute("access", "readOnly");

        }
        NodeList allChilds = datasetsNode.getChildNodes();
        int len = allChilds.getLength();
        Node data = null;
        int k = 0;
        do
        {
            if(k >= len)
                break;
            Node n = allChilds.item(k);
            if(n.getNodeType() == 1 && n.getLocalName().equals("data") && "http://www.xfa.org/schema/xfa-data/1.0/".equals(n.getNamespaceURI()))
            {
                data = n;
                break;
            }
            k++;
        } while(true);
        if(data == null)
        {
            data = datasetsNode.getOwnerDocument().createElementNS("http://www.xfa.org/schema/xfa-data/1.0/", "xfa:data");
            datasetsNode.appendChild(data);
        }
        NodeList list = data.getChildNodes();
        if(list.getLength() == 0)
        {
            data.appendChild(domDocument.importNode(node, true));
        } else
        {
            Node firstNode = getFirstElementNode(data);
            if(firstNode != null)
                data.replaceChild(domDocument.importNode(node, true), firstNode);
        }
        extractNodes();
        setChanged(true);
    }

    private Node getFirstElementNode(Node src)
    {
        Node result = null;
        NodeList list = src.getChildNodes();
        int i = 0;
        do
        {
            if(i >= list.getLength())
                break;
            if(list.item(i).getNodeType() == 1)
            {
                result = list.item(i);
                break;
            }
            i++;
        } while(true);
        return result;
    }

    private Xml2SomTemplate templateSom;
    private Node templateNode;
    private Xml2SomDatasets datasetsSom;
    private Node datasetsNode;
    private AcroFieldsSearch acroFieldsSom;
    private PdfReader reader;
    private boolean xfaPresent;
    private Document domDocument;
    private boolean changed;
    public static final String XFA_DATA_SCHEMA = "http://www.xfa.org/schema/xfa-data/1.0/";
}
