// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfContentStreamProcessor.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.pdf.PdfLiteral;
import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            ContentOperator, PdfContentStreamProcessor

private static class PdfContentStreamProcessor$MoveNextLineAndShowText
    implements ContentOperator
{

    public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
    {
        textMoveNextLine.invoke(processor, null, new ArrayList(0));
        showText.invoke(processor, null, operands);
    }

    private final PdfContentStreamProcessor.TextMoveNextLine textMoveNextLine;
    private final PdfContentStreamProcessor.ShowText showText;

    public PdfContentStreamProcessor$MoveNextLineAndShowText(PdfContentStreamProcessor.TextMoveNextLine textMoveNextLine, PdfContentStreamProcessor.ShowText showText)
    {
        this.textMoveNextLine = textMoveNextLine;
        this.showText = showText;
    }
}
