// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfShadingPattern.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.IOException;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfNumber, PdfName, PdfArray, 
//            PdfShading, PdfWriter, PdfIndirectReference, ColorDetails

public class PdfShadingPattern extends PdfDictionary
{

    public PdfShadingPattern(PdfShading shading)
    {
        writer = shading.getWriter();
        put(PdfName.PATTERNTYPE, new PdfNumber(2));
        this.shading = shading;
    }

    PdfName getPatternName()
    {
        return patternName;
    }

    PdfName getShadingName()
    {
        return shading.getShadingName();
    }

    PdfIndirectReference getPatternReference()
    {
        if(patternReference == null)
            patternReference = writer.getPdfIndirectReference();
        return patternReference;
    }

    PdfIndirectReference getShadingReference()
    {
        return shading.getShadingReference();
    }

    void setName(int number)
    {
        patternName = new PdfName((new StringBuilder()).append("P").append(number).toString());
    }

    public void addToBody()
        throws IOException
    {
        put(PdfName.SHADING, getShadingReference());
        put(PdfName.MATRIX, new PdfArray(matrix));
        writer.addToBody(this, getPatternReference());
    }

    public void setMatrix(float matrix[])
    {
        if(matrix.length != 6)
        {
            throw new RuntimeException(MessageLocalization.getComposedMessage("the.matrix.size.must.be.6", new Object[0]));
        } else
        {
            this.matrix = matrix;
            return;
        }
    }

    public float[] getMatrix()
    {
        return matrix;
    }

    public PdfShading getShading()
    {
        return shading;
    }

    ColorDetails getColorDetails()
    {
        return shading.getColorDetails();
    }

    protected PdfShading shading;
    protected PdfWriter writer;
    protected float matrix[] = {
        1.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F
    };
    protected PdfName patternName;
    protected PdfIndirectReference patternReference;
}
