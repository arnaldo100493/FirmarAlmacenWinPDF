// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImageRenderInfo.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.pdf.*;
import java.io.IOException;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            PdfImageObject, Vector, InlineImageInfo, Matrix

public class ImageRenderInfo
{

    private ImageRenderInfo(Matrix ctm, PdfIndirectReference ref, PdfDictionary colorSpaceDictionary)
    {
        imageObject = null;
        this.ctm = ctm;
        this.ref = ref;
        inlineImageInfo = null;
        this.colorSpaceDictionary = colorSpaceDictionary;
    }

    private ImageRenderInfo(Matrix ctm, InlineImageInfo inlineImageInfo, PdfDictionary colorSpaceDictionary)
    {
        imageObject = null;
        this.ctm = ctm;
        ref = null;
        this.inlineImageInfo = inlineImageInfo;
        this.colorSpaceDictionary = colorSpaceDictionary;
    }

    public static ImageRenderInfo createForXObject(Matrix ctm, PdfIndirectReference ref, PdfDictionary colorSpaceDictionary)
    {
        return new ImageRenderInfo(ctm, ref, colorSpaceDictionary);
    }

    protected static ImageRenderInfo createForEmbeddedImage(Matrix ctm, InlineImageInfo inlineImageInfo, PdfDictionary colorSpaceDictionary)
    {
        ImageRenderInfo renderInfo = new ImageRenderInfo(ctm, inlineImageInfo, colorSpaceDictionary);
        return renderInfo;
    }

    public PdfImageObject getImage()
        throws IOException
    {
        prepareImageObject();
        return imageObject;
    }

    private void prepareImageObject()
        throws IOException
    {
        if(imageObject != null)
            return;
        if(ref != null)
        {
            PRStream stream = (PRStream)PdfReader.getPdfObject(ref);
            imageObject = new PdfImageObject(stream, colorSpaceDictionary);
        } else
        if(inlineImageInfo != null)
            imageObject = new PdfImageObject(inlineImageInfo.getImageDictionary(), inlineImageInfo.getSamples(), colorSpaceDictionary);
    }

    public Vector getStartPoint()
    {
        return (new Vector(0.0F, 0.0F, 1.0F)).cross(ctm);
    }

    public Matrix getImageCTM()
    {
        return ctm;
    }

    public float getArea()
    {
        return ctm.getDeterminant();
    }

    public PdfIndirectReference getRef()
    {
        return ref;
    }

    private final Matrix ctm;
    private final PdfIndirectReference ref;
    private final InlineImageInfo inlineImageInfo;
    private final PdfDictionary colorSpaceDictionary;
    private PdfImageObject imageObject;
}
