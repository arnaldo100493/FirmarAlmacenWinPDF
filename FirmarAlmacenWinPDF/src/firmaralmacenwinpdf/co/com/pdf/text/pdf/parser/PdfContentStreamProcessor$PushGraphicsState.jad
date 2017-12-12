// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfContentStreamProcessor.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.pdf.PdfLiteral;
import java.util.ArrayList;
import java.util.Stack;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            GraphicsState, ContentOperator, PdfContentStreamProcessor

private static class PdfContentStreamProcessor$PushGraphicsState
    implements ContentOperator
{

    public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
    {
        GraphicsState gs = (GraphicsState)PdfContentStreamProcessor.access$4300(processor).peek();
        GraphicsState copy = new GraphicsState(gs);
        PdfContentStreamProcessor.access$4300(processor).push(copy);
    }

    private PdfContentStreamProcessor$PushGraphicsState()
    {
    }

    PdfContentStreamProcessor$PushGraphicsState(PdfContentStreamProcessor._cls1 x0)
    {
        this();
    }
}
