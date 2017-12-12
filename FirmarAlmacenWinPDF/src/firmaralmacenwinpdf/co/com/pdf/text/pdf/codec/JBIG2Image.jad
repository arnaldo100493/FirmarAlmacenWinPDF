// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JBIG2Image.java

package co.com.pdf.text.pdf.codec;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.RandomAccessFileOrArray;

// Referenced classes of package co.com.pdf.text.pdf.codec:
//            JBIG2SegmentReader

public class JBIG2Image
{

    public JBIG2Image()
    {
    }

    public static byte[] getGlobalSegment(RandomAccessFileOrArray ra)
    {
        try
        {
            JBIG2SegmentReader sr = new JBIG2SegmentReader(ra);
            sr.read();
            return sr.getGlobal(true);
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public static Image getJbig2Image(RandomAccessFileOrArray ra, int page)
    {
        if(page < 1)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.page.number.must.be.gt.eq.1", new Object[0]));
        try
        {
            JBIG2SegmentReader sr = new JBIG2SegmentReader(ra);
            sr.read();
            JBIG2SegmentReader.JBIG2Page p = sr.getPage(page);
            Image img = new ImgJBIG2(p.pageBitmapWidth, p.pageBitmapHeight, p.getData(true), sr.getGlobal(true));
            return img;
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public static int getNumberOfPages(RandomAccessFileOrArray ra)
    {
        try
        {
            JBIG2SegmentReader sr = new JBIG2SegmentReader(ra);
            sr.read();
            return sr.numberOfPages();
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }
}
