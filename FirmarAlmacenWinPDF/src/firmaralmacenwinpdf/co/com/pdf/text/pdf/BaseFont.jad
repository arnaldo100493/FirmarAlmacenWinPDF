// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseFont.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.DocumentException;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            Type1Font, TrueTypeFontUnicode, TrueTypeFont, CJKFont, 
//            DocumentFont, IntHashtable, EnumerateTTC, PdfDictionary, 
//            PdfName, PRIndirectReference, PdfObject, PdfReader, 
//            GlyphList, PdfEncodings, PdfWriter, PdfIndirectReference, 
//            PdfStream, PdfNumber

public abstract class BaseFont
{
    static class StreamFont extends PdfStream
    {

        public StreamFont(byte contents[], int lengths[], int compressionLevel)
            throws DocumentException
        {
            try
            {
                bytes = contents;
                put(PdfName.LENGTH, new PdfNumber(bytes.length));
                for(int k = 0; k < lengths.length; k++)
                    put(new PdfName((new StringBuilder()).append("Length").append(k + 1).toString()), new PdfNumber(lengths[k]));

                flateCompress(compressionLevel);
            }
            catch(Exception e)
            {
                throw new DocumentException(e);
            }
        }

        public StreamFont(byte contents[], String subType, int compressionLevel)
            throws DocumentException
        {
            try
            {
                bytes = contents;
                put(PdfName.LENGTH, new PdfNumber(bytes.length));
                if(subType != null)
                    put(PdfName.SUBTYPE, new PdfName(subType));
                flateCompress(compressionLevel);
            }
            catch(Exception e)
            {
                throw new DocumentException(e);
            }
        }
    }


    protected BaseFont()
    {
        widths = new int[256];
        differences = new String[256];
        unicodeDifferences = new char[256];
        charBBoxes = new int[256][];
        compressionLevel = -1;
        fontSpecific = true;
        forceWidthsOutput = false;
        directTextToByte = false;
        subset = true;
        fastWinansi = false;
        vertical = false;
    }

    public static BaseFont createFont()
        throws DocumentException, IOException
    {
        return createFont("Helvetica", "Cp1252", false);
    }

    public static BaseFont createFont(String name, String encoding, boolean embedded)
        throws DocumentException, IOException
    {
        return createFont(name, encoding, embedded, true, null, null, false);
    }

    public static BaseFont createFont(String name, String encoding, boolean embedded, boolean forceRead)
        throws DocumentException, IOException
    {
        return createFont(name, encoding, embedded, true, null, null, forceRead);
    }

    public static BaseFont createFont(String name, String encoding, boolean embedded, boolean cached, byte ttfAfm[], byte pfb[])
        throws DocumentException, IOException
    {
        return createFont(name, encoding, embedded, cached, ttfAfm, pfb, false);
    }

    public static BaseFont createFont(String name, String encoding, boolean embedded, boolean cached, byte ttfAfm[], byte pfb[], boolean noThrow)
        throws DocumentException, IOException
    {
        return createFont(name, encoding, embedded, cached, ttfAfm, pfb, noThrow, false);
    }

