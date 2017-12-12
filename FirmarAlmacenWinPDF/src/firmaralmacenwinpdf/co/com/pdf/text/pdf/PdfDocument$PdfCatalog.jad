// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfDocument.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.ExceptionConverter;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfArray, PdfString, PdfWriter, 
//            PdfName, PdfDocument, PdfIndirectObject, PdfNameTree, 
//            PdfIndirectReference, PdfAction

static class PdfDocument$PdfCatalog extends PdfDictionary
{

    void addNames(TreeMap localDestinations, HashMap documentLevelJS, HashMap documentFileAttachment, PdfWriter writer)
    {
        if(localDestinations.isEmpty() && documentLevelJS.isEmpty() && documentFileAttachment.isEmpty())
            return;
        try
        {
            PdfDictionary names = new PdfDictionary();
            if(!localDestinations.isEmpty())
            {
                PdfArray ar = new PdfArray();
                Iterator i$ = localDestinations.entrySet().iterator();
                do
                {
                    if(!i$.hasNext())
                        break;
                    java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
                    String name = (String)entry.getKey();
                     dest = ()entry.getValue();
                    if(dest.destination != null)
                    {
                        PdfIndirectReference ref = dest.reference;
                        ar.add(new PdfString(name, null));
                        ar.add(ref);
                    }
                } while(true);
                if(ar.size() > 0)
                {
                    PdfDictionary dests = new PdfDictionary();
                    dests.put(PdfName.NAMES, ar);
                    names.put(PdfName.DESTS, writer.addToBody(dests).getIndirectReference());
                }
            }
            if(!documentLevelJS.isEmpty())
            {
                PdfDictionary tree = PdfNameTree.writeTree(documentLevelJS, writer);
                names.put(PdfName.JAVASCRIPT, writer.addToBody(tree).getIndirectReference());
            }
            if(!documentFileAttachment.isEmpty())
                names.put(PdfName.EMBEDDEDFILES, writer.addToBody(PdfNameTree.writeTree(documentFileAttachment, writer)).getIndirectReference());
            if(names.size() > 0)
                put(PdfName.NAMES, writer.addToBody(names).getIndirectReference());
        }
        catch(IOException e)
        {
            throw new ExceptionConverter(e);
        }
    }

    void setOpenAction(PdfAction action)
    {
        put(PdfName.OPENACTION, action);
    }

    void setAdditionalActions(PdfDictionary actions)
    {
        try
        {
            put(PdfName.AA, writer.addToBody(actions).getIndirectReference());
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    PdfWriter writer;

    PdfDocument$PdfCatalog(PdfIndirectReference pages, PdfWriter writer)
    {
        super(CATALOG);
        this.writer = writer;
        put(PdfName.PAGES, pages);
    }
}
