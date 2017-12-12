// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfResources.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfName

class PdfResources extends PdfDictionary
{

    PdfResources()
    {
    }

    void add(PdfName key, PdfDictionary resource)
    {
        if(resource.size() == 0)
            return;
        PdfDictionary dic = getAsDict(key);
        if(dic == null)
            put(key, resource);
        else
            dic.putAll(resource);
    }
}
