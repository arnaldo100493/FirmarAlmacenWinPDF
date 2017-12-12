// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfPageEvent.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfWriter

public interface PdfPageEvent
{

    public abstract void onOpenDocument(PdfWriter pdfwriter, Document document);

    public abstract void onStartPage(PdfWriter pdfwriter, Document document);

    public abstract void onEndPage(PdfWriter pdfwriter, Document document);

    public abstract void onCloseDocument(PdfWriter pdfwriter, Document document);

    public abstract void onParagraph(PdfWriter pdfwriter, Document document, float f);

    public abstract void onParagraphEnd(PdfWriter pdfwriter, Document document, float f);

    public abstract void onChapter(PdfWriter pdfwriter, Document document, float f, Paragraph paragraph);

    public abstract void onChapterEnd(PdfWriter pdfwriter, Document document, float f);

    public abstract void onSection(PdfWriter pdfwriter, Document document, float f, int i, Paragraph paragraph);

    public abstract void onSectionEnd(PdfWriter pdfwriter, Document document, float f);

    public abstract void onGenericTag(PdfWriter pdfwriter, Document document, Rectangle rectangle, String s);
}
