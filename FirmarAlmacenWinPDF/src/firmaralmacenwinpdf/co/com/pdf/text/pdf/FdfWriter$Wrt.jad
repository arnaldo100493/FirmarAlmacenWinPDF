// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FdfWriter.java

package co.com.pdf.text.pdf;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfWriter, PdfDocument, PdfDictionary, PdfString, 
//            PdfArray, PdfAction, PdfObject, FdfWriter, 
//            OutputStreamCounter, PdfName, PdfIndirectObject

static class FdfWriter$Wrt extends PdfWriter
{

    void writeTo()
        throws IOException
    {
        PdfDictionary dic = new PdfDictionary();
        dic.put(PdfName.FIELDS, calculate(fdf.fields));
        if(FdfWriter.access$100(fdf) != null)
            dic.put(PdfName.F, new PdfString(FdfWriter.access$100(fdf), "UnicodeBig"));
        PdfDictionary fd = new PdfDictionary();
        fd.put(PdfName.FDF, dic);
        PdfIndirectReference ref = addToBody(fd).getIndirectReference();
        os.write(getISOBytes("trailer\n"));
        PdfDictionary trailer = new PdfDictionary();
        trailer.put(PdfName.ROOT, ref);
        trailer.toPdf(null, os);
        os.write(getISOBytes("\n%%EOF\n"));
        os.close();
    }

    PdfArray calculate(HashMap map)
        throws IOException
    {
        PdfArray ar = new PdfArray();
        PdfDictionary dic;
        for(Iterator i$ = map.entrySet().iterator(); i$.hasNext(); ar.add(dic))
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
            String key = (String)entry.getKey();
            Object v = entry.getValue();
            dic = new PdfDictionary();
            dic.put(PdfName.T, new PdfString(key, "UnicodeBig"));
            if(v instanceof HashMap)
            {
                dic.put(PdfName.KIDS, calculate((HashMap)v));
                continue;
            }
            if(v instanceof PdfAction)
                dic.put(PdfName.A, (PdfAction)v);
            else
                dic.put(PdfName.V, (PdfObject)v);
        }

        return ar;
    }

    private FdfWriter fdf;

    FdfWriter$Wrt(OutputStream os, FdfWriter fdf)
        throws IOException
    {
        super(new PdfDocument(), os);
        this.fdf = fdf;
        this.os.write(FdfWriter.access$000());
        body = new ody(this);
    }
}
