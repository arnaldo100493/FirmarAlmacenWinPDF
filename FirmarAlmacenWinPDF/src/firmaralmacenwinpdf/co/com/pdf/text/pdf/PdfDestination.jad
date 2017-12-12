// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfDestination.java

package co.com.pdf.text.pdf;

import java.util.StringTokenizer;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfArray, PdfNumber, PdfName, PdfNull, 
//            PdfIndirectReference

public class PdfDestination extends PdfArray
{

    public PdfDestination(int type)
    {
        status = false;
        if(type == 5)
            add(PdfName.FITB);
        else
            add(PdfName.FIT);
    }

    public PdfDestination(int type, float parameter)
    {
        super(new PdfNumber(parameter));
        status = false;
        switch(type)
        {
        case 4: // '\004'
        case 5: // '\005'
        default:
            addFirst(PdfName.FITH);
            break;

        case 3: // '\003'
            addFirst(PdfName.FITV);
            break;

        case 6: // '\006'
            addFirst(PdfName.FITBH);
            break;

        case 7: // '\007'
            addFirst(PdfName.FITBV);
            break;
        }
    }

    public PdfDestination(int type, float left, float top, float zoom)
    {
        super(PdfName.XYZ);
        status = false;
        if(left < 0.0F)
            add(PdfNull.PDFNULL);
        else
            add(new PdfNumber(left));
        if(top < 0.0F)
            add(PdfNull.PDFNULL);
        else
            add(new PdfNumber(top));
        add(new PdfNumber(zoom));
    }

    public PdfDestination(int type, float left, float bottom, float right, float top)
    {
        super(PdfName.FITR);
        status = false;
        add(new PdfNumber(left));
        add(new PdfNumber(bottom));
        add(new PdfNumber(right));
        add(new PdfNumber(top));
    }

    public PdfDestination(String dest)
    {
        status = false;
        StringTokenizer tokens = new StringTokenizer(dest);
        if(tokens.hasMoreTokens())
            add(new PdfName(tokens.nextToken()));
        while(tokens.hasMoreTokens()) 
        {
            String token = tokens.nextToken();
            if("null".equals(token))
                add(new PdfNull());
            else
                try
                {
                    add(new PdfNumber(token));
                }
                catch(RuntimeException e)
                {
                    add(new PdfNull());
                }
        }
    }

    public boolean hasPage()
    {
        return status;
    }

    public boolean addPage(PdfIndirectReference page)
    {
        if(!status)
        {
            addFirst(page);
            status = true;
            return true;
        } else
        {
            return false;
        }
    }

    public static final int XYZ = 0;
    public static final int FIT = 1;
    public static final int FITH = 2;
    public static final int FITV = 3;
    public static final int FITR = 4;
    public static final int FITB = 5;
    public static final int FITBH = 6;
    public static final int FITBV = 7;
    private boolean status;
}
