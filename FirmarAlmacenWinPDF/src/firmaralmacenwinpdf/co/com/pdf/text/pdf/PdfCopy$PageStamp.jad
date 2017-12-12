// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfCopy.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.ExceptionConverter;
import co.com.pdf.text.Rectangle;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PageResources, PdfArray, ByteBuffer, PdfStream, 
//            PdfFormField, PdfAnnotation, PdfRectangle, PdfObject, 
//            PdfIndirectReference, PdfName, PdfDictionary, PdfCopy, 
//            PdfReader, PdfContents, PdfIndirectObject, PdfContentByte

public static class PdfCopy$PageStamp
{

    public PdfContentByte getUnderContent()
    {
        if(under == null)
        {
            if(pageResources == null)
            {
                pageResources = new PageResources();
                PdfDictionary resources = pageN.getAsDict(PdfName.RESOURCES);
                pageResources.setOriginalResources(resources, cstp.namePtr);
            }
            under = new nt(cstp, pageResources);
        }
        return under;
    }

    public PdfContentByte getOverContent()
    {
        if(over == null)
        {
            if(pageResources == null)
            {
                pageResources = new PageResources();
                PdfDictionary resources = pageN.getAsDict(PdfName.RESOURCES);
                pageResources.setOriginalResources(resources, cstp.namePtr);
            }
            over = new nt(cstp, pageResources);
        }
        return over;
    }

    public void alterContents()
        throws IOException
    {
        if(over == null && under == null)
            return;
        PdfArray ar = null;
        PdfObject content = PdfReader.getPdfObject(pageN.get(PdfName.CONTENTS), pageN);
        if(content == null)
        {
            ar = new PdfArray();
            pageN.put(PdfName.CONTENTS, ar);
        } else
        if(content.isArray())
            ar = (PdfArray)content;
        else
        if(content.isStream())
        {
            ar = new PdfArray();
            ar.add(pageN.get(PdfName.CONTENTS));
            pageN.put(PdfName.CONTENTS, ar);
        } else
        {
            ar = new PdfArray();
            pageN.put(PdfName.CONTENTS, ar);
        }
        ByteBuffer out = new ByteBuffer();
        if(under != null)
        {
            out.append(PdfContents.SAVESTATE);
            applyRotation(pageN, out);
            out.append(under.getInternalBuffer());
            out.append(PdfContents.RESTORESTATE);
        }
        if(over != null)
            out.append(PdfContents.SAVESTATE);
        PdfStream stream = new PdfStream(out.toByteArray());
        stream.flateCompress(cstp.getCompressionLevel());
        PdfIndirectReference ref1 = cstp.addToBody(stream).getIndirectReference();
        ar.addFirst(ref1);
        out.reset();
        if(over != null)
        {
            out.append(' ');
            out.append(PdfContents.RESTORESTATE);
            out.append(PdfContents.SAVESTATE);
            applyRotation(pageN, out);
            out.append(over.getInternalBuffer());
            out.append(PdfContents.RESTORESTATE);
            stream = new PdfStream(out.toByteArray());
            stream.flateCompress(cstp.getCompressionLevel());
            ar.add(cstp.addToBody(stream).getIndirectReference());
        }
        pageN.put(PdfName.RESOURCES, pageResources.getResources());
    }

    void applyRotation(PdfDictionary pageN, ByteBuffer out)
    {
        if(!PdfCopy.access$000(cstp))
            return;
        Rectangle page = reader.getPageSizeWithRotation(pageN);
        int rotation = page.getRotation();
        switch(rotation)
        {
        case 90: // 'Z'
            out.append(PdfContents.ROTATE90);
            out.append(page.getTop());
            out.append(' ').append('0').append(PdfContents.ROTATEFINAL);
            break;

        case 180: 
            out.append(PdfContents.ROTATE180);
            out.append(page.getRight());
            out.append(' ');
            out.append(page.getTop());
            out.append(PdfContents.ROTATEFINAL);
            break;

        case 270: 
            out.append(PdfContents.ROTATE270);
            out.append('0').append(' ');
            out.append(page.getRight());
            out.append(PdfContents.ROTATEFINAL);
            break;
        }
    }