    public static BaseFont createFont(String name, String encoding, boolean embedded, boolean cached, byte ttfAfm[], byte pfb[], boolean noThrow, boolean forceRead)
        throws DocumentException, IOException
    {
        BaseFont fontBuilt;
        String key;
        String nameBase = getBaseName(name);
        encoding = normalizeEncoding(encoding);
        boolean isBuiltinFonts14 = BuiltinFonts14.containsKey(name);
        boolean isCJKFont = isBuiltinFonts14 ? false : CJKFont.isCJKFont(nameBase, encoding);
        if(isBuiltinFonts14 || isCJKFont)
            embedded = false;
        else
        if(encoding.equals("Identity-H") || encoding.equals("Identity-V"))
            embedded = true;
        BaseFont fontFound = null;
        fontBuilt = null;
        key = (new StringBuilder()).append(name).append("\n").append(encoding).append("\n").append(embedded).toString();
        if(cached)
        {
            synchronized(fontCache)
            {
                fontFound = (BaseFont)fontCache.get(key);
            }
            if(fontFound != null)
                return fontFound;
        }
        if(isBuiltinFonts14 || name.toLowerCase().endsWith(".afm") || name.toLowerCase().endsWith(".pfm"))
        {
            fontBuilt = new Type1Font(name, encoding, embedded, ttfAfm, pfb, forceRead);
            fontBuilt.fastWinansi = encoding.equals("Cp1252");
        } else
        if(nameBase.toLowerCase().endsWith(".ttf") || nameBase.toLowerCase().endsWith(".otf") || nameBase.toLowerCase().indexOf(".ttc,") > 0)
        {
            if(encoding.equals("Identity-H") || encoding.equals("Identity-V"))
            {
                fontBuilt = new TrueTypeFontUnicode(name, encoding, embedded, ttfAfm, forceRead);
            } else
            {
                fontBuilt = new TrueTypeFont(name, encoding, embedded, ttfAfm, false, forceRead);
                fontBuilt.fastWinansi = encoding.equals("Cp1252");
            }
        } else
        if(isCJKFont)
            fontBuilt = new CJKFont(name, encoding, embedded);
        else
        if(noThrow)
            return null;
        else
            throw new DocumentException(MessageLocalization.getComposedMessage("font.1.with.2.is.not.recognized", new Object[] {
                name, encoding
            }));
        if(!cached)
            break MISSING_BLOCK_LABEL_438;
        HashMap hashmap1 = fontCache;
        JVM INSTR monitorenter ;
        BaseFont fontFound = (BaseFont)fontCache.get(key);
        if(fontFound != null)
            return fontFound;
        fontCache.put(key, fontBuilt);
        hashmap1;
        JVM INSTR monitorexit ;
        break MISSING_BLOCK_LABEL_438;
        Exception exception1;
        exception1;
        throw exception1;
        return fontBuilt;
    }

    public static BaseFont createFont(PRIndirectReference fontRef)
    {
        return new DocumentFont(fontRef);
    }

    public boolean isVertical()
    {
        return vertical;
    }

    protected static String getBaseName(String name)
    {
        if(name.endsWith(",Bold"))
            return name.substring(0, name.length() - 5);
        if(name.endsWith(",Italic"))
            return name.substring(0, name.length() - 7);
        if(name.endsWith(",BoldItalic"))
            return name.substring(0, name.length() - 11);
        else
            return name;
    }

    protected static String normalizeEncoding(String enc)
    {
        if(enc.equals("winansi") || enc.equals(""))
            return "Cp1252";
        if(enc.equals("macroman"))
            return "MacRoman";
        else
            return enc;
    }

    protected void createEncoding()
    {
        if(encoding.startsWith("#"))
        {
            specialMap = new IntHashtable();
            StringTokenizer tok = new StringTokenizer(encoding.substring(1), " ,\t\n\r\f");
            if(tok.nextToken().equals("full"))
            {
                while(tok.hasMoreTokens()) 
                {
                    String order = tok.nextToken();
                    String name = tok.nextToken();
                    char uni = (char)Integer.parseInt(tok.nextToken(), 16);
                    int orderK;
                    if(order.startsWith("'"))
                        orderK = order.charAt(1);
                    else
                        orderK = Integer.parseInt(order);
                    orderK %= 256;
                    specialMap.put(uni, orderK);
                    differences[orderK] = name;
                    unicodeDifferences[orderK] = uni;
                    widths[orderK] = getRawWidth(uni, name);
                    charBBoxes[orderK] = getRawCharBBox(uni, name);
                }
            } else
            {
                int k = 0;
                if(tok.hasMoreTokens())
                    k = Integer.parseInt(tok.nextToken());
                do
                {
                    if(!tok.hasMoreTokens() || k >= 256)
                        break;
                    String hex = tok.nextToken();
                    int uni = Integer.parseInt(hex, 16) % 0x10000;
                    String name = GlyphList.unicodeToName(uni);
                    if(name != null)
                    {
                        specialMap.put(uni, k);
                        differences[k] = name;
                        unicodeDifferences[k] = (char)uni;
                        widths[k] = getRawWidth(uni, name);
                        charBBoxes[k] = getRawCharBBox(uni, name);
                        k++;
                    }
                } while(true);
            }
            for(int k = 0; k < 256; k++)
                if(differences[k] == null)
                    differences[k] = ".notdef";

        } else
        if(fontSpecific)
        {
            for(int k = 0; k < 256; k++)
            {
                widths[k] = getRawWidth(k, null);
                charBBoxes[k] = getRawCharBBox(k, null);
            }

        } else
        {
            byte b[] = new byte[1];
            for(int k = 0; k < 256; k++)
            {
                b[0] = (byte)k;
                String s = PdfEncodings.convertToString(b, encoding);
                char c;
                if(s.length() > 0)
                    c = s.charAt(0);
                else
                    c = '?';
                String name = GlyphList.unicodeToName(c);
                if(name == null)
                    name = ".notdef";
                differences[k] = name;
                unicodeDifferences[k] = c;
                widths[k] = getRawWidth(c, name);
                charBBoxes[k] = getRawCharBBox(c, name);
            }

        }
    }

