// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfAnnotation.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.Rectangle;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.util.HashMap;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfArray, PRIndirectReference, PdfNumber, PdfAnnotation, 
//            PdfDictionary, PdfIndirectReference, PdfReader, PdfName, 
//            PdfWriter

public static class PdfAnnotation$PdfImportedLink
{

    public boolean isInternal()
    {
        return destination != null;
    }

    public int getDestinationPage()
    {
        if(!isInternal())
            return 0;
        PdfIndirectReference ref = destination.getAsIndirectObject(0);
        PRIndirectReference pr = (PRIndirectReference)ref;
        PdfReader r = pr.getReader();
        for(int i = 1; i <= r.getNumberOfPages(); i++)
        {
            PRIndirectReference pp = r.getPageOrigRef(i);
            if(pp.getGeneration() == pr.getGeneration() && pp.getNumber() == pr.getNumber())
                return i;
        }

        throw new IllegalArgumentException(MessageLocalization.getComposedMessage("page.not.found", new Object[0]));
    }

    public void setDestinationPage(int newPage)
    {
        if(!isInternal())
        {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("cannot.change.destination.of.external.link", new Object[0]));
        } else
        {
            this.newPage = newPage;
            return;
        }
    }

    public void transformDestination(float a, float b, float c, float d, float e, float f)
    {
        if(!isInternal())
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("cannot.change.destination.of.external.link", new Object[0]));
        if(destination.getAsName(1).equals(PdfName.XYZ))
        {
            float x = destination.getAsNumber(2).floatValue();
            float y = destination.getAsNumber(3).floatValue();
            float xx = x * a + y * c + e;
            float yy = x * b + y * d + f;
            destination.set(2, new PdfNumber(xx));
            destination.set(3, new PdfNumber(yy));
        }
    }

    public void transformRect(float a, float b, float c, float d, float e, float f)
    {
        float x = llx * a + lly * c + e;
        float y = llx * b + lly * d + f;
        llx = x;
        lly = y;
        x = urx * a + ury * c + e;
        y = urx * b + ury * d + f;
        urx = x;
        ury = y;
    }

    public PdfAnnotation createAnnotation(PdfWriter writer)
    {
        PdfAnnotation annotation = new PdfAnnotation(writer, new Rectangle(llx, lly, urx, ury));
        if(newPage != 0)
        {
            PdfIndirectReference ref = writer.getPageReference(newPage);
            destination.set(0, ref);
        }
        if(destination != null)
            annotation.put(PdfName.DEST, destination);
        annotation.hashMap.putAll(parameters);
        return annotation;
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer("Imported link: location [");
        buf.append(llx);
        buf.append(' ');
        buf.append(lly);
        buf.append(' ');
        buf.append(urx);
        buf.append(' ');
        buf.append(ury);
        buf.append("] destination ");
        buf.append(destination);
        buf.append(" parameters ");
        buf.append(parameters);
        return buf.toString();
    }

    float llx;
    float lly;
    float urx;
    float ury;
    HashMap parameters;
    PdfArray destination;
    int newPage;

    PdfAnnotation$PdfImportedLink(PdfDictionary annotation)
    {
        parameters = new HashMap();
        destination = null;
        newPage = 0;
        parameters.putAll(annotation.hashMap);
        try
        {
            destination = (PdfArray)parameters.remove(PdfName.DEST);
        }
        catch(ClassCastException ex)
        {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("you.have.to.consolidate.the.named.destinations.of.your.reader", new Object[0]));
        }
        if(destination != null)
            destination = new PdfArray(destination);
        PdfArray rc = (PdfArray)parameters.remove(PdfName.RECT);
        llx = rc.getAsNumber(0).floatValue();
        lly = rc.getAsNumber(1).floatValue();
        urx = rc.getAsNumber(2).floatValue();
        ury = rc.getAsNumber(3).floatValue();
    }
}
