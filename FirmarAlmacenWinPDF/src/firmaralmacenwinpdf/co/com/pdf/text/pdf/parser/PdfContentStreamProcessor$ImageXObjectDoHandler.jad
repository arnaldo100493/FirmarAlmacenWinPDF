// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfContentStreamProcessor.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.pdf.*;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            XObjectDoHandler, PdfContentStreamProcessor, GraphicsState, ImageRenderInfo, 
//            RenderListener

private static class PdfContentStreamProcessor$ImageXObjectDoHandler
    implements XObjectDoHandler
{

    public void handleXObject(PdfContentStreamProcessor processor, PdfStream xobjectStream, PdfIndirectReference ref)
    {
        co.com.pdf.text.pdf.PdfDictionary colorSpaceDic = PdfContentStreamProcessor.access$4000(processor).getAsDict(PdfName.COLORSPACE);
        ImageRenderInfo renderInfo = ImageRenderInfo.createForXObject(PdfContentStreamProcessor.access$3700(processor).ctm, ref, colorSpaceDic);
        PdfContentStreamProcessor.access$5100(processor).renderImage(renderInfo);
    }

    private PdfContentStreamProcessor$ImageXObjectDoHandler()
    {
    }

    PdfContentStreamProcessor$ImageXObjectDoHandler(PdfContentStreamProcessor._cls1 x0)
    {
        this();
    }
}
