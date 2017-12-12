// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HTMLTagProcessors.java

package co.com.pdf.text.html.simpleparser;

import co.com.pdf.text.DocumentException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package co.com.pdf.text.html.simpleparser:
//            HTMLTagProcessor, HTMLWorker, TableWrapper

/**
 * @deprecated Class HTMLTagProcessors is deprecated
 */

public class HTMLTagProcessors extends HashMap
{

    public HTMLTagProcessors()
    {
        put("a", A);
        put("b", EM_STRONG_STRIKE_SUP_SUP);
        put("body", DIV);
        put("br", BR);
        put("div", DIV);
        put("em", EM_STRONG_STRIKE_SUP_SUP);
        put("font", SPAN);
        put("h1", H);
        put("h2", H);
        put("h3", H);
        put("h4", H);
        put("h5", H);
        put("h6", H);
        put("hr", HR);
        put("i", EM_STRONG_STRIKE_SUP_SUP);
        put("img", IMG);
        put("li", LI);
        put("ol", UL_OL);
        put("p", DIV);
        put("pre", PRE);
        put("s", EM_STRONG_STRIKE_SUP_SUP);
        put("span", SPAN);
        put("strike", EM_STRONG_STRIKE_SUP_SUP);
        put("strong", EM_STRONG_STRIKE_SUP_SUP);
        put("sub", EM_STRONG_STRIKE_SUP_SUP);
        put("sup", EM_STRONG_STRIKE_SUP_SUP);
        put("table", TABLE);
        put("td", TD);
        put("th", TD);
        put("tr", TR);
        put("u", EM_STRONG_STRIKE_SUP_SUP);
        put("ul", UL_OL);
    }

