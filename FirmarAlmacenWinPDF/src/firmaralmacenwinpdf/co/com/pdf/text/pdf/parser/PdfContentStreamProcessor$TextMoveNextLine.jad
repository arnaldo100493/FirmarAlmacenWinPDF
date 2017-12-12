// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfContentStreamProcessor.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.pdf.PdfLiteral;
import co.com.pdf.text.pdf.PdfNumber;
import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            ContentOperator, PdfContentStreamProcessor, GraphicsState

private static class PdfContentStreamProcessor$TextMoveNextLine
    implements ContentOperator
{

    public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
    {
        ArrayList tdoperands = new ArrayList(2);
        tdoperands.add(0, new PdfNumber(0));
        tdoperands.add(1, new PdfNumber(-PdfContentStreamProcessor.access$3700(processor).leading));
        moveStartNextLine.invoke(processor, null, tdoperands);
    }

    private final Line moveStartNextLine;

    public PdfContentStreamProcessor$TextMoveNextLine(Line moveStartNextLine)
    {
        this.moveStartNextLine = moveStartNextLine;
    }
}
