// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Type3Glyph.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.DocumentException;
import co.com.pdf.text.Image;
import co.com.pdf.text.error_messages.MessageLocalization;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfContentByte, PdfWriter, PageResources, ByteBuffer

public final class Type3Glyph extends PdfContentByte
{

    private Type3Glyph()
    {
        super(null);
    }

    Type3Glyph(PdfWriter writer, PageResources pageResources, float wx, float llx, float lly, float urx, float ury, 
            boolean colorized)
    {
        super(writer);
        this.pageResources = pageResources;
        this.colorized = colorized;
        if(colorized)
            content.append(wx).append(" 0 d0\n");
        else
            content.append(wx).append(" 0 ").append(llx).append(' ').append(lly).append(' ').append(urx).append(' ').append(ury).append(" d1\n");
    }

    PageResources getPageResources()
    {
        return pageResources;
    }

    public void addImage(Image image, float a, float b, float c, float d, float e, float f, 
            boolean inlineImage)
        throws DocumentException
    {
        if(!colorized && (!image.isMask() || image.getBpc() != 1 && image.getBpc() <= 255))
        {
            throw new DocumentException(MessageLocalization.getComposedMessage("not.colorized.typed3.fonts.only.accept.mask.images", new Object[0]));
        } else
        {
            super.addImage(image, a, b, c, d, e, f, inlineImage);
            return;
        }
    }

    public PdfContentByte getDuplicate()
    {
        Type3Glyph dup = new Type3Glyph();
        dup.writer = writer;
        dup.pdf = pdf;
        dup.pageResources = pageResources;
        dup.colorized = colorized;
        return dup;
    }

    private PageResources pageResources;
    private boolean colorized;
}