    abstract int getRawWidth(int i, String s);

    public abstract int getKerning(int i, int j);

    public abstract boolean setKerning(int i, int j, int k);

    public int getWidth(int char1)
    {
        if(fastWinansi)
            if(char1 < 128 || char1 >= 160 && char1 <= 255)
                return widths[char1];
            else
                return widths[PdfEncodings.winansi.get(char1)];
        int total = 0;
        byte mbytes[] = convertToBytes((char)char1);
        for(int k = 0; k < mbytes.length; k++)
            total += widths[0xff & mbytes[k]];

        return total;
    }

    public int getWidth(String text)
    {
        int total = 0;
        if(fastWinansi)
        {
            int len = text.length();
            for(int k = 0; k < len; k++)
            {
                char char1 = text.charAt(k);
                if(char1 < '\200' || char1 >= '\240' && char1 <= '\377')
                    total += widths[char1];
                else
                    total += widths[PdfEncodings.winansi.get(char1)];
            }

            return total;
        }
        byte mbytes[] = convertToBytes(text);
        for(int k = 0; k < mbytes.length; k++)
            total += widths[0xff & mbytes[k]];

        return total;
    }

    public int getDescent(String text)
    {
        int min = 0;
        char chars[] = text.toCharArray();
        for(int k = 0; k < chars.length; k++)
        {
            int bbox[] = getCharBBox(chars[k]);
            if(bbox != null && bbox[1] < min)
                min = bbox[1];
        }

        return min;
    }

    public int getAscent(String text)
    {
        int max = 0;
        char chars[] = text.toCharArray();
        for(int k = 0; k < chars.length; k++)
        {
            int bbox[] = getCharBBox(chars[k]);
            if(bbox != null && bbox[3] > max)
                max = bbox[3];
        }

        return max;
    }

    public float getDescentPoint(String text, float fontSize)
    {
        return (float)getDescent(text) * 0.001F * fontSize;
    }

    public float getAscentPoint(String text, float fontSize)
    {
        return (float)getAscent(text) * 0.001F * fontSize;
    }

    public float getWidthPointKerned(String text, float fontSize)
    {
        float size = (float)getWidth(text) * 0.001F * fontSize;
        if(!hasKernPairs())
            return size;
        int len = text.length() - 1;
        int kern = 0;
        char c[] = text.toCharArray();
        for(int k = 0; k < len; k++)
            kern += getKerning(c[k], c[k + 1]);

        return size + (float)kern * 0.001F * fontSize;
    }

    public float getWidthPoint(String text, float fontSize)
    {
        return (float)getWidth(text) * 0.001F * fontSize;
    }

    public float getWidthPoint(int char1, float fontSize)
    {
        return (float)getWidth(char1) * 0.001F * fontSize;
    }

