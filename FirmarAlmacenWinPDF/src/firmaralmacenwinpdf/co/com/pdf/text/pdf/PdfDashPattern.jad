// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfDashPattern.java

package co.com.pdf.text.pdf;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfArray, PdfNumber, PdfWriter

public class PdfDashPattern extends PdfArray
{

    public PdfDashPattern()
    {
        dash = -1F;
        gap = -1F;
        phase = -1F;
    }

    public PdfDashPattern(float dash)
    {
        super(new PdfNumber(dash));
        this.dash = -1F;
        gap = -1F;
        phase = -1F;
        this.dash = dash;
    }

    public PdfDashPattern(float dash, float gap)
    {
        super(new PdfNumber(dash));
        this.dash = -1F;
        this.gap = -1F;
        phase = -1F;
        add(new PdfNumber(gap));
        this.dash = dash;
        this.gap = gap;
    }

    public PdfDashPattern(float dash, float gap, float phase)
    {
        super(new PdfNumber(dash));
        this.dash = -1F;
        this.gap = -1F;
        this.phase = -1F;
        add(new PdfNumber(gap));
        this.dash = dash;
        this.gap = gap;
        this.phase = phase;
    }

    public void add(float n)
    {
        add(((PdfObject) (new PdfNumber(n))));
    }

    public void toPdf(PdfWriter writer, OutputStream os)
        throws IOException
    {
        os.write(91);
        if(dash >= 0.0F)
        {
            (new PdfNumber(dash)).toPdf(writer, os);
            if(gap >= 0.0F)
            {
                os.write(32);
                (new PdfNumber(gap)).toPdf(writer, os);
            }
        }
        os.write(93);
        if(phase >= 0.0F)
        {
            os.write(32);
            (new PdfNumber(phase)).toPdf(writer, os);
        }
    }

    private float dash;
    private float gap;
    private float phase;
}
