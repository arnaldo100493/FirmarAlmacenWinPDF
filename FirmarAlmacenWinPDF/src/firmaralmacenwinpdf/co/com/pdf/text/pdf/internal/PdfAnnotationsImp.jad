// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfAnnotationsImp.java

package co.com.pdf.text.pdf.internal;

import co.com.pdf.text.*;
import co.com.pdf.text.pdf.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class PdfAnnotationsImp
{

    public PdfAnnotationsImp(PdfWriter writer)
    {
        delayedAnnotations = new ArrayList();
        acroForm = new PdfAcroForm(writer);
    }

    public boolean hasValidAcroForm()
    {
        return acroForm.isValid();
    }

    public PdfAcroForm getAcroForm()
    {
        return acroForm;
    }

    public void setSigFlags(int f)
    {
        acroForm.setSigFlags(f);
    }

    public void addCalculationOrder(PdfFormField formField)
    {
        acroForm.addCalculationOrder(formField);
    }

    public void addAnnotation(PdfAnnotation annot)
    {
        if(annot.isForm())
        {
            PdfFormField field = (PdfFormField)annot;
            if(field.getParent() == null)
                addFormFieldRaw(field);
        } else
        {
            annotations.add(annot);
        }
    }

    public void addPlainAnnotation(PdfAnnotation annot)
    {
        annotations.add(annot);
    }

    void addFormFieldRaw(PdfFormField field)
    {
        annotations.add(field);
        ArrayList kids = field.getKids();
        if(kids != null)
        {
            for(int k = 0; k < kids.size(); k++)
                addFormFieldRaw((PdfFormField)kids.get(k));

        }
    }

    public boolean hasUnusedAnnotations()
    {
        return !annotations.isEmpty();
    }

    public void resetAnnotations()
    {
        annotations = delayedAnnotations;
        delayedAnnotations = new ArrayList();
    }

    public PdfArray rotateAnnotations(PdfWriter writer, Rectangle pageSize)
    {
        PdfArray array = new PdfArray();
        int rotation = pageSize.getRotation() % 360;
        int currentPage = writer.getCurrentPageNumber();
        for(int k = 0; k < annotations.size(); k++)
        {
            PdfAnnotation dic = (PdfAnnotation)annotations.get(k);
            int page = dic.getPlaceInPage();
            if(page > currentPage)
            {
                delayedAnnotations.add(dic);
                continue;
            }
            if(dic.isForm())
            {
                if(!dic.isUsed())
                {
                    java.util.HashSet templates = dic.getTemplates();
                    if(templates != null)
                        acroForm.addFieldTemplates(templates);
                }
                PdfFormField field = (PdfFormField)dic;
                if(field.getParent() == null)
                    acroForm.addDocumentField(field.getIndirectReference());
            }
            if(dic.isAnnotation())
            {
                array.add(dic.getIndirectReference());
                if(!dic.isUsed())
                {
                    PdfArray tmp = dic.getAsArray(PdfName.RECT);
                    PdfRectangle rect;
                    if(tmp.size() == 4)
                        rect = new PdfRectangle(tmp.getAsNumber(0).floatValue(), tmp.getAsNumber(1).floatValue(), tmp.getAsNumber(2).floatValue(), tmp.getAsNumber(3).floatValue());
                    else
                        rect = new PdfRectangle(tmp.getAsNumber(0).floatValue(), tmp.getAsNumber(1).floatValue());
                    if(rect != null)
                        switch(rotation)
                        {
                        case 90: // 'Z'
                            dic.put(PdfName.RECT, new PdfRectangle(pageSize.getTop() - rect.bottom(), rect.left(), pageSize.getTop() - rect.top(), rect.right()));
                            break;

                        case 180: 
                            dic.put(PdfName.RECT, new PdfRectangle(pageSize.getRight() - rect.left(), pageSize.getTop() - rect.bottom(), pageSize.getRight() - rect.right(), pageSize.getTop() - rect.top()));
                            break;

                        case 270: 
                            dic.put(PdfName.RECT, new PdfRectangle(rect.bottom(), pageSize.getRight() - rect.left(), rect.top(), pageSize.getRight() - rect.right()));
                            break;
                        }
                }
            }
            if(dic.isUsed())
                continue;
            dic.setUsed();
            try
            {
                writer.addToBody(dic, dic.getIndirectReference());
            }
            catch(IOException e)
            {
                throw new ExceptionConverter(e);
            }
        }

        return array;
    }

    public static PdfAnnotation convertAnnotation(PdfWriter writer, Annotation annot, Rectangle defaultRect)
        throws IOException
    {
        switch(annot.annotationType())
        {
        case 1: // '\001'
            return new PdfAnnotation(writer, annot.llx(), annot.lly(), annot.urx(), annot.ury(), new PdfAction((URL)annot.attributes().get("url")));

        case 2: // '\002'
            return new PdfAnnotation(writer, annot.llx(), annot.lly(), annot.urx(), annot.ury(), new PdfAction((String)annot.attributes().get("file")));

        case 3: // '\003'
            return new PdfAnnotation(writer, annot.llx(), annot.lly(), annot.urx(), annot.ury(), new PdfAction((String)annot.attributes().get("file"), (String)annot.attributes().get("destination")));

        case 7: // '\007'
            boolean sparams[] = (boolean[])(boolean[])annot.attributes().get("parameters");
            String fname = (String)annot.attributes().get("file");
            String mimetype = (String)annot.attributes().get("mime");
            PdfFileSpecification fs;
            if(sparams[0])
                fs = PdfFileSpecification.fileEmbedded(writer, fname, fname, null);
            else
                fs = PdfFileSpecification.fileExtern(writer, fname);
            PdfAnnotation ann = PdfAnnotation.createScreen(writer, new Rectangle(annot.llx(), annot.lly(), annot.urx(), annot.ury()), fname, fs, mimetype, sparams[1]);
            return ann;

        case 4: // '\004'
            return new PdfAnnotation(writer, annot.llx(), annot.lly(), annot.urx(), annot.ury(), new PdfAction((String)annot.attributes().get("file"), ((Integer)annot.attributes().get("page")).intValue()));

        case 5: // '\005'
            return new PdfAnnotation(writer, annot.llx(), annot.lly(), annot.urx(), annot.ury(), new PdfAction(((Integer)annot.attributes().get("named")).intValue()));

        case 6: // '\006'
            return new PdfAnnotation(writer, annot.llx(), annot.lly(), annot.urx(), annot.ury(), new PdfAction((String)annot.attributes().get("application"), (String)annot.attributes().get("parameters"), (String)annot.attributes().get("operation"), (String)annot.attributes().get("defaultdir")));
        }
        return new PdfAnnotation(writer, defaultRect.getLeft(), defaultRect.getBottom(), defaultRect.getRight(), defaultRect.getTop(), new PdfString(annot.title(), "UnicodeBig"), new PdfString(annot.content(), "UnicodeBig"));
    }

    protected PdfAcroForm acroForm;
    protected ArrayList annotations;
    protected ArrayList delayedAnnotations;
}