    public byte[] convertToBytes(String text)
    {
        if(directTextToByte)
            return PdfEncodings.convertToBytes(text, null);
        if(specialMap != null)
        {
            byte b[] = new byte[text.length()];
            int ptr = 0;
            int length = text.length();
            for(int k = 0; k < length; k++)
            {
                char c = text.charAt(k);
                if(specialMap.containsKey(c))
                    b[ptr++] = (byte)specialMap.get(c);
            }

            if(ptr < length)
            {
                byte b2[] = new byte[ptr];
                System.arraycopy(b, 0, b2, 0, ptr);
                return b2;
            } else
            {
                return b;
            }
        } else
        {
            return PdfEncodings.convertToBytes(text, encoding);
        }
    }

    byte[] convertToBytes(int char1)
    {
        if(directTextToByte)
            return PdfEncodings.convertToBytes((char)char1, null);
        if(specialMap != null)
        {
            if(specialMap.containsKey(char1))
                return (new byte[] {
                    (byte)specialMap.get(char1)
                });
            else
                return new byte[0];
        } else
        {
            return PdfEncodings.convertToBytes((char)char1, encoding);
        }
    }

    abstract void writeFont(PdfWriter pdfwriter, PdfIndirectReference pdfindirectreference, Object aobj[])
        throws DocumentException, IOException;

    abstract PdfStream getFullFontStream()
        throws IOException, DocumentException;

    public String getEncoding()
    {
        return encoding;
    }

    public abstract float getFontDescriptor(int i, float f);

    public void setFontDescriptor(int i, float f)
    {
    }

    public int getFontType()
    {
        return fontType;
    }

    public boolean isEmbedded()
    {
        return embedded;
    }

    public boolean isFontSpecific()
    {
        return fontSpecific;
    }

    public static String createSubsetPrefix()
    {
        StringBuilder s = new StringBuilder("");
        for(int k = 0; k < 6; k++)
            s.append((char)(int)(Math.random() * 26D + 65D));

        return (new StringBuilder()).append(s).append("+").toString();
    }

    char getUnicodeDifferences(int index)
    {
        return unicodeDifferences[index];
    }

    public abstract String getPostscriptFontName();

    public abstract void setPostscriptFontName(String s);

    public abstract String[][] getFullFontName();

    public abstract String[][] getAllNameEntries();

    public static String[][] getFullFontName(String name, String encoding, byte ttfAfm[])
        throws DocumentException, IOException
    {
        String nameBase = getBaseName(name);
        BaseFont fontBuilt = null;
        if(nameBase.toLowerCase().endsWith(".ttf") || nameBase.toLowerCase().endsWith(".otf") || nameBase.toLowerCase().indexOf(".ttc,") > 0)
            fontBuilt = new TrueTypeFont(name, "Cp1252", false, ttfAfm, true, false);
        else
            fontBuilt = createFont(name, encoding, false, false, ttfAfm, null);
        return fontBuilt.getFullFontName();
    }

    public static Object[] getAllFontNames(String name, String encoding, byte ttfAfm[])
        throws DocumentException, IOException
    {
        String nameBase = getBaseName(name);
        BaseFont fontBuilt = null;
        if(nameBase.toLowerCase().endsWith(".ttf") || nameBase.toLowerCase().endsWith(".otf") || nameBase.toLowerCase().indexOf(".ttc,") > 0)
            fontBuilt = new TrueTypeFont(name, "Cp1252", false, ttfAfm, true, false);
        else
            fontBuilt = createFont(name, encoding, false, false, ttfAfm, null);
        return (new Object[] {
            fontBuilt.getPostscriptFontName(), fontBuilt.getFamilyFontName(), fontBuilt.getFullFontName()
        });
    }

