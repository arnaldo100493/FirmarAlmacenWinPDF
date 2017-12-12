// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfContentStreamProcessor.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.pdf.PdfLiteral;
import co.com.pdf.text.pdf.PdfNumber;
import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            Matrix, ContentOperator, PdfContentStreamProcessor

private static class PdfContentStreamProcessor$TextSetTextMatrix
    implements ContentOperator
{

    public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
    {
        float a = ((PdfNumber)operands.get(0)).floatValue();
        float b = ((PdfNumber)operands.get(1)).floatValue();
        float c = ((PdfNumber)operands.get(2)).floatValue();
        float d = ((PdfNumber)operands.get(3)).floatValue();
        float e = ((PdfNumber)operands.get(4)).floatValue();
        float f = ((PdfNumber)operands.get(5)).floatValue();
        PdfContentStreamProcessor.access$3802(processor, new Matrix(a, b, c, d, e, f));
        PdfContentStreamProcessor.access$3902(processor, PdfContentStreamProcessor.access$3800(processor));
    }

    private PdfContentStreamProcessor$TextSetTextMatrix()
    {
    }

    PdfContentStreamProcessor$TextSetTextMatrix(PdfContentStreamProcessor._cls1 x0)
    {
        this();
    }
}
