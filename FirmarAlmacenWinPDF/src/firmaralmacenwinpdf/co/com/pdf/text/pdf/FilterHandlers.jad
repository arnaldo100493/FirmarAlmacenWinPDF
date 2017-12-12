// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FilterHandlers.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.exceptions.UnsupportedPdfException;
import co.com.pdf.text.pdf.codec.TIFFFaxDecoder;
import co.com.pdf.text.pdf.codec.TIFFFaxDecompressor;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfName, PdfObject, PdfDictionary, PdfNumber, 
//            PdfBoolean, PdfReader

public final class FilterHandlers
{
    private static class Filter_RUNLENGTHDECODE
        implements FilterHandler
    {

        public byte[] decode(byte b[], PdfName filterName, PdfObject decodeParams, PdfDictionary streamDictionary)
            throws IOException
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte dupCount = -1;
            for(int i = 0; i < b.length; i++)
            {
                dupCount = b[i];
                if(dupCount == -128)
                    break;
                if(dupCount >= 0 && dupCount <= 127)
                {
                    int bytesToCopy = dupCount + 1;
                    baos.write(b, i, bytesToCopy);
                    i += bytesToCopy;
                    continue;
                }
                i++;
                for(int j = 0; j < 1 - dupCount; j++)
                    baos.write(b[i]);

            }

            return baos.toByteArray();
        }

        private Filter_RUNLENGTHDECODE()
        {
        }

    }

    private static class Filter_DoNothing
        implements FilterHandler
    {

        public byte[] decode(byte b[], PdfName filterName, PdfObject decodeParams, PdfDictionary pdfdictionary)
            throws IOException
        {
            return b;
        }

        private Filter_DoNothing()
        {
        }

    }

    private static class Filter_CCITTFAXDECODE
        implements FilterHandler
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

        private Filter_CCITTFAXDECODE()
        {
        }

    }

    private static class Filter_LZWDECODE
        implements FilterHandler
    {

        public byte[] decode(byte b[], PdfName filterName, PdfObject decodeParams, PdfDictionary streamDictionary)
            throws IOException
        {
            b = PdfReader.LZWDecode(b);
            b = PdfReader.decodePredictor(b, decodeParams);
            return b;
        }

        private Filter_LZWDECODE()
        {
        }

    }

    private static class Filter_ASCII85DECODE
        implements FilterHandler
    {

        public byte[] decode(byte b[], PdfName filterName, PdfObject decodeParams, PdfDictionary streamDictionary)
            throws IOException
        {
            b = PdfReader.ASCII85Decode(b);
            return b;
        }

        private Filter_ASCII85DECODE()
        {
        }

    }

    private static class Filter_ASCIIHEXDECODE
        implements FilterHandler
    {

        public byte[] decode(byte b[], PdfName filterName, PdfObject decodeParams, PdfDictionary streamDictionary)
            throws IOException
        {
            b = PdfReader.ASCIIHexDecode(b);
            return b;
        }

        private Filter_ASCIIHEXDECODE()
        {
        }

    }

    private static class Filter_FLATEDECODE
        implements FilterHandler
    {

        public byte[] decode(byte b[], PdfName filterName, PdfObject decodeParams, PdfDictionary streamDictionary)
            throws IOException
        {
            b = PdfReader.FlateDecode(b);
            b = PdfReader.decodePredictor(b, decodeParams);
            return b;
        }

        private Filter_FLATEDECODE()
        {
        }

    }

    public static interface FilterHandler
    {

        public abstract byte[] decode(byte abyte0[], PdfName pdfname, PdfObject pdfobject, PdfDictionary pdfdictionary)
            throws IOException;
    }


    public FilterHandlers()
    {
    }

    public static Map getDefaultFilterHandlers()
    {
        return defaults;
    }

    private static final Map defaults;

    static 
    {
        HashMap map = new HashMap();
        map.put(PdfName.FLATEDECODE, new Filter_FLATEDECODE());
        map.put(PdfName.FL, new Filter_FLATEDECODE());
        map.put(PdfName.ASCIIHEXDECODE, new Filter_ASCIIHEXDECODE());
        map.put(PdfName.AHX, new Filter_ASCIIHEXDECODE());
        map.put(PdfName.ASCII85DECODE, new Filter_ASCII85DECODE());
        map.put(PdfName.A85, new Filter_ASCII85DECODE());
        map.put(PdfName.LZWDECODE, new Filter_LZWDECODE());
        map.put(PdfName.CCITTFAXDECODE, new Filter_CCITTFAXDECODE());
        map.put(PdfName.CRYPT, new Filter_DoNothing());
        map.put(PdfName.RUNLENGTHDECODE, new Filter_RUNLENGTHDECODE());
        defaults = Collections.unmodifiableMap(map);
    }
}
