// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HTMLWorker.java

package co.com.pdf.text.html.simpleparser;

import co.com.pdf.text.Chunk;
import co.com.pdf.text.DocListener;
import co.com.pdf.text.DocumentException;
import co.com.pdf.text.Element;
import co.com.pdf.text.ExceptionConverter;
import co.com.pdf.text.FontProvider;
import co.com.pdf.text.Image;
import co.com.pdf.text.List;
import co.com.pdf.text.ListItem;
import co.com.pdf.text.Paragraph;
import co.com.pdf.text.Phrase;
import co.com.pdf.text.Rectangle;
import co.com.pdf.text.TextElementArray;
import co.com.pdf.text.html.HtmlUtilities;
import co.com.pdf.text.log.Logger;
import co.com.pdf.text.log.LoggerFactory;
import co.com.pdf.text.pdf.PdfPTable;
import co.com.pdf.text.pdf.draw.LineSeparator;
import co.com.pdf.text.xml.simpleparser.SimpleXMLDocHandler;
import co.com.pdf.text.xml.simpleparser.SimpleXMLParser;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

// Referenced classes of package co.com.pdf.text.html.simpleparser:
//            StyleSheet, ChainedProperties, ElementFactory, HTMLTagProcessors, 
//            HTMLTagProcessor, ImageProvider, ImageStore, CellWrapper, 
//            LinkProcessor, ImageProcessor, TableWrapper

/**
 * @deprecated Class HTMLWorker is deprecated
 */

