// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfPages.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.DocumentException;
import co.com.pdf.text.ExceptionConverter;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.IOException;
import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfIndirectReference, PdfDictionary, PdfNumber, PdfArray, 
//            PdfObject, PdfWriter, PdfName

public class PdfPages
{

    PdfPages(PdfWriter writer)
    {
        pages = new ArrayList();
        parents = new ArrayList();
        leafSize = 10;
        this.writer = writer;
    }

    void addPage(PdfDictionary page)
    {
        try
        {
            if(pages.size() % leafSize == 0)
                parents.add(writer.getPdfIndirectReference());
            PdfIndirectReference parent = (PdfIndirectReference)parents.get(parents.size() - 1);
            page.put(PdfName.PARENT, parent);
            PdfIndirectReference current = writer.getCurrentPage();
            writer.addToBody(page, current);
            pages.add(current);
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    PdfIndirectReference addPageRef(PdfIndirectReference pageRef)
    {
        try
        {
            if(pages.size() % leafSize == 0)
                parents.add(writer.getPdfIndirectReference());
            pages.add(pageRef);
            return (PdfIndirectReference)parents.get(parents.size() - 1);
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    PdfIndirectReference writePageTree()
        throws IOException
    {
        if(pages.isEmpty())
            throw new IOException(MessageLocalization.getComposedMessage("the.document.has.no.pages", new Object[0]));
        int leaf = 1;
        ArrayList tParents = parents;
        ArrayList tPages = pages;
        ArrayList nextParents = new ArrayList();
        do
        {
            leaf *= leafSize;
            int stdCount = leafSize;
            int rightCount = tPages.size() % leafSize;
            if(rightCount == 0)
                rightCount = leafSize;
            for(int p = 0; p < tParents.size(); p++)
            {
                int thisLeaf = leaf;
                int count;
                if(p == tParents.size() - 1)
                {
                    count = rightCount;
                    thisLeaf = pages.size() % leaf;
                    if(thisLeaf == 0)
                        thisLeaf = leaf;
                } else
                {
                    count = stdCount;
                }
                PdfDictionary top = new PdfDictionary(PdfName.PAGES);
                top.put(PdfName.COUNT, new PdfNumber(thisLeaf));
                PdfArray kids = new PdfArray();
                ArrayList internal = kids.getArrayList();
                internal.addAll(tPages.subList(p * stdCount, p * stdCount + count));
                top.put(PdfName.KIDS, kids);
                if(tParents.size() > 1)
                {
                    if(p % leafSize == 0)
                        nextParents.add(writer.getPdfIndirectReference());
                    top.put(PdfName.PARENT, (PdfObject)nextParents.get(p / leafSize));
                }
                writer.addToBody(top, (PdfIndirectReference)tParents.get(p));
            }

            if(tParents.size() == 1)
            {
                topParent = (PdfIndirectReference)tParents.get(0);
                return topParent;
            }
            tPages = tParents;
            tParents = nextParents;
            nextParents = new ArrayList();
        } while(true);
    }

    PdfIndirectReference getTopParent()
    {
        return topParent;
    }

    void setLinearMode(PdfIndirectReference topParent)
    {
        if(parents.size() > 1)
            throw new RuntimeException(MessageLocalization.getComposedMessage("linear.page.mode.can.only.be.called.with.a.single.parent", new Object[0]));
        if(topParent != null)
        {
            this.topParent = topParent;
            parents.clear();
            parents.add(topParent);
        }
        leafSize = 0x989680;
    }

    void addPage(PdfIndirectReference page)
    {
        pages.add(page);
    }

    int reorderPages(int order[])
        throws DocumentException
    {
        if(order == null)
            return pages.size();
        if(parents.size() > 1)
            throw new DocumentException(MessageLocalization.getComposedMessage("page.reordering.requires.a.single.parent.in.the.page.tree.call.pdfwriter.setlinearmode.after.open", new Object[0]));
        if(order.length != pages.size())
            throw new DocumentException(MessageLocalization.getComposedMessage("page.reordering.requires.an.array.with.the.same.size.as.the.number.of.pages", new Object[0]));
        int max = pages.size();
        boolean temp[] = new boolean[max];
        for(int k = 0; k < max; k++)
        {
            int p = order[k];
            if(p < 1 || p > max)
                throw new DocumentException(MessageLocalization.getComposedMessage("page.reordering.requires.pages.between.1.and.1.found.2", new Object[] {
                    String.valueOf(max), String.valueOf(p)
                }));
            if(temp[p - 1])
                throw new DocumentException(MessageLocalization.getComposedMessage("page.reordering.requires.no.page.repetition.page.1.is.repeated", p));
            temp[p - 1] = true;
        }

        PdfIndirectReference copy[] = (PdfIndirectReference[])pages.toArray(new PdfIndirectReference[pages.size()]);
        for(int k = 0; k < max; k++)
            pages.set(k, copy[order[k] - 1]);

        return max;
    }

    private ArrayList pages;
    private ArrayList parents;
    private int leafSize;
    private PdfWriter writer;
    private PdfIndirectReference topParent;
}
