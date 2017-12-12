// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfContentStreamProcessor.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.pdf.*;
import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            ContentOperator, PdfContentStreamProcessor

private static class PdfContentStreamProcessor$MoveNextLineAndShowTextWithSpacing
    implements ContentOperator
{

    public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
    {
        PdfNumber aw = (PdfNumber)operands.get(0);
        PdfNumber ac = (PdfNumber)operands.get(1);
        PdfString string = (PdfString)operands.get(2);
        ArrayList twOperands = new ArrayList(1);
        twOperands.add(0, aw);
        setTextWordSpacing.invoke(processor, null, twOperands);
        ArrayList tcOperands = new ArrayList(1);
        tcOperands.add(0, ac);
        setTextCharacterSpacing.invoke(processor, null, tcOperands);
        ArrayList tickOperands = new ArrayList(1);
        tickOperands.add(0, string);
        moveNextLineAndShowText.invoke(processor, null, tickOperands);
    }

    private final PdfContentStreamProcessor.SetTextWordSpacing setTextWordSpacing;
    private final PdfContentStreamProcessor.SetTextCharacterSpacing setTextCharacterSpacing;
    private final PdfContentStreamProcessor.MoveNextLineAndShowText moveNextLineAndShowText;

    public PdfContentStreamProcessor$MoveNextLineAndShowTextWithSpacing(PdfContentStreamProcessor.SetTextWordSpacing setTextWordSpacing, PdfContentStreamProcessor.SetTextCharacterSpacing setTextCharacterSpacing, PdfContentStreamProcessor.MoveNextLineAndShowText moveNextLineAndShowText)
    {
        this.setTextWordSpacing = setTextWordSpacing;
        this.setTextCharacterSpacing = setTextCharacterSpacing;
        this.moveNextLineAndShowText = moveNextLineAndShowText;
    }
}
