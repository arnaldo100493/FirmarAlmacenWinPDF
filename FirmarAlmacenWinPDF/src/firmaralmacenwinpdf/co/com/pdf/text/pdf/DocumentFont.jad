// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DocumentFont.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.io.RandomAccessSourceFactory;
import co.com.pdf.text.pdf.fonts.cmaps.CMapParserEx;
import co.com.pdf.text.pdf.fonts.cmaps.CMapToUnicode;
import co.com.pdf.text.pdf.fonts.cmaps.CidLocationFromByte;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            BaseFont, IntHashtable, PdfDictionary, CJKFont, 
//            PdfArray, PdfNumber, PRStream, PdfContentParser, 
//            PRTokeniser, RandomAccessFileOrArray, PdfString, PdfName, 
//            PdfObject, PdfReader, PdfEncodings, GlyphList, 
//            PRIndirectReference, PdfWriter, PdfIndirectReference, PdfStream

public class DocumentFont extends BaseFont
{

    DocumentFont(PdfDictionary font)
    {
        metrics = new HashMap();
        uni2byte = new IntHashtable();
        byte2uni = new IntHashtable();
        ascender = 800F;
        capHeight = 700F;
        descender = -200F;
        italicAngle = 0.0F;
        fontWeight = 0.0F;
        llx = -50F;
        lly = -200F;
        urx = 100F;
        ury = 900F;
        isType0 = false;
        defaultWidth = 1000;
        refFont = null;
        this.font = font;
        init();
    }

    DocumentFont(PRIndirectReference refFont)
    {
        metrics = new HashMap();
        uni2byte = new IntHashtable();
        byte2uni = new IntHashtable();
        ascender = 800F;
        capHeight = 700F;
        descender = -200F;
        italicAngle = 0.0F;
        fontWeight = 0.0F;
        llx = -50F;
        lly = -200F;
        urx = 100F;
        ury = 900F;
        isType0 = false;
        defaultWidth = 1000;
        this.refFont = refFont;
        font = (PdfDictionary)PdfReader.getPdfObject(refFont);
        init();
    }

    public PdfDictionary getFontDictionary()
    {
        return font;
    }

    private void init()
    {
        encoding = "";
        fontSpecific = false;
        fontType = 4;
        PdfName baseFont = font.getAsName(PdfName.BASEFONT);
        fontName = baseFont == null ? "Unspecified Font Name" : PdfName.decodeName(baseFont.toString());
        PdfName subType = font.getAsName(PdfName.SUBTYPE);
        if(PdfName.TYPE1.equals(subType) || PdfName.TRUETYPE.equals(subType))
        {
            doType1TT();
        } else
        {
            PdfName encodingName = font.getAsName(PdfName.ENCODING);
            if(encodingName != null)
            {
                String enc = PdfName.decodeName(encodingName.toString());
                String ffontname = CJKFont.GetCompatibleFont(enc);
                if(ffontname != null)
                {
                    try
                    {
                        cjkMirror = BaseFont.createFont(ffontname, enc, false);
                    }
                    catch(Exception e)
                    {
                        throw new ExceptionConverter(e);
                    }
                    cjkEncoding = enc;
                    uniMap = ((CJKFont)cjkMirror).getUniMap();
                }
                if(PdfName.TYPE0.equals(subType))
                {
                    isType0 = true;
                    if(!enc.equals("Identity-H") && cjkMirror != null)
                    {
                        PdfArray df = (PdfArray)PdfReader.getPdfObjectRelease(font.get(PdfName.DESCENDANTFONTS));
                        PdfDictionary cidft = (PdfDictionary)PdfReader.getPdfObjectRelease(df.getPdfObject(0));
                        PdfNumber dwo = (PdfNumber)PdfReader.getPdfObjectRelease(cidft.get(PdfName.DW));
                        if(dwo != null)
                            defaultWidth = dwo.intValue();
                        hMetrics = readWidths((PdfArray)PdfReader.getPdfObjectRelease(cidft.get(PdfName.W)));
                        PdfDictionary fontDesc = (PdfDictionary)PdfReader.getPdfObjectRelease(cidft.get(PdfName.FONTDESCRIPTOR));
                        fillFontDesc(fontDesc);
                    } else
                    {
                        processType0(font);
                    }
                }
            }
        }
    }