    public static String[][] getAllNameEntries(String name, String encoding, byte ttfAfm[])
        throws DocumentException, IOException
    {
        String nameBase = getBaseName(name);
        BaseFont fontBuilt = null;
        if(nameBase.toLowerCase().endsWith(".ttf") || nameBase.toLowerCase().endsWith(".otf") || nameBase.toLowerCase().indexOf(".ttc,") > 0)
            fontBuilt = new TrueTypeFont(name, "Cp1252", false, ttfAfm, true, false);
        else
            fontBuilt = createFont(name, encoding, false, false, ttfAfm, null);
        return fontBuilt.getAllNameEntries();
    }

    public abstract String[][] getFamilyFontName();

    public String[] getCodePagesSupported()
    {
        return new String[0];
    }

    public static String[] enumerateTTCNames(String ttcFile)
        throws DocumentException, IOException
    {
        return (new EnumerateTTC(ttcFile)).getNames();
    }

    public static String[] enumerateTTCNames(byte ttcArray[])
        throws DocumentException, IOException
    {
        return (new EnumerateTTC(ttcArray)).getNames();
    }

    public int[] getWidths()
    {
        return widths;
    }

    public String[] getDifferences()
    {
        return differences;
    }

    public char[] getUnicodeDifferences()
    {
        return unicodeDifferences;
    }

    public boolean isForceWidthsOutput()
    {
        return forceWidthsOutput;
    }

    public void setForceWidthsOutput(boolean forceWidthsOutput)
    {
        this.forceWidthsOutput = forceWidthsOutput;
    }

    public boolean isDirectTextToByte()
    {
        return directTextToByte;
    }

    public void setDirectTextToByte(boolean directTextToByte)
    {
        this.directTextToByte = directTextToByte;
    }

    public boolean isSubset()
    {
        return subset;
    }

    public void setSubset(boolean subset)
    {
        this.subset = subset;
    }

    public int getUnicodeEquivalent(int c)
    {
        return c;
    }

    public int getCidCode(int c)
    {
        return c;
    }

    public abstract boolean hasKernPairs();

    public boolean charExists(int c)
    {
        byte b[] = convertToBytes(c);
        return b.length > 0;
    }

    public boolean setCharAdvance(int c, int advance)
    {
        byte b[] = convertToBytes(c);
        if(b.length == 0)
        {
            return false;
        } else
        {
            widths[0xff & b[0]] = advance;
            return true;
        }
    }

    private static void addFont(PRIndirectReference fontRef, IntHashtable hits, ArrayList fonts)
    {
        PdfObject obj = PdfReader.getPdfObject(fontRef);
        if(obj == null || !obj.isDictionary())
            return;
        PdfDictionary font = (PdfDictionary)obj;
        PdfName subtype = font.getAsName(PdfName.SUBTYPE);
        if(!PdfName.TYPE1.equals(subtype) && !PdfName.TRUETYPE.equals(subtype) && !PdfName.TYPE0.equals(subtype))
        {
            return;
        } else
        {
            PdfName name = font.getAsName(PdfName.BASEFONT);
            fonts.add(((Object) (new Object[] {
                PdfName.decodeName(name.toString()), fontRef
            })));
            hits.put(fontRef.getNumber(), 1);
            return;
        }
    }

