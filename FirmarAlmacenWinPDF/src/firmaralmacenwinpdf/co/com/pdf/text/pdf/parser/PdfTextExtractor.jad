// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfTextExtractor.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.pdf.PdfReader;
import java.io.IOException;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            PdfReaderContentParser, TextExtractionStrategy, LocationTextExtractionStrategy

public final class PdfTextExtractor
{

    private PdfTextExtractor()
    {
    }

    public static String getTextFromPage(PdfReader reader, int pageNumber, TextExtractionStrategy strategy)
        throws IOException
    {
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        return ((TextExtractionStrategy)parser.processContent(pageNumber, strategy)).getResultantText();
    }

    public static String getTextFromPage(PdfReader reader, int pageNumber)
        throws IOException
    {
        return getTextFromPage(reader, pageNumber, ((TextExtractionStrategy) (new LocationTextExtractionStrategy())));
    }
}
