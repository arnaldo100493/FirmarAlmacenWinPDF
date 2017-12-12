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

private static class PdfContentStreamProcessor$TextMoveStartNextLine
    implements ContentOperator
{

    public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
    {
        float tx = ((PdfNumber)operands.get(0)).floatValue();
        float ty = ((PdfNumber)operands.get(1)).floatValue();
        Matrix translationMatrix = new Matrix(tx, ty);
        PdfContentStreamProcessor.access$3902(processor, translationMatrix.multiply(PdfContentStreamProcessor.access$3800(processor)));
        PdfContentStreamProcessor.access$3802(processor, PdfContentStreamProcessor.access$3900(processor));
    }

    private PdfContentStreamProcessor$TextMoveStartNextLine()
    {
    }

    PdfContentStreamProcessor$TextMoveStartNextLine(PdfContentStreamProcessor._cls1 x0)
    {
        this();
    }
}
