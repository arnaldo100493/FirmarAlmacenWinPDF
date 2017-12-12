// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfFormXObject.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            PdfStream, PdfRectangle, PdfNumber, PdfLiteral, 
//            PdfTemplate, PdfArray, PdfName, PdfOCG

public class PdfFormXObject extends PdfStream
{

    PdfFormXObject(PdfTemplate template, int compressionLevel)
    {
        put(PdfName.TYPE, PdfName.XOBJECT);
        put(PdfName.SUBTYPE, PdfName.FORM);
        put(PdfName.RESOURCES, template.getResources());
        put(PdfName.BBOX, new PdfRectangle(template.getBoundingBox()));
        put(PdfName.FORMTYPE, ONE);
        if(template.getLayer() != null)
            put(PdfName.OC, template.getLayer().getRef());
        if(template.getGroup() != null)
            put(PdfName.GROUP, template.getGroup());
        PdfArray matrix = template.getMatrix();
        if(matrix == null)
            put(PdfName.MATRIX, MATRIX);
        else
            put(PdfName.MATRIX, matrix);
        bytes = template.toPdf(null);
        put(PdfName.LENGTH, new PdfNumber(bytes.length));
        if(template.getAdditional() != null)
            putAll(template.getAdditional());
        flateCompress(compressionLevel);
    }

    public static final PdfNumber ZERO = new PdfNumber(0);
    public static final PdfNumber ONE = new PdfNumber(1);
    public static final PdfLiteral MATRIX = new PdfLiteral("[1 0 0 1 0 0]");

}
