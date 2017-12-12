// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CJKFont.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.io.StreamUtil;
import co.com.pdf.text.pdf.fonts.cmaps.CMapCache;
import co.com.pdf.text.pdf.fonts.cmaps.CMapCidByte;
import co.com.pdf.text.pdf.fonts.cmaps.CMapCidUni;
import co.com.pdf.text.pdf.fonts.cmaps.CMapUniCid;
import java.io.*;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            BaseFont, IntHashtable, PdfDictionary, PdfLiteral, 
//            PdfName, PdfString, PdfNumber, PdfArray, 
//            PdfWriter, PdfIndirectReference, PdfObject, PdfIndirectObject, 
//            PdfStream

class CJKFont extends BaseFont
{

    private static void loadProperties()
    {
label0:
        {
            if(propertiesLoaded)
                return;
            synchronized(allFonts)
            {
                if(!propertiesLoaded)
                    break label0;
            }
            return;
        }
        try
        {
            loadRegistry();
            String font;
            for(Iterator i$ = ((Set)registryNames.get("fonts")).iterator(); i$.hasNext(); allFonts.put(font, readFontProperties(font)))
                font = (String)i$.next();

        }
        catch(Exception e) { }
        propertiesLoaded = true;
        hashmap;
        JVM INSTR monitorexit ;
          goto _L1
        exception;
        throw exception;
_L1:
    }

