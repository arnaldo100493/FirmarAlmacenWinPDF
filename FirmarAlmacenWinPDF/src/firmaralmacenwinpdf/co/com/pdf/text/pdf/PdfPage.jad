// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfPage.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.DocumentException;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.util.HashMap;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfNumber, PdfObject, PdfName, 
//            PdfRectangle, PdfIndirectReference

public class PdfPage extends PdfDictionary
{

    PdfPage(PdfRectangle mediaBox, HashMap boxSize, PdfDictionary resources, int rotate)
        throws DocumentException
    {
        super(PAGE);
        this.mediaBox = mediaBox;
        if(mediaBox != null && (mediaBox.width() > 14400F || mediaBox.height() > 14400F))
            throw new DocumentException(MessageLocalization.getComposedMessage("the.page.size.must.be.smaller.than.14400.by.14400.its.1.by.2", new Object[] {
                Float.valueOf(mediaBox.width()), Float.valueOf(mediaBox.height())
            }));
        put(PdfName.MEDIABOX, mediaBox);
        put(PdfName.RESOURCES, resources);
        if(rotate != 0)
            put(PdfName.ROTATE, new PdfNumber(rotate));
        for(int k = 0; k < boxStrings.length; k++)
        {
            PdfObject rect = (PdfObject)boxSize.get(boxStrings[k]);
            if(rect != null)
                put(boxNames[k], rect);
        }

    }

    PdfPage(PdfRectangle mediaBox, HashMap boxSize, PdfDictionary resources)
        throws DocumentException
    {
        this(mediaBox, boxSize, resources, 0);
    }

    public boolean isParent()
    {
        return false;
    }

    void add(PdfIndirectReference contents)
    {
        put(PdfName.CONTENTS, contents);
    }

    PdfRectangle rotateMediaBox()
    {
        mediaBox = mediaBox.rotate();
        put(PdfName.MEDIABOX, mediaBox);
        return mediaBox;
    }

    PdfRectangle getMediaBox()
    {
        return mediaBox;
    }

    private static final String boxStrings[] = {
        "crop", "trim", "art", "bleed"
    };
    private static final PdfName boxNames[];
    public static final PdfNumber PORTRAIT = new PdfNumber(0);
    public static final PdfNumber LANDSCAPE = new PdfNumber(90);
    public static final PdfNumber INVERTEDPORTRAIT = new PdfNumber(180);
    public static final PdfNumber SEASCAPE = new PdfNumber(270);
    PdfRectangle mediaBox;

    static 
    {
        boxNames = (new PdfName[] {
            PdfName.CROPBOX, PdfName.TRIMBOX, PdfName.ARTBOX, PdfName.BLEEDBOX
        });
    }
}
