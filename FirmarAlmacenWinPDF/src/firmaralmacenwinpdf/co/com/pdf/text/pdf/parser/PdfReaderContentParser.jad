// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfReaderContentParser.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.pdf.*;
import java.io.IOException;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            PdfContentStreamProcessor, ContentByteUtils, RenderListener

public class PdfReaderContentParser
{

    public PdfReaderContentParser(PdfReader reader)
    {
        this.reader = reader;
    }

    public RenderListener processContent(int pageNumber, RenderListener renderListener)
        throws IOException
    {
        PdfDictionary pageDic = reader.getPageN(pageNumber);
        PdfDictionary resourcesDic = pageDic.getAsDict(PdfName.RESOURCES);
        PdfContentStreamProcessor processor = new PdfContentStreamProcessor(renderListener);
        processor.processContent(ContentByteUtils.getContentBytesForPage(reader, pageNumber), resourcesDic);
        return renderListener;
    }

    private final PdfReader reader;
}
