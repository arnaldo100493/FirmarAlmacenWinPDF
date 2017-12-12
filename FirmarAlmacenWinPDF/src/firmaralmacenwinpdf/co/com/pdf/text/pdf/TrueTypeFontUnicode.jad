// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TrueTypeFontUnicode.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.DocumentException;
import co.com.pdf.text.Utilities;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.fonts.otf.GlyphSubstitutionTableReader;
import co.com.pdf.text.pdf.fonts.otf.Language;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            TrueTypeFont, PdfStream, PdfDictionary, PdfName, 
//            PdfString, PdfNumber, PdfLiteral, PdfArray, 
//            PdfIndirectReference, PdfEncodings, PdfWriter, TtfUnicodeWriter, 
//            BaseFont

class TrueTypeFontUnicode extends TrueTypeFont
    implements Comparator
{

    TrueTypeFontUnicode(String ttFile, String enc, boolean emb, byte ttfAfm[], boolean forceRead)
        throws DocumentException, IOException
    {
        String nameBase = getBaseName(ttFile);
        String ttcName = getTTCName(nameBase);
        if(nameBase.length() < ttFile.length())
            style = ttFile.substring(nameBase.length());
        encoding = enc;
        embedded = emb;
        fileName = ttcName;
        ttcIndex = "";
        if(ttcName.length() < nameBase.length())
            ttcIndex = nameBase.substring(ttcName.length() + 1);
        fontType = 3;
        if((fileName.toLowerCase().endsWith(".ttf") || fileName.toLowerCase().endsWith(".otf") || fileName.toLowerCase().endsWith(".ttc")) && (enc.equals("Identity-H") || enc.equals("Identity-V")) && emb)
        {
            process(ttfAfm, forceRead);
            if(os_2.fsType == 2)
                throw new DocumentException(MessageLocalization.getComposedMessage("1.cannot.be.embedded.due.to.licensing.restrictions", new Object[] {
                    (new StringBuilder()).append(fileName).append(style).toString()
                }));
            if(cmap31 == null && !fontSpecific || cmap10 == null && fontSpecific)
                directTextToByte = true;
            if(fontSpecific)
            {
                fontSpecific = false;
                String tempEncoding = encoding;
                encoding = "";
                createEncoding();
                encoding = tempEncoding;
                fontSpecific = true;
            }
        } else
        {
            throw new DocumentException(MessageLocalization.getComposedMessage("1.2.is.not.a.ttf.font.file", new Object[] {
                fileName, style
            }));
        }
        vertical = enc.endsWith("V");
    }

    void process(byte ttfAfm[], boolean preload)
        throws DocumentException, IOException
    {
        super.process(ttfAfm, preload);
    }

    public int getWidth(int char1)
    {
        if(vertical)
            return 1000;
        if(fontSpecific)
        {
            if((char1 & 0xff00) == 0 || (char1 & 0xff00) == 61440)
                return getRawWidth(char1 & 0xff, null);
            else
                return 0;
        } else
        {
            return getRawWidth(char1, encoding);
        }
    }

    public int getWidth(String text)
    {
        if(vertical)
            return text.length() * 1000;
        int total = 0;
        if(fontSpecific)
        {
            char cc[] = text.toCharArray();
            int len = cc.length;
            for(int k = 0; k < len; k++)
            {
                char c = cc[k];
                if((c & 0xff00) == 0 || (c & 0xff00) == 61440)
                    total += getRawWidth(c & 0xff, null);
            }

        } else
        {
            int len = text.length();
            for(int k = 0; k < len; k++)
                if(Utilities.isSurrogatePair(text, k))
                {
                    total += getRawWidth(Utilities.convertToUtf32(text, k), encoding);
                    k++;
                } else
                {
                    total += getRawWidth(text.charAt(k), encoding);
                }

        }
        return total;
    }

    public PdfStream getToUnicode(Object metrics[])
    {
        if(metrics.length == 0)
            return null;
        StringBuffer buf = new StringBuffer("/CIDInit /ProcSet findresource begin\n12 dict begin\nbegincmap\n/CIDSystemInfo\n<< /Registry (TTX+0)\n/Ordering (T42UV)\n/Supplement 0\n>> def\n/CMapName /TTX+0 def\n/CMapType 2 def\n1 begincodespacerange\n<0000><FFFF>\nendcodespacerange\n");
        int size = 0;
        for(int k = 0; k < metrics.length; k++)
        {
            if(size == 0)
            {
                if(k != 0)
                    buf.append("endbfrange\n");
                size = Math.min(100, metrics.length - k);
                buf.append(size).append(" beginbfrange\n");
            }
            size--;
            int metric[] = (int[])(int[])metrics[k];
            String fromTo = toHex(metric[0]);
            buf.append(fromTo).append(fromTo).append(toHex(metric[2])).append('\n');
        }

        buf.append("endbfrange\nendcmap\nCMapName currentdict /CMap defineresource pop\nend end\n");
        String s = buf.toString();
        PdfStream stream = new PdfStream(PdfEncodings.convertToBytes(s, null));
        stream.flateCompress(compressionLevel);
        return stream;
    }

    private static String toHex4(int n)
    {
        String s = (new StringBuilder()).append("0000").append(Integer.toHexString(n)).toString();
        return s.substring(s.length() - 4);
    }

    static String toHex(int n)
    {
        if(n < 0x10000)
        {
            return (new StringBuilder()).append("<").append(toHex4(n)).append(">").toString();
        } else
        {
            n -= 0x10000;
            int high = n / 1024 + 55296;
            int low = n % 1024 + 56320;
            return (new StringBuilder()).append("[<").append(toHex4(high)).append(toHex4(low)).append(">]").toString();
        }
    }

    public PdfDictionary getCIDFontType2(PdfIndirectReference fontDescriptor, String subsetPrefix, Object metrics[])
    {
        PdfDictionary dic = new PdfDictionary(PdfName.FONT);
        if(cff)
        {
            dic.put(PdfName.SUBTYPE, PdfName.CIDFONTTYPE0);
            dic.put(PdfName.BASEFONT, new PdfName((new StringBuilder()).append(subsetPrefix).append(fontName).append("-").append(encoding).toString()));
        } else
        {
            dic.put(PdfName.SUBTYPE, PdfName.CIDFONTTYPE2);
            dic.put(PdfName.BASEFONT, new PdfName((new StringBuilder()).append(subsetPrefix).append(fontName).toString()));
        }
        dic.put(PdfName.FONTDESCRIPTOR, fontDescriptor);
        if(!cff)
            dic.put(PdfName.CIDTOGIDMAP, PdfName.IDENTITY);
        PdfDictionary cdic = new PdfDictionary();
        cdic.put(PdfName.REGISTRY, new PdfString("Adobe"));
        cdic.put(PdfName.ORDERING, new PdfString("Identity"));
        cdic.put(PdfName.SUPPLEMENT, new PdfNumber(0));
        dic.put(PdfName.CIDSYSTEMINFO, cdic);
        if(!vertical)
        {
            dic.put(PdfName.DW, new PdfNumber(1000));
            StringBuffer buf = new StringBuffer("[");
            int lastNumber = -10;
            boolean firstTime = true;
            for(int k = 0; k < metrics.length; k++)
            {
                int metric[] = (int[])(int[])metrics[k];
                if(metric[1] == 1000)
                    continue;
                int m = metric[0];
                if(m == lastNumber + 1)
                {
                    buf.append(' ').append(metric[1]);
                } else
                {
                    if(!firstTime)
                        buf.append(']');
                    firstTime = false;
                    buf.append(m).append('[').append(metric[1]);
                }
                lastNumber = m;
            }

            if(buf.length() > 1)
            {
                buf.append("]]");
                dic.put(PdfName.W, new PdfLiteral(buf.toString()));
            }
        }
        return dic;
    }

    public PdfDictionary getFontBaseType(PdfIndirectReference descendant, String subsetPrefix, PdfIndirectReference toUnicode)
    {
        PdfDictionary dic = new PdfDictionary(PdfName.FONT);
        dic.put(PdfName.SUBTYPE, PdfName.TYPE0);
        if(cff)
            dic.put(PdfName.BASEFONT, new PdfName((new StringBuilder()).append(subsetPrefix).append(fontName).append("-").append(encoding).toString()));
        else
            dic.put(PdfName.BASEFONT, new PdfName((new StringBuilder()).append(subsetPrefix).append(fontName).toString()));
        dic.put(PdfName.ENCODING, new PdfName(encoding));
        dic.put(PdfName.DESCENDANTFONTS, new PdfArray(descendant));
        if(toUnicode != null)
            dic.put(PdfName.TOUNICODE, toUnicode);
        return dic;
    }

    public int compare(int o1[], int o2[])
    {
        int m1 = o1[0];
        int m2 = o2[0];
        if(m1 < m2)
            return -1;
        return m1 != m2 ? 1 : 0;
    }

    void writeFont(PdfWriter writer, PdfIndirectReference ref, Object params[])
        throws DocumentException, IOException
    {
        writer.getTtfUnicodeWriter().writeFont(this, ref, params, rotbits);
    }

    public PdfStream getFullFontStream()
        throws IOException, DocumentException
    {
        if(cff)
            return new BaseFont.StreamFont(readCffFont(), "CIDFontType0C", compressionLevel);
        else
            return super.getFullFontStream();
    }

    public byte[] convertToBytes(String text)
    {
        return null;
    }

    byte[] convertToBytes(int char1)
    {
        return null;
    }

    public int[] getMetricsTT(int c)
    {
        if(cmapExt != null)
            return (int[])cmapExt.get(Integer.valueOf(c));
        HashMap map = null;
        if(fontSpecific)
            map = cmap10;
        else
            map = cmap31;
        if(map == null)
            return null;
        if(fontSpecific)
        {
            if((c & 0xffffff00) == 0 || (c & 0xffffff00) == 61440)
                return (int[])map.get(Integer.valueOf(c & 0xff));
            else
                return null;
        } else
        {
            return (int[])map.get(Integer.valueOf(c));
        }
    }

    public boolean charExists(int c)
    {
        return getMetricsTT(c) != null;
    }

    public boolean setCharAdvance(int c, int advance)
    {
        int m[] = getMetricsTT(c);
        if(m == null)
        {
            return false;
        } else
        {
            m[1] = advance;
            return true;
        }
    }

    public int[] getCharBBox(int c)
    {
        if(bboxes == null)
            return null;
        int m[] = getMetricsTT(c);
        if(m == null)
            return null;
        else
            return bboxes[m[0]];
    }

    protected Map getGlyphSubstitutionMap()
    {
        return glyphSubstitutionMap;
    }

    Language getSupportedLanguage()
    {
        return supportedLanguage;
    }

    private void readGsubTable()
        throws IOException
    {
        if(tables.get("GSUB") != null)
        {
            Map glyphToCharacterMap = new HashMap(cmap31.size());
            char c;
            int glyphCode;
            for(Iterator i$ = cmap31.keySet().iterator(); i$.hasNext(); glyphToCharacterMap.put(Integer.valueOf(glyphCode), Character.valueOf(c)))
            {
                Integer charCode = (Integer)i$.next();
                c = (char)charCode.intValue();
                glyphCode = ((int[])cmap31.get(charCode))[0];
            }

            GlyphSubstitutionTableReader gsubReader = new GlyphSubstitutionTableReader(fileName, ((int[])tables.get("GSUB"))[0], glyphToCharacterMap, glyphWidthsByIndex);
            try
            {
                gsubReader.read();
                supportedLanguage = gsubReader.getSupportedLanguage();
                if(SUPPORTED_LANGUAGES_FOR_OTF.contains(supportedLanguage))
                    glyphSubstitutionMap = gsubReader.getGlyphSubstitutionMap();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public volatile int compare(Object x0, Object x1)
    {
        return compare((int[])x0, (int[])x1);
    }

    private static final List SUPPORTED_LANGUAGES_FOR_OTF;
    private Map glyphSubstitutionMap;
    private Language supportedLanguage;
    private static final byte rotbits[] = {
        -128, 64, 32, 16, 8, 4, 2, 1
    };

    static 
    {
        SUPPORTED_LANGUAGES_FOR_OTF = Arrays.asList(new Language[] {
            Language.BENGALI
        });
    }
}
