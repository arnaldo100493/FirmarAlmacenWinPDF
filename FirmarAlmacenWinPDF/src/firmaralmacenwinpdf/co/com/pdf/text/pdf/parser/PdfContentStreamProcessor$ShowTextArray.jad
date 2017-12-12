// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfContentStreamProcessor.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.pdf.*;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            ContentOperator, PdfContentStreamProcessor

private static class PdfContentStreamProcessor$ShowTextArray
    implements ContentOperator
{

    public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
    {
        PdfArray array = (PdfArray)operands.get(0);
        float tj = 0.0F;
        for(Iterator i = array.listIterator(); i.hasNext();)
        {
            PdfObject entryObj = (PdfObject)i.next();
            if(entryObj instanceof PdfString)
            {
                PdfContentStreamProcessor.access$3500(processor, (PdfString)entryObj);
                tj = 0.0F;
            } else
            {
                tj = ((PdfNumber)entryObj).floatValue();
                PdfContentStreamProcessor.access$3600(processor, tj);
            }
        }

    }

    private PdfContentStreamProcessor$ShowTextArray()
    {
    }

    PdfContentStreamProcessor$ShowTextArray(PdfContentStreamProcessor._cls1 x0)
    {
        this();
    }
}
