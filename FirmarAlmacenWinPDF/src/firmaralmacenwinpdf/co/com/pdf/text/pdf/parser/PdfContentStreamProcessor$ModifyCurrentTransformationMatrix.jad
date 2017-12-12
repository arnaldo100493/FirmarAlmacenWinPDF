// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfContentStreamProcessor.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.pdf.PdfLiteral;
import co.com.pdf.text.pdf.PdfNumber;
import java.util.ArrayList;
import java.util.Stack;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            Matrix, GraphicsState, ContentOperator, PdfContentStreamProcessor

private static class PdfContentStreamProcessor$ModifyCurrentTransformationMatrix
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
        Matrix matrix = new Matrix(a, b, c, d, e, f);
        GraphicsState gs = (GraphicsState)PdfContentStreamProcessor.access$4300(processor).peek();
        gs.ctm = matrix.multiply(gs.ctm);
    }

    private PdfContentStreamProcessor$ModifyCurrentTransformationMatrix()
    {
    }

    PdfContentStreamProcessor$ModifyCurrentTransformationMatrix(PdfContentStreamProcessor._cls1 x0)
    {
        this();
    }
}
