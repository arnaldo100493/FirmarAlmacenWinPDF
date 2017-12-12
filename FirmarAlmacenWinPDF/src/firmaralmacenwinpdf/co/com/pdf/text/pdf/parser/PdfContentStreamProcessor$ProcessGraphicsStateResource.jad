// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfContentStreamProcessor.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.*;
import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            ContentOperator, PdfContentStreamProcessor, GraphicsState

private static class PdfContentStreamProcessor$ProcessGraphicsStateResource
    implements ContentOperator
{

    public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
    {
        PdfName dictionaryName = (PdfName)operands.get(0);
        PdfDictionary extGState = PdfContentStreamProcessor.access$4000(processor).getAsDict(PdfName.EXTGSTATE);
        if(extGState == null)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("resources.do.not.contain.extgstate.entry.unable.to.process.operator.1", new Object[] {
                operator
            }));
        PdfDictionary gsDic = extGState.getAsDict(dictionaryName);
        if(gsDic == null)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("1.is.an.unknown.graphics.state.dictionary", new Object[] {
                dictionaryName
            }));
        PdfArray fontParameter = gsDic.getAsArray(PdfName.FONT);
        if(fontParameter != null)
        {
            co.com.pdf.text.pdf.CMapAwareDocumentFont font = PdfContentStreamProcessor.access$4200(processor, (PRIndirectReference)fontParameter.getPdfObject(0));
            float size = fontParameter.getAsNumber(1).floatValue();
            PdfContentStreamProcessor.access$3700(processor).font = font;
            PdfContentStreamProcessor.access$3700(processor).fontSize = size;
        }
    }

    private PdfContentStreamProcessor$ProcessGraphicsStateResource()
    {
    }

    PdfContentStreamProcessor$ProcessGraphicsStateResource(PdfContentStreamProcessor._cls1 x0)
    {
        this();
    }
}
