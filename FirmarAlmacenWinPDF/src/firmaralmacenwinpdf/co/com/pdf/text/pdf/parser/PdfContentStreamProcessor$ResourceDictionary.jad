// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfContentStreamProcessor.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.pdf.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            PdfContentStreamProcessor

private static class PdfContentStreamProcessor$ResourceDictionary extends PdfDictionary
{

    public void push(PdfDictionary resources)
    {
        resourcesStack.add(resources);
    }

    public void pop()
    {
        resourcesStack.remove(resourcesStack.size() - 1);
    }

    public PdfObject getDirectObject(PdfName key)
    {
        for(int i = resourcesStack.size() - 1; i >= 0; i--)
        {
            PdfDictionary subResource = (PdfDictionary)resourcesStack.get(i);
            if(subResource == null)
                continue;
            PdfObject obj = subResource.getDirectObject(key);
            if(obj != null)
                return obj;
        }

        return super.getDirectObject(key);
    }

    private final List resourcesStack = new ArrayList();

    public PdfContentStreamProcessor$ResourceDictionary()
    {
    }
}
