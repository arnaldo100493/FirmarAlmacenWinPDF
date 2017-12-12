// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMapAwareDocumentFont.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.ExceptionConverter;
import co.com.pdf.text.Utilities;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.fonts.cmaps.CMapByteCid;
import co.com.pdf.text.pdf.fonts.cmaps.CMapCache;
import co.com.pdf.text.pdf.fonts.cmaps.CMapCidUni;
import co.com.pdf.text.pdf.fonts.cmaps.CMapParserEx;
import co.com.pdf.text.pdf.fonts.cmaps.CMapSequence;
import co.com.pdf.text.pdf.fonts.cmaps.CMapToUnicode;
import co.com.pdf.text.pdf.fonts.cmaps.CidLocationFromByte;
import co.com.pdf.text.pdf.fonts.cmaps.IdentityToUnicode;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            DocumentFont, PdfDictionary, PRStream, PdfArray, 
//            PdfObject, PdfName, PdfString, IntHashtable, 
//            PdfReader, PRIndirectReference

public class CMapAwareDocumentFont extends DocumentFont
{

    public CMapAwareDocumentFont(PdfDictionary font)
    {
        super(font);
        fontDic = font;
        initFont();
    }

    public CMapAwareDocumentFont(PRIndirectReference refFont)
    {
        super(refFont);
        fontDic = (PdfDictionary)PdfReader.getPdfObjectRelease(refFont);
        initFont();
    }

    private void initFont()
    {
        processToUnicode();
        try
        {
            processUni2Byte();
            spaceWidth = super.getWidth(32);
            if(spaceWidth == 0)
                spaceWidth = computeAverageWidth();
            if(cjkEncoding != null)
            {
                byteCid = CMapCache.getCachedCMapByteCid(cjkEncoding);
                cidUni = CMapCache.getCachedCMapCidUni(uniMap);
            }
        }
        catch(Exception ex)
        {
            throw new ExceptionConverter(ex);
        }
    }

    private void processToUnicode()
    {
        PdfObject toUni = PdfReader.getPdfObjectRelease(fontDic.get(PdfName.TOUNICODE));
        if(toUni instanceof PRStream)
        {
            try
            {
                byte touni[] = PdfReader.getStreamBytes((PRStream)toUni);
                CidLocationFromByte lb = new CidLocationFromByte(touni);
                toUnicodeCmap = new CMapToUnicode();
                CMapParserEx.parseCid("", toUnicodeCmap, lb);
                uni2cid = toUnicodeCmap.createReverseMapping();
            }
            catch(IOException e)
            {
                toUnicodeCmap = null;
                uni2cid = null;
            }
            break MISSING_BLOCK_LABEL_239;
        }
        if(!isType0)
            break MISSING_BLOCK_LABEL_239;
        PdfName encodingName;
        encodingName = fontDic.getAsName(PdfName.ENCODING);
        if(encodingName == null)
            return;
        String enc = PdfName.decodeName(encodingName.toString());
        if(!enc.equals("Identity-H"))
            return;
        PdfDictionary cidinfo;
        PdfArray df = (PdfArray)PdfReader.getPdfObjectRelease(fontDic.get(PdfName.DESCENDANTFONTS));
        PdfDictionary cidft = (PdfDictionary)PdfReader.getPdfObjectRelease(df.getPdfObject(0));
        cidinfo = cidft.getAsDict(PdfName.CIDSYSTEMINFO);
        if(cidinfo == null)
            return;
        PdfString ordering;
        ordering = cidinfo.getAsString(PdfName.ORDERING);
        if(ordering == null)
            return;
        CMapToUnicode touni;
        touni = IdentityToUnicode.GetMapFromOrdering(ordering.toUnicodeString());
        if(touni == null)
            return;
        try
        {
            toUnicodeCmap = touni;
            uni2cid = toUnicodeCmap.createReverseMapping();
        }
        catch(IOException e)
        {
            toUnicodeCmap = null;
            uni2cid = null;
        }
    }

    private void processUni2Byte()
        throws IOException
    {
        IntHashtable byte2uni = getByte2Uni();
        int e[] = byte2uni.toOrderedKeys();
        if(e.length == 0)
            return;
        cidbyte2uni = new char[256];
        for(int k = 0; k < e.length; k++)
        {
            int key = e[k];
            cidbyte2uni[key] = (char)byte2uni.get(key);
        }

        if(toUnicodeCmap != null)
        {
            Map dm = toUnicodeCmap.createDirectMapping();
            Iterator i$ = dm.entrySet().iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                java.util.Map.Entry kv = (java.util.Map.Entry)i$.next();
                if(((Integer)kv.getKey()).intValue() < 256)
                    cidbyte2uni[((Integer)kv.getKey()).intValue()] = (char)((Integer)kv.getValue()).intValue();
            } while(true);
        }
        IntHashtable diffmap = getDiffmap();
        if(diffmap != null)
        {
            e = diffmap.toOrderedKeys();
            for(int k = 0; k < e.length; k++)
            {
                int n = diffmap.get(e[k]);
                if(n < 256)
                    cidbyte2uni[n] = (char)e[k];
            }

        }
    }

    private int computeAverageWidth()
    {
        int count = 0;
        int total = 0;
        for(int i = 0; i < super.widths.length; i++)
            if(super.widths[i] != 0)
            {
                total += super.widths[i];
                count++;
            }

        return count == 0 ? 0 : total / count;
    }

    public int getWidth(int char1)
    {
        if(char1 == 32)
            return spaceWidth;
        else
            return super.getWidth(char1);
    }

    private String decodeSingleCID(byte bytes[], int offset, int len)
    {
        if(toUnicodeCmap != null)
        {
            if(offset + len > bytes.length)
                throw new ArrayIndexOutOfBoundsException(MessageLocalization.getComposedMessage("invalid.index.1", offset + len));
            String s = toUnicodeCmap.lookup(bytes, offset, len);
            if(s != null)
                return s;
            if(len != 1 || cidbyte2uni == null)
                return null;
        }
        if(len == 1)
        {
            if(cidbyte2uni == null)
                return "";
            else
                return new String(cidbyte2uni, 0xff & bytes[offset], 1);
        } else
        {
            throw new Error("Multi-byte glyphs not implemented yet");
        }
    }

    public String decode(byte cidbytes[], int offset, int len)
    {
        StringBuilder sb = new StringBuilder();
        if(toUnicodeCmap == null && byteCid != null)
        {
            CMapSequence seq = new CMapSequence(cidbytes, offset, len);
            String cid = byteCid.decodeSequence(seq);
            for(int k = 0; k < cid.length(); k++)
            {
                int c = cidUni.lookup(cid.charAt(k));
                if(c > 0)
                    sb.append(Utilities.convertFromUtf32(c));
            }

        } else
        {
            for(int i = offset; i < offset + len; i++)
            {
                String rslt = decodeSingleCID(cidbytes, i, 1);
                if(rslt == null && i < (offset + len) - 1)
                {
                    rslt = decodeSingleCID(cidbytes, i, 2);
                    i++;
                }
                if(rslt != null)
                    sb.append(rslt);
            }

        }
        return sb.toString();
    }

    /**
     * @deprecated Method encode is deprecated
     */

    public String encode(byte bytes[], int offset, int len)
    {
        return decode(bytes, offset, len);
    }

    private PdfDictionary fontDic;
    private int spaceWidth;
    private CMapToUnicode toUnicodeCmap;
    private CMapByteCid byteCid;
    private CMapCidUni cidUni;
    private char cidbyte2uni[];
    private Map uni2cid;
}