    public static final HTMLTagProcessor EM_STRONG_STRIKE_SUP_SUP = new HTMLTagProcessor() {

        public void startElement(HTMLWorker worker, String tag, Map attrs)
        {
            tag = mapTag(tag);
            attrs.put(tag, null);
            worker.updateChain(tag, attrs);
        }

        public void endElement(HTMLWorker worker, String tag)
        {
            tag = mapTag(tag);
            worker.updateChain(tag);
        }

        private String mapTag(String tag)
        {
            if("em".equalsIgnoreCase(tag))
                return "i";
            if("strong".equalsIgnoreCase(tag))
                return "b";
            if("strike".equalsIgnoreCase(tag))
                return "s";
            else
                return tag;
        }

    }
;
    public static final HTMLTagProcessor A = new HTMLTagProcessor() {

        public void startElement(HTMLWorker worker, String tag, Map attrs)
        {
            worker.updateChain(tag, attrs);
            worker.flushContent();
        }

        public void endElement(HTMLWorker worker, String tag)
        {
            worker.processLink();
            worker.updateChain(tag);
        }

    }
;
    public static final HTMLTagProcessor BR = new HTMLTagProcessor() {

        public void startElement(HTMLWorker worker, String tag, Map attrs)
        {
            worker.newLine();
        }

        public void endElement(HTMLWorker htmlworker, String s)
        {
        }

    }
;
    public static final HTMLTagProcessor UL_OL = new HTMLTagProcessor() {

        public void startElement(HTMLWorker worker, String tag, Map attrs)
            throws DocumentException
        {
            worker.carriageReturn();
            if(worker.isPendingLI())
                worker.endElement("li");
            worker.setSkipText(true);
            worker.updateChain(tag, attrs);
            worker.pushToStack(worker.createList(tag));
        }

        public void endElement(HTMLWorker worker, String tag)
            throws DocumentException
        {
            worker.carriageReturn();
            if(worker.isPendingLI())
                worker.endElement("li");
            worker.setSkipText(false);
            worker.updateChain(tag);
            worker.processList();
        }

    }
;
    public static final HTMLTagProcessor HR = new HTMLTagProcessor() {

        public void startElement(HTMLWorker worker, String tag, Map attrs)
            throws DocumentException
        {
            worker.carriageReturn();
            worker.pushToStack(worker.createLineSeparator(attrs));
        }

        public void endElement(HTMLWorker htmlworker, String s)
        {
        }

    }
;
    public static final HTMLTagProcessor SPAN = new HTMLTagProcessor() {

        public void startElement(HTMLWorker worker, String tag, Map attrs)
        {
            worker.updateChain(tag, attrs);
        }

        public void endElement(HTMLWorker worker, String tag)
        {
            worker.updateChain(tag);
        }

    }
;
    public static final HTMLTagProcessor H = new HTMLTagProcessor() {

        public void startElement(HTMLWorker worker, String tag, Map attrs)
            throws DocumentException
        {
            worker.carriageReturn();
            if(!attrs.containsKey("size"))
            {
                int v = 7 - Integer.parseInt(tag.substring(1));
                attrs.put("size", Integer.toString(v));
            }
            worker.updateChain(tag, attrs);
        }

        public void endElement(HTMLWorker worker, String tag)
            throws DocumentException
        {
            worker.carriageReturn();
            worker.updateChain(tag);
        }

    }
;
    public static final HTMLTagProcessor LI = new HTMLTagProcessor() {

        public void startElement(HTMLWorker worker, String tag, Map attrs)
            throws DocumentException
        {
            worker.carriageReturn();
            if(worker.isPendingLI())
                worker.endElement(tag);
            worker.setSkipText(false);
            worker.setPendingLI(true);
            worker.updateChain(tag, attrs);
            worker.pushToStack(worker.createListItem());
        }

        public void endElement(HTMLWorker worker, String tag)
            throws DocumentException
        {
            worker.carriageReturn();
            worker.setPendingLI(false);
            worker.setSkipText(true);
            worker.updateChain(tag);
            worker.processListItem();
        }

    }
;
    public static final HTMLTagProcessor PRE = new HTMLTagProcessor() {

        public void startElement(HTMLWorker worker, String tag, Map attrs)
            throws DocumentException
        {
            worker.carriageReturn();
            if(!attrs.containsKey("face"))
                attrs.put("face", "Courier");
            worker.updateChain(tag, attrs);
            worker.setInsidePRE(true);
        }

        public void endElement(HTMLWorker worker, String tag)
            throws DocumentException
        {
            worker.carriageReturn();
            worker.updateChain(tag);
            worker.setInsidePRE(false);
        }

    }
;
    public static final HTMLTagProcessor DIV = new HTMLTagProcessor() {

        public void startElement(HTMLWorker worker, String tag, Map attrs)
            throws DocumentException
        {
            worker.carriageReturn();
            worker.updateChain(tag, attrs);
        }

        public void endElement(HTMLWorker worker, String tag)
            throws DocumentException
        {
            worker.carriageReturn();
            worker.updateChain(tag);
        }

    }
;
    public static final HTMLTagProcessor TABLE = new HTMLTagProcessor() {

        public void startElement(HTMLWorker worker, String tag, Map attrs)
            throws DocumentException
        {
            worker.carriageReturn();
            TableWrapper table = new TableWrapper(attrs);
            worker.pushToStack(table);
            worker.pushTableState();
            worker.setPendingTD(false);
            worker.setPendingTR(false);
            worker.setSkipText(true);
            attrs.remove("align");
            attrs.put("colspan", "1");
            attrs.put("rowspan", "1");
            worker.updateChain(tag, attrs);
        }

        public void endElement(HTMLWorker worker, String tag)
            throws DocumentException
        {
            worker.carriageReturn();
            if(worker.isPendingTR())
                worker.endElement("tr");
            worker.updateChain(tag);
            worker.processTable();
            worker.popTableState();
            worker.setSkipText(false);
        }

    }
;
    public static final HTMLTagProcessor TR = new HTMLTagProcessor() {

        public void startElement(HTMLWorker worker, String tag, Map attrs)
            throws DocumentException
        {
            worker.carriageReturn();
            if(worker.isPendingTR())
                worker.endElement(tag);
            worker.setSkipText(true);
            worker.setPendingTR(true);
            worker.updateChain(tag, attrs);
        }

        public void endElement(HTMLWorker worker, String tag)
            throws DocumentException
        {
            worker.carriageReturn();
            if(worker.isPendingTD())
                worker.endElement("td");
            worker.setPendingTR(false);
            worker.updateChain(tag);
            worker.processRow();
            worker.setSkipText(true);
        }

    }
;
    public static final HTMLTagProcessor TD = new HTMLTagProcessor() {

        public void startElement(HTMLWorker worker, String tag, Map attrs)
            throws DocumentException
        {
            worker.carriageReturn();
            if(worker.isPendingTD())
                worker.endElement(tag);
            worker.setSkipText(false);
            worker.setPendingTD(true);
            worker.updateChain("td", attrs);
            worker.pushToStack(worker.createCell(tag));
        }

        public void endElement(HTMLWorker worker, String tag)
            throws DocumentException
        {
            worker.carriageReturn();
            worker.setPendingTD(false);
            worker.updateChain("td");
            worker.setSkipText(true);
        }

    }
;
    public static final HTMLTagProcessor IMG = new HTMLTagProcessor() {

        public void startElement(HTMLWorker worker, String tag, Map attrs)
            throws DocumentException, IOException
        {
            worker.updateChain(tag, attrs);
            worker.processImage(worker.createImage(attrs), attrs);
            worker.updateChain(tag);
        }

        public void endElement(HTMLWorker htmlworker, String s)
        {
        }

    }
;
    private static final long serialVersionUID = 0xf2b0055fb8193958L;

}
