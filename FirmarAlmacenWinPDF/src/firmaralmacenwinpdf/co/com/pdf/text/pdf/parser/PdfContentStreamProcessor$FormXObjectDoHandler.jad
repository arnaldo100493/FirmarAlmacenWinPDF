// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfContentStreamProcessor.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.ExceptionConverter;
import co.com.pdf.text.pdf.*;
import java.io.IOException;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            Matrix, XObjectDoHandler, PdfContentStreamProcessor, ContentByteUtils, 
//            GraphicsState

private static class PdfContentStreamProcessor$FormXObjectDoHandler
    implements XObjectDoHandler
{

    public void handleXObject(PdfContentStreamProcessor processor, PdfStream stream, PdfIndirectReference ref)
    {
        PdfDictionary resources = stream.getAsDict(PdfName.RESOURCES);
        byte contentBytes[];
        try
        {
            contentBytes = ContentByteUtils.getContentBytesFromContentObject(stream);
        }
        catch(IOException e1)
        {
            throw new ExceptionConverter(e1);
        }
        PdfArray matrix = stream.getAsArray(PdfName.MATRIX);
        (new PdfContentStreamProcessor.PushGraphicsState(null)).invoke(processor, null, null);
        if(matrix != null)
        {
            float a = matrix.getAsNumber(0).floatValue();
            float b = matrix.getAsNumber(1).floatValue();
            float c = matrix.getAsNumber(2).floatValue();
            float d = matrix.getAsNumber(3).floatValue();
            float e = matrix.getAsNumber(4).floatValue();
            float f = matrix.getAsNumber(5).floatValue();
            Matrix formMatrix = new Matrix(a, b, c, d, e, f);
            PdfContentStreamProcessor.access$3700(processor).ctm = formMatrix.multiply(PdfContentStreamProcessor.access$3700(processor).ctm);
        }
        processor.processContent(contentBytes, resources);
        (new PdfContentStreamProcessor.PopGraphicsState(null)).invoke(processor, null, null);
    }

    private PdfContentStreamProcessor$FormXObjectDoHandler()
    {
    }

    PdfContentStreamProcessor$FormXObjectDoHandler(PdfContentStreamProcessor._cls1 x0)
    {
        this();
    }
}