    private void processType0(PdfDictionary font)
    {
        try
        {
            PdfObject toUniObject = PdfReader.getPdfObjectRelease(font.get(PdfName.TOUNICODE));
            PdfArray df = (PdfArray)PdfReader.getPdfObjectRelease(font.get(PdfName.DESCENDANTFONTS));
            PdfDictionary cidft = (PdfDictionary)PdfReader.getPdfObjectRelease(df.getPdfObject(0));
            PdfNumber dwo = (PdfNumber)PdfReader.getPdfObjectRelease(cidft.get(PdfName.DW));
            int dw = 1000;
            if(dwo != null)
                dw = dwo.intValue();
            IntHashtable widths = readWidths((PdfArray)PdfReader.getPdfObjectRelease(cidft.get(PdfName.W)));
            PdfDictionary fontDesc = (PdfDictionary)PdfReader.getPdfObjectRelease(cidft.get(PdfName.FONTDESCRIPTOR));
            fillFontDesc(fontDesc);
            if(toUniObject instanceof PRStream)
                fillMetrics(PdfReader.getStreamBytes((PRStream)toUniObject), widths, dw);
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    private IntHashtable readWidths(PdfArray ws)
    {
        IntHashtable hh = new IntHashtable();
        if(ws == null)
            return hh;
        for(int k = 0; k < ws.size(); k++)
        {
            int c1 = ((PdfNumber)PdfReader.getPdfObjectRelease(ws.getPdfObject(k))).intValue();
            PdfObject obj = PdfReader.getPdfObjectRelease(ws.getPdfObject(++k));
            if(obj.isArray())
            {
                PdfArray a2 = (PdfArray)obj;
                for(int j = 0; j < a2.size(); j++)
                {
                    int c2 = ((PdfNumber)PdfReader.getPdfObjectRelease(a2.getPdfObject(j))).intValue();
                    hh.put(c1++, c2);
                }

                continue;
            }
            int c2 = ((PdfNumber)obj).intValue();
            int w = ((PdfNumber)PdfReader.getPdfObjectRelease(ws.getPdfObject(++k))).intValue();
            for(; c1 <= c2; c1++)
                hh.put(c1, w);

        }

        return hh;
    }

    private String decodeString(PdfString ps)
    {
        if(ps.isHexWriting())
            return PdfEncodings.convertToString(ps.getBytes(), "UnicodeBigUnmarked");
        else
            return ps.toUnicodeString();
    }

    private void fillMetrics(byte touni[], IntHashtable widths, int dw)
    {
        try
        {
            PdfContentParser ps = new PdfContentParser(new PRTokeniser(new RandomAccessFileOrArray((new RandomAccessSourceFactory()).createSource(touni))));
            PdfObject ob = null;
            boolean notFound = true;
            int nestLevel = 0;
            int maxExc = 50;
label0:
            do
            {
                do
                {
                    if(!notFound && nestLevel <= 0)
                        break MISSING_BLOCK_LABEL_604;
                    try
                    {
                        ob = ps.readPRObject();
                    }
                    catch(Exception ex)
                    {
                        if(--maxExc < 0)
                            break MISSING_BLOCK_LABEL_604;
                        continue label0;
                    }
                    if(ob == null)
                        break MISSING_BLOCK_LABEL_604;
                    if(ob.type() != 200)
                        continue label0;
                    if(ob.toString().equals("begin"))
                    {
                        notFound = false;
                        nestLevel++;
                        continue label0;
                    }
                    if(ob.toString().equals("end"))
                    {
                        nestLevel--;
                        continue label0;
                    }
                    if(!ob.toString().equals("beginbfchar"))
                        continue;
                    do
                    {
                        PdfObject nx = ps.readPRObject();
                        if(nx.toString().equals("endbfchar"))
                            continue label0;
                        String cid = decodeString((PdfString)nx);
                        String uni = decodeString((PdfString)ps.readPRObject());
                        if(uni.length() == 1)
                        {
                            int cidc = cid.charAt(0);
                            int unic = uni.charAt(uni.length() - 1);
                            int w = dw;
                            if(widths.containsKey(cidc))
                                w = widths.get(cidc);
                            metrics.put(Integer.valueOf(unic), new int[] {
                                cidc, w
                            });
                        }
                    } while(true);
                } while(!ob.toString().equals("beginbfrange"));
                do
                {
                    PdfObject nx = ps.readPRObject();
                    if(nx.toString().equals("endbfrange"))
                        break;
                    String cid1 = decodeString((PdfString)nx);
                    String cid2 = decodeString((PdfString)ps.readPRObject());
                    int cid1c = cid1.charAt(0);
                    int cid2c = cid2.charAt(0);
                    PdfObject ob2 = ps.readPRObject();
                    if(ob2.isString())
                    {
                        String uni = decodeString((PdfString)ob2);
                        if(uni.length() == 1)
                        {
                            int unic = uni.charAt(uni.length() - 1);
                            while(cid1c <= cid2c) 
                            {
                                int w = dw;
                                if(widths.containsKey(cid1c))
                                    w = widths.get(cid1c);
                                metrics.put(Integer.valueOf(unic), new int[] {
                                    cid1c, w
                                });
                                cid1c++;
                                unic++;
                            }
                        }
                    } else
                    {
                        PdfArray a = (PdfArray)ob2;
                        int j = 0;
                        while(j < a.size()) 
                        {
                            String uni = decodeString(a.getAsString(j));
                            if(uni.length() == 1)
                            {
                                int unic = uni.charAt(uni.length() - 1);
                                int w = dw;
                                if(widths.containsKey(cid1c))
                                    w = widths.get(cid1c);
                                metrics.put(Integer.valueOf(unic), new int[] {
                                    cid1c, w
                                });
                            }
                            j++;
                            cid1c++;
                        }
                    }
                } while(true);
            } while(true);
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    private void doType1TT()
    {
        CMapToUnicode toUnicode = null;
        PdfObject enc = PdfReader.getPdfObject(font.get(PdfName.ENCODING));
        if(enc == null)
        {
            fillEncoding(null);
            try
            {
                toUnicode = processToUnicode();
                if(toUnicode != null)
                {
                    Map rm = toUnicode.createReverseMapping();
                    java.util.Map.Entry kv;
                    for(Iterator i$ = rm.entrySet().iterator(); i$.hasNext(); byte2uni.put(((Integer)kv.getValue()).intValue(), ((Integer)kv.getKey()).intValue()))
                    {
                        kv = (java.util.Map.Entry)i$.next();
                        uni2byte.put(((Integer)kv.getKey()).intValue(), ((Integer)kv.getValue()).intValue());
                    }

                }
            }
            catch(Exception ex)
            {
                throw new ExceptionConverter(ex);
            }
        } else
        if(enc.isName())
            fillEncoding((PdfName)enc);
        else
        if(enc.isDictionary())
        {
            PdfDictionary encDic = (PdfDictionary)enc;
            enc = PdfReader.getPdfObject(encDic.get(PdfName.BASEENCODING));
            if(enc == null)
                fillEncoding(null);
            else
                fillEncoding((PdfName)enc);
            PdfArray diffs = encDic.getAsArray(PdfName.DIFFERENCES);
            if(diffs != null)
            {
                diffmap = new IntHashtable();
                int currentNumber = 0;
                for(int k = 0; k < diffs.size(); k++)
                {
                    PdfObject obj = diffs.getPdfObject(k);
                    if(obj.isNumber())
                    {
                        currentNumber = ((PdfNumber)obj).intValue();
                        continue;
                    }
                    int c[] = GlyphList.nameToUnicode(PdfName.decodeName(((PdfName)obj).toString()));
                    if(c != null && c.length > 0)
                    {
                        uni2byte.put(c[0], currentNumber);
                        byte2uni.put(currentNumber, c[0]);
                        diffmap.put(c[0], currentNumber);
                    } else
                    {
                        if(toUnicode == null)
                        {
                            toUnicode = processToUnicode();
                            if(toUnicode == null)
                                toUnicode = new CMapToUnicode();
                        }
                        String unicode = toUnicode.lookup(new byte[] {
                            (byte)currentNumber
                        }, 0, 1);
                        if(unicode != null && unicode.length() == 1)
                        {
                            uni2byte.put(unicode.charAt(0), currentNumber);
                            byte2uni.put(currentNumber, unicode.charAt(0));
                            diffmap.put(unicode.charAt(0), currentNumber);
                        }
                    }
                    currentNumber++;
                }

            }
        }
        PdfArray newWidths = font.getAsArray(PdfName.WIDTHS);
        PdfNumber first = font.getAsNumber(PdfName.FIRSTCHAR);
        PdfNumber last = font.getAsNumber(PdfName.LASTCHAR);
        if(BuiltinFonts14.containsKey(fontName))
        {
            BaseFont bf;
            try
            {
                bf = BaseFont.createFont(fontName, "Cp1252", false);
            }
            catch(Exception e)
            {
                throw new ExceptionConverter(e);
            }
            int e[] = uni2byte.toOrderedKeys();
            for(int k = 0; k < e.length; k++)
            {
                int n = uni2byte.get(e[k]);
                widths[n] = bf.getRawWidth(n, GlyphList.unicodeToName(e[k]));
            }

            if(diffmap != null)
            {
                e = diffmap.toOrderedKeys();
                for(int k = 0; k < e.length; k++)
                {
                    int n = diffmap.get(e[k]);
                    widths[n] = bf.getRawWidth(n, GlyphList.unicodeToName(e[k]));
                }

                diffmap = null;
            }
            ascender = bf.getFontDescriptor(1, 1000F);
            capHeight = bf.getFontDescriptor(2, 1000F);
            descender = bf.getFontDescriptor(3, 1000F);
            italicAngle = bf.getFontDescriptor(4, 1000F);
            fontWeight = bf.getFontDescriptor(23, 1000F);
            llx = bf.getFontDescriptor(5, 1000F);
            lly = bf.getFontDescriptor(6, 1000F);
            urx = bf.getFontDescriptor(7, 1000F);
            ury = bf.getFontDescriptor(8, 1000F);
        }
        if(first != null && last != null && newWidths != null)
        {
            int f = first.intValue();
            int nSize = f + newWidths.size();
            if(widths.length < nSize)
            {
                int tmp[] = new int[nSize];
                System.arraycopy(widths, 0, tmp, 0, f);
                widths = tmp;
            }
            for(int k = 0; k < newWidths.size(); k++)
                widths[f + k] = newWidths.getAsNumber(k).intValue();

        }
        fillFontDesc(font.getAsDict(PdfName.FONTDESCRIPTOR));
    }

    private CMapToUnicode processToUnicode()
    {
        CMapToUnicode cmapRet = null;
        PdfObject toUni = PdfReader.getPdfObjectRelease(font.get(PdfName.TOUNICODE));
        if(toUni instanceof PRStream)
            try
            {
                byte touni[] = PdfReader.getStreamBytes((PRStream)toUni);
                CidLocationFromByte lb = new CidLocationFromByte(touni);
                cmapRet = new CMapToUnicode();
                CMapParserEx.parseCid("", cmapRet, lb);
            }
            catch(Exception e)
            {
                cmapRet = null;
            }
        return cmapRet;
    }

    private void fillFontDesc(PdfDictionary fontDesc)
    {
        if(fontDesc == null)
            return;
        PdfNumber v = fontDesc.getAsNumber(PdfName.ASCENT);
        if(v != null)
            ascender = v.floatValue();
        v = fontDesc.getAsNumber(PdfName.CAPHEIGHT);
        if(v != null)
            capHeight = v.floatValue();
        v = fontDesc.getAsNumber(PdfName.DESCENT);
        if(v != null)
            descender = v.floatValue();
        v = fontDesc.getAsNumber(PdfName.ITALICANGLE);
        if(v != null)
            italicAngle = v.floatValue();
        v = fontDesc.getAsNumber(PdfName.FONTWEIGHT);
        if(v != null)
            fontWeight = v.floatValue();
        PdfArray bbox = fontDesc.getAsArray(PdfName.FONTBBOX);
        if(bbox != null)
        {
            llx = bbox.getAsNumber(0).floatValue();
            lly = bbox.getAsNumber(1).floatValue();
            urx = bbox.getAsNumber(2).floatValue();
            ury = bbox.getAsNumber(3).floatValue();
            if(llx > urx)
            {
                float t = llx;
                llx = urx;
                urx = t;
            }
            if(lly > ury)
            {
                float t = lly;
                lly = ury;
                ury = t;
            }
        }
        float maxAscent = Math.max(ury, ascender);
        float minDescent = Math.min(lly, descender);
        ascender = (maxAscent * 1000F) / (maxAscent - minDescent);
        descender = (minDescent * 1000F) / (maxAscent - minDescent);
    }

    private void fillEncoding(PdfName encoding)
    {
        if(PdfName.MAC_ROMAN_ENCODING.equals(encoding) || PdfName.WIN_ANSI_ENCODING.equals(encoding))
        {
            byte b[] = new byte[256];
            for(int k = 0; k < 256; k++)
                b[k] = (byte)k;

            String enc = "Cp1252";
            if(PdfName.MAC_ROMAN_ENCODING.equals(encoding))
                enc = "MacRoman";
            String cv = PdfEncodings.convertToString(b, enc);
            char arr[] = cv.toCharArray();
            for(int k = 0; k < 256; k++)
            {
                uni2byte.put(arr[k], k);
                byte2uni.put(k, arr[k]);
            }

        } else
        {
            for(int k = 0; k < 256; k++)
            {
                uni2byte.put(stdEnc[k], k);
                byte2uni.put(k, stdEnc[k]);
            }

        }
    }

    public String[][] getFamilyFontName()
    {
        return getFullFontName();
    }

    public float getFontDescriptor(int key, float fontSize)
    {
        if(cjkMirror != null)
            return cjkMirror.getFontDescriptor(key, fontSize);
        switch(key)
        {
        case 1: // '\001'
        case 9: // '\t'
            return (ascender * fontSize) / 1000F;

        case 2: // '\002'
            return (capHeight * fontSize) / 1000F;

        case 3: // '\003'
        case 10: // '\n'
            return (descender * fontSize) / 1000F;

        case 4: // '\004'
            return italicAngle;

        case 5: // '\005'
            return (llx * fontSize) / 1000F;

        case 6: // '\006'
            return (lly * fontSize) / 1000F;

        case 7: // '\007'
            return (urx * fontSize) / 1000F;

        case 8: // '\b'
            return (ury * fontSize) / 1000F;

        case 11: // '\013'
            return 0.0F;

        case 12: // '\f'
            return ((urx - llx) * fontSize) / 1000F;

        case 23: // '\027'
            return (fontWeight * fontSize) / 1000F;

        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        case 16: // '\020'
        case 17: // '\021'
        case 18: // '\022'
        case 19: // '\023'
        case 20: // '\024'
        case 21: // '\025'
        case 22: // '\026'
        default:
            return 0.0F;
        }
    }

    public String[][] getFullFontName()
    {
        return (new String[][] {
            new String[] {
                "", "", "", fontName
            }
        });
    }

    public String[][] getAllNameEntries()
    {
        return (new String[][] {
            new String[] {
                "4", "", "", "", fontName
            }
        });
    }

    public int getKerning(int char1, int char2)
    {
        return 0;
    }

    public String getPostscriptFontName()
    {
        return fontName;
    }

    int getRawWidth(int c, String name)
    {
        return 0;
    }

    public boolean hasKernPairs()
    {
        return false;
    }

    void writeFont(PdfWriter pdfwriter, PdfIndirectReference pdfindirectreference, Object aobj[])
        throws DocumentException, IOException
    {
    }

    public PdfStream getFullFontStream()
    {
        return null;
    }

    public int getWidth(int char1)
    {
        if(isType0)
        {
            if(hMetrics != null && cjkMirror != null && !cjkMirror.isVertical())
            {
                int c = cjkMirror.getCidCode(char1);
                int v = hMetrics.get(c);
                if(v > 0)
                    return v;
                else
                    return defaultWidth;
            }
            int ws[] = (int[])metrics.get(Integer.valueOf(char1));
            if(ws != null)
                return ws[1];
            else
                return 0;
        }
        if(cjkMirror != null)
            return cjkMirror.getWidth(char1);
        else
            return super.getWidth(char1);
    }

    public int getWidth(String text)
    {
        if(isType0)
        {
            int total = 0;
            if(hMetrics != null && cjkMirror != null && !cjkMirror.isVertical())
            {
                if(((CJKFont)cjkMirror).isIdentity())
                {
                    for(int k = 0; k < text.length(); k++)
                        total += getWidth(text.charAt(k));

                } else
                {
                    for(int k = 0; k < text.length(); k++)
                    {
                        int val;
                        if(Utilities.isSurrogatePair(text, k))
                        {
                            val = Utilities.convertToUtf32(text, k);
                            k++;
                        } else
                        {
                            val = text.charAt(k);
                        }
                        total += getWidth(val);
                    }

                }
            } else
            {
                char chars[] = text.toCharArray();
                int len = chars.length;
                for(int k = 0; k < len; k++)
                {
                    int ws[] = (int[])metrics.get(Integer.valueOf(chars[k]));
                    if(ws != null)
                        total += ws[1];
                }

            }
            return total;
        }
        if(cjkMirror != null)
            return cjkMirror.getWidth(text);
        else
            return super.getWidth(text);
    }

    public byte[] convertToBytes(String text)
    {
        if(cjkMirror != null)
            return cjkMirror.convertToBytes(text);
        if(isType0)
        {
            char chars[] = text.toCharArray();
            int len = chars.length;
            byte b[] = new byte[len * 2];
            int bptr = 0;
            for(int k = 0; k < len; k++)
            {
                int ws[] = (int[])metrics.get(Integer.valueOf(chars[k]));
                if(ws != null)
                {
                    int g = ws[0];
                    b[bptr++] = (byte)(g / 256);
                    b[bptr++] = (byte)g;
                }
            }

            if(bptr == b.length)
            {
                return b;
            } else
            {
                byte nb[] = new byte[bptr];
                System.arraycopy(b, 0, nb, 0, bptr);
                return nb;
            }
        }
        char cc[] = text.toCharArray();
        byte b[] = new byte[cc.length];
        int ptr = 0;
        for(int k = 0; k < cc.length; k++)
            if(uni2byte.containsKey(cc[k]))
                b[ptr++] = (byte)uni2byte.get(cc[k]);

        if(ptr == b.length)
        {
            return b;
        } else
        {
            byte b2[] = new byte[ptr];
            System.arraycopy(b, 0, b2, 0, ptr);
            return b2;
        }
    }

    byte[] convertToBytes(int char1)
    {
        if(cjkMirror != null)
            return cjkMirror.convertToBytes(char1);
        if(isType0)
        {
            int ws[] = (int[])metrics.get(Integer.valueOf(char1));
            if(ws != null)
            {
                int g = ws[0];
                return (new byte[] {
                    (byte)(g / 256), (byte)g
                });
            } else
            {
                return new byte[0];
            }
        }
        if(uni2byte.containsKey(char1))
            return (new byte[] {
                (byte)uni2byte.get(char1)
            });
        else
            return new byte[0];
    }

    PdfIndirectReference getIndirectReference()
    {
        if(refFont == null)
            throw new IllegalArgumentException("Font reuse not allowed with direct font objects.");
        else
            return refFont;
    }

    public boolean charExists(int c)
    {
        if(cjkMirror != null)
            return cjkMirror.charExists(c);
        if(isType0)
            return metrics.containsKey(Integer.valueOf(c));
        else
            return super.charExists(c);
    }

    public void setPostscriptFontName(String s)
    {
    }

    public boolean setKerning(int char1, int char2, int kern)
    {
        return false;
    }

    public int[] getCharBBox(int c)
    {
        return null;
    }

    protected int[] getRawCharBBox(int c, String name)
    {
        return null;
    }

    public boolean isVertical()
    {
        if(cjkMirror != null)
            return cjkMirror.isVertical();
        else
            return super.isVertical();
    }

    IntHashtable getUni2Byte()
    {
        return uni2byte;
    }

    IntHashtable getByte2Uni()
    {
        return byte2uni;
    }

    IntHashtable getDiffmap()
    {
        return diffmap;
    }

    private HashMap metrics;
    private String fontName;
    private PRIndirectReference refFont;
    private PdfDictionary font;
    private IntHashtable uni2byte;
    private IntHashtable byte2uni;
    private IntHashtable diffmap;
    private float ascender;
    private float capHeight;
    private float descender;
    private float italicAngle;
    private float fontWeight;
    private float llx;
    private float lly;
    private float urx;
    private float ury;
    protected boolean isType0;
    protected int defaultWidth;
    private IntHashtable hMetrics;
    protected String cjkEncoding;
    protected String uniMap;
    private BaseFont cjkMirror;
    private static final int stdEnc[] = {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 32, 33, 34, 35, 36, 37, 38, 8217, 
        40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 
        50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 
        60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 
        70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 
        80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 
        90, 91, 92, 93, 94, 95, 8216, 97, 98, 99, 
        100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 
        110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 
        120, 121, 122, 123, 124, 125, 126, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 161, 162, 163, 8260, 165, 402, 167, 164, 39, 
        8220, 171, 8249, 8250, 64257, 64258, 0, 8211, 8224, 8225, 
        183, 0, 182, 8226, 8218, 8222, 8221, 187, 8230, 8240, 
        0, 191, 0, 96, 180, 710, 732, 175, 728, 729, 
        168, 0, 730, 184, 0, 733, 731, 711, 8212, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 198, 0, 170, 0, 0, 
        0, 0, 321, 216, 338, 186, 0, 0, 0, 0, 
        0, 230, 0, 0, 0, 305, 0, 0, 322, 248, 
        339, 223, 0, 0, 0, 0
    };

}
