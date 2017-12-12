// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfContentStreamProcessor.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.pdf.*;
import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            ContentOperator, PdfContentStreamProcessor, GraphicsState

private static class PdfContentStreamProcessor$SetTextFont
    implements ContentOperator
{

    public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
    {
        PdfName fontResourceName = (PdfName)operands.get(0);
        float size = ((PdfNumber)operands.get(1)).floatValue();
        PdfDictionary fontsDictionary = PdfContentStreamProcessor.access$4000(processor).getAsDict(PdfName.FONT);
        PdfObject fontObject = fontsDictionary.get(fontResourceName);
        CMapAwareDocumentFont font;
        if(fontObject instanceof PdfDictionary)
            font = PdfContentStreamProcessor.access$4100(processor, (PdfDictionary)fontObject);
        else
            font = PdfContentStreamProcessor.access$4200(processor, (PRIndirectReference)fontObject);
        PdfContentStreamProcessor.access$3700(processor).font = font;
        PdfContentStreamProcessor.access$3700(processor).fontSize = size;
    }

    private PdfContentStreamProcessor$SetTextFont()
    {
    }

    PdfContentStreamProcessor$SetTextFont(PdfContentStreamProcessor._cls1 x0)
    {
        this();
    }
}