public class HTMLWorker
    implements SimpleXMLDocHandler, DocListener
{

    public HTMLWorker(DocListener document)
    {
        this(document, null, null);
    }

    public HTMLWorker(DocListener document, Map tags, StyleSheet style)
    {
        this.style = new StyleSheet();
        stack = new Stack();
        chain = new ChainedProperties();
        providers = new HashMap();
        factory = new ElementFactory();
        tableState = new Stack();
        pendingTR = false;
        pendingTD = false;
        pendingLI = false;
        insidePRE = false;
        skipText = false;
        this.document = document;
        setSupportedTags(tags);
        setStyleSheet(style);
    }

    public void setSupportedTags(Map tags)
    {
        if(tags == null)
            tags = new HTMLTagProcessors();
        this.tags = tags;
    }

    public void setStyleSheet(StyleSheet style)
    {
        if(style == null)
            style = new StyleSheet();
        this.style = style;
    }

    public void parse(Reader reader)
        throws IOException
    {
        LOGGER.info("Please note, there is a more extended version of the HTMLWorker available in the iText XMLWorker");
        SimpleXMLParser.parse(this, null, reader, true);
    }

    public void startDocument()
    {
        HashMap attrs = new HashMap();
        style.applyStyle("body", attrs);
        chain.addToChain("body", attrs);
    }

    public void startElement(String tag, Map attrs)
    {
        HTMLTagProcessor htmlTag = (HTMLTagProcessor)tags.get(tag);
        if(htmlTag == null)
            return;
        style.applyStyle(tag, attrs);
        StyleSheet.resolveStyleAttribute(attrs, chain);
        try
        {
            htmlTag.startElement(this, tag, attrs);
        }
        catch(DocumentException e)
        {
            throw new ExceptionConverter(e);
        }
        catch(IOException e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public void text(String content)
    {
        if(skipText)
            return;
        if(currentParagraph == null)
            currentParagraph = createParagraph();
        if(!insidePRE)
        {
            if(content.trim().length() == 0 && content.indexOf(' ') < 0)
                return;
            content = HtmlUtilities.eliminateWhiteSpace(content);
        }
        Chunk chunk = createChunk(content);
        currentParagraph.add(chunk);
    }

    public void endElement(String tag)
    {
        HTMLTagProcessor htmlTag = (HTMLTagProcessor)tags.get(tag);
        if(htmlTag == null)
            return;
        try
        {
            htmlTag.endElement(this, tag);
        }
        catch(DocumentException e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public void endDocument()
    {
        try
        {
            for(int k = 0; k < stack.size(); k++)
                document.add((Element)stack.elementAt(k));

            if(currentParagraph != null)
                document.add(currentParagraph);
            currentParagraph = null;
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public void newLine()
    {
        if(currentParagraph == null)
            currentParagraph = new Paragraph();
        currentParagraph.add(createChunk("\n"));
    }

    public void carriageReturn()
        throws DocumentException
    {
        if(currentParagraph == null)
            return;
        if(stack.empty())
        {
            document.add(currentParagraph);
        } else
        {
            Element obj = (Element)stack.pop();
            if(obj instanceof TextElementArray)
            {
                TextElementArray current = (TextElementArray)obj;
                current.add(currentParagraph);
            }
            stack.push(obj);
        }
        currentParagraph = null;
    }

    public void flushContent()
    {
        pushToStack(currentParagraph);
        currentParagraph = new Paragraph();
    }

    public void pushToStack(Element element)
    {
        if(element != null)
            stack.push(element);
    }

    public void updateChain(String tag, Map attrs)
    {
        chain.addToChain(tag, attrs);
    }

    public void updateChain(String tag)
    {
        chain.removeChain(tag);
    }

    public void setProviders(Map providers)
    {
        if(providers == null)
            return;
        this.providers = providers;
        FontProvider ff = null;
        if(providers != null)
            ff = (FontProvider)providers.get("font_factory");
        if(ff != null)
            factory.setFontProvider(ff);
    }

    public Chunk createChunk(String content)
    {
        return factory.createChunk(content, chain);
    }

    public Paragraph createParagraph()
    {
        return factory.createParagraph(chain);
    }

    public List createList(String tag)
    {
        return factory.createList(tag, chain);
    }

    public ListItem createListItem()
    {
        return factory.createListItem(chain);
    }

    public LineSeparator createLineSeparator(Map attrs)
    {
        return factory.createLineSeparator(attrs, currentParagraph.getLeading() / 2.0F);
    }

    public Image createImage(Map attrs)
        throws DocumentException, IOException
    {
        String src = (String)attrs.get("src");
        if(src == null)
        {
            return null;
        } else
        {
            Image img = factory.createImage(src, attrs, chain, document, (ImageProvider)providers.get("img_provider"), (ImageStore)providers.get("img_static"), (String)providers.get("img_baseurl"));
            return img;
        }
    }

    public CellWrapper createCell(String tag)
    {
        return new CellWrapper(tag, chain);
    }

    public void processLink()
    {
        if(currentParagraph == null)
            currentParagraph = new Paragraph();
        LinkProcessor i = (LinkProcessor)providers.get("alink_interface");
        if(i == null || !i.process(currentParagraph, chain))
        {
            String href = chain.getProperty("href");
            if(href != null)
            {
                Chunk ck;
                for(Iterator i$ = currentParagraph.getChunks().iterator(); i$.hasNext(); ck.setAnchor(href))
                    ck = (Chunk)i$.next();

            }
        }
        if(stack.isEmpty())
        {
            Paragraph tmp = new Paragraph(new Phrase(currentParagraph));
            currentParagraph = tmp;
        } else
        {
            Paragraph tmp = (Paragraph)stack.pop();
            tmp.add(new Phrase(currentParagraph));
            currentParagraph = tmp;
        }
    }

    public void processList()
        throws DocumentException
    {
        if(stack.empty())
            return;
        Element obj = (Element)stack.pop();
        if(!(obj instanceof List))
        {
            stack.push(obj);
            return;
        }
        if(stack.empty())
            document.add(obj);
        else
            ((TextElementArray)stack.peek()).add(obj);
    }

    public void processListItem()
        throws DocumentException
    {
        if(stack.empty())
            return;
        Element obj = (Element)stack.pop();
        if(!(obj instanceof ListItem))
        {
            stack.push(obj);
            return;
        }
        if(stack.empty())
        {
            document.add(obj);
            return;
        }
        ListItem item = (ListItem)obj;
        Element list = (Element)stack.pop();
        if(!(list instanceof List))
        {
            stack.push(list);
            return;
        } else
        {
            ((List)list).add(item);
            item.adjustListSymbolFont();
            stack.push(list);
            return;
        }
    }

    public void processImage(Image img, Map attrs)
        throws DocumentException
    {
        ImageProcessor processor = (ImageProcessor)providers.get("img_interface");
        if(processor == null || !processor.process(img, attrs, chain, document))
        {
            String align = (String)attrs.get("align");
            if(align != null)
                carriageReturn();
            if(currentParagraph == null)
                currentParagraph = createParagraph();
            currentParagraph.add(new Chunk(img, 0.0F, 0.0F, true));
            currentParagraph.setAlignment(HtmlUtilities.alignmentValue(align));
            if(align != null)
                carriageReturn();
        }
    }

    public void processTable()
        throws DocumentException
    {
        TableWrapper table = (TableWrapper)stack.pop();
        PdfPTable tb = table.createTable();
        tb.setSplitRows(true);
        if(stack.empty())
            document.add(tb);
        else
            ((TextElementArray)stack.peek()).add(tb);
    }

    public void processRow()
    {
        ArrayList row = new ArrayList();
        ArrayList cellWidths = new ArrayList();
        boolean percentage = false;
        float totalWidth = 0.0F;
        int zeroWidth = 0;
        TableWrapper table = null;
        Element obj;
        do
        {
            obj = (Element)stack.pop();
            if(obj instanceof CellWrapper)
            {
                CellWrapper cell = (CellWrapper)obj;
                float width = cell.getWidth();
                cellWidths.add(new Float(width));
                percentage |= cell.isPercentage();
                if(width == 0.0F)
                    zeroWidth++;
                else
                    totalWidth += width;
                row.add(cell.getCell());
            }
        } while(!(obj instanceof TableWrapper));
        table = (TableWrapper)obj;
        table.addRow(row);
        if(cellWidths.size() > 0)
        {
            totalWidth = 100F - totalWidth;
            Collections.reverse(cellWidths);
            float widths[] = new float[cellWidths.size()];
            boolean hasZero = false;
            int i = 0;
            do
            {
                if(i >= widths.length)
                    break;
                widths[i] = ((Float)cellWidths.get(i)).floatValue();
                if(widths[i] == 0.0F && percentage && zeroWidth > 0)
                    widths[i] = totalWidth / (float)zeroWidth;
                if(widths[i] == 0.0F)
                {
                    hasZero = true;
                    break;
                }
                i++;
            } while(true);
            if(!hasZero)
                table.setColWidths(widths);
        }
        stack.push(table);
    }

    public void pushTableState()
    {
        tableState.push(new boolean[] {
            pendingTR, pendingTD
        });
    }

    public void popTableState()
    {
        boolean state[] = (boolean[])tableState.pop();
        pendingTR = state[0];
        pendingTD = state[1];
    }

    public boolean isPendingTR()
    {
        return pendingTR;
    }

    public void setPendingTR(boolean pendingTR)
    {
        this.pendingTR = pendingTR;
    }

    public boolean isPendingTD()
    {
        return pendingTD;
    }

    public void setPendingTD(boolean pendingTD)
    {
        this.pendingTD = pendingTD;
    }

    public boolean isPendingLI()
    {
        return pendingLI;
    }

    public void setPendingLI(boolean pendingLI)
    {
        this.pendingLI = pendingLI;
    }

    public boolean isInsidePRE()
    {
        return insidePRE;
    }

    public void setInsidePRE(boolean insidePRE)
    {
        this.insidePRE = insidePRE;
    }

    public boolean isSkipText()
    {
        return skipText;
    }

    public void setSkipText(boolean skipText)
    {
        this.skipText = skipText;
    }

    public static java.util.List parseToList(Reader reader, StyleSheet style)
        throws IOException
    {
        return parseToList(reader, style, null);
    }

    public static java.util.List parseToList(Reader reader, StyleSheet style, HashMap providers)
        throws IOException
    {
        return parseToList(reader, style, null, providers);
    }

    public static java.util.List parseToList(Reader reader, StyleSheet style, Map tags, HashMap providers)
        throws IOException
    {
        HTMLWorker worker = new HTMLWorker(null, tags, style);
        worker.document = worker;
        worker.setProviders(providers);
        worker.objectList = new ArrayList();
        worker.parse(reader);
        return worker.objectList;
    }

    public boolean add(Element element)
        throws DocumentException
    {
        objectList.add(element);
        return true;
    }

    public void close()
    {
    }

    public boolean newPage()
    {
        return true;
    }

    public void open()
    {
    }

    public void resetPageCount()
    {
    }

    public boolean setMarginMirroring(boolean marginMirroring)
    {
        return false;
    }

    public boolean setMarginMirroringTopBottom(boolean marginMirroring)
    {
        return false;
    }

    public boolean setMargins(float marginLeft, float marginRight, float marginTop, float f)
    {
        return true;
    }

    public void setPageCount(int i)
    {
    }

    public boolean setPageSize(Rectangle pageSize)
    {
        return true;
    }

    /**
     * @deprecated Method setInterfaceProps is deprecated
     */

    public void setInterfaceProps(HashMap providers)
    {
        setProviders(providers);
    }

    /**
     * @deprecated Method getInterfaceProps is deprecated
     */

    public Map getInterfaceProps()
    {
        return providers;
    }

    private static Logger LOGGER = LoggerFactory.getLogger(co/com/pdf/text/html/simpleparser/HTMLWorker);
    protected DocListener document;
    protected Map tags;
    private StyleSheet style;
    protected Stack stack;
    protected Paragraph currentParagraph;
    private final ChainedProperties chain;
    public static final String IMG_PROVIDER = "img_provider";
    public static final String IMG_PROCESSOR = "img_interface";
    public static final String IMG_STORE = "img_static";
    public static final String IMG_BASEURL = "img_baseurl";
    public static final String FONT_PROVIDER = "font_factory";
    public static final String LINK_PROVIDER = "alink_interface";
    private Map providers;
    private final ElementFactory factory;
    private final Stack tableState;
    private boolean pendingTR;
    private boolean pendingTD;
    private boolean pendingLI;
    private boolean insidePRE;
    protected boolean skipText;
    protected java.util.List objectList;

}