    private void addDocumentField(PdfIndirectReference ref)
    {
        if(cstp.fieldArray == null)
            cstp.fieldArray = new PdfArray();
        cstp.fieldArray.add(ref);
    }

    private void expandFields(PdfFormField field, ArrayList allAnnots)
    {
        allAnnots.add(field);
        ArrayList kids = field.getKids();
        if(kids != null)
        {
            PdfFormField f;
            for(Iterator i$ = kids.iterator(); i$.hasNext(); expandFields(f, allAnnots))
                f = (PdfFormField)i$.next();

        }
    }

    public void addAnnotation(PdfAnnotation annot)
    {
        ArrayList allAnnots;
        PdfFormField field;
        try
        {
            allAnnots = new ArrayList();
            if(!annot.isForm())
                break MISSING_BLOCK_LABEL_61;
            field = (PdfFormField)annot;
            if(field.getParent() != null)
                return;
        }
        catch(IOException e)
        {
            throw new ExceptionConverter(e);
        }
        expandFields(field, allAnnots);
        if(cstp.fieldTemplates == null)
            cstp.fieldTemplates = new HashSet();
        break MISSING_BLOCK_LABEL_67;
        allAnnots.add(annot);
        for(int k = 0; k < allAnnots.size(); k++)
        {
            annot = (PdfAnnotation)allAnnots.get(k);
            if(annot.isForm())
            {
                if(!annot.isUsed())
                {
                    HashSet templates = annot.getTemplates();
                    if(templates != null)
                        cstp.fieldTemplates.addAll(templates);
                }
                PdfFormField field = (PdfFormField)annot;
                if(field.getParent() == null)
                    addDocumentField(field.getIndirectReference());
            }
            if(annot.isAnnotation())
            {
                PdfObject pdfobj = PdfReader.getPdfObject(pageN.get(PdfName.ANNOTS), pageN);
                PdfArray annots = null;
                if(pdfobj == null || !pdfobj.isArray())
                {
                    annots = new PdfArray();
                    pageN.put(PdfName.ANNOTS, annots);
                } else
                {
                    annots = (PdfArray)pdfobj;
                }
                annots.add(annot.getIndirectReference());
                if(!annot.isUsed())
                {
                    PdfRectangle rect = (PdfRectangle)annot.get(PdfName.RECT);
                    if(rect != null && (rect.left() != 0.0F || rect.right() != 0.0F || rect.top() != 0.0F || rect.bottom() != 0.0F))
                    {
                        int rotation = reader.getPageRotation(pageN);
                        Rectangle pageSize = reader.getPageSizeWithRotation(pageN);
                        switch(rotation)
                        {
                        case 90: // 'Z'
                            annot.put(PdfName.RECT, new PdfRectangle(pageSize.getTop() - rect.bottom(), rect.left(), pageSize.getTop() - rect.top(), rect.right()));
                            break;

                        case 180: 
                            annot.put(PdfName.RECT, new PdfRectangle(pageSize.getRight() - rect.left(), pageSize.getTop() - rect.bottom(), pageSize.getRight() - rect.right(), pageSize.getTop() - rect.top()));
                            break;

                        case 270: 
                            annot.put(PdfName.RECT, new PdfRectangle(rect.bottom(), pageSize.getRight() - rect.left(), rect.top(), pageSize.getRight() - rect.right()));
                            break;
                        }
                    }
                }
            }
            if(!annot.isUsed())
            {
                annot.setUsed();
                cstp.addToBody(annot, annot.getIndirectReference());
            }
        }

        break MISSING_BLOCK_LABEL_555;
    }

    PdfDictionary pageN;
    nt under;
    nt over;
    PageResources pageResources;
    PdfReader reader;
    PdfCopy cstp;

    PdfCopy$PageStamp(PdfReader reader, PdfDictionary pageN, PdfCopy cstp)
    {
        this.pageN = pageN;
        this.reader = reader;
        this.cstp = cstp;
    }
}
