// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FilterHandlers.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.exceptions.UnsupportedPdfException;
import co.com.pdf.text.pdf.codec.TIFFFaxDecoder;
import co.com.pdf.text.pdf.codec.TIFFFaxDecompressor;
import java.io.IOException;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfNumber, PdfDictionary, PdfName, PdfObject, 
//            PdfBoolean, PdfReader, FilterHandlers

private static class FilterHandlers$Filter_CCITTFAXDECODE
    implements FilterHandlers.FilterHandler
{

    public byte[] decode(byte b[], PdfName filterName, PdfObject decodeParams, PdfDictionary streamDictionary)
        throws IOException
    {
        PdfNumber wn = (PdfNumber)PdfReader.getPdfObjectRelease(streamDictionary.get(PdfName.WIDTH));
        PdfNumber hn = (PdfNumber)PdfReader.getPdfObjectRelease(streamDictionary.get(PdfName.HEIGHT));
        if(wn == null || hn == null)
            throw new UnsupportedPdfException(MessageLocalization.getComposedMessage("filter.ccittfaxdecode.is.only.supported.for.images", new Object[0]));
        int width = wn.intValue();
        int height = hn.intValue();
        PdfDictionary param = (decodeParams instanceof PdfDictionary) ? (PdfDictionary)decodeParams : null;
        int k = 0;
        boolean blackIs1 = false;
        boolean byteAlign = false;
        if(param != null)
        {
            PdfNumber kn = param.getAsNumber(PdfName.K);
            if(kn != null)
                k = kn.intValue();
            PdfBoolean bo = param.getAsBoolean(PdfName.BLACKIS1);
            if(bo != null)
                blackIs1 = bo.booleanValue();
            bo = param.getAsBoolean(PdfName.ENCODEDBYTEALIGN);
            if(bo != null)
                byteAlign = bo.booleanValue();
        }
        byte outBuf[] = new byte[((width + 7) / 8) * height];
        TIFFFaxDecompressor decoder = new TIFFFaxDecompressor();
        if(k == 0 || k > 0)
        {
            int tiffT4Options = k <= 0 ? 0 : 1;
            tiffT4Options |= byteAlign ? 4 : 0;
            decoder.SetOptions(1, 3, tiffT4Options, 0);
            decoder.decodeRaw(outBuf, b, width, height);
            if(decoder.fails > 0)
            {
                byte outBuf2[] = new byte[((width + 7) / 8) * height];
                int oldFails = decoder.fails;
                decoder.SetOptions(1, 2, tiffT4Options, 0);
                decoder.decodeRaw(outBuf2, b, width, height);
                if(decoder.fails < oldFails)
                    outBuf = outBuf2;
            }
        } else
        {
            TIFFFaxDecoder deca = new TIFFFaxDecoder(1, width, height);
            deca.decodeT6(outBuf, b, 0, height, 0L);
        }
        if(!blackIs1)
        {
            int len = outBuf.length;
            for(int t = 0; t < len; t++)
                outBuf[t] ^= 0xff;

        }
        b = outBuf;
        return b;
    }

    private FilterHandlers$Filter_CCITTFAXDECODE()
    {
    }

    FilterHandlers$Filter_CCITTFAXDECODE(FilterHandlers._cls1 x0)
    {
        this();
    }
}
