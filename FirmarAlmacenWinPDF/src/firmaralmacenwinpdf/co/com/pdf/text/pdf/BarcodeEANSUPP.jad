// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BarcodeEANSUPP.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.BaseColor;
import co.com.pdf.text.Rectangle;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.awt.Color;
import java.awt.Image;

// Referenced classes of package co.com.pdf.text.pdf:
//            Barcode, BaseFont, PdfContentByte

public class BarcodeEANSUPP extends Barcode
{

    public BarcodeEANSUPP(Barcode ean, Barcode supp)
    {
        n = 8F;
        this.ean = ean;
        this.supp = supp;
    }

    public Rectangle getBarcodeSize()
    {
        Rectangle rect = ean.getBarcodeSize();
        rect.setRight(rect.getWidth() + supp.getBarcodeSize().getWidth() + n);
        return rect;
    }

    public Rectangle placeBarcode(PdfContentByte cb, BaseColor barColor, BaseColor textColor)
    {
        if(supp.getFont() != null)
            supp.setBarHeight((ean.getBarHeight() + supp.getBaseline()) - supp.getFont().getFontDescriptor(2, supp.getSize()));
        else
            supp.setBarHeight(ean.getBarHeight());
        Rectangle eanR = ean.getBarcodeSize();
        cb.saveState();
        ean.placeBarcode(cb, barColor, textColor);
        cb.restoreState();
        cb.saveState();
        cb.concatCTM(1.0F, 0.0F, 0.0F, 1.0F, eanR.getWidth() + n, eanR.getHeight() - ean.getBarHeight());
        supp.placeBarcode(cb, barColor, textColor);
        cb.restoreState();
        return getBarcodeSize();
    }

    public Image createAwtImage(Color foreground, Color background)
    {
        throw new UnsupportedOperationException(MessageLocalization.getComposedMessage("the.two.barcodes.must.be.composed.externally", new Object[0]));
    }

    protected Barcode ean;
    protected Barcode supp;
}
