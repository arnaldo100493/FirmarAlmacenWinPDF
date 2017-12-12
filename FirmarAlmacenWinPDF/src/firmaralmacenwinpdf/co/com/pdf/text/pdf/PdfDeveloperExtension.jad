// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfDeveloperExtension.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfNumber, PdfName, PdfWriter

public class PdfDeveloperExtension
{

    public PdfDeveloperExtension(PdfName prefix, PdfName baseversion, int extensionLevel)
    {
        this.prefix = prefix;
        this.baseversion = baseversion;
        this.extensionLevel = extensionLevel;
    }

    public PdfName getPrefix()
    {
        return prefix;
    }

    public PdfName getBaseversion()
    {
        return baseversion;
    }

    public int getExtensionLevel()
    {
        return extensionLevel;
    }

    public PdfDictionary getDeveloperExtensions()
    {
        PdfDictionary developerextensions = new PdfDictionary();
        developerextensions.put(PdfName.BASEVERSION, baseversion);
        developerextensions.put(PdfName.EXTENSIONLEVEL, new PdfNumber(extensionLevel));
        return developerextensions;
    }

    public static final PdfDeveloperExtension ADOBE_1_7_EXTENSIONLEVEL3;
    public static final PdfDeveloperExtension ESIC_1_7_EXTENSIONLEVEL2;
    public static final PdfDeveloperExtension ESIC_1_7_EXTENSIONLEVEL5;
    protected PdfName prefix;
    protected PdfName baseversion;
    protected int extensionLevel;

    static 
    {
        ADOBE_1_7_EXTENSIONLEVEL3 = new PdfDeveloperExtension(PdfName.ADBE, PdfWriter.PDF_VERSION_1_7, 3);
        ESIC_1_7_EXTENSIONLEVEL2 = new PdfDeveloperExtension(PdfName.ESIC, PdfWriter.PDF_VERSION_1_7, 2);
        ESIC_1_7_EXTENSIONLEVEL5 = new PdfDeveloperExtension(PdfName.ESIC, PdfWriter.PDF_VERSION_1_7, 5);
    }
}
