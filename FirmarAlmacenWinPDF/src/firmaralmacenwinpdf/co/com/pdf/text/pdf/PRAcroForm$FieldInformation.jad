// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PRAcroForm.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            PdfObject, PdfName, PdfDictionary, PRAcroForm, 
//            PRIndirectReference

public static class PRAcroForm$FieldInformation
{

    public String getWidgetName()
    {
        PdfObject name = info.get(PdfName.NM);
        if(name != null)
            return name.toString();
        else
            return null;
    }

    public String getName()
    {
        return fieldName;
    }

    public PdfDictionary getInfo()
    {
        return info;
    }

    public PRIndirectReference getRef()
    {
        return ref;
    }

    String fieldName;
    PdfDictionary info;
    PRIndirectReference ref;

    PRAcroForm$FieldInformation(String fieldName, PdfDictionary info, PRIndirectReference ref)
    {
        this.fieldName = fieldName;
        this.info = info;
        this.ref = ref;
    }
}
