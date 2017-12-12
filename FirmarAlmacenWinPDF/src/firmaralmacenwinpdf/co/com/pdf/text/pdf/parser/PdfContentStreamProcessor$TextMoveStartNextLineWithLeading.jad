// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfContentStreamProcessor.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.pdf.PdfLiteral;
import co.com.pdf.text.pdf.PdfNumber;
import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            ContentOperator, PdfContentStreamProcessor

private static class PdfContentStreamProcessor$TextMoveStartNextLineWithLeading
    implements ContentOperator
{

    public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
    {
        float ty = ((PdfNumber)operands.get(1)).floatValue();
        ArrayList tlOperands = new ArrayList(1);
        tlOperands.add(0, new PdfNumber(-ty));
        setTextLeading.invoke(processor, null, tlOperands);
        moveStartNextLine.invoke(processor, null, operands);
    }

    private final PdfContentStreamProcessor.TextMoveStartNextLine moveStartNextLine;
    private final PdfContentStreamProcessor.SetTextLeading setTextLeading;

    public PdfContentStreamProcessor$TextMoveStartNextLineWithLeading(PdfContentStreamProcessor.TextMoveStartNextLine moveStartNextLine, PdfContentStreamProcessor.SetTextLeading setTextLeading)
    {
        this.moveStartNextLine = moveStartNextLine;
        this.setTextLeading = setTextLeading;
    }
}
