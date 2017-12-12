// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfContentStreamProcessor.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.pdf.*;
import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            ContentOperator, PdfContentStreamProcessor

private static class PdfContentStreamProcessor$BeginMarkedContentDictionary
    implements ContentOperator
{

    public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        throws Exception
    {
        PdfObject properties = (PdfObject)operands.get(1);
        PdfContentStreamProcessor.access$4800(processor, (PdfName)operands.get(0), getPropertiesDictionary(properties, PdfContentStreamProcessor.access$4000(processor)));
    }

    private PdfDictionary getPropertiesDictionary(PdfObject operand1, PdfContentStreamProcessor.ResourceDictionary resources)
    {
        if(operand1.isDictionary())
        {
            return (PdfDictionary)operand1;
        } else
        {
            PdfName dictionaryName = (PdfName)operand1;
            return resources.getAsDict(dictionaryName);
        }
    }

    private PdfContentStreamProcessor$BeginMarkedContentDictionary()
    {
    }

    PdfContentStreamProcessor$BeginMarkedContentDictionary(PdfContentStreamProcessor._cls1 x0)
    {
        this();
    }
}