    private static void recourseFonts(PdfDictionary page, IntHashtable hits, ArrayList fonts, int level)
    {
        if(++level > 50)
            return;
        if(page == null)
            return;
        PdfDictionary resources = page.getAsDict(PdfName.RESOURCES);
        if(resources == null)
            return;
        PdfDictionary font = resources.getAsDict(PdfName.FONT);
        if(font != null)
        {
            Iterator i$ = font.getKeys().iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                PdfName key = (PdfName)i$.next();
                PdfObject ft = font.get(key);
                if(ft != null && ft.isIndirect())
                {
                    int hit = ((PRIndirectReference)ft).getNumber();
                    if(!hits.containsKey(hit))
                        addFont((PRIndirectReference)ft, hits, fonts);
                }
            } while(true);
        }
        PdfDictionary xobj = resources.getAsDict(PdfName.XOBJECT);
        if(xobj != null)
        {
            Iterator i$ = xobj.getKeys().iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                PdfName key = (PdfName)i$.next();
                PdfObject po = xobj.getDirectObject(key);
                if(po instanceof PdfDictionary)
                    recourseFonts((PdfDictionary)po, hits, fonts, level);
            } while(true);
        }
    }

    public static ArrayList getDocumentFonts(PdfReader reader)
    {
        IntHashtable hits = new IntHashtable();
        ArrayList fonts = new ArrayList();
        int npages = reader.getNumberOfPages();
        for(int k = 1; k <= npages; k++)
            recourseFonts(reader.getPageN(k), hits, fonts, 1);

        return fonts;
    }

    public static ArrayList getDocumentFonts(PdfReader reader, int page)
    {
        IntHashtable hits = new IntHashtable();
        ArrayList fonts = new ArrayList();
        recourseFonts(reader.getPageN(page), hits, fonts, 1);
        return fonts;
    }

    public int[] getCharBBox(int c)
    {
        byte b[] = convertToBytes(c);
        if(b.length == 0)
            return null;
        else
            return charBBoxes[b[0] & 0xff];
    }

    protected abstract int[] getRawCharBBox(int i, String s);

    public void correctArabicAdvance()
    {
        for(char c = '\u064B'; c <= '\u0658'; c++)
            setCharAdvance(c, 0);

        setCharAdvance(1648, 0);
        for(char c = '\u06D6'; c <= '\u06DC'; c++)
            setCharAdvance(c, 0);

        for(char c = '\u06DF'; c <= '\u06E4'; c++)
            setCharAdvance(c, 0);

        for(char c = '\u06E7'; c <= '\u06E8'; c++)
            setCharAdvance(c, 0);

        for(char c = '\u06EA'; c <= '\u06ED'; c++)
            setCharAdvance(c, 0);

    }

    public void addSubsetRange(int range[])
    {
        if(subsetRanges == null)
            subsetRanges = new ArrayList();
        subsetRanges.add(range);
    }

    public int getCompressionLevel()
    {
        return compressionLevel;
    }

    public void setCompressionLevel(int compressionLevel)
    {
        if(compressionLevel < 0 || compressionLevel > 9)
            this.compressionLevel = -1;
        else
            this.compressionLevel = compressionLevel;
    }

    public static final String COURIER = "Courier";
    public static final String COURIER_BOLD = "Courier-Bold";
    public static final String COURIER_OBLIQUE = "Courier-Oblique";
    public static final String COURIER_BOLDOBLIQUE = "Courier-BoldOblique";
    public static final String HELVETICA = "Helvetica";
    public static final String HELVETICA_BOLD = "Helvetica-Bold";
    public static final String HELVETICA_OBLIQUE = "Helvetica-Oblique";
    public static final String HELVETICA_BOLDOBLIQUE = "Helvetica-BoldOblique";
    public static final String SYMBOL = "Symbol";
    public static final String TIMES_ROMAN = "Times-Roman";
    public static final String TIMES_BOLD = "Times-Bold";
    public static final String TIMES_ITALIC = "Times-Italic";
    public static final String TIMES_BOLDITALIC = "Times-BoldItalic";
    public static final String ZAPFDINGBATS = "ZapfDingbats";
    public static final int ASCENT = 1;
    public static final int CAPHEIGHT = 2;
    public static final int DESCENT = 3;
    public static final int ITALICANGLE = 4;
    public static final int BBOXLLX = 5;
    public static final int BBOXLLY = 6;
    public static final int BBOXURX = 7;
    public static final int BBOXURY = 8;
    public static final int AWT_ASCENT = 9;
    public static final int AWT_DESCENT = 10;
    public static final int AWT_LEADING = 11;
    public static final int AWT_MAXADVANCE = 12;
    public static final int UNDERLINE_POSITION = 13;
    public static final int UNDERLINE_THICKNESS = 14;
    public static final int STRIKETHROUGH_POSITION = 15;
    public static final int STRIKETHROUGH_THICKNESS = 16;
    public static final int SUBSCRIPT_SIZE = 17;
    public static final int SUBSCRIPT_OFFSET = 18;
    public static final int SUPERSCRIPT_SIZE = 19;
    public static final int SUPERSCRIPT_OFFSET = 20;
    public static final int WEIGHT_CLASS = 21;
    public static final int WIDTH_CLASS = 22;
    public static final int FONT_WEIGHT = 23;
    public static final int FONT_TYPE_T1 = 0;
    public static final int FONT_TYPE_TT = 1;
    public static final int FONT_TYPE_CJK = 2;
    public static final int FONT_TYPE_TTUNI = 3;
    public static final int FONT_TYPE_DOCUMENT = 4;
    public static final int FONT_TYPE_T3 = 5;
    public static final String IDENTITY_H = "Identity-H";
    public static final String IDENTITY_V = "Identity-V";
    public static final String CP1250 = "Cp1250";
    public static final String CP1252 = "Cp1252";
    public static final String CP1257 = "Cp1257";
    public static final String WINANSI = "Cp1252";
    public static final String MACROMAN = "MacRoman";
    public static final int CHAR_RANGE_LATIN[] = {
        0, 383, 8192, 8303, 8352, 8399, 64256, 64262
    };
    public static final int CHAR_RANGE_ARABIC[] = {
        0, 127, 1536, 1663, 8352, 8399, 64336, 64511, 65136, 65279
    };
    public static final int CHAR_RANGE_HEBREW[] = {
        0, 127, 1424, 1535, 8352, 8399, 64285, 64335
    };
    public static final int CHAR_RANGE_CYRILLIC[] = {
        0, 127, 1024, 1327, 8192, 8303, 8352, 8399
    };
    public static final boolean EMBEDDED = true;
    public static final boolean NOT_EMBEDDED = false;
    public static final boolean CACHED = true;
    public static final boolean NOT_CACHED = false;
    public static final String RESOURCE_PATH = "co/com/pdf/text/pdf/fonts/";
    public static final char CID_NEWLINE = 32767;
    public static final char PARAGRAPH_SEPARATOR = 8233;
    protected ArrayList subsetRanges;
    int fontType;
    public static final String notdef = ".notdef";
    protected int widths[];
    protected String differences[];
    protected char unicodeDifferences[];
    protected int charBBoxes[][];
    protected String encoding;
    protected boolean embedded;
    protected int compressionLevel;
    protected boolean fontSpecific;
    protected static HashMap fontCache = new HashMap();
    protected static final HashMap BuiltinFonts14;
    protected boolean forceWidthsOutput;
    protected boolean directTextToByte;
    protected boolean subset;
    protected boolean fastWinansi;
    protected IntHashtable specialMap;
    protected boolean vertical;

    static 
    {
        BuiltinFonts14 = new HashMap();
        BuiltinFonts14.put("Courier", PdfName.COURIER);
        BuiltinFonts14.put("Courier-Bold", PdfName.COURIER_BOLD);
        BuiltinFonts14.put("Courier-BoldOblique", PdfName.COURIER_BOLDOBLIQUE);
        BuiltinFonts14.put("Courier-Oblique", PdfName.COURIER_OBLIQUE);
        BuiltinFonts14.put("Helvetica", PdfName.HELVETICA);
        BuiltinFonts14.put("Helvetica-Bold", PdfName.HELVETICA_BOLD);
        BuiltinFonts14.put("Helvetica-BoldOblique", PdfName.HELVETICA_BOLDOBLIQUE);
        BuiltinFonts14.put("Helvetica-Oblique", PdfName.HELVETICA_OBLIQUE);
        BuiltinFonts14.put("Symbol", PdfName.SYMBOL);
        BuiltinFonts14.put("Times-Roman", PdfName.TIMES_ROMAN);
        BuiltinFonts14.put("Times-Bold", PdfName.TIMES_BOLD);
        BuiltinFonts14.put("Times-BoldItalic", PdfName.TIMES_BOLDITALIC);
        BuiltinFonts14.put("Times-Italic", PdfName.TIMES_ITALIC);
        BuiltinFonts14.put("ZapfDingbats", PdfName.ZAPFDINGBATS);
    }
}