    private static void loadRegistry()
        throws IOException
    {
        InputStream is = StreamUtil.getResourceStream("co/com/pdf/text/pdf/fonts/cmaps/cjk_registry.properties");
        Properties p = new Properties();
        p.load(is);
        is.close();
        Object key;
        Set hs;
        for(Iterator i$ = p.keySet().iterator(); i$.hasNext(); registryNames.put((String)key, hs))
        {
            key = i$.next();
            String value = p.getProperty((String)key);
            String sp[] = value.split(" ");
            hs = new HashSet();
            String arr$[] = sp;
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                String s = arr$[i$];
                if(s.length() > 0)
                    hs.add(s);
            }

        }

    }

    CJKFont(String fontName, String enc, boolean emb)
        throws DocumentException
    {
        style = "";
        cidDirect = false;
        loadProperties();
        fontType = 2;
        String nameBase = getBaseName(fontName);
        if(!isCJKFont(nameBase, enc))
            throw new DocumentException(MessageLocalization.getComposedMessage("font.1.with.2.encoding.is.not.a.cjk.font", new Object[] {
                fontName, enc
            }));
        if(nameBase.length() < fontName.length())
        {
            style = fontName.substring(nameBase.length());
            fontName = nameBase;
        }
        this.fontName = fontName;
        encoding = "UnicodeBigUnmarked";
        vertical = enc.endsWith("V");
        CMap = enc;
        if(enc.equals("Identity-H") || enc.equals("Identity-V"))
            cidDirect = true;
        loadCMaps();
    }

    String getUniMap()
    {
        return uniMap;
    }

    private void loadCMaps()
        throws DocumentException
    {
        try
        {
            fontDesc = (HashMap)allFonts.get(fontName);
            hMetrics = (IntHashtable)fontDesc.get("W");
            vMetrics = (IntHashtable)fontDesc.get("W2");
            String registry = (String)fontDesc.get("Registry");
            uniMap = "";
            Iterator i$ = ((Set)registryNames.get((new StringBuilder()).append(registry).append("_Uni").toString())).iterator();
            String name;
            do
            {
                if(!i$.hasNext())
                    break;
                name = (String)i$.next();
                uniMap = name;
            } while((!name.endsWith("V") || !vertical) && (name.endsWith("V") || vertical));
            if(cidDirect)
            {
                cidUni = CMapCache.getCachedCMapCidUni(uniMap);
            } else
            {
                uniCid = CMapCache.getCachedCMapUniCid(uniMap);
                cidByte = CMapCache.getCachedCMapCidByte(CMap);
            }
        }
        catch(Exception ex)
        {
            throw new DocumentException(ex);
        }
    }

    public static String GetCompatibleFont(String enc)
    {
        loadProperties();
        String registry = null;
        Iterator i$ = registryNames.entrySet().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            java.util.Map.Entry e = (java.util.Map.Entry)i$.next();
            if(!((Set)e.getValue()).contains(enc))
                continue;
            registry = (String)e.getKey();
            break;
        } while(true);
        if(registry == null)
            return null;
        for(i$ = allFonts.entrySet().iterator(); i$.hasNext();)
        {
            java.util.Map.Entry e = (java.util.Map.Entry)i$.next();
            if(registry.equals(((HashMap)e.getValue()).get("Registry")))
                return (String)e.getKey();
        }

        return null;
    }

    public static boolean isCJKFont(String fontName, String enc)
    {
        loadProperties();
        if(!registryNames.containsKey("fonts"))
            return false;
        if(!((Set)registryNames.get("fonts")).contains(fontName))
            return false;
        if(enc.equals("Identity-H") || enc.equals("Identity-V"))
        {
            return true;
        } else
        {
            String registry = (String)((HashMap)allFonts.get(fontName)).get("Registry");
            Set encodings = (Set)registryNames.get(registry);
            return encodings != null && encodings.contains(enc);
        }
    }

    public int getWidth(int char1)
    {
        int c = char1;
        if(!cidDirect)
            c = uniCid.lookup(char1);
        int v;
        if(vertical)
            v = vMetrics.get(c);
        else
            v = hMetrics.get(c);
        if(v > 0)
            return v;
        else
            return 1000;
    }

    public int getWidth(String text)
    {
        int total = 0;
        if(cidDirect)
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
        return total;
    }

    int getRawWidth(int c, String name)
    {
        return 0;
    }

    public int getKerning(int char1, int char2)
    {
        return 0;
    }

    private PdfDictionary getFontDescriptor()
    {
        PdfDictionary dic = new PdfDictionary(PdfName.FONTDESCRIPTOR);
        dic.put(PdfName.ASCENT, new PdfLiteral((String)fontDesc.get("Ascent")));
        dic.put(PdfName.CAPHEIGHT, new PdfLiteral((String)fontDesc.get("CapHeight")));
        dic.put(PdfName.DESCENT, new PdfLiteral((String)fontDesc.get("Descent")));
        dic.put(PdfName.FLAGS, new PdfLiteral((String)fontDesc.get("Flags")));
        dic.put(PdfName.FONTBBOX, new PdfLiteral((String)fontDesc.get("FontBBox")));
        dic.put(PdfName.FONTNAME, new PdfName((new StringBuilder()).append(fontName).append(style).toString()));
        dic.put(PdfName.ITALICANGLE, new PdfLiteral((String)fontDesc.get("ItalicAngle")));
        dic.put(PdfName.STEMV, new PdfLiteral((String)fontDesc.get("StemV")));
        PdfDictionary pdic = new PdfDictionary();
        pdic.put(PdfName.PANOSE, new PdfString((String)fontDesc.get("Panose"), null));
        dic.put(PdfName.STYLE, pdic);
        return dic;
    }

    private PdfDictionary getCIDFont(PdfIndirectReference fontDescriptor, IntHashtable cjkTag)
    {
        PdfDictionary dic = new PdfDictionary(PdfName.FONT);
        dic.put(PdfName.SUBTYPE, PdfName.CIDFONTTYPE0);
        dic.put(PdfName.BASEFONT, new PdfName((new StringBuilder()).append(fontName).append(style).toString()));
        dic.put(PdfName.FONTDESCRIPTOR, fontDescriptor);
        int keys[] = cjkTag.toOrderedKeys();
        String w = convertToHCIDMetrics(keys, hMetrics);
        if(w != null)
            dic.put(PdfName.W, new PdfLiteral(w));
        if(vertical)
        {
            w = convertToVCIDMetrics(keys, vMetrics, hMetrics);
            if(w != null)
                dic.put(PdfName.W2, new PdfLiteral(w));
        } else
        {
            dic.put(PdfName.DW, new PdfNumber(1000));
        }
        PdfDictionary cdic = new PdfDictionary();
        if(cidDirect)
        {
            cdic.put(PdfName.REGISTRY, new PdfString(cidUni.getRegistry(), null));
            cdic.put(PdfName.ORDERING, new PdfString(cidUni.getOrdering(), null));
            cdic.put(PdfName.SUPPLEMENT, new PdfNumber(cidUni.getSupplement()));
        } else
        {
            cdic.put(PdfName.REGISTRY, new PdfString(cidByte.getRegistry(), null));
            cdic.put(PdfName.ORDERING, new PdfString(cidByte.getOrdering(), null));
            cdic.put(PdfName.SUPPLEMENT, new PdfNumber(cidByte.getSupplement()));
        }
        dic.put(PdfName.CIDSYSTEMINFO, cdic);
        return dic;
    }

    private PdfDictionary getFontBaseType(PdfIndirectReference CIDFont)
    {
        PdfDictionary dic = new PdfDictionary(PdfName.FONT);
        dic.put(PdfName.SUBTYPE, PdfName.TYPE0);
        String name = fontName;
        if(style.length() > 0)
            name = (new StringBuilder()).append(name).append("-").append(style.substring(1)).toString();
        name = (new StringBuilder()).append(name).append("-").append(CMap).toString();
        dic.put(PdfName.BASEFONT, new PdfName(name));
        dic.put(PdfName.ENCODING, new PdfName(CMap));
        dic.put(PdfName.DESCENDANTFONTS, new PdfArray(CIDFont));
        return dic;
    }

    void writeFont(PdfWriter writer, PdfIndirectReference ref, Object params[])
        throws DocumentException, IOException
    {
        IntHashtable cjkTag = (IntHashtable)params[0];
        PdfIndirectReference ind_font = null;
        PdfObject pobj = null;
        PdfIndirectObject obj = null;
        pobj = getFontDescriptor();
        if(pobj != null)
        {
            obj = writer.addToBody(pobj);
            ind_font = obj.getIndirectReference();
        }
        pobj = getCIDFont(ind_font, cjkTag);
        if(pobj != null)
        {
            obj = writer.addToBody(pobj);
            ind_font = obj.getIndirectReference();
        }
        pobj = getFontBaseType(ind_font);
        writer.addToBody(pobj, ref);
    }

    public PdfStream getFullFontStream()
    {
        return null;
    }

    private float getDescNumber(String name)
    {
        return (float)Integer.parseInt((String)fontDesc.get(name));
    }

    private float getBBox(int idx)
    {
        String s = (String)fontDesc.get("FontBBox");
        StringTokenizer tk = new StringTokenizer(s, " []\r\n\t\f");
        String ret = tk.nextToken();
        for(int k = 0; k < idx; k++)
            ret = tk.nextToken();

        return (float)Integer.parseInt(ret);
    }

    public float getFontDescriptor(int key, float fontSize)
    {
        switch(key)
        {
        case 1: // '\001'
        case 9: // '\t'
            return (getDescNumber("Ascent") * fontSize) / 1000F;

        case 2: // '\002'
            return (getDescNumber("CapHeight") * fontSize) / 1000F;

        case 3: // '\003'
        case 10: // '\n'
            return (getDescNumber("Descent") * fontSize) / 1000F;

        case 4: // '\004'
            return getDescNumber("ItalicAngle");

        case 5: // '\005'
            return (fontSize * getBBox(0)) / 1000F;

        case 6: // '\006'
            return (fontSize * getBBox(1)) / 1000F;

        case 7: // '\007'
            return (fontSize * getBBox(2)) / 1000F;

        case 8: // '\b'
            return (fontSize * getBBox(3)) / 1000F;

        case 11: // '\013'
            return 0.0F;

        case 12: // '\f'
            return (fontSize * (getBBox(2) - getBBox(0))) / 1000F;
        }
        return 0.0F;
    }

    public String getPostscriptFontName()
    {
        return fontName;
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

    public String[][] getFamilyFontName()
    {
        return getFullFontName();
    }

    static IntHashtable createMetric(String s)
    {
        IntHashtable h = new IntHashtable();
        int n1;
        for(StringTokenizer tk = new StringTokenizer(s); tk.hasMoreTokens(); h.put(n1, Integer.parseInt(tk.nextToken())))
            n1 = Integer.parseInt(tk.nextToken());

        return h;
    }

    static String convertToHCIDMetrics(int keys[], IntHashtable h)
    {
        if(keys.length == 0)
            return null;
        int lastCid = 0;
        int lastValue = 0;
        int start = 0;
        do
        {
            if(start >= keys.length)
                break;
            lastCid = keys[start];
            lastValue = h.get(lastCid);
            if(lastValue != 0)
            {
                start++;
                break;
            }
            start++;
        } while(true);
        if(lastValue == 0)
            return null;
        StringBuilder buf = new StringBuilder();
        buf.append('[');
        buf.append(lastCid);
        int state = 0;
        for(int k = start; k < keys.length; k++)
        {
            int cid = keys[k];
            int value = h.get(cid);
            if(value == 0)
                continue;
            switch(state)
            {
            default:
                break;

            case 0: // '\0'
                if(cid == lastCid + 1 && value == lastValue)
                {
                    state = 2;
                    break;
                }
                if(cid == lastCid + 1)
                {
                    state = 1;
                    buf.append('[').append(lastValue);
                } else
                {
                    buf.append('[').append(lastValue).append(']').append(cid);
                }
                break;

            case 1: // '\001'
                if(cid == lastCid + 1 && value == lastValue)
                {
                    state = 2;
                    buf.append(']').append(lastCid);
                    break;
                }
                if(cid == lastCid + 1)
                {
                    buf.append(' ').append(lastValue);
                } else
                {
                    state = 0;
                    buf.append(' ').append(lastValue).append(']').append(cid);
                }
                break;

            case 2: // '\002'
                if(cid != lastCid + 1 || value != lastValue)
                {
                    buf.append(' ').append(lastCid).append(' ').append(lastValue).append(' ').append(cid);
                    state = 0;
                }
                break;
            }
            lastValue = value;
            lastCid = cid;
        }

        switch(state)
        {
        case 0: // '\0'
            buf.append('[').append(lastValue).append("]]");
            break;

        case 1: // '\001'
            buf.append(' ').append(lastValue).append("]]");
            break;

        case 2: // '\002'
            buf.append(' ').append(lastCid).append(' ').append(lastValue).append(']');
            break;
        }
        return buf.toString();
    }

    static String convertToVCIDMetrics(int keys[], IntHashtable v, IntHashtable h)
    {
        if(keys.length == 0)
            return null;
        int lastCid = 0;
        int lastValue = 0;
        int lastHValue = 0;
        int start = 0;
        do
        {
            if(start >= keys.length)
                break;
            lastCid = keys[start];
            lastValue = v.get(lastCid);
            if(lastValue != 0)
            {
                start++;
                break;
            }
            lastHValue = h.get(lastCid);
            start++;
        } while(true);
        if(lastValue == 0)
            return null;
        if(lastHValue == 0)
            lastHValue = 1000;
        StringBuilder buf = new StringBuilder();
        buf.append('[');
        buf.append(lastCid);
        int state = 0;
        for(int k = start; k < keys.length; k++)
        {
            int cid = keys[k];
            int value = v.get(cid);
            if(value == 0)
                continue;
            int hValue = h.get(lastCid);
            if(hValue == 0)
                hValue = 1000;
            switch(state)
            {
            default:
                break;

            case 0: // '\0'
                if(cid == lastCid + 1 && value == lastValue && hValue == lastHValue)
                    state = 2;
                else
                    buf.append(' ').append(lastCid).append(' ').append(-lastValue).append(' ').append(lastHValue / 2).append(' ').append(880).append(' ').append(cid);
                break;

            case 2: // '\002'
                if(cid != lastCid + 1 || value != lastValue || hValue != lastHValue)
                {
                    buf.append(' ').append(lastCid).append(' ').append(-lastValue).append(' ').append(lastHValue / 2).append(' ').append(880).append(' ').append(cid);
                    state = 0;
                }
                break;
            }
            lastValue = value;
            lastCid = cid;
            lastHValue = hValue;
        }

        buf.append(' ').append(lastCid).append(' ').append(-lastValue).append(' ').append(lastHValue / 2).append(' ').append(880).append(" ]");
        return buf.toString();
    }

    private static HashMap readFontProperties(String name)
        throws IOException
    {
        name = (new StringBuilder()).append(name).append(".properties").toString();
        InputStream is = StreamUtil.getResourceStream((new StringBuilder()).append("co/com/pdf/text/pdf/fonts/cmaps/").append(name).toString());
        Properties p = new Properties();
        p.load(is);
        is.close();
        IntHashtable W = createMetric(p.getProperty("W"));
        p.remove("W");
        IntHashtable W2 = createMetric(p.getProperty("W2"));
        p.remove("W2");
        HashMap map = new HashMap();
        Object obj;
        for(Enumeration e = p.keys(); e.hasMoreElements(); map.put((String)obj, p.getProperty((String)obj)))
            obj = e.nextElement();

        map.put("W", W);
        map.put("W2", W2);
        return map;
    }

    public int getUnicodeEquivalent(int c)
    {
        if(cidDirect)
        {
            if(c == 32767)
                return 10;
            else
                return cidUni.lookup(c);
        } else
        {
            return c;
        }
    }

    public int getCidCode(int c)
    {
        if(cidDirect)
            return c;
        else
            return uniCid.lookup(c);
    }

    public boolean hasKernPairs()
    {
        return false;
    }

    public boolean charExists(int c)
    {
        if(cidDirect)
            return true;
        else
            return cidByte.lookup(uniCid.lookup(c)).length > 0;
    }

    public boolean setCharAdvance(int c, int advance)
    {
        return false;
    }

    public void setPostscriptFontName(String name)
    {
        fontName = name;
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

    public byte[] convertToBytes(String text)
    {
        if(cidDirect)
            return super.convertToBytes(text);
        ByteArrayOutputStream bout;
        int k;
        int val;
        try
        {
            if(text.length() == 1)
                return convertToBytes(text.charAt(0));
        }
        catch(Exception ex)
        {
            throw new ExceptionConverter(ex);
        }
        bout = new ByteArrayOutputStream();
        for(k = 0; k < text.length(); k++)
        {
            if(Utilities.isSurrogatePair(text, k))
            {
                val = Utilities.convertToUtf32(text, k);
                k++;
            } else
            {
                val = text.charAt(k);
            }
            bout.write(convertToBytes(val));
        }

        return bout.toByteArray();
    }

    byte[] convertToBytes(int char1)
    {
        if(cidDirect)
            return super.convertToBytes(char1);
        else
            return cidByte.lookup(uniCid.lookup(char1));
    }

    public boolean isIdentity()
    {
        return cidDirect;
    }

    static final String CJK_ENCODING = "UnicodeBigUnmarked";
    private static final int FIRST = 0;
    private static final int BRACKET = 1;
    private static final int SERIAL = 2;
    private static final int V1Y = 880;
    static Properties cjkFonts = new Properties();
    static Properties cjkEncodings = new Properties();
    private static final HashMap allFonts = new HashMap();
    private static boolean propertiesLoaded = false;
    public static final String RESOURCE_PATH_CMAP = "co/com/pdf/text/pdf/fonts/cmaps/";
    private static final HashMap registryNames = new HashMap();
    private CMapCidByte cidByte;
    private CMapUniCid uniCid;
    private CMapCidUni cidUni;
    private String uniMap;
    private String fontName;
    private String style;
    private String CMap;
    private boolean cidDirect;
    private IntHashtable vMetrics;
    private IntHashtable hMetrics;
    private HashMap fontDesc;

}
